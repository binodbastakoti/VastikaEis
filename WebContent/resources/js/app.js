var app = angular.module('app', [ 'ui.router', 'ngFileUpload', 'ui.bootstrap.showErrors', 'ui.bootstrap', 'ngCookies', 'bootstrapLightbox' ]).constant("baseUrl", "http://localhost:8080/VastikaEIS/");

app.config([ 'showErrorsConfigProvider', function(showErrorsConfigProvider) {
	showErrorsConfigProvider.showSuccess(true);
	showErrorsConfigProvider.trigger('blur');
} ]);

app.run(function($rootScope, $location, $q, CurrentUser, $state) {

	$rootScope.$on('$stateChangeStart', function(event, state) {
		$q.all([ CurrentUser.initPromise ]).then(function() {
			if (state.data.permission && !CurrentUser.canAccess(state.data.permission)) {
				toastr.error('Page: ' + state.data.pageTitle, "Unauthorized Access");
				$state.go('app');
			}
		}, function(reason) {
		});
	});

	$rootScope.$on('$locationChangeSuccess', function() {
		if ($rootScope.previousLocation == $location.path()) {
			$rootScope.$broadcast('back-button-pressed');
		}
		$rootScope.previousLocation = $rootScope.actualLocation;
		$rootScope.actualLocation = $location.path();
	});
});