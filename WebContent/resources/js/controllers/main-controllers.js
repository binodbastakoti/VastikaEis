app.controller('AppController', [ '$scope', 'baseUrl', '$cookies', '$location', function($scope, baseUrl, $cookies, $location) {
	var full = $cookies.get('full');
	$scope.full = !full ? 'yes' : full;
	$scope.baseUrl = baseUrl;
	
} ]);

app.controller('HeaderController', [ '$scope', '$http', 'baseUrl', 'CurrentUser', function($scope, $http, baseUrl, CurrentUser) {
	$scope.userName = CurrentUser.getCurrentUserName();
	$scope.userId = CurrentUser.getCurrentUserId();

} ])

app.controller('NavigationController', [ '$scope', '$state', '$cookies', function($scope, $state, $cookies) {
	App.init();
	$scope.uiRouterState = $state;
	
	$scope.toggleSideBar = function() {
		var full = $cookies.get('full');
		if(full == 'no')
			$cookies.put('full', 'yes');
		else
			$cookies.put('full', 'no');
	}
} ])

app.controller('FooterController', [ '$scope', function($scope) {
	$scope.goTop = function() {
		$('html,body').animate({ scrollTop: 0 }, 'slow');
	}
}]);

app.controller('DashBoardController', [ '$scope', function($scope) {
}]);

app.controller('ConfirmController', function($scope, $modalInstance) {
	
	$scope.yes = function() {
		$modalInstance.close();
	};
	
	$scope.no = function() {
		$modalInstance.dismiss('cancel');
	};
	
	$scope.$on('back-button-pressed', function (event, args) {
		$modalInstance.dismiss('cancel');
	});
});