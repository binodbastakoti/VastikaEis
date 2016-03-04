app.config(['$stateProvider', function($stateProvider) {
    
    $stateProvider
    .state('app.users',{
        url: 'users',
        views: {
            'content@': {
                templateUrl: 'resources/views/user/users.html',
                controller: 'UserController' 
            }
        },
        data: {
            pageTitle: 'Marketers',
            permission: "View Users"
        }
    })
    .state('app.add-user',{
        url: 'users/add',
        views: {
            'content@': {
                templateUrl: 'resources/views/user/add-user.html',
                controller: 'UserAddController' 
            }
        },
        data: {
            pageTitle: 'Add Marketer',
            permission: "Add User"
        }
    })
    .state('app.edit-user',{
        url: 'users/:id',
        views: {
            'content@': {
                templateUrl: 'resources/views/user/add-user.html',
                controller: 'UserAddController' 
            }
        },
        data: {
            pageTitle: 'Edit Marketer',
            permission: "Edit User"
        }
    });
  
}]);