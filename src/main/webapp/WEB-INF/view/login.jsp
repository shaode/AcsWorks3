<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/Include.jspf"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>AcsWorks Login</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript">
	window.onload = function() {
		//alert('sucheng');
		document.loginData.j_username.focus();
	}
</script>
<style type="text/css">
#login-header {
	height: 100px;
}

#title {
	display: block;
	*display: inline-block;
}

}
.tit_1 {
	font: normal 22px/40px 'Microsoft YaHei';
}

.tit_1 img,.tit_2 img,.tit_3 img {
	margin: 0 6px -10px 0;
}

#login {
	border: 1px solid #FFFFFF;
	padding: 7px 9px 10px;
	float: right;
	margin-right: 52px;
	margin-top: 60px;
}

#login-form {
	text-align: right;
}

#login-footer {
	height: 70px;
	padding: 0px 0px 6px 15px;
	text-align: left;
	font-size: 10px;
	white-space: nowrap;
}

#login-footer .info p {
	margin: 1px 0px;
}
</style>
</head>
<body>
	<div id="page" class="easyui-layout" fit="true">
		<div id="login-header" region="north">
			<a href="/login" title="登录">您还未登录 !</a> <a href="/login">登录</a> <a href="/signup" class="nobg">注册</a>
			<div id="logo">
				<img src="<%=request.getContextPath()%>/img/Branding_Login_WeblogicConsole.gif" alt="Oracle WebLogic Server Administration Console ">
			</div>
		</div>
		<div id="content" region="center">
			<div id="login">
				<div id="title">
					<h1 class="tit_1">
						<img src="<%=request.getContextPath()%>/img/tit_login.gif" title="用户登录" alt="用户登录" />用户登录
					</h1>
				</div>
				<div id="login-form">
					<form id="loginData" name="loginData" method="post" action="<%=request.getContextPath()%>/login">
						<table>
							<tr>
								<td>用户名：</td>
								<td><input type="text" name="name" id="j_username" validType="length[3,30]" class="easyui-validatebox" required="true" /></td>
							</tr>
							<tr>
								<td>密码：</td>
								<td><input type="password" name="password" id="j_password" validType="length[3,30]" class="easyui-validatebox" required="true" /></td>
							</tr>
						</table>
						<input type="submit" value="登 陆" style="margin: 10px 0;" /> <input type="reset" value="重 置" style="margin: 10px 0;" />
					</form>
				</div>
				<div id="info"></div>
			</div>
		</div>
		<div id="login-footer" region="south">
			<div class="info">
				<p id="footerVersion">WebLogic Server Version: 10.3.6.0</p>
				<p id="copyright">Copyright &copy; 1996, 2011, Oracle and/or its affiliates. All rights reserved.</p>
				<p id="trademark">Oracle is a registered trademark of Oracle Corporation and/or its affiliates. Other names may be trademarks of their respective owners.</p>
			</div>
		</div>
	</div>
</body>
</html>
