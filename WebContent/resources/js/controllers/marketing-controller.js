app.controller('AssignMarketerController', [ '$scope', '$http', 'baseUrl', '$modal', assignMarketerController ]);

function assignMarketerController($scope, $http, baseUrl, $modal) {
	
	$http.get(baseUrl + 'api/heroes/names').then(function(response) {
		$scope.heroes = response.data;
		$('#select-hero').select2({
            placeholder: "Select a Consultant",
            allowClear: true
        });
	});
	
	$scope.show = function(selectedHeroId) {
		if(selectedHeroId) {
			$scope.selectedHeroId = selectedHeroId;
			$http.get(baseUrl + 'api/marketer/' + selectedHeroId).then(function(response) {
				$scope.assignedRecruiters = response.data.assignedRecruiter;
				$scope.notAssignedRecuiters = response.data.notAssignedRecuiter;
			});
		}
	}
	
	$scope.assign=function(marketer)
	{
		var modalInstance=$modal.open({
			animation:true,
			templateUrl:'resources/views/marketing/assign.html',
			controller:'MarketingModal',
			resolve:{
				selectedHeroId: function() {
					return $scope.selectedHeroId;
				},
				marketer: function() {
					return marketer;
				}
			}
		});
		
		modalInstance.result.then(function(marketer){
			if(marketer){
				$scope.notAssignedRecuiters = _.reject($scope.notAssignedRecuiters, function(el) { return el.id === marketer.id; });
				$scope.assignedRecruiters.push(marketer);
			}
		}, function () {
		});
	} 
	
	$scope.unAssign = function(marketer) {
		$modal.open({
			animation : true,
			templateUrl : 'resources/views/partials/confirm.html',
			controller : 'ConfirmController',
		}).result.then(function() {
			$http.delete(baseUrl + 'api/marketer/removeHero/' + $scope.selectedHeroId + '/' + marketer.id)
			.then(function(response) {
				if(response.data.status == 'success') {
					toastr.success(response.data.message, "Success");
					$scope.assignedRecruiters = _.reject($scope.assignedRecruiters, function(el) { return el.id === marketer.id; });
					$scope.notAssignedRecuiters.push(marketer);
				} else {
					toastr.error("Something went wrong.", "Error");
				}
			}, function() {
				toastr.error("Something went wrong.", "Error");
			});
		});		
	}
};

app.controller('MarketingModal',['$scope', '$modalInstance', 'baseUrl', '$http', 'selectedHeroId', 'marketer', marketingModal]);

function marketingModal($scope,$modalInstance,baseUrl,$http, selectedHeroId, marketer){

	$scope.assign = {
		heroId: selectedHeroId,
		recruiterId: marketer.id,
		resumeIds : []
	};
	
	$scope.marketer = marketer;
	
	$http.get(baseUrl + 'api/heroes/' + selectedHeroId + '/resumes')
	.then(function(response) {
		$scope.resumes = response.data;
	});

	$scope.assignMarketer = function() {

		$scope.$broadcast('show-errors-check-validity');

		if (!$scope.assignForm.$valid) {
			toastr.error("Please select appropriate resume.", "Error");
			return;
		}
		
		$http.post(baseUrl + 'api/marketer/assignHero/', $scope.assign).then(function(response) {
			if(response.data.status == "success") {
				toastr.success(response.data.message, "Success");				
				$modalInstance.close($scope.marketer);
			} else {
				toastr.error("Something went wrong.", "Error");
			}
		}, function() {
			toastr.error("Something went wrong.", "Error");
		}); 
	};

	$scope.cancel = function() {
		$modalInstance.dismiss('cancel');
	};
}