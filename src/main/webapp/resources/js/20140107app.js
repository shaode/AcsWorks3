'use strict';

var AcsWorks3 = {};

var App = angular.module('AcsWorks3', [ 'ngRoute', 'ngResource', 'ngCookies' ]);

//Declare app level module which depends on filters, and services
App.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/login2', {
		templateUrl : 'login_layout.html',
		controller : LoginController
	}).when('/main2', {
		templateUrl : 'main_layout.html',
		controller : MainController
	}).otherwise({
		
	});
} ]);

// Login validation.
// !!!!!! ATTENTION !!!!!!!
// If session id is expired, $rootScope.SessionID should be set to null or empty
// string!!!
App.run(function($rootScope, $location, $cookies) {
	// register listener to watch route changes
	$rootScope.$on("$routeChangeStart", function(event, next, current) {
		console.log("Routechanged sessionId=" + $rootScope.SessionId);
		console.log("JsessionId=" + $cookies.JSESSIONID);
		if ($cookies.JSESSIONID == '' || $cookies.JSESSIONID == null) {
			// no logged user, we should be going to #login
			if (next.templateUrl == "login_layout.html") {
				// already going to #login, no redirect needed
			} else {
				// not going to #login, we should redirect now
				$location.path("/login2");
			}
		}
	});
});

// LoginController.js
function LoginController($scope, $http, $location, $rootScope) {
	$scope.user = {};
	$scope.user.name = '';
	$scope.user.password = '';

	$scope.loginUser = function(user) {
		$scope.resetError();
		$http.post('login', user).success(function(login) {
			if (login.sessionId === null) {
				$scope.setError(login.status);
				return;
			}
			$scope.user.name = '';
			$scope.user.password = '';

			$rootScope.SessionId = login.sessionId;
			console.log("Routechanged sessionId12=" + $rootScope.SessionId);
			$location.path("/main2");
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

// LoginController.js
function MainController($scope, $http, $location, $rootScope) {
	$scope.user = {};
	$scope.user.name = '';
	$scope.user.password = '';
};
