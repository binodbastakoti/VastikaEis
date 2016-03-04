app.config(['$stateProvider', function($stateProvider) {
    
    $stateProvider
    .state('app.roles',{
        url: 'roles',
        views: {
            'content@': {
                templateUrl: 'resources/views/roles/roles.html',
                controller: 'RolesController' 
            }
        }

    })
    .state('app.permissions',{
        url: 'roles/:id/permissions',
        views: {
            'content@': {
                templateUrl: 'resources/views/roles/permissions.html',
                controller: 'PermissionsController' 
            }
        }

    });
}]);