<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/Include.jspf"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>AcsWorks</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript">
	//ajax不缓存
	jQuery.ajaxSetup({
		cache : false
	});
	
	jQuery(function($) {
		$('#systabs').tabs({
			//onLoad:function(panel){
			//	console.log("1111");
			//}
		});
		$('#systabs').tabs({
			onSelect:function(title){
	        	//alert(title+' is selected');
	    	}
		});
		$('#systabs').tabs({
			onClose:function(title){
				//$('#HandleDialog').dialog('destroy');
				//$.messager.alert('Warning','The warning message');
			}
		});
	});

	function openTab(title, href) {
		if ($('#systabs').tabs('exists', title)) {
			$('#systabs').tabs('select', title);
		} else {
			$('#systabs').tabs('add', {
				title : title,
				href : href,
				border: false,
				fit: true,
				closable : true
			});
		}
		return true;
	}
	//弹出窗口
	function showWindow(options) {
		jQuery("#MyPopWindow").window(options);
	}
	//关闭弹出窗口
	function closeWindow() {
		//$("#MyPopWindow").window('destroy');
		$("#MyPopWindow").window('close');
	}
</script>
</head>
<body >
<div id="mainBody" class="easyui-layout" fit="true" >
	<div region="north" >
		<div style="float:right;margin:10px 5px 0 0">
			<input class="easyui-searchbox" prompt="Please input value" style="width:300px">
		</div>
		<div >
			<a href="#" class="easyui-linkbutton" data-options="plain:true">欢迎:${userSessionInfo.name}</a>
			<a href="#" class="easyui-splitbutton" data-options="menu:'#mm1',iconCls:'icon-edit'">Edit</a>
			<a href="#" class="easyui-menubutton" data-options="menu:'#mm2',iconCls:'icon-help'">Help</a>
			<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-save',toggle:true">Save</a>
			<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-print'">Print</a>
		</div>
		<div id="mm1" style="width:150px;">
			<div data-options="iconCls:'icon-undo'">Undo</div>
			<div data-options="iconCls:'icon-redo',disabled:true">Redo</div>
			<div class="menu-sep"></div>
			<div>Cut</div>
			<div>Copy</div>
			<div>Paste</div>
			<div class="menu-sep"></div>
			<div>
				<span>Toolbar</span>
				<div style="width:150px;">
					<div>Address</div>
					<div>Link</div>
					<div>Navigation Toolbar</div>
					<div>Bookmark Toolbar</div>
					<div class="menu-sep"></div>
					<div>New Toolbar...</div>
				</div>
			</div>
			<div data-options="iconCls:'icon-remove'">Delete</div>
			<div>Select All</div>
		</div>
		<div id="mm2" style="width:100px;">
			<div><a href="${root}/logout">退出</a></div>
			<div>Help</div>
			<div>Update</div>
			<span>About</span>
            <div class="menu-content" style="padding:10px;text-align:left">
                <img src="http://www.jeasyui.com/images/logo1.png" style="width:150px;height:50px">
                <p style="font-size:14px;color:#444">Try jQuery EasyUI to build your modern, interactive, javascript applications.</p>
            </div>
		</div>
    </div>
	<div region="west" title="功能选项" split="true" border="false" style="width:205px;">
		<div id="menuDiv" class="easyui-accordion" border="false" fit="true">
			<div title="工作管理" style="padding:10px">
				<ul>
					<li id="rightLi${child.id}" style="cursor: pointer;" onclick="openTab('工作记录','${root}/workrecord/list')">工作记录</li>
					<li id="rightLi${child.id}" style="cursor: pointer;" onclick="openTab('用户管理','${root}/user/list')">用户管理</li>
					<li id="rightLi${child.id}" style="cursor: pointer;" onclick="openTab('部门管理','${root}/dept/list')">部门管理</li>
					<li id="rightLi${child.id}" style="cursor: pointer;" onclick="openTab('工作记录查询','${root}/myquery/workrecord')">工作记录查询</li>
					<li id="rightLi${child.id}" style="cursor: pointer;" onclick="openTab('上传项目','${root}/upload/main')">上传项目</li>
				</ul>
			</div>
			<div title="系统管理" style="padding:10px">
				<p><a href="#">密码修改</a></p>
				<p><a href="#">关于</a></p>
				<p><a href="${root}/logout">退出</a></p>
			</div>
		</div>
	</div>
	<div region="center" title="工作面板" border="true">
		<div id="systabs" class="easyui-tabs" border="false" fit="true">
			<div title="Welcome" closable="false" style="padding:10px">
				<p>welcome you.</p>
			</div>
		</div>
	</div>
	<div region="south" border="false" style="height:230px;padding-top:5px">
		<div class="easyui-layout" fit="true" style="height:210px;margin-top1:10px">
			<div region="west" split="true" style="width:205px;">
				<div class="easyui-calendar" fit="true"></div>
			</div>
			<div region="east" style="width:300px;padding:10px;position:relative">
				<div class="easyui-progressbar" value="50" style="width:250px;margin:10px 0"></div>
				<div style="margin:20px 0;">
					<input class="easyui-slider" style="width:250px" value="20" data-options="showTip:true,rule: [0,'|',25,'|',50,'|',75,'|',100]">
				</div>
				<div style="margin-top:50px">
					<a href="#" class="easyui-tooltip" title="This is a tooltip message">Hover me</a>
				</div>
			</div>
			<div region="center" border="false" style="padding:0 5px">
				<div class="easyui-panel" fit="true" style="padding:10px;">
					<div style="font-weight:bold;font-size:16px;margin-bottom:10px">Form Fields</div>
					<table>
						<tr>
							<td>ValidateBox:</td>
							<td><input class="easyui-validatebox" required></td>
						</tr>
						<tr>
							<td>CombobBox:</td>
							<td>
								<select class="easyui-combobox" name="language" style="width:200px">
									<option value="en" selected="selected">English</option>
									<option value="cn" selected="selected">Chinese</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>TimeSpinner:</td>
							<td><input class="easyui-timespinner" showSeconds="true" style="width:200px"></td>
						</tr>
						<tr>
							<td>DateTimeBox:</td>
							<td><input class="easyui-datetimebox" showSeconds="true" style="width:200px">	</td>
						</tr>
					</table>
					<div style="margin:10px 0;">
						<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">Add</a>
						<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">Remove</a>
						<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save',toggle:true">Save</a>
						<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cut',disabled:true">Cut</a>
						<a href="#" class="easyui-linkbutton">Text Button</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="MyPopWindow" modal="true" shadow="false" minimizable="false" cache="false" maximizable="false" collapsible="false" resizable="false" style="margin:0px;padding:0px;overflow:auto;"></div>
</div>
</body>
</html>

