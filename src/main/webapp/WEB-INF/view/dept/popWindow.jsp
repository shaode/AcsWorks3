<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
</head>
<body>
	<br>
	<br>
	<br>
	<form id="deptForm" method="post" style="margin: 10; text-align: center;">
		<input type="hidden" name="id" id="uuid" /> 
		部门编码：<input id="dept_code" name="code" style="width: 200" validType="length[3,3]" class="easyui-validatebox" required="true" /> <br> 
		部门名称：<input id="dept_name" name="name" style="width: 200" validType="length[3,30]" class="easyui-validatebox" required="true" /> <br> <br> 
		<a href="#" id="btn-back" onclick="closeWindow();" class="easyui-linkbutton" iconCls="icon-back">返回</a> 
		<a href="#" id="btn-add" onclick="saveDept();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
	</form>
	<script type="text/javascript">
		jQuery(function($) {
		});

		function saveDept() {
			var r = $('#deptForm').form('validate');
			if (!r) {
				return false;
			}
			$.post("dept/createOrUpdate", $("#deptForm").serializeArray(),
					function(data) {
						$('#MyPopWindow').window('close');
						$('#deptTable').datagrid('reload');
						$.messager.alert('提示', data.mes, 'info');
					});
		}
	</script>
</body>
</html>
