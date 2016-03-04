app.directive('scrollOnClick', function() {
	return {
		restrict : 'A',
		link : function(scope, $elm) {
			$elm.on('click', function() {
				window.scrollTo(0, 0);
			});
		}
	}
});

app.directive('updateTitle', [ '$rootScope', '$timeout', function($rootScope, $timeout) {
	return {
		link : function(scope, element) {

			var listener = function(event, toState) {

				var title = 'NO TITLE';
				if (toState.data && toState.data.pageTitle)
					title = toState.data.pageTitle;

				$timeout(function() {
					element.text(title);
				}, 0, false);
			};

			$rootScope.$on('$stateChangeSuccess', listener);
		}
	};
} ]);

app.directive('uniqueEmail', [ '$q', '$http', 'baseUrl', function($q, $http, baseUrl) {
	return {
		require : 'ngModel',
		link : function(scope, ele, attrs, ctrl) {

			ctrl.$asyncValidators.uniqueEmail = function(modelValue, viewValue) {

				if (ctrl.$isEmpty(modelValue)) {
					// consider empty model valid
					return $q.when();
				}

				var def = $q.defer();

				var requestData = attrs.userId != '' ? {
					id : attrs.userId,
					email : ele.val()
				} : {
					email : ele.val()
				};
				$http.post(baseUrl + 'api/users/checkIfEmailExist', requestData).success(function(data, status, headers, cfg) {
					if (data.status == 'success') {
						// The username is available
						def.resolve();
					} else {
						def.reject();
					}
				}).error(function(data, status, headers, cfg) {
					def.reject();
				});

				return def.promise;
			};
		}
	}
} ]);

app.directive('phone', function() {
	return {
		require : 'ngModel',
		link : function(scope, elm, attrs, ctrl) {
			ctrl.$validators.integer = function(modelValue, viewValue) {
				if (ctrl.$isEmpty(modelValue)) {
					// consider empty models to be valid
					return true;
				}

				if (/^\d{10}$/.test(viewValue)) {
					// it is valid
					return true;
				}

				// it is invalid
				return false;
			};
		}
	};
});
app.directive('zipCode', function() {
	return {
		require : 'ngModel',
		restrict : 'A',
		link : function(scope, elm, attrs, ctrl) {
			ctrl.$validators.integer = function(modelValue, viewValue) {
				if (ctrl.$isEmpty(modelValue)) {
					// consider empty models to be valid
					return true;
				}

				if (/^\d{5}$/.test(viewValue)) {
					// it is valid
					return true;
				}

				// it is invalid
				return false;
			};
		}
	};
});

app.directive('ngIfPermission', [ '$animate', 'CurrentUser', function($animate, CurrentUser) {
	return {
		multiElement : true,
		transclude : 'element',
		priority : 601,
		terminal : true,
		restrict : 'A',
		$$tlb : true,
		link : function($scope, $element, $attr, ctrl, $transclude) {
			var block, childScope, previousElements;
			$attr.$observe('ngIfPermission', function ngIfPermissionObserve(value) {
				var canAccess = false;
				if(_.contains(value, '&')) {
					canAccess = CurrentUser.canAccessAll(value.split('&'));
				} else if (_.contains(value, '|')) {
					canAccess = CurrentUser.canAccessAny(value.split('|'));
				} else {
					canAccess = CurrentUser.canAccess(value)
				}
				
				if (canAccess) {
					if (!childScope) {
						$transclude(function(clone, newScope) {
							childScope = newScope;
							clone[clone.length++] = document.createComment(' end ngIfPermission: ' + $attr.ngIfPermission + ' ');
							// Note: We only need the first/last node of the cloned nodes.
							// However, we need to keep the reference to the jqlite wrapper as it might be changed later
							// by a directive with templateUrl when its template arrives.
							block = {
								clone : clone
							};
							$animate.enter(clone, $element.parent(), $element);
						});
					}
				} else {
					if (previousElements) {
						previousElements.remove();
						previousElements = null;
					}
					if (childScope) {
						childScope.$destroy();
						childScope = null;
					}
					if (block) {
						previousElements = getBlockNodes(block.clone);
						$animate.leave(previousElements).then(function() {
							previousElements = null;
						});
						block = null;
					}
				}
			});
		}
	};
} ]);