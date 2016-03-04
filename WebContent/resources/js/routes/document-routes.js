app.config(['$stateProvider', function($stateProvider) {
    
    $stateProvider
    .state('app.resumes',{
        url: 'resumes',
        views: {
            'content@': {
                templateUrl: 'resources/views/document/resumes.html',
                controller: 'ResumeController' 
            }
        }
    })
    .state('app.heroes-documents',{
        url: 'heroes/:id/documents',
        views: {
            'content@': {
                templateUrl: 'resources/views/document/documents.html',
                controller: 'HeroesDocumentController' 
            }
        }
    })
    .state('app.add-resume',{
        url: 'resumes/add',
        views: {
            'content@': {
                templateUrl: 'resources/views/document/add-resume.html',
                controller: 'AddResumeController' 
            }
        }
    })
    .state('app.set-resume-marketers',{
        url: 'resumes/set',
        views: {
            'content@': {
                templateUrl: 'resources/views/document/set-resume-marketers.html',
                controller: 'SetResumeController' 
            }
        }
    });
    $stateProvider
    .state('app.document-type',{
        url: 'document-type',
        views: {
            'content@': {
                templateUrl: 'resources/views/documents/document-type.html',
                controller: 'DocumentTypeController' 
            }
        }

    })
}]);