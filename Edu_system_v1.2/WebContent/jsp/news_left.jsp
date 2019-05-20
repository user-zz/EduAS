<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>

<style type="text/css">
	a{
			text-decoration:none;
			color:#4169E1;
	}
	.div1{
		background-color:#4169E1;
	}
</style>
</head>
<body bgcolor="#DCDCDC"> <!-- 取消横向滚动条，保留垂直滚动条 style="overflow:-Scroll;overflow-x:hidden" -->

	<%
		String username =(String) session.getAttribute("username");
	%>
	
	<div>
		<span><i><font size="1">&nbsp;&nbsp;亲爱的，<%=username %></font></i></span><br />
		<span><i><font size="1">&nbsp;&nbsp;教学辅助系统欢迎您</font></i></span>
	</div>
	<hr align="left" width="90%" />
	<div class="div1">
		<font>&nbsp;&nbsp;相关操作</font>	
	</div>
	<div>
		<p><a href="publish.jsp" target="mainFrame"><font  size="1">&nbsp;发布论题</font></a></p>
		<p><a href="searchNews.jsp" target="mainFrame"><font size="1">&nbsp;搜索论题</font></a></p>
	</div>
</body>
</html>