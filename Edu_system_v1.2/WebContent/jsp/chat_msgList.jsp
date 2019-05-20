<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
    
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

	response.setHeader("refresh", "2");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<base href="<%=basePath%>">
<title></title>

<script type="text/javascript">

	function back(){
		history.go(-1);	
	}
	
	
</script>

<style type="text/css">
	.txta_styles{
		font-size:10px;
	}
	
	a{
		text-decoration:none;
	}
	.div1{
		background:#D3D3D3;
		width:100px; 
		position:fixed; 

	}
	
</style>

</head>
<body style="overflow-x:hidden;">
	<div class="div1">
	<!--
		<a href="./index.jsp" target="_top"><font color="#4169E1">首页&gt;&gt;</font></a>
	-->
		<a href="" onclick="back()" ><font size="2px" color="#4169E1"><b>&lt;&lt;上一页</b></font></a>	
	</div>
	<br/>
	<div align="left" >
		<!-- 
		<textarea  cols="110" rows="10" name="show_textarea"  class="txta_styles" readonly>
		 -->
		
		<%
	
		List<Object> msgs = (List<Object>) application.getAttribute("msgs");
        List<Object> to_sb = (List<Object>) application.getAttribute("private");
        List<Object> from_sb = (List<Object>) application.getAttribute("S_private");
        String user=(String)session.getAttribute("user");

        try{
            for (int i = 0; i < msgs.size(); i++) {
                String t1 = (String) msgs.get(i);
                String t2 = (String) to_sb.get(i);
                String t3 = (String) from_sb.get(i);

                if(t2.equals(user) || t2.equals("所有人") || t3.equals(user))
                    out.print(t1);
            }
        }catch(Exception e){}
        
        %>

	</div>
</body>
</html>