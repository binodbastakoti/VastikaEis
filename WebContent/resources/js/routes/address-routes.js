app.config(['$stateProvider', function($stateProvider) {
    
	$stateProvider
    .state('app.heroes-addresses',{
        url: 'heroes/:id/addresses',
        views: {
            'content@': {
                templateUrl: 'resources/views/address/addresses.html',
                controller: 'heroesAddressesController' 
            }
        }
    })
}]);