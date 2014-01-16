'use strict';

/**
 * LoginController
 * 
 * @constructor
 */
var LoginController = function($scope, $http, $location, $rootScope) {
	$scope.user = {};
	$scope.user.name = '';
	$scope.user.password = '';

	$scope.loginUser = function(user) {
		$scope.resetError();
		$http.post('login', user).success(function(data) {
			$scope.user.name = '';
			$scope.user.password = '';
			console.log(data);
			if (data.message == 'OK') {
				console.log("user=" + data.userSessionInfo);
				$location.path("/main");
			} else {
				$scope.setError(data.message);
			};
		}).error(function() {
			$scope.setError('Invalid user/password combination');
		});
	};

	$scope.resetError = function() {
		$scope.error = false;
		$scope.errorMessage = '';
	};

	$scope.setError = function(message) {
		$scope.error = true;
		$scope.errorMessage = message;
		$rootScope.SessionId = '';
	};
};
