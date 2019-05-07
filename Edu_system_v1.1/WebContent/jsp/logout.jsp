<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>退出登录</title>
</head>
<body>
	 <%
        //从applicaton作用域中取出用户列表  
        List<Object> users = (List<Object>)application.getAttribute("users");
	 	
        String username = (String) session.getAttribute("username");
       
        int from_index = users.indexOf(username);
        int to_index = from_index+2;
        /*
        //从该用户列表中移除该用户  
        users.remove(username);
        users.remove(index+1);
        */
       users.subList(from_index, to_index).clear(); //从users集合中删除当前登录的用户数据
        
        application.setAttribute("users", users);
        //注销该用户的会话
        session.invalidate();
        
        /*
        //更新在线人数
        Integer count = (Integer) application.getAttribute("peopleOnline");
        application.setAttribute("peopleOnline", count-1);
        */
        response.sendRedirect("../index.jsp");
    %>
</body>
</html>