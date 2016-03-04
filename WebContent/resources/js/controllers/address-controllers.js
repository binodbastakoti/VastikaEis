app.controller(
				'heroesAddressesController',
				[
						'$scope',
						'$http',
						'baseUrl',
						'$stateParams',
						'$modal',
						'Lightbox',
						function($scope, $http, baseUrl, $stateParams, $modal,
								Lightbox) {

							$scope.userId = parseInt($stateParams.id, 10);

							$http.get(
									baseUrl + "api/users/" + $scope.userId
											+ "/address").then(
									function(response) {
										$scope.addresses = response.data;
									});

							$http.get(baseUrl + "api/users/" + $scope.userId)
									.then(function(response) {
										$scope.user = response.data;
									});

							$scope.addAddress = function() {
								var modalInstance = $modal
										.open({
											animation : true,
											templateUrl : 'resources/views/address/add-address.html',
											controller : 'AddressModal',
											resolve : {
												address : false
											}

										});

								modalInstance.result.then(function(newAddress) {
									if (newAddress) {
										$scope.addresses.push(newAddress);
									}
								}, function() {
								});
							}

							$scope.editAddress = function(address) {

								var modalInstance = $modal
										.open({
											animation : true,
											templateUrl : 'resources/views/address/add-address.html',
											controller : 'AddressModal',
											resolve : {
												address : function() {
													return address;
												}
											}
										});

								modalInstance.result
										.then(function(updatedAddress) {

											if (updatedAddress) {
												var ref = _
														.find(
																$scope.addresses,
																function(el) {
																	return el.addressId == updatedAddress.addressId;
																});
												$scope.addresses[_.indexOf(
														$scope.addresses, ref)] = updatedAddress;
											}

										});

							}
							
							$scope.deleteAddress = function(address){
								console.log(address);
								
								$modalInstance = $modal.open({
									animation:true,
									templateUrl: 'resources/views/partials/confirm.html',
									controller: 'ConfirmController'
								}).result
								.then(function(){
									$http.delete(baseUrl + 'api/addresses/' + address.addressId)
									.then(function(response) {
										console.log(address);
										console.log(response);
										toastr.success(response.data.message, "Success");
										$scope.addresses = _.reject($scope.addresses, function(el) { return el.addressId === address.addressId; });
									});
								});
									
							}
							
							
//							$scope.delete = function(skillCategory) {
//								$modal.open({
//									animation : true,
//									templateUrl : 'resources/views/partials/confirm.html',
//									controller : 'ConfirmController',
//								}).result.then(function() {
//									$http.delete(baseUrl + 'api/skillCategories/'+skillCategory.categoryId)
//									.then(function(response) {
//										toastr.success(response.data.message, "Success");
//										$scope.skillCategories = _.reject($scope.skillCategories, function(el) { return el.categoryId === skillCategory.categoryId; });
//									});
//								});		
//							}

							
							

						} ]);

app.controller('AddressModal', addressModal);
function addressModal($scope, $modalInstance, address, baseUrl, $http,
		$stateParams) {

	if (address) {
		$scope.mode = "Edit";
		$scope.address = {
			street : address.street,
			city : address.city,
			state : address.state,
			zipCode : address.zipCode,
			startDate : address.startDate,
			endDate : address.endDate
		};
		$scope.addressId = address.addressId;

	} else {
		$scope.mode = "Add";
		$scope.address = {
			city : "",
			street : ""
		};

	}

	$modalInstance.rendered.then(function() {
		$('.endDate-picker').datepicker({
			Date : new Date()
		});
		$('.startDate-picker').datepicker({
			Date : new Date()
		});
	});

	$scope.add = function() {

		$scope.$broadcast('show-errors-check-validity');

		if (!$scope.addAddressForm.$valid) {
			toastr.error("Please fill required fields.", "Error");
			return;
		}

		if ($scope.address.startDate.indexOf('/') != -1) {
			var date = $scope.address.startDate.split('/');
			$scope.address.startDate = date[2] + '-' + date[0] + '-' + date[1];
		}

		if ($scope.address.endDate.indexOf('/') != -1) {
			var date = $scope.address.endDate.split('/');
			$scope.address.endDate = date[2] + '-' + date[0] + '-' + date[1];
		}

		$scope.userId = parseInt($stateParams.id, 10);

		if ($scope.mode == "Add") {
			$scope.processing = true;
			var promise = $http.post(baseUrl + 'api/users/' + $scope.userId
					+ '/address', $scope.address);
		} else {
			$scope.processing = true;
			var promise = $http.put(baseUrl + 'api/users/' + $scope.userId
					+ '/address/' + $scope.addressId, $scope.address);
		}

		promise.then(function(response) {
			if (response.data.status == 'success') {
				toastr.success(response.data.message, "Success");
				if ($scope.mode == "Add") {
					$scope.address.addressId = response.data.id;
				}
				else{
					$scope.address.addressId = $scope.addressId;
				}
				$modalInstance.close($scope.address);
			} else {
				toastr.error("Something went wrong.", "Error");
				$modalInstance.close();
			}
		}, function() {
			toastr.error("Something went wrong.", "Error");
		});

	}

	$scope.cancel = function() {
		$modalInstance.dismiss('cancel');
	};
}