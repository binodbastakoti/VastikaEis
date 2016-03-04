app.config(['$stateProvider', function($stateProvider) {
    
    $stateProvider
    .state('app.addNewAddress',{
        url: 'addressHistory/add',
        views: {
            'content@': {
                templateUrl: 'resources/views/addressHistory/addNewAddress.html',
                controller: 'addNewAddressController' 
            }
        }
    })
    .state('app.addressList',{
        url: 'addressList/list',
        views: {
            'content@': {
                templateUrl: 'resources/views/addressHistory/addressList.html',
                controller: 'addressListController' 
            }
        }
    });
}]);