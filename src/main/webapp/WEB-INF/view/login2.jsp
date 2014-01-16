<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
html {
	background-color: #185E87;
}

#page {
	background: #FFFFFF url(/AcsWorks/img/Login_GC_LoginPage_Bg.gif)
		repeat-x top left;
	min-width: 920px;
	height: 550px;
}

#content {
	overflow: hidden;
	width: 100%;
}

#login-header {
	height: 89px;
}

#login-header img {
	margin-top: 20px;
	margin-left: 25px;
}

#sidebar {
	float: left;
	width: 527px;
}

#login {
	background: #FFFFFF,
	border: 1px solid #316D91;
	padding: 7px 9px 10px;
	float: right;
	width: 320px;
	margin-right: 52px;
	margin-top: 60px;
}

.login-footer .info p {
	margin: 1px 0px;
}

.login-footer {
	padding: 0px 0px 6px 15px;
	text-align: left;
	color: #B2CBDB;
	background-color: #185E87;
	font-size: 10px;
	white-space: nowrap;
}

.loginFailed {
	color: #AD0E25;
}
</style>
</head>
<body>
	<div id="page">
		<div id="login-header">
			<a href="/login" title="登录">您还未登录 !</a> <a href="/login">登录</a> <a href="/signup" class="nobg">注册</a>
			<div id="logo">
				<img src="<%=request.getContextPath()%>/img/Branding_Login_WeblogicConsole.gif" alt="Oracle WebLogic Server Administration Console ">
			</div>
		</div>
		<div id="content">
			<div id="sidebar">
				<img src="<%=request.getContextPath()%>/img/Login_11gLogo1.gif" alt="">
			</div>
			<div id="login">
				<div id="title">
					<img src="<%=request.getContextPath()%>/img/tit_login.gif" title="用户登录" alt="用户登录" />用户登录
				</div>
				<div id="login-form">
					<form id="loginData" name="loginData" method="post" action="<%=request.getContextPath()%>/login">
						<table>
							<tr>
								<td>用户名：</td>
								<td><input class="textinput" type="text" autocomplete="on" name="name" id="j_username" /></td>
							</tr>
							<tr>
								<td>密码：</td>
								<td><input class="textinput" type="password" autocomplete="on" name="password" id="j_password" /></td>
							</tr>
						</table>
						<input type="submit" value="登陆"> <input type="reset" value="重置" />
					</form>
				</div>
				<div id="info"></div>
			</div>
		</div>
		<div class="login-footer">
			<div class="info">
				<p id="footerVersion">WebLogic Server Version: 10.3.6.0</p>
				<p id="copyright">Copyright &copy; 1996, 2011, Oracle and/or its affiliates. All rights reserved.</p>
				<p id="trademark">Oracle is a registered trademark of Oracle Corporation and/or its affiliates. Other names may be trademarks of their respective owners.</p>
			</div>
		</div>

	</div>
</body>
</html>
