<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html ng-app="userService">
<head>
<title>angularjs</title>
<script src="js/angular-1.2.7/angular.js" type="text/javascript"></script>
<script src="js/angular-1.2.7/angular-resource.js" type="text/javascript"></script>

<script type="text/javascript">
	var projectName = "/restful";
	var userUrl = {
		addUrl: projectName + '/user/add',
		deleteUrl: projectName + '/user/delete',
		updateUrl: projectName + '/user/update',
		queryUrl: projectName + '/user/getUserList'
	};
	var user = angular.module('userService', ['ngResource'], angular.noop);
	user.controller('userController', function ($scope, $resource) {
		var actions = {
			add: {method: 'PUT', isArray: true, headers: {'Content-Type': 'application/json'}},
			delete: {method: 'DELETE', isArray: true},
			query: {method: 'GET', isArray: true},
			update: {method: 'POST', isArray: true, headers: {'Content-Type': 'application/json'}}
		};
		var getUserList = $resource(userUrl.queryUrl, {}, actions);
		getUserList.query({}, function (data) {
			subobj = data;
			$scope.mydata = data;
		});
		var userAdd = $resource(userUrl.addUrl, {}, actions);
		$scope.addUserClick = function () {
			userAdd.add($scope.saveUser, function (data) {
				subobj = data;
				$scope.mydata = data;
			});
		};
		var userUpdate = $resource(userUrl.updateUrl, {}, actions);
			$scope.updateUserClick = function () {
				userUpdate.update($scope.modifyUser, function (data) {
				subobj = data;
				$scope.mydata = data;
			});
		};
		var userDelete = $resource(userUrl.deleteUrl + "/:id", {id: '@id'}, actions);
		$scope.deleteUser = function (user) {
			userDelete.delete({id: user.id}, {}, function (data) {
				subobj = data;
				$scope.mydata = data;
			});
		};
		$scope.updateUser = function (user) {
			$scope.modifyUser = user;
		};
	});
</script>
<style type="text/css">
</style>
</head>
<body ng-controller="userController">
	<div id="save" style="display: block; margin-left: auto; margin-right: auto;">
		<table>
			<tr style="display: none">
				<td>用户id</td>
				<td><input type="text" name="user.id" ng-model="saveUser.id" /></td>
			</tr>
			<tr>
				<td>用户名</td>
				<td><input type="text" name="user.username" ng-model="saveUser.username" /></td>
			</tr>
			<tr>
				<td>密码</td>
				<td><input type="password" name="user.password" ng-model="saveUser.password" /></td>
			</tr>
			<tr>
				<td>参数</td>
				<td><input type="text" name="user.cs" ng-model="saveUser.cs" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="button" value="添加" ng-click="addUserClick()" /></td>
			</tr>
		</table>
	</div>
	<div id="userList" style="margin-left: auto; margin-right: auto;">
		<table border="1" style="margin-left: auto; margin-right: auto;">
			<tr>
				<th>序号</th>
				<th>用户id</th>
				<th>用户名称</th>
				<th>用户密码</th>
				<th>用户参数</th>
				<th>用户操作</th>
			</tr>
			<tr ng-repeat="user in mydata" ng-class-even="'even'" ng-class-odd="'odd'">
				<td>{{$index + 1}}</td>
				<td>{{user.id}}</td>
				<td>{{user.username}}</td>
				<td>{{user.password}}</td>
				<td>{{user.cs}}</td>
				<td><a href="" ng-click="updateUser(user)">修改</a> &nbsp; 
				<a href="" ng-click="deleteUser(user)">删除</a></td>
			</tr>
		</table>
	</div>
	<div id="update" style="display: block; margin-left: auto; margin-right: auto;">
		<table>
			<tr style="display: none">
				<td>用户id</td>
				<td><input type="text" id="id" name="user.id" ng-model="modifyUser.id" /></td>
			</tr>
			<tr>
				<td>用户名</td>
				<td><input type="text" id="username" name="user.username" ng-model="modifyUser.username" /></td>
			</tr>
			<tr>
				<td>密码</td>
				<td><input type="password" id="password" name="user.password" ng-model="modifyUser.password" /></td>
			</tr>
			<tr>
				<td>参数</td>
				<td><input type="text" id="cs" name="user.cs" ng-model="modifyUser.cs" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="button" value="更新" ng-click="updateUserClick()" /></td>
			</tr>
		</table>
	</div>
</body>
</html>
