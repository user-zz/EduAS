<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>">

		<title>My JSP 'index.jsp' starting page</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<title>Insert title here</title>


<style type="text/css">

	.div1{
		border-left:3px solid #C0C0C0;
		width:80%;
		height:100% ;
	}
	
</style>

<!-- 

<body bgcolor="#FAEBD7">
<div>
	<div class="div1">
		<h5><font>&nbsp;审核材料</font></h5>
	</div>
	<div>
		<p>这里将进行课题、文件、评论消息等材料的审查</p>
		<!-- 方法：给各材料加上通过标识符 " T "即可 
	
	</div>
</div>

</body>

 -->


</head>
<body>
<table>
    <tr>
    <td>帖子编号</td>
    <td>标题</td>
    <td>内容</td>
    <td>楼主</td>
  
    </tr>
    <tr>
    <td>%=Id %></td>
    <td>%=title %></td>
    <td>%=leaveBody %></td>
    <td>%=username%></td>
    </tr>
    </table>

    
    <hr/>
    <table>
    <tr>
    <td>回复编号</td>
    <td>内容</td>
    <td>回复人</td>
    <td>帖子编号</td>
   </tr>
   
   <tr>
   <td>%=i+1 %>楼</td>
   <td>%=replayBody %></td>
   <td>%=replayname %></td>
   <td>%=leaveId %></td>

   </tr>
   </table>
    <hr/>
   
   <form action="reply" method="post">
   
   <label>回复内容</label><br/>
   
   <textarea rows="5" cols="60" name="replayBody"></textarea><br/>
   <input  type="hidden"  value="%=leaveId%>"  name="leaveId"/> 
   <input  type="submit"  name="fabiao"  value="发表回复" />
   </form>
</body>
</html>