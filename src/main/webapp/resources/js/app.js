'use strict';

var AcsWorks3 = {};

var App = angular.module('AcsWorks3', [ 'ngRoute', 'ngResource', 'ngCookies',
		'AcsWorks3.filters', 'AcsWorks3.services', 'AcsWorks3.directives' ]);

// Declare app level module which depends on filters, and services
App.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/dept', {
		templateUrl : 'dept/list',
		controller : DeptController
	});
	$routeProvider.when('/dept1111111', {
		templateUrl : 'dept/list',
		controller : DeptController
	});
	$routeProvider.otherwise({
		redirectTo : '#'
	});
} ]);
