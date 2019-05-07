<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/edua-logo.ico" />
	<title>欢迎登录&nbsp;|&nbsp;教学辅助系统</title>
	<link rel="stylesheet" href="css/login_styles.css" type="text/css" />
	<script type="text/javascript" src="js/login_js.js"></script>
</head>
<body>
	<br/>
	<div align="center">
		<font size="4px" color="#0066FF">用户登录</font>
	</div><br/>
		<form action="../Login" method="post" target="mainFrame" onSubmit="return isCheckUserNull();">
			<div align="center">
						<table bgcolor="#FFFFFF" border="0">
							<tr>
								<td align="center"><img alt="账号：" src="../images/username.ico"></td>
								<td ><input type="text" class="text1" name="username" id="username" value="用户名或学工号"  onfocus="javascript:if(this.value=='用户名或学工号')this.value='';this.type='text'"/></td>
							</tr>
							<tr>
								<td align="center"><img alt="密码：" src="../images/password.ico"></td>
								<td><input type="password" class="text1"  id="pwd"  name="pwd" /></td>
								<td><a class="find_pwd" href="jsp/findpwd.jsp"><font size="1px">忘记密码？</font></a></td>
							</tr>
							<tr>
								<td colspan="2" align="center"><input type="radio" name="ID" value="2" checked /><font size="1" >学生</font>
								&nbsp;&nbsp;<input type="radio" name="ID" value="1" /><font size="1" >教师</font>
								&nbsp;&nbsp;<input type="radio" name="ID" value="0" /><font size="1" >管理员</font></td>
							</tr>
					</table>
			</div><br/>
			<div align="center">	
					&nbsp;<span><input type=submit class="btn1" name="submit" value="登录" /></span>&nbsp;&nbsp;
					<span><input type=reset class="btn2" name="reset" value="清空" /></span>
		    </div>
		</form>
</body>
</html>