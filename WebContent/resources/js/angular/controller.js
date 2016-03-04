/**
 * Controller
 */
angular.module('vastikaApp')
		.controller('CategoryCtrl',function CategoryCtrl($scope,CategoryService) {
			CategoryService.getAllCategories()
			.then(angular.bind(this,function then(){
				$scope.categories = CategoryService.categories;
			}));
			$scope.categoryMesg = "We are from Category Controller";
		})
		.controller('ProductCtrl', function ProductCtrl($scope,ProductService) {
			ProductService.getAllProducts()
			.then(function() {
				$scope.products = ProductService.products;
			});
			
			
//			
//			$scope.products = ProductService.products;
//			alert($scope.products);
			$scope.productMesg = "We are from Product Controller";
})
.controller('AddProductCtrl',function($scope,AddProductService){
	$scope.addProduct = function() {
		console.log($scope.product);
		AddProductService.addProduct($scope.product);
	}
});