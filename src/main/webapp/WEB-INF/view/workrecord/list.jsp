<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
</head>
<body>
	<form id="workrecordQueryForm" style="margin: 10; text-align: center;" >
		<table width="100%">
			<tr>
				<td>客户名称：<input name="custInfo.custName" style="width: 200" /></td>
				<td>客户项目号：<input name="custInfo.custProject" style="width: 200" /></td>
				<td align="center"><a href="#" onclick="clearWorkrecordForm();" class="easyui-linkbutton" iconCls="icon-search">清空</a></td>
			</tr>
			<tr>
				<td>工程师：<input id="mUserCombo" name="mUser.name" style="width: 200" /></td>
				<td align="center"><a href="#" onclick="searchWorkrecord();" class="easyui-linkbutton" iconCls="icon-search">查询</a></td>
			</tr>
		</table>
	</form>
	<div style="padding: 10" id="tabdiv">
		<table id="workrecordTable"></table>
	</div>
	<script type="text/javascript">
		jQuery(function($) {
			$('#workrecordTable').datagrid({
				title : '工作记录列表', //标题
				method : 'post',
				iconCls : 'icon-edit', //图标
				singleSelect : false, //多选
				//height : 360, //高度
				fitColumns : true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
				striped : true, //奇偶行颜色不同
				collapsible : true,//可折叠
				url : "workrecord/queryList", //数据来源
				sortName : 'workrecord.id', //排序的列
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
					field : 'custInfo.custName',
					title : '客户名称',
					width : 20,
					sortable : true,
					formatter : function(value, row, index) {
						return row.custInfo.custName;
					}
				}, {
					field : 'custInfo.custProject',
					title : '客户项目号',
					width : 20,
					sortable : true,
					formatter : function(value, row, index) {
						return row.custInfo.custProject;
					}
				}, {
					field : 'workDate',
					title : '工作日期',
					width : 20,
					sortable : true,
				}, {
					field : 'workStart',
					title : '开始时间',
					width : 20,
				}, {
					field : 'workEnd',
					title : '结束时间',
					width : 20,
				}, {
					field : 'totalHours',
					title : '总小时数',
					width : 20,
					sortable : true,
				}, {
					field : 'mUser_id',
					title : '工程师',
					width : 30,
					sortable : true,
					formatter : function(value, row, index) {
						return row.muser.name;
					}
				}, {
					field : 'custInfo.custContactor',
					title : '客户联系人',
					width : 20,
					sortable : true,
					formatter : function(value, row, index) {
						return row.custInfo.custContactor;
					}
				}, {
					field : 'custInfo.custInfo',
					title : '客户信息',
					width : 20,
					sortable : true,
					formatter : function(value, row, index) {
						return row.custInfo.custInfo;
					}
				}, {
					field : 'workContent',
					title : '工作内容',
					width : 50,
				}, {
					field : 'comments',
					title : '备注',
					width : 50,
				} ] ],
				toolbar : [ {
					text : '新增',
					iconCls : 'icon-add',
					handler : function() {
						addWorkrecordRow();
					}
				}, '-', {
					text : '更新',
					iconCls : 'icon-edit',
					handler : function() {
						updateWorkrecordRow();
					}
				}, '-', {
					text : '删除',
					iconCls : 'icon-remove',
					handler : function() {
						deleteWorkrecordRow();
					}
				}, '-' ],
				onLoadSuccess : function() {
					$('#workrecordTable').datagrid('clearSelections'); 
				}
			});

			$('#mUserCombo').combogrid({
				panelWidth: 300,
				idField : 'name', //ID字段
				textField : 'mail', //显示的字段
				url : "user/queryAll",
				fitColumns : true,
				striped : true,  //奇偶行颜色不同
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
		});
		//新增
		function addWorkrecordRow() {
			showWindow({
				title : '增加用户信息',
				href : 'workrecord/popWindow',
				width : 350,
				height : 450,
				onLoad : function() {
					$('#workrecordForm').form('clear');
					$('#uuid').val('0');
					$('#custInfo_custProject').select();
				}
			});
		}
		//更新
		function updateWorkrecordRow() {
			var rows = $('#workrecordTable').datagrid('getSelections');
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
				href : 'workrecord/popWindow',
				width : 350,
				height : 450,
				onLoad : function() {
					$("#workrecordForm").form('load', rows[0]);
					$('#custInfo_custProject').val(rows[0]['custInfo']['custProject']);
					$('#custInfo_custName').val(rows[0]['custInfo']['custName']);
					$('#custInfo_custContactor').val(rows[0]['custInfo']['custContactor']);
					$('#custInfo_custInfo').val(rows[0]['custInfo']['custInfo']);
					$("#mOttype").combobox('setValue',rows[0]['mottype']['id']);
					$("#mUser").combobox('setValue',rows[0]['muser']['id']);
					$('#custInfo_custProject').select();
				}
			});
		}
		//删除
		function deleteWorkrecordRow() {
			$.messager.confirm('提示', '确定要删除吗?', function(result) {
				if (result) {
					var rows = $('#workrecordTable').datagrid('getSelections');
					var ps = "";
					$.each(rows, function(i, n) {
						if (i == 0)
							ps += "?id=" + n.id;
						else
							ps += "&id=" + n.id;
					});
					$.post('workrecord/delete' + ps, function(data) {
						$('#workrecordTable').datagrid('reload');
						$.messager.alert('提示', data.mes, 'info');
					});
				}
			});
		}
		//表格查询
		function searchWorkrecord() {
			var params = $('#workrecordTable').datagrid('options').queryParams; //先取得 datagrid 的查询参数
			var fields = $('#workrecordQueryForm').serializeArray(); //自动序列化表单元素为JSON对象
			$.each(fields, function(i, field) {
				params[field.name] = field.value; //设置查询参数
			});
			$('#workrecordTable').datagrid('reload'); //设置好查询参数 reload 一下就可以了
		}
		//清空查询条件
		function clearWorkrecordForm() {
			$('#workrecordQueryForm').form('clear');
			searchWorkrecord();
		}
	</script>
</body>
</html>
