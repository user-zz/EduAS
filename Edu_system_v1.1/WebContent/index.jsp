<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/edua-logo.ico" />
	<title>首页</title>
</head>

<frameset rows="200,*" cols="*" frameborder="no" border="0" framespacing="0">
        <frame src="header.jsp" name="topFrame" scrolling="auto" noresize="noresize" id="topFrame"/>
      <frameset cols="220,*" frameborder="no" border="0" framespacing="0">
        <frame src="index_left.jsp" name="leftFrame" noresize="noresize" id="leftFrame"/>
        <frame src="index_main.jsp" name="mainFrame" scrolling="auto" id="mainFrame"/>
      </frameset>
</frameset> 

<body>
</body>
</html>