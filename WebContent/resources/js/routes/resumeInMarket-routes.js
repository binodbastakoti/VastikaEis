app.config(['$stateProvider', function($stateProvider) {
    
    $stateProvider
    .state('app.addResumeInMarket',{
        url: 'resumeInMarket/add',
        views: {
            'content@': {
                templateUrl: 'resources/views/resumeInMarket/addResumeInMarket.html',
                controller: 'addResumeInMarketController' 
            }
        }
    })
    .state('app.resumeInMarket',{
        url: 'resumeInMarket/list',
        views: {
            'content@': {
                templateUrl: 'resources/views/resumeInMarket/resumeInMarket.html',
                controller: 'employeeListController' 
            }
        }
    });
}]);