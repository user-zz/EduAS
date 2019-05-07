<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String username = (String)session.getAttribute("username");
	if(username == null){
		out.print("请您先登录,正在为您返回登录页面，请稍后...");
		response.setHeader("refresh", "3,../login.jsp");
	}

%>  
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<base href="<%=basePath%>">
	<meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/edua-logo.ico" />
	
<title>师生互动中心 | 教学管理系统</title>
</head>

<frameset rows="200,*" cols="*" frameborder="no" border="0" framespacing="0">
         <frame src="<%=basePath %>jsp/header2.jsp" name="topFrame" scrolling="no" noresize="noresize" id="topFrame"/>
      <frameset cols="220,*" frameborder="no" border="0" framespacing="0" >
        <frame src="<%=basePath %>jsp/chat_left.jsp" name="leftFrame" noresize="noresize" scrolling="no" frameborder="0"   id="leftFrame"/>
        <frame src="<%=basePath %>jsp/chat_main.jsp" name="mainFrame" scrolling="auto" id="mainFrame"/>
      </frameset>
</frameset> 
   
<body>

</body>
</html>