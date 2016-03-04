app.config(['$stateProvider', function($stateProvider) {
    
    $stateProvider
    .state('app.setFollowUp',{
        url: 'followup/set',
        views: {
            'content@': {
                templateUrl: 'resources/views/followup/setFollowUp.html',
                controller: 'SetFollowUpController' 
            }
        }
    })
    .state('app.followUpList',{
        url: 'followup/list',
        views: {
            'content@': {
                templateUrl: 'resources/views/followup/followUpList.html',
                controller: 'followUpListController' 
            }
        }
    });
}]);