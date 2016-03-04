app.service('CurrentUser', [ "$http", "baseUrl", function($http, baseUrl) {
	
	var currentUser = null;
	
	var initPromise = $http.get(baseUrl + "api/users/currentUser").then(function(response) {
		if(response.data.status == 'success') {
			currentUser = response.data;
		}	
	});	

	return {
		initPromise: initPromise,
		getCurrentUserId: function() {
			return currentUser.id;
		} ,
		getCurrentUserName: function() {
			return currentUser.name;
		} ,
		getCurrentUserPermissions: function() {
			return currentUser.permissions;
		},
		canAccess: function(permission) {
			return _.contains(currentUser.permissions, permission);
		},
		canAccessAny: function(permissions) {
			var returnThis = false;
			_.each(permissions, function(permission) {
				if(_.contains(currentUser.permissions, permission.trim())) returnThis = true;
			});
			return returnThis;
		},
		canAccessAll: function(permissions) {
			var returnThis = true;
			_.each(permissions, function(permission) {
				if(!_.contains(currentUser.permissions, permission.trim())) returnThis = false;
			});
			return returnThis;
		}
	};
	
} ]);