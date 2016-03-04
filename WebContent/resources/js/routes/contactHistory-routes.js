app.config(['$stateProvider', function($stateProvider) {
    
    $stateProvider
    .state('app.addNewContact',{
        url: 'contactHistory/add',
        views: {
            'content@': {
                templateUrl: 'resources/views/contactHistory/addNewContact.html',
                controller: 'addNewContactController' 
            }
        }
    })
    .state('app.contactList',{
        url: 'contactList/list',
        views: {
            'content@': {
                templateUrl: 'resources/views/contactHistory/contactList.html',
                controller: 'contactListController' 
            }
        }
    });
}]);