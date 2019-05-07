<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    
    String username = (String)session.getAttribute("username");
	if(username == null){
		out.print("<h3>请您先登录,正在为您返回登录页面，请稍后...</h3><br/>");
		response.setHeader("refresh", "3,../login.jsp");
	}
    
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/edua-logo.ico" />
<title>热点资讯</title>
</head>
<frameset rows="200,*" cols="*" frameborder="no" border="0" framespacing="0">
         <frame src="header2.jsp" name="topFrame" scrolling="auto" noresize="noresize" id="topFrame"/>
      <frameset cols="220,*" frameborder="no" border="0" framespacing="0">
        <frame src="news_left.jsp" name="leftFrame" noresize="noresize" scrolling="no"  id="leftFrame"/>
        <frame src="news_main.jsp" name="mainFrame" scrolling="auto" id="mainFrame"/>
      </frameset>
</frameset> 
			<!-- 热点资讯  -->
<body>

</body>
</html>