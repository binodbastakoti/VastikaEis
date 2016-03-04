app.config(['$stateProvider', function($stateProvider) {
    
    $stateProvider
    .state('app.add-hero',{
        url: 'heroes/add',
        views: {
            'content@': {
                templateUrl: 'resources/views/heros/add-hero.html',
                controller: 'HeroAddController' 
            }
        },
        data: {
            pageTitle: 'Add Consultant'
        }
    })
    .state('app.heroes',{
        url: 'heroes',
        views: {
            'content@': {
                templateUrl: 'resources/views/heros/heroes.html',
                controller: 'HeroController' 
            }
        },
        data: {
            pageTitle: 'Consultants'
        }
    })
    .state('app.edit-heroes',{
        url: 'heroes/:id',
        views: {
            'content@': {
                templateUrl: 'resources/views/heros/add-hero.html',
                controller: 'HeroAddController' 
            }
        },
        data: {
            pageTitle: 'Edit Consultant'
        }
    })
     .state('app.my-heroes',{
        url: 'myHeroes',
        views: {
            'content@': {
                templateUrl: 'resources/views/heros/heroes.html',
                controller: 'MyHeroController' 
            }
        },
        data: {
            pageTitle: 'My Consultants'
        }
    });
}]);