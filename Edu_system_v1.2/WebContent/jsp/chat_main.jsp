<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		
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
<title></title>

</head>

<frameset rows="50%,*" cols="90%" frameborder="no"  border="0" framespacing="0">
         <frame src="<%=basePath %>jsp/chat_msgList.jsp" name="main_upFrame"  scrolling="auto" id="main_upFrame"/>
      <frameset cols="90%,*" frameborder="no" border="0" framespacing="0">
        <frame src="<%=basePath %>jsp/chat_sendMsg.jsp" name="main_downFrame"  scrolling="no" id="main_downFrame"/>
      </frameset>
</frameset> 

<body >
</body>
</html>