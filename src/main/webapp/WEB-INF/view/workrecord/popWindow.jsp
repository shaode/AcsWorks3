<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
</head>
<body>
	<form id="workrecordForm" method="post" style="margin: 10; text-align: center;">
		<input type="hidden" name="id" id="uuid" />
		<table style="text-align: left;">
			<tr>
				<td>客户项目号：</td>
				<td><input id="custInfo_custProject" name="custInfo.custProject" style="width: 200" validType="length[3,30]" class="easyui-validatebox" required="true" /></td>
			</tr>
			<tr>
				<td>客户名称：</td>
				<td><input id="custInfo_custName" name="custInfo.custName" style="width: 200" validType="length[3,30]" class="easyui-validatebox" required="true" /></td>
			</tr>
			<tr>
				<td>客户联系人：</td>
				<td><input id="custInfo_custContactor" name="custInfo.custContactor" style="width: 200" /></td>
			</tr>
			<tr>
				<td>客户信息：</td>
				<td><input id="custInfo_custInfo" name="custInfo.custInfo" style="width: 200" />
			</tr>
			<tr>
				<td>工作日期：</td>
				<td><input name="workDate" type="text" class="easyui-datebox" required="required" /></td>
			</tr>
			<tr>
				<td>开始时间：</td>
				<td><input name="workStart" class="easyui-timespinner" required="required" data-options="showSeconds:true" /></td>
			</tr>
			<tr>
				<td>结束时间：</td>
				<td><input name="workEnd" class="easyui-timespinner" required="required" data-options="showSeconds:true" /></td>
			</tr>
			<tr>
				<td>工作时长：</td>
				<td><input name="workHours" class="easyui-numberbox" required="required" data-options="min:0,max:24,precision:2" /></td>
			</tr>
			<tr>
				<td>加班时长：</td>
				<td><input name="otHours" class="easyui-numberbox" data-options="min:0,max:24,precision:2" /></td>
			</tr>
			<tr>
				<td>OT类型：</td>
				<td><input id="mOttype" name="mOttype.id" style="width:200px;" /></td>
			</tr>
			<tr>
				<td>工程师：</td>
				<td><input id="mUser" name="mUser.id" style="width:200" /></td>
			</tr>
			<tr>
				<td>工作内容：</td>
				<td><input name="workContent" style="width: 200" /></td>
			</tr>
			<tr>
				<td>备注：</td>
				<td><input name="comments" style="width: 200" /></td>
			</tr>
		</table>
		<a href="#" id="btn-back" onclick="closeWindow();" class="easyui-linkbutton" iconCls="icon-back">返回</a> 
		<a href="#" id="btn-add" onclick="saveWorkrecord();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
	</form>
	<script type="text/javascript">
		$(function($) {
			$('#mOttype').combobox({
				valueField : 'id', //值字段
				textField : 'name', //显示的字段
				url : 'workrecord/queryAllOttpe',
				panelHeight : 'auto',
				required : true,
				editable : false
			});
			$('#mUser').combobox({
				valueField : 'id', //值字段
				textField : 'name', //显示的字段
				url : 'user/queryAll',
				panelHeight : 'auto',
				required : true,
				editable : false
			});
		});

		function saveWorkrecord() {
			var r = $('#workrecordForm').form('validate');
			if (!r) {
				return false;
			}
			$.post("workrecord/addOrUpdate", $('#workrecordForm')
					.serializeArray(), function(data) {
				$('#MyPopWindow').window('close');
				$('#workrecordTable').datagrid('reload');
				$.messager.alert('提示', data.mes, 'info');
			});
		}
	</script>
</body>
</html>
