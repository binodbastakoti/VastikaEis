app.config(['$stateProvider', function($stateProvider) {
    
    $stateProvider
    .state('app.addNewEmployee',{
        url: 'employmentHistory/add',
        views: {
            'content@': {
                templateUrl: 'resources/views/employmentHistory/addNewEmployee.html',
                controller: 'addNewEmployeeController' 
            }
        }
    })
    .state('app.employeeList',{
        url: 'employeeList/list',
        views: {
            'content@': {
                templateUrl: 'resources/views/employmentHistory/employeeList.html',
                controller: 'employeeListController' 
            }
        }
    });
}]);