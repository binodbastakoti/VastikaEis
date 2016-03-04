app.config(['$stateProvider', function($stateProvider) {
    
    $stateProvider
    .state('app.setDefaultPermission',{
        url: 'defaultPermission/set',
        views: {
            'content@': {
                templateUrl: 'resources/views/permission/setDefaultPermission.html',
                controller: 'SetDefaultPermissionController' 
            }
        }
    })
    .state('app.setUserPermission',{
        url: 'userPermission/sets',
        views: {
            'content@': {
                templateUrl: 'resources/views/permission/setUserPermission.html',
                controller: 'SetUserPermissionController' 
            }
        }
    });
}]);