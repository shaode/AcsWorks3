<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
</head>
<body>
	<br>
	<br>
	<form id="userForm" method="post" style="margin:10;text-align:center;">
		<input type="hidden" name="id" id="uuid" /> 
		名字：<input id="user_name" name="name" style="width: 200" validType="length[3,30]" class="easyui-validatebox" required="true"> <br> 
		密码：<input name="password" style="width: 200" type="password" validType="length[3,30]" class="easyui-validatebox" required="true"> <br> 
		邮箱：<input name="mail" style="width: 200" validType="length[3,30]" class="easyui-validatebox" required="true"> <br> 
		备注：<input name="comments" style="width: 200" validType="length[3,30]" class="easyui-validatebox" /> <br> 
		部门：<input id="addDeptId" name="mDept.id" style="width: 200"> <br> <br> 
		<a href="#" id="btn-back" onclick="closeWindow();" class="easyui-linkbutton" iconCls="icon-back">返回</a> 
		<a href="#" id="btn-add" onclick="saveUser();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
	</form>
	<script type="text/javascript">
		jQuery(function($) {
			$('#addDeptId').combobox({
				valueField : 'id', //值字段
				textField : 'name', //显示的字段
				url : 'dept/queryAll',
				panelHeight : 'auto',
				required : true,
				editable : false
			});
		});

		function saveUser() {
			var r = $('#userForm').form('validate');
			if (!r) {
				return false;
			}
			$.post("user/addOrUpdate", $("#userForm").serializeArray(),
					function(data) {
						$('#MyPopWindow').window('close');
						$('#userTable').datagrid('reload');
						$.messager.alert('提示', data.mes, 'info');
					});
		}
	</script>
</body>
</html>
