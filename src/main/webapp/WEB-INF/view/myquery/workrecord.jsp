<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
</head>
<body>
	<form id="myquery_workrecordform" style="margin: 10; text-align: center;" method="post">
		<table width="100%">
			<tr>
				<td>开始日期：<input name="startDate" type="text" class="easyui-datebox" required="required" /></td>
				<td>结束日期：<input name="endDate" type="text" class="easyui-datebox" required="required"/></td>
				<td align="center"><a href="#" onclick="queryWorkrecord();" class="easyui-linkbutton" iconCls="icon-search">查询</a></td>
				<td align="center"><a href="#" onclick="clearMyQueryForm();" class="easyui-linkbutton" iconCls="icon-search">清空</a></td>
			</tr>
			<tr>
				<td>客户名称：<input name="custInfo.custName" style="width: 200" /></td>
				<td>客户项目号：<input name="custInfo.custProject" style="width: 200" /></td>
				<td>工程师：<input id="mUserCombo" name="mUser.name" style="width: 200" /></td>
				<td>加班类型：<input id="mOttypeCombo" name="mOttype.id" style="width: 200px;" /></td>
			</tr>
		</table>
	</form>
	<div style="padding: 10" id="tabdiv">
		<table id="myquery_workrecordtable" class="easyui-datagrid" fitcolumns="true" striped="true"
		data-options="rownumbers:true,collapsible:true,pagination:true,title:'查询结果'">
			<thead>
				<tr>
					<th data-options="field:'workDate',width:20">工作日期</th>
					<th data-options="field:'workStart',width:20">开始时间</th>
					<th data-options="field:'workEnd',width:20">结束时间</th>
					<th data-options="field:'custInfo.custName',width:20,formatter:function(value, row, index) {return row.custInfo.custName;}">客户名称</th>
					<th data-options="field:'custInfo.custProject',width:20,formatter:function(value, row, index) {return row.custInfo.custProject;}">客户项目号</th>
					<th data-options="field:'mUser_id',width:20,sortable:true,formatter:function(value, row, index) {return row.muser.name;}">工程师</th>
					<th data-options="field:'totalHours',width:20">总小时数</th>
					<th data-options="field:'custInfo.custContactor',width:20,formatter:function(value, row, index) {return row.custInfo.custContactor;}">客户联系人</th>
					<th data-options="field:'custInfo.custInfo',width:20,formatter:function(value, row, index) {return row.custInfo.custInfo;}">客户信息</th>
					<th data-options="field:'workContent',width:50">工作内容</th>
					<th data-options="field:'comments',width:50">备注</th>
				</tr>
			</thead>
		</table>
	</div>
	<script type="text/javascript">
		jQuery(function($) {
			$('#mUserCombo').combogrid({
				panelWidth : 300,
				idField : 'name', //ID字段
				textField : 'mail', //显示的字段
				url : "user/queryAll",
				fitColumns : true,
				striped : true, //奇偶行颜色不同
				//editable : false,//不可编辑，只能选择
				columns : [ [ {
					field : 'name',
					title : '姓名',
					width : 100
				}, {
					field : 'mail',
					title : '邮箱',
					width : 150
				} ] ]
			});
			$('#mOttypeCombo').combogrid({
				panelWidth : 300,
				idField : 'name', //ID字段
				textField : 'factor', //显示的字段
				url : "workrecord/queryAllOttpe",
				fitColumns : true,
				striped : true, //奇偶行颜色不同
				editable : false,//不可编辑，只能选择
				columns : [ [ {
					field : 'name',
					title : '名称',
					width : 100
				}, {
					field : 'factor',
					title : '系数',
					width : 150
				} ] ]
			});
		});

		//表格查询
		function queryWorkrecord() {
			var r = $('#myquery_workrecordform').form('validate');
			if (!r) {
				return false;
			}
			$('#myquery_workrecordform').form('submit',
					{
						url : 'myquery/workrecordByDate2',
						onSubmit : function() {
							// do some check
							// return false to prevent submit;
						},
						success : function(data) {
							$('#myquery_workrecordtable').datagrid('loadData',JSON.parse(data));
						}
					});
		}
		//清空查询条件
		function clearMyQueryForm() {
			$('#myquery_workrecordform').form('clear');
			//$('#myquery_workrecordtable').datagrid('reload');
		}
		
		function formatCustName(value, row, index) { 
			return row.custInfo.custName;
		}
		
		function formatCustProject(value, row, index) {
			return row.custInfo.custProject;
		}
	</script>
</body>
</html>
