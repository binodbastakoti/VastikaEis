app.controller('UserController', [ '$scope', '$http', 'baseUrl', '$modal', function($scope, $http, baseUrl, $modal) {

	window.scrollTo(0, 0);

	$http.get(baseUrl + "api/users").then(function(response) {
		$scope.users = response.data;
	});	

	$scope.viewUser = function(hero) {
		$modal.open({
			animation : true,
			size: 'lg',
			templateUrl : 'resources/views/users/view-user.html',
			controller : 'ViewUserController',
			resolve: {
				user: function() {
					return user;
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
			$http.delete(baseUrl + 'api/users/'+userId)
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

app.controller('UserAddController', [ '$scope', '$http', 'baseUrl', '$state', '$stateParams', function($scope, $http, baseUrl, $state, $stateParams) {

	$('.date-picker').datepicker({});

	$http.get(baseUrl + 'api/roles').then(function(response) {
		$scope.roles = response.data;
	});

	if($state.current.name != 'app.edit-user') {
		$scope.mode = 'Add';
		$scope.user = {
				user: {
					firstName: "Greg",
					middleName: "X",
					lastName: "Joshi",
					dob: "01/01/1965",
					gender: "M",
					email: "greg.joshi@mail.com"
				},
				address: {
					street: "1200 Wallnut Hill",
					city: "Irving",
					state: "TX",
					zipCode: "75038"
				},
				contacts: {
					contactNumber: "6098349340"
				},
				roleIds: []
		};
	} else {
		$scope.mode = 'Edit';
		$scope.userId = $stateParams.id;
		$http.get(baseUrl + 'api/users/' + $stateParams.id).then(function(response){
			$scope.user = {
					user: _.pick(response.data, 'firstName', 'middleName', 'lastName', 'dob', 'gender', 'email'),
					address: _.pick(response.data, 'street', 'city', 'state', 'zipCode'),
					contacts: {
						contactNumber: response.data.contact
					},
					roleIds: _.pluck(response.data.roles, 'id')
			};
		}, function(){
		});
	}
	$scope.save = function(){

		$scope.$broadcast('show-errors-check-validity');

		if (!$scope.addUserForm.$valid){
			toastr.error('Please input valid information.', "Error");
			return;
		}

		if($scope.user.user.dob.indexOf('/') != -1) {
			var date = $scope.user.user.dob.split('/');
			$scope.user.user.dob = date[2] + '-' + date[0] + '-' + date[1];
		}

		if($state.current.name != 'app.edit-user') {
			$scope.processing=true;
			$http.post('api/users', $scope.user).then(function(response){
				if(response.data.status == 'success') {
					toastr.success(response.data.message, "Success");
					$state.go('app.users');
				} else if(response.data.status == 'failure') {
					toastr.error(response.data.message, "Error");
				} else {
					toastr.error("Something went wrong.", "Error");
				}
				$scope.processing=false;
			}, function(data){
				toastr.error("Something went wrong.", "Error");
				$scope.processing=false;
			});
		} else {
			$scope.processing=true;
			$http.put('api/users/' + $stateParams.id, $scope.user).then(function(response){
				if(response.data.status == 'success') {
					toastr.success(response.data.message, "Success");
					$state.go('app.users');
				} else if(response.data.status == 'failure') {
					toastr.error(response.data.message, "Error");
				} else {
					toastr.error("Something went wrong.", "Error");
				}
				$scope.processing=false;
			}, function(data){
				toastr.error("Something went wrong.", "Error");
				$scope.processing=false;
			});
		}
	};
}]);

app.controller('ViewUserController', ['$scope', '$modalInstance', 'user', 'baseUrl', '$http', function($scope, $modalInstance, user, baseUrl, $http){
	$scope.user = hero;

	$scope.cancel = function() {
		$modalInstance.dismiss('cancel');
	};
}]);