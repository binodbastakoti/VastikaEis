app.controller('SkillCategoryController', [ '$scope', '$http', 'baseUrl','$modal', skillCategoryController ]);
		
function skillCategoryController ($scope, $http, baseUrl, $modal) {
	
	$http.get(baseUrl + 'api/skillCategories')
	.then(function(response) {
		$scope.skillCategories = response.data;
	})

	$scope.open = function() {
		var modalInstance = $modal.open({
			animation : true,
			templateUrl : 'resources/views/skills/add-skill-category.html',
			controller : 'SkillCategoryModal',
			resolve: {
		        skillCategory: false
		    }
		});
		
		modalInstance.result.then(function (newSkillCategory) {
			if(newSkillCategory) {
				$scope.skillCategories.push(newSkillCategory);
			}
	    }, function () {
		});
	}
	$scope.edit = function(skillCategory) {
		var modalInstance = $modal.open({
			animation : true,
			templateUrl : 'resources/views/skills/add-skill-category.html',
			controller : 'SkillCategoryModal',
			resolve: {
		        skillCategory: function() {
		        	return skillCategory;
		        }
		    }
		});
		
		modalInstance.result.then(function (updatedSkillCategory) {
			if(updatedSkillCategory) {
				var ref = _.find($scope.skillCategories, function(el) {
					return el.categoryId == updatedSkillCategory.categoryId; 
				});
				$scope.skillCategories[_.indexOf($scope.skillCategories, ref)] = updatedSkillCategory;
			}
	    });
	}
	$scope.delete = function(skillCategory) {
		$modal.open({
			animation : true,
			templateUrl : 'resources/views/partials/confirm.html',
			controller : 'ConfirmController',
		}).result.then(function() {
			$http.delete(baseUrl + 'api/skillCategories/'+skillCategory.categoryId)
			.then(function(response) {
				toastr.success(response.data.message, "Success");
				$scope.skillCategories = _.reject($scope.skillCategories, function(el) { return el.categoryId === skillCategory.categoryId; });
			});
		});		
	}

}

app.controller('SkillCategoryModal',skillCategoryModal);
 function skillCategoryModal($scope, $modalInstance, skillCategory, baseUrl, $http) {
	
	if(skillCategory) {
		$scope.mode = "Edit";
		$scope.skillCategory = {categoryName: skillCategory.categoryName, categoryDesc: skillCategory.categoryDesc};
		$scope.skillCategoryId = skillCategory.categoryId;
	} else {
		$scope.mode = "Add";
		$scope.skillCategory = {categoryName: "", categoryDesc: ""};
	}
	
	$modalInstance.rendered.then(function(){
		$("#skill-category-name").focus();
	});

	$scope.add = function() {

		$scope.$broadcast('show-errors-check-validity');

		if (!$scope.addSkillCategoryForm.$valid) {
			toastr.error("Please fill required fields.", "Error");
			return;
		}
		
		if($scope.mode == "Add") {
			$scope.processing=true;
			var promise = $http.post(baseUrl + 'api/skillCategories', $scope.skillCategory); 
		} else {
			$scope.processing=true;
			var promise = $http.put(baseUrl + 'api/skillCategories/' + $scope.skillCategoryId, $scope.skillCategory);
		}
		
		promise.then(function(response) {
			
			if(response.data.status == 'success') {
				toastr.success(response.data.message, "Success");
				if($scope.mode == "Add")
					$scope.skillCategory.categoryId = response.data.id;
				else 
					$scope.skillCategory.categoryId = $scope.skillCategoryId;
				$modalInstance.close($scope.skillCategory);
				
			} else {
				toastr.error("Something went wrong.", "Error");
				$modalInstance.close();
			}
			$scope.processing=false;
		}, function() {
			toastr.error("Something went wrong.", "Error");
			$scope.processing=false;
		});
	};
	
	$scope.cancel = function() {
		$modalInstance.dismiss('cancel');
	};
}



app.controller('SkillController', [ '$scope', '$http', 'baseUrl','$modal', '$stateParams',skillController]);
                                    		
function skillController($scope, $http, baseUrl, $modal, $stateParams) {
	$scope.skillCategoryId = parseInt($stateParams.id, 10);
	
	$http.get(baseUrl + 'api/skillCategories/' + $scope.skillCategoryId)
	.then(function(response) {
		$scope.skillCategory = response.data;
	});
	

	$http.get(baseUrl + 'api/skillCategories/' + $scope.skillCategoryId + '/skills')
	.then(function(response) {
		$scope.skills = response.data;
	});
	
	$scope.open = function() {
		var modalInstance = $modal.open({
			animation : true,
			templateUrl : 'resources/views/skills/add-skill.html',
			controller : 'SkillModal',
			resolve: {
		        skillCategoryId: function() {
		        	return $scope.skillCategoryId;
		        },
		        skill: false
		    }
		});
		
		modalInstance.result.then(function (newSkill) {
			if(newSkill) {
				$scope.skills.push(newSkill);
			}
	    }, function () {
		});
	}
	
	$scope.edit = function(skill) {
		var modalInstance = $modal.open({
			animation : true,
			templateUrl : 'resources/views/skills/add-skill.html',
			controller : 'SkillModal',
			resolve: {
		        skillCategoryId: function() {
		        	return $scope.skillCategoryId;
		        },
		        skill: function() {
		        	return skill;
		        }
		    }
		});
		
		modalInstance.result.then(function (updatedSkill) {
			if(updatedSkill) {
				var ref = _.find($scope.skills, function(el) {
					return el.skillId == updatedSkill.skillId; 
				});
				$scope.skills[_.indexOf($scope.skills, ref)] = updatedSkill;
			}
	    });
	}
	

	$scope.delete = function(skill) {
		$modal.open({
			animation : true,
			templateUrl : 'resources/views/partials/confirm.html',
			controller : 'ConfirmController',
		}).result.then(function() {
			$http.delete(baseUrl + 'api/skills/'+skill.skillId)
			.then(function(response) {
				toastr.success(response.data.message, "Success");
				$scope.skills = _.reject($scope.skills, function(el) { return el.skillId === skill.skillId; });
			});
		});		
	}
	
	
}


app.controller('SkillModal',skillModal);

function skillModal($scope, $modalInstance, skillCategoryId, skill, baseUrl, $http) {
	$scope.skillCategoryId = skillCategoryId;
	if(skill) {
		$scope.mode = "Edit";
		$scope.skill = {skillName: skill.skillName};
		$scope.skillId = skill.skillId;
	} else {
		$scope.mode = "Add";
		$scope.skill = {skillName: ""};
	}
	
	$modalInstance.rendered.then(function(){
		$("#skill-name").focus();
	});

	$scope.add = function() {

		$scope.$broadcast('show-errors-check-validity');

		if (!$scope.addSkillForm.$valid) {
			toastr.error("Please fill required fields.", "Error");
			return;
		}
		
		var data = {
				skill: $scope.skill,
				skillCategoryId: $scope.skillCategoryId
		}
		if($scope.mode == "Add") {
			$scope.processing=true;
			var promise = $http.post(baseUrl + 'api/skills', data); 
		} else {
			$scope.processing=true;
			var promise = $http.put(baseUrl + 'api/skills/' + $scope.skillId, data);
		}
		
		promise.then(function(response) {
			if(response.data.status == 'success') {
				toastr.success(response.data.message, "Success");
				if($scope.mode == "Add")
					$scope.skill.skillId = response.data.id;
				else 
					$scope.skill.skillId = $scope.skillId;
				$modalInstance.close($scope.skill);
			} else {
				toastr.error("Something went wrong.", "Error");
				$modalInstance.close();
			}
			$scope.processing=false;
		}, function() {
			toastr.error("Something went wrong.", "Error");
			$scope.processing=false;
		});
	};
	
	$scope.cancel = function() {
		$modalInstance.dismiss('cancel');
	};
}
