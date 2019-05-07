<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
    <%
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		response.setHeader("refresh", "5");

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
<title></title>
<style type="text/css">
        *
        {
            margin: 0;
            padding: 0;
        }
        body
        {
            font-size: 15px;
            padding: 00px;
        }
        .menu
        {
            width: 500px;
            border-bottom: solid 1px gray;
        }
        .menu h3
        {
            border: solid 1px gray;
            height: 30px;
            line-height: 30px;
            padding-left: 10px;
            padding:0 5px;
            border-bottom: none;
            cursor: pointer;
           
        }
        .menu p
        {
            border-left: solid 1px gray;
            border-right: solid 1px gray;
            padding: 20px 0;
            padding-left: 5px;
        }
        .changecolor{background-color:red;}
        .div1{
		background:#1E90FF;
		color:#FFFFFF;

	}
    </style>
    <script src="js/jquery-1.4.2.min.js" type="text/javascript"></script>
    <script type="text/javascript">
          $(function () {
            $(".menu p:not(:first)").hide();
            $(".menu h3").css("background", "#ccc");       
            $(".menu h3").hover(function () { $(this).css("background-color", "gray").siblings("h3").css
			("background-color", "#ccc");});
            $(".menu h3").mouseleave(function () { 
               $(".menu h3").css("background", "#ccc");}); //离开时将其变为原来颜色
            var index = $(".menu h3").index(this);
            $(".menu h3").click(function () { $(this).next("p").slideToggle().siblings("p").slideUp(); });
        });
    </script>

</head>
<body bgcolor="#DCDCDC">
<div>
	<div >
			<div align="center"  class="div1" >
				<h3>互动大厅</h3>
			</div>
			<div>
				<h4>
		            欢迎<%=(String)session.getAttribute("username") %>访问!
		        </h4>
		    	<!--       
		        <br/>&nbsp;&nbsp;当前在线人数为<%=application.getAttribute("peopleOnline")%>人</h4>
				 -->  
			</div>
	</div>
	<div class="menu">
	       <h3>
	           在线的老师</h3>
	      	<p>
	        	<%
	        	List<Object> users = (List<Object>) application.getAttribute("users");
	        	boolean T_flag = true;
	        	
	        	for (int i = 0; i < users.size(); i++) {
	                String username = (String) users.get(i);
	                String userid = (String)users.get(i+1);
	                
	                if(userid.equals("1")){
	                	 out.print("&nbsp;"+username+"<br/>");
	                	 i++;
	                	 T_flag = false;
	                }else {
	                	out.print("");
	                	i++;
	                } 
	            }
	        	
	        	if(T_flag){
	        		out.print("&nbsp;暂无在线教师<br/>");
	        	}
	        	%>
			</p>
	        <h3>
	            在线的学生</h3>
	        <p>
			  <%
			  boolean S_flag = true;
			  for (int i = 0; i < users.size(); i++) {
	                String username = (String) users.get(i);
	                String userid = (String)users.get(i+1);
	                if(userid.equals("2")){
	                	 out.print("&nbsp;"+username+"<br/>");
	                	 i++;
	                	 S_flag = false;
	                }else {
	                	out.print("");
	                	i++;
	                }  
	            }
			  
			  if(S_flag){
				  out.print("&nbsp;暂无在线学生<br/>");
			  }
			  %>
	         </p>
	         <h3>
	            系统管理员</h3>
	        <p>
			  <%
			  boolean M_flag = true;
			  for (int i = 0; i < users.size(); i++) {
	                String username = (String) users.get(i);
	                String userid = (String)users.get(i+1);
	                if(userid.equals("0")){
	                	 out.print("&nbsp;"+username+"<br/>");
	                	 i++;
	                	 M_flag = false;
	                }else {
	                	out.print("");
	                	i++;
	                } 
	            }
			  
			  if(M_flag){
				  out.print("&nbsp;暂无在线管理员<br/>");
			  }
			  %>
	         </p>
	 </div>
</div>
</body>
</html>