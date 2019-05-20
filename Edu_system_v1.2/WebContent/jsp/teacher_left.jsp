<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>

<style>
	a{
			text-decoration:none;
			color:#000000;
	}
	
	.div1{
		background:#1E90FF;
		color:#FFFFFF;
	}
</style>

</head>
<body bgcolor="#D3D3D3">

	<%
		String username = (String)session.getAttribute("username");
	%>
	
	<div>
		<div>
			<h5>亲爱的<%=username %>,欢迎访问教学辅助系统</h5>
		</div>
		<div align="left"  class="div1" >
			<h3>相关操作</h3>
		</div>
		<div align="left">
		<p>
			<a href="publish.jsp" target="teacher_mainFrame">发布论题<br/></a>
		</p>
		<p>
			<a href="download_homework.jsp" target="teacher_mainFrame">收集作业<br/></a>
		</p>
		<p>
			<a href="chat.jsp" target="_top">互动聊天<br/></a>
		</p>
		<p>
			<a href="fileupload.jsp" target="teacher_mainFrame">上传资料<br/></a>
		</p>
		</div>
	</div>
</body>
</html>