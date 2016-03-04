app.config(['$stateProvider', function($stateProvider) {
    
    $stateProvider
    .state('app.assignMarketer',{
        url: 'marketing/assignMarketer',
        views: {
            'content@': {
                templateUrl: 'resources/views/marketing/assignMarketer.html',
                controller: 'AssignMarketerController' 
            }
        }
    });
}]);
    