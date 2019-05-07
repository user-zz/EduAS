<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<style type="text/css">
	a.up_down{
		color:#0000FF;
		text-decoration:none;
	}
</style>
</head>

<body bgcolor="#DCDCDC">
	<div>
		<div>
			<font color="#4169E1"  size="2">亲爱的<%=(String)session.getAttribute("username") %>,</font>
			<h5>&nbsp;&nbsp;资料共享平台欢迎您</h5>
			<hr />
		</div>
		<div>
			<h5>相关操作</h5>
			<p>	
				<a class="up_down" href="searchMtrl.jsp" target="mainFrame" ><font size="1">查找资料</font></a>
			</p>
			<p>
				<a class="up_down" href="fileupload.jsp" target="mainFrame" ><font size="1">上传资料</font></a>
			</p>
		</div>
	</div>
</body>
</html>