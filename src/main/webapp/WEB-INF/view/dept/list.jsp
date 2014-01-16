<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
</head>
<body>
	<form id="deptQueryForm" style="margin:10;text-align:center;">
		<table width="100%">
			<tr>
				<td>部门编号：<input name="code" style="width: 200" /></td>
				<td align="center"><a href="#" onclick="clearDeptQueryForm();" class="easyui-linkbutton" iconCls="icon-clear">清空</a></td>
			</tr>
			<tr>
				<td>部门名称：<input name="name" style="width: 200" /></td>
				<td align="center"><a href="#" onclick="searchDept();" class="easyui-linkbutton" iconCls="icon-search">查询</a></td>
			</tr>
		</table>
	</form>
	<div style="padding: 10" id="tabdiv">
		<table id="deptTable"></table>
	</div>
	<script type="text/javascript">
		$(function() {
			$('#deptTable').datagrid({
				title : '部门列表',
				method : 'post',
				iconCls : 'icon-edit',
				singleSelect : false,
				//height : 400, 
				fitColumns : true,
				striped : true,
				collapsible : true,
				url : "dept/queryList",
				sortName : 'dept.id',
				sortOrder : 'desc',
				remoteSort : true, //服务器端排序
				idField : 'id', //主键字段
				queryParams : {}, //查询条件
				pagination : true, //显示分页
				rownumbers : true, //显示行号
				columns : [ [ {
					field : 'ck',
					checkbox : true,
					width : 2
				}, {
					field : 'code',
					title : '部门编码',
					width : 20,
					sortable : true,
				}, {
					field : 'name',
					title : '部门名称',
					width : 20,
					sortable : true,
				}, ] ],
				toolbar : [ {
					text : '新增',
					iconCls : 'icon-add',
					handler : function() {
						addDeptRow();
					}
				}, '-', {
					text : '更新',
					iconCls : 'icon-edit',
					handler : function() {
						updateDeptRow();
					}
				}, '-', {
					text : '删除',
					iconCls : 'icon-remove',
					handler : function() {
						deleteDeptRow();
					}
				}, '-' ],
				onLoadSuccess : function() {
					$('#deptTable').datagrid('clearSelections');
				}
			});
		});
		//新增
		function addDeptRow() {
			showWindow({
				title : '增加部门信息',
				href : 'dept/popWindow',
				width : 300,
				height : 200,
				onLoad : function() {
					$('#deptForm').form('clear');
					$('#uuid').val('0');
					//$('#dept_code').focus();
					$('#dept_code').select();
				}
			});
		}
		//更新
		function updateDeptRow() {
			var rows = $('#deptTable').datagrid('getSelections');
			if (rows.length == 0) {
				$.messager.alert('提示', "请选择你要更新的用户", 'info');
				return;
			}
			if (rows.length > 1) {
				$.messager.alert('提示', "只能选择一位用户进行更新", 'info');
				return;
			}
			showWindow({
				title : '更新部门信息',
				href : 'dept/popWindow',
				width : 300,
				height : 200,
				onLoad : function() {
					$("#deptForm").form('load', rows[0]);
					$('#dept_code').select();
					//$('#firstname').attr("disabled", true);
				}
			});
		}
		//删除
		function deleteDeptRow() {
			$.messager.confirm('提示', '确定要删除吗?', function(result) {
				if (result) {
					var rows = $('#deptTable').datagrid('getSelections');
					var ps = "";
					$.each(rows, function(i, n) {
						if (i == 0)
							ps += "?id=" + n.id;
						else
							ps += "&id=" + n.id;
					});
					$.post('dept/delete' + ps, function(data) {
						$('#deptTable').datagrid('reload');
						$.messager.alert('提示', data.mes, 'info');
					});
				}
			});
		}
		//表格查询
		function searchDept() {
			var params = $('#deptTable').datagrid('options').queryParams;
			var fields = $('#deptQueryForm').serializeArray(); //自动序列化表单元素为JSON对象
			$.each(fields, function(i, field) {
				params[field.name] = field.value; //设置查询参数
			});
			$('#deptTable').datagrid('reload'); //设置好查询参数 reload 一下就可以了
		}
		//清空查询条件
		function clearDeptQueryForm() {
			$('#deptQueryForm').form('clear');
			searchDept();
		}
	</script>
</body>
</html>
