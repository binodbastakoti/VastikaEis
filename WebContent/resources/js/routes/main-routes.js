app.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/');
    
    $stateProvider.state('app',{
        url: '/',
        views: {
            'header': {
                templateUrl: 'resources/views/partials/header.html',
                controller: 'HeaderController'
            },
            'navigation': {
                templateUrl: 'resources/views/partials/navigation.html',
                controller: 'NavigationController'
            },
            'content': {
                templateUrl: 'resources/views/dashboard.html',
                controller: 'DashBoardController' 
            },
            'footer': {
                templateUrl: 'resources/views/partials/footer.html',
                controller: 'FooterController'
            }
        },
        data: {
            pageTitle: 'Dashboard'
        },
        resolve: {
        	currentUser: function(CurrentUser) {
        		return CurrentUser.initPromise;
        	}
        }
    });
    
}]);