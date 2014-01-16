'use strict';

/**
 * MainController
 * 
 * @constructor
 */
var MainController = function MainController($scope, $http) {
	$scope.error = false;
	$scope.errorMessage = "";

	$scope.setMain = function(title, href) {
		$('#content-title').text("位置：" + title);
		
		$('body').layout('panel', 'center').panel({
			title : "所在位置：" + centerTitle,
			href : centerURL
		});
		$('body').layout('panel', 'center').panel('refresh');
		return false;
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
