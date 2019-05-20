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
		}

</style>

</head>
<body bgcolor="#DCDCDC">

<div>
	<h4>欢迎访问教学辅助系统</h4>
	<hr>
	<p><a href="jsp/admin.jsp" target="_top" id="admin" onclick="isAdmin()"><font size="3" color="#4169E1">管理员平台</font></a></p>
	<p><a href="jsp/teacher.jsp" target="_top" id="teacher" onclick="isTeacher()"><font size="3" color="#4169E1">教师平台</font></a></p>
	<p><a href="jsp/student.jsp" target="_top" id="student" onclick="isStudent()"><font size="3" color="#4169E1">学生平台</font></a></p>
	<hr>
	<p><font size="2">若未登录，请先<a href="login.jsp" target="_top">登录</a></font></p>
</div>
<div>
	<input type="hidden" id="userid" value="<%=(String)session.getAttribute("userid")%> " />
</div>
</body>
<script type="text/javascript">
	function isAdmin(){
		var userid = document.getElementById("userid").value;
		if(userid != 0){
			alert("对不起，您还不是管理员！");
			event.preventDefault();
		}
	}
	
	function isTeacher(){
		var userid = document.getElementById("userid").value;
		if(userid != 1){
			alert("对不起，您还不是教师！");
			event.preventDefault();
		}
	}
	
	function isStudent(){
		var userid = document.getElementById("userid").value;
		if(userid != 2){
			alert("对不起，您的身份不是学生！");
			event.preventDefault();
		}
	}

</script>

</html>