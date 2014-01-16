'use strict';

var DeptController = function($scope, $http) {
	$('#content-title').text("位置：部门管理");
	$scope.dgm = {};
	$scope.dgm.page = 1;
	$scope.dgm.rows = 10;
	$scope.dgm.sort = 'dept.id';
	$scope.dgm.order = 'desc';
	$scope.dept = {};

	$scope.fetchDeptList = function() {
		$scope.resetError();
		$http.post('dept/queryList', { dgm: $scope.dgm, dept: $scope.dept }).success(function(data) {
			console.log(data);
			$scope.depts = data.rows;
		});
	};

	$scope.fetchDeptList2 = function() {
		$http.get('dept/queryAll').success(function(deptList) {
			console.log(deptList);
			$scope.depts = deptList;
		});
	};

	$scope.createOrUpdateDept = function(dept) {
		$scope.resetError();
		$http.post('dept/createOrUpdate', dept).success(function() {
			$scope.fetchDeptList();
			$scope.dept.id = '';
			$scope.dept.code = '';
			$scope.dept.name = '';
		}).error(function() {
			$scope.setError('Could not create or update dept');
		});
		$('#myModal').modal('hide');
	};

	$scope.editDept = function(dept) {
		$scope.resetError();
		$scope.dept.id = dept.id;
		$scope.dept.code = dept.code;
		$scope.dept.name = dept.name;
	};

	$scope.resetDept = function() {
		$scope.resetError();
		$scope.dept = {};
	};

	$scope.removeDept = function(dept) {
		$scope.resetError();
		$http.post('dept/delete?id=' + dept.id).success(function() {
			$scope.fetchDeptList();
		}).error(function() {
			$scope.setError('Could not remove dept');
		});
	};

	$scope.resetError = function() {
		$scope.error = false;
		$scope.errorMessage = '';
	};

	$scope.setError = function(message) {
		$scope.error = true;
		$scope.errorMessage = message;
	};

	$scope.fetchDeptList();

	$scope.predicate = 'id';

};
