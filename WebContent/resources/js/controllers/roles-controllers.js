app.controller('RolesController', [ '$scope', '$http', 'baseUrl','$modal',rolesController ]);
function rolesController($scope, $http, baseUrl,$modal) {
	$http.get(baseUrl+'api/roles')
	.then(function(response){
		$scope.roles=response.data;
	})
    $scope.open=function(){
    	var modalInstance=$modal.open({
    		animation:true,
    		templateUrl:'resources/views/roles/add-roles.html',
    		controller:'RoleModal',
    		resolve:{
    			roles:false
    		}
    	});
    	modalInstance.result.then(function(newRole){
    		if(newRole){
    			$scope.roles.push(newRole);
    		}
    	},function(){
    	});
  }	
	$scope.edit=function(roles){
		var modalInstance=$modal.open({
			animation : true,
			templateUrl : 'resources/views/roles/add-roles.html',
			controller : 'RoleModal',
			resolve: {
				
		        roles: function() {
		        	return roles;
		        }
		    }
		});
		modalInstance.result.then(function (updateRole) {
			if(updateRole){
				var ref=_.find($scope.roles,function(el){
					return el.id==updateRole.id;
				});
				$scope.roles[_.indexOf($scope.roles,ref)]=updateRole;
			}

	    });
	}
	$scope.delete = function(roles) {
		$modal.open({
			animation : true,
			templateUrl : 'resources/views/partials/confirm.html',
			controller : 'ConfirmController',
		}).result.then(function() {
			$http.delete(baseUrl + 'api/roles/'+roles.id)
			.then(function(response) {
				toastr.success(response.data.message, "Success");
				$scope.roles = _.reject($scope.roles, function(el) { return el.id === roles.id; });
			});
		});		
	}
}

app.controller('RoleModal',['$scope', '$modalInstance', 'roles', 'baseUrl', '$http', roleModal]);
function roleModal($scope,$modalInstance,roles,baseUrl,$http){
	if(roles){
		$scope.mode="Edit";
		$scope.roles={name:roles.name};
		$scope.id=roles.id;
	}else {
		$scope.mode="Add";
		$scope.roles={name:""};
		
	}
	
	$modalInstance.rendered.then(function(){
		$("#role-name").focus();
	});
	
	$scope.add=function(){
	$scope.$broadcast('show-errors-check-validity');
	if(!$scope.addroleForm.$valid){
		toastr.error("Please fill required fields.","Error");
		return;
	}

	if($scope.mode=="Add"){
		$scope.processing=true;
		var promise=$http.post(baseUrl+'api/roles',$scope.roles);
	}else {
		$scope.processing=true;
		var promise=$http.put(baseUrl+'api/roles/'+$scope.id,$scope.roles);
	}
	
	promise.then(function(response) {
		if(response.data.status=='success'){
			toastr.success(response.data.message, "Success");
			if($scope.mode=="Add")
				$scope.roles.id=response.data.id;
			else
				$scope.roles.id=$scope.id;
			$modalInstance.close($scope.roles);
		}
		else{
			toastr.error("Something went wrong.","Error");
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

app.controller('PermissionsController', [ '$scope', '$http', 'baseUrl','$modal', '$stateParams', '$state', permissionsController]);
function permissionsController($scope, $http, baseUrl, $modal, $stateParams, $state) {
	
	$scope.roleId=parseInt($stateParams.id,10);
	
	$http.get(baseUrl+'api/permissions/').then(function(response){
		var permissions = response.data;
		$scope.permissions= {};
		_.each(permissions,function(p){
			if(p.parentId==0){
				$scope.permissions[p.name]= _.where(permissions, {parentId: p.id});
			}
		})
	});
	
	$scope.roleIds = {};
	$http.get(baseUrl + 'api/roles/' + $scope.roleId).then(function(response) {
		$scope.role = response.data;
		_.each($scope.role.permissions, function(roleId) {
			$scope.roleIds[roleId] = true;
		});
	});
	
	$scope.save = function() {
		var dataToSend = _.keys(_.omit($scope.roleIds, function(value) {
			return !value;
		}));
		$scope.processing = true;
		$http.put(baseUrl + 'api/roles/' + $scope.roleId + '/permission', dataToSend).then(function(response){
			if(response.data.status == 'success') {
				toastr.success(response.data.message, "Success");
				$state.go('app.roles');
			} else if(response.data.status == 'failure') {
				toastr.error(response.data.message, "Error");
			} else {
				toastr.error("Something went wrong.", "Error");
			}
			$scope.processing = false;
		}, function(data){
			toastr.error("Something went wrong.", "Error");
			$scope.processing = false;
		});
	}
}