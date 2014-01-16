<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
</head>
<body>
	<form id="userQueryForm" style="margin: 10; text-align: center;">
		<table width="100%">
			<tr>
				<td>名字：<input name="name" style="width: 200" /></td>
				<td>邮箱：<input name="mail" style="width: 200" /></td>
				<td align="center"><a href="#" onclick="clearUserQueryForm();" class="easyui-linkbutton" iconCls="icon-search">清空</a></td>
			</tr>
			<tr>
				<td>备注：<input name="comments" style="width: 200" /></td>
				<td>部门：<input id="mDeptCombo" name="mDept.code" style="width: 200" /></td>
				<td align="center"><a href="#" onclick="searchUser();" class="easyui-linkbutton" iconCls="icon-search">查询</a></td>
			</tr>
		</table>
	</form>
	<div style="padding: 10" id="tabdiv">
		<table id="userTable"></table>
	</div>
	<script type="text/javascript">
		jQuery(function($) {
			$('#userTable').datagrid({
				title : '用户列表', //标题
				method : 'post',
				iconCls : 'icon-edit', //图标
				singleSelect : false, //多选
				//height : 360, //高度
				fitColumns : true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
				striped : true, //奇偶行颜色不同
				collapsible : true,//可折叠
				url : "user/queryList", //数据来源
				sortName : 'user.id', //排序的列
				sortOrder : 'desc', //倒序
				remoteSort : true, //服务器端排序
				idField : 'id', //主键字段
				queryParams : {}, //查询条件
				pagination : true, //显示分页
				rownumbers : true, //显示行号
				columns : [ [ {
					field : 'ck',
					checkbox : true,
					width : 2
				}, //显示复选框
				{
					field : 'name',
					title : '名字',
					width : 20,
					sortable : true,
				}, {
					field : 'mail',
					title : '邮箱',
					width : 20,
					sortable : true,
				}, {
					field : 'mDept_id',
					title : '部门',
					width : 30,
					sortable : true,
					formatter : function(value, row, index) {
						return row.mdept.name;
					}
				}, {
					field : 'comments',
					title : '备注',
					width : 50,
				} ] ],
				toolbar : [ {
					text : '新增',
					iconCls : 'icon-add',
					handler : function() {
						addUserRow();
					}
				}, '-', {
					text : '更新',
					iconCls : 'icon-edit',
					handler : function() {
						updateUserRow();
					}
				}, '-', {
					text : '删除',
					iconCls : 'icon-remove',
					handler : function() {
						deleteUserRow();
					}
				}, '-' ],
				onLoadSuccess : function() {
					$('#userTable').datagrid('clearSelections'); 
				}
			});

			$('#mDeptCombo').combogrid({
				panelWidth: 300,
				idField : 'code', //ID字段
				textField : 'name', //显示的字段
				url : "dept/queryAll",
				fitColumns : true,
				striped : true,  //奇偶行颜色不同
				//editable : false,//不可编辑，只能选择
				columns : [ [ {
					field : 'code',
					title : '编号',
					width : 100
				}, {
					field : 'name',
					title : '名称',
					width : 150
				} ] ]
			});
		});
		//新增
		function addUserRow() {
			showWindow({
				title : '增加用户信息',
				href : 'user/popWindow',
				width : 300,
				height : 250,
				onLoad : function() {
					$('#userForm').form('clear');
					$('#uuid').val('0');
					$('#user_name').select();
				}
			});
		}
		//更新
		function updateUserRow() {
			var rows = $('#userTable').datagrid('getSelections');
			if (rows.length == 0) {
				$.messager.alert('提示', "请选择你要更新的用户", 'info');
				return;
			}
			if (rows.length > 1) {
				$.messager.alert('提示', "只能选择一位用户进行更新", 'info');
				return;
			}
			//$.messager.alert('提示', rows[0]['mdept']['id'], 'info');
			showWindow({
				title : '更新用户信息',
				href : 'user/popWindow',
				width : 300,
				height : 250,
				onLoad : function() {
					$("#userForm").form('load', rows[0]);
					$("#addDeptId").combobox('setValue',rows[0]['mdept']['id']);
					$('#user_name').select();
				}
			});
		}
		//删除
		function deleteUserRow() {
			$.messager.confirm('提示', '确定要删除吗?', function(result) {
				if (result) {
					var rows = $('#userTable').datagrid('getSelections');
					var ps = "";
					$.each(rows, function(i, n) {
						if (i == 0)
							ps += "?id=" + n.id;
						else
							ps += "&id=" + n.id;
					});
					$.post('user/delete' + ps, function(data) {
						$('#userTable').datagrid('reload');
						$.messager.alert('提示', data.mes, 'info');
					});
				}
			});
		}
		//表格查询
		function searchUser() {
			var params = $('#userTable').datagrid('options').queryParams; //先取得 datagrid 的查询参数
			var fields = $('#userQueryForm').serializeArray(); //自动序列化表单元素为JSON对象
			$.each(fields, function(i, field) {
				params[field.name] = field.value; //设置查询参数
			});
			$('#userTable').datagrid('reload'); //设置好查询参数 reload 一下就可以了
		}
		//清空查询条件
		function clearUserQueryForm() {
			$('#userQueryForm').form('clear');
			searchUser();
		}
	</script>
</body>
</html>
