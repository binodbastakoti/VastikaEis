/**
 * 
 */
angular.module('vastikaApp',['ngRoute'])
	.config(function($routeProvider) {
		$routeProvider.when('/categories',{
			templateUrl:'views/skills/skill-category.html',
			controller :'CategoryCtrl'
		})
		.when('/products',{
			templateUrl:'views/products.html',
			controller:'ProductCtrl'
		})
		.when('/addProduct',{
			templateUrl:'views/addProduct.html',
			controller:'AddProductCtrl'
		})
	}).run(function($rootScope) {
		$rootScope.$on('$routeChangeError',function(){
			alert("Error occured while routing...");
	})
});