<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件上传</title>
</head>
<body>
	<%--<form action="user/fileUpload" method="post" enctype="multipart/form-data">--%>
	<form action="upload/excel" method="post" enctype="multipart/form-data">
		yourfile: <input type="file" name="myfiles" /> 
		yourfile: <input type="file" name="myfiles" /> 
		<input type="submit" value="上传" />
	</form>
</body>
</html>
