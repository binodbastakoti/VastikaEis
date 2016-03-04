
app.controller('HeroController', [ '$scope', '$http', 'baseUrl', '$modal', function($scope, $http, baseUrl, $modal) {
	
	window.scrollTo(0, 0);
	
/*	$http.get(baseUrl + "api/myHeroes").then(function(response) {
		$scope.users = response.data;
	});	*/
	
	$http.get(baseUrl + "api/heroes").then(function(response) {
		$scope.users = response.data;
	});	
	$scope.mode = '';
	
	$scope.showRecruiters = function(userId) {
		if(userId) {
			//$scope.selectedHeroId = userId;
			$http.get(baseUrl + 'api/marketer/' + userId).then(function(response) {
				$scope.assignedRecruiters = response.data.assignedRecruiter;
			});
		}
	}

	$scope.viewHero = function(hero) {
		$modal.open({
			animation : true,
			size: 'lg',
			templateUrl : 'resources/views/heros/view-hero.html',
			controller : 'ViewHeroController',
			resolve: {
				hero: function() {
					return hero;
				}
			}
		});		
	}

	$scope.delete = function(userId) {
		$modal.open({
			animation : true,
			templateUrl : 'resources/views/partials/confirm.html',
			controller : 'ConfirmController',
		}).result.then(function() {
			$http.delete(baseUrl + 'api/heroes/'+userId)
			.then(function(response) {
				if(response.data.status == "success") {
					toastr.success(response.data.message, "Success");
					$scope.users = _.reject($scope.users, function(el) { return el.userId === userId; });
				} else {
					toastr.success("Something went wrong.", "Error");
				}
			});
		});		
	}
}]);


app.controller('MyHeroController', [ '$scope', '$http', 'baseUrl', '$modal', function($scope, $http, baseUrl, $modal) {
	
	window.scrollTo(0, 0);
	
	$http.get(baseUrl + "api/myHeroes").then(function(response) {
		$scope.users = response.data;
	});	
	$scope.mode = 'My ';
	
/*	$http.get(baseUrl + "api/heroes").then(function(response) {
		$scope.users = response.data;
	});	*/

	$scope.viewHero = function(hero) {
		$modal.open({
			animation : true,
			size: 'lg',
			templateUrl : 'resources/views/heros/view-hero.html',
			controller : 'ViewHeroController',
			resolve: {
				hero: function() {
					return hero;
				}
			}
		});		
	}

	$scope.delete = function(userId) {
		$modal.open({
			animation : true,
			templateUrl : 'resources/views/partials/confirm.html',
			controller : 'ConfirmController',
		}).result.then(function() {
			$http.delete(baseUrl + 'api/heroes/'+userId)
			.then(function(response) {
				if(response.data.status == "success") {
					toastr.success(response.data.message, "Success");
					$scope.users = _.reject($scope.users, function(el) { return el.userId === userId; });
				} else {
					toastr.success("Something went wrong.", "Error");
				}
			});
		});		
	}
    
}]);

app.controller('HeroAddController', [ '$scope', '$http', 'baseUrl', '$state', '$stateParams', function($scope, $http, baseUrl, $state, $stateParams) {
	
	$('.date-picker').datepicker({endDate: new Date()});
	
	if($state.current.name != 'app.edit-heroes') {
		$scope.mode = 'Add';
		$scope.user = {
			user: {
				firstName: "Dummy",
				middleName: "",
				lastName: "Man",
				dob: "1/2/1974",
				gender: "M",
				email: "dummy.man@email.com"
			},
			heroes: {
				availability: "2 weeks",
				status: "Active",
				primeOK: "Y",
				skillSpecialization: "",
				visaStatus: "F1"
			},
			address: {
				street: "1200 Wallnut Hill",
				city: "Irving",
				state: "TX",
				zipCode: "75038"
			},
			contacts: {
				contactNumber: "1234567890"
			},
			skillCategoryId: "1",
			skillsMap: {}
		};
		$http.get(baseUrl + "api/skillCategories").then(function(response) {
			$scope.skillsCategories = response.data;
		});
	} else {
		$scope.mode = 'Edit';
		$scope.userId = $stateParams.id;
		$http.get(baseUrl + 'api/heroes/' + $stateParams.id).then(function(response){
			$scope.user = {
				user: _.pick(response.data, 'firstName', 'middleName', 'lastName', 'dob', 'gender', 'email'),
				heroes: _.pick(response.data, 'availability', 'status', 'primeOK', 'skillSpecialization', 'visaStatus'),
				address: _.pick(response.data, 'street', 'city', 'state', 'zipCode'),
				contacts: {
					contactNumber: response.data.contactNumber
				},
				skillCategoryId: response.data.skillCategoryId,
				skillsMap: response.data.skillsMap
			};
			$http.get(baseUrl + "api/skillCategories").then(function(response) {
				$scope.skillsCategories = response.data;
				$scope.selectedSkillCategory = _.find($scope.skillsCategories, function(el) {
					return el.categoryId == $scope.user.skillCategoryId;
				});
				$scope.ShowSkills(true);
			});
		}, function(){
		});
	}
	
	$scope.status = ["Active", "In Project", "No longer with us", "In training", "Marketing"];
	$scope.visaStatus = ["F1", "F1-CPT", "F1-OPT", "H1", "GC", "EAD", "US Citizen"];
	$scope.checkBox = [];
	
	$scope.ShowSkills = function(edit){
		$http.get('api/skillCategories/' + $scope.selectedSkillCategory.categoryId + '/skills').then(function(response){
			$scope.user.skillCategoryId = $scope.selectedSkillCategory.categoryId;
			$scope.skills = response.data;
			
			//console.log(response.data);
			
			if(edit) {
				_.each($scope.skills, function(value, key){
					$scope.checkBox[key] = _.contains(_.keys($scope.user.skillsMap), value.skillId+'');
				});
			} else {
				$scope.checkBox = [];
				$scope.user.skillsMap = {};
			}
		}, function(data){
		});
	};
	
	$scope.save = function(){
		
		$scope.$broadcast('show-errors-check-validity');

		if (!$scope.addUserForm.$valid) {
			toastr.error('Please input valid information.', "Error");
			return;
		}
		
		if($scope.user.user.dob.indexOf('/') != -1) {
			var date = $scope.user.user.dob.split('/');
			$scope.user.user.dob = date[2] + '-' + date[0] + '-' + date[1];
		}
		
		
		if($state.current.name != 'app.edit-heroes') {
			$scope.processing = true;
			
			$http.post('api/heroes', $scope.user).then(function(response){
				if(response.data.status == 'success') {
					toastr.success(response.data.message, "Success");
					$state.go('app.heroes-documents', {id: response.data.id});
				} else if(response.data.status == 'failure') {
					toastr.error(response.data.message, "Error");
				} else {
					toastr.error("Something went wrong1.", "Error");
				}
				$scope.processing = false;
			}, function(data){
				toastr.error("Something went wrong2.", "Error");
				$scope.processing = false;
			});
		} else {
			$scope.processing = true;

			//the following http request should be "put"
			// but because it is not working using "put", post is used.
			$http.put('api/heroes/' + $stateParams.id, $scope.user).then(function(response){
				if(response.data.status == 'success') {
					toastr.success(response.data.message, "Success");
					//$state.go('app.heroes');
					$state.go('app.heroes-documents', {id: $stateParams.id});
				} else if(response.data.status == 'failure') {
					toastr.error(response.data.message, "Error");
				} else {
					toastr.error("Something went wrong3.", "Error");
				}
				$scope.processing = false;
			}, function(data){
				toastr.error("Something went wrong4.", "Error");
				$scope.processing = false;
			});
		}
	};
    
}]);

app.controller('ViewHeroController', ['$scope', '$modalInstance', 'hero', 'baseUrl', '$http', function($scope, $modalInstance, hero, baseUrl, $http){
	$scope.user = hero;
	
	$http.get(baseUrl + 'api/heroes/' + $scope.user.userId + '/resumes')
	.then(function(response) {
		$scope.resumes = response.data;
	});
	
	$http.get(baseUrl + 'api/heroes/' + $scope.user.userId + '/documents')
	.then(function(response) {
		$scope.documents = response.data;
	});
	
	$scope.cancel = function() {
		$modalInstance.dismiss('cancel');
	};
}]);