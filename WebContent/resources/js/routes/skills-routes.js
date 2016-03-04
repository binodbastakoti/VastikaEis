app.config(['$stateProvider', function($stateProvider) {
    
    $stateProvider
    .state('app.skills-category',{
        url: 'skills-category',
        views: {
            'content@': {
                templateUrl: 'resources/views/skills/skill-category.html',
                controller: 'SkillCategoryController' 
            }
        }

    })
    .state('app.skills', {
    	url: 'skills-category/:id/skills',
    	views: {
    		'content@': {
                templateUrl: 'resources/views/skills/skills.html',
                controller: 'SkillController'
    		}
    	}

    });
}]);