/**
 * Services
 */

angular.module('vastikaApp')
.factory('ProductService',function($http){
	var factory ={};
//	factory.products =[];
	factory.getAllProducts = function(){
//		var deferred = $q.defer();
		return $http.get('products/products').success(function(data){
//			alert(data[0].name);
			console.log(data);
//			deferred.resolve(data);
			factory.products = data;
			console.log("In factory: "+factory.products);
		}).error(function(data) {
			alert("error in loading data");
		});
//		return deferred.promise();
	}
	return factory;
}).factory('CategoryService',function($http){
	var factory={};
	factory.categories=[];
	factory.getAllCategories=function (){
		return $http.get('api/skillCategories').success(function(data){
			
			factory.categories= data;
			console.log(factory.categories);
		}).error(function(data) {
			alert("error in loading data");
		});
//		return deferred.promise();
	}
	return factory;
}).factory('AddProductService',function($http){
	var service ={};
	service.addProduct = function(product) {
		return $http.post('products/add',product).success(function(data,status) {
			alert("added..");
		})
	}
	return service;
});
