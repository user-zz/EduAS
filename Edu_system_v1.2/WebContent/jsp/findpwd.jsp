<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>找回密码 | 教学辅助系统</title>

<jsp:include page="header2.jsp"></jsp:include>
<script type="text/javascript" src="../js/findpwd_js.js"></script>

<style>

	.table_style{
		width: 50%; 
		max-width: 50%;
		margin-bottom: 40px;
		border-collapse:collapse;
	}
	.txt1{
			width:200px;
			height:20px;
	    }
	    .btn1{
		width:80px;
		height:30px;
		color:white;
		background-color:#33CC66;
		padding:0px;
		border:0px;
	}
	.btn2{
		width:80px;
		height:30px;
		color:white;
		background-color:#D5E449;
		padding:0px;
		border:0px;
	}

</style>

</head>
<body>
<div align="center">
	<div>
		<h4><font color="#0000FF">请通过手机号码找回密码</font></h4>
		
	</div>

	<form action="../UpdataPwd" method="post"  onSubmit="return isCheckNull();">
		<table border="0">
		<tr>
			<th align="center" colspan="3"></th>
		</tr>
		
		<tr>
			<td align="left">用户名</td>
			<td ><input type="text"  class="txt1" name="username"  size="50" maxlength="12"  id="username" onfocus="fun1('username_msg')" onblur="check_username()" /></td>
			<td><font size="1px" color="#D3D3D3" id="username_msg">*请设置6~12位的用户名，且必须以大写字母开始</font></td>
		</tr>
		<tr>
			<td align="left">请输入您的新密码</td>
			<td><input type="password"  class="txt1" size="50" maxlength="16" id="pwd1"  onfocus="fun1('pwd1_msg')" onblur="fun2('pwd1_msg')"></td>
			<td><font size="1px" color="#D3D3D3" id="pwd1_msg">*请设置6~16位长度的密码</font></td>
		</tr>
		<tr>
			<td align="left">请再次确认您的密码</td>
			<td><input type="password"  class="txt1" size="50" maxlength="16" name="pwd" id="pwd2" onfocus="fun1('pwd2_msg')" onblur="check_pwd()" /></td>
			<td><font size="1px" color="#D3D3D3" id="pwd2_msg">*请输入与上面一致的密码</font></td>
		</tr>
		<tr>
			<td align="left">请输入绑定的手机号码</td>
			<td colspan="2"><input type="text"  class="txt1" name="phonenum" id="phonenum" size="50"  /></td>
 		</tr>
		<tr></tr>
		<tr>
			<td align="center" colspan="3">
				<input type="submit"  class="btn1" value="确认提交" />&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="reset" class="btn1"  value="重新填写" />
			</td>
		</tr>
		</table>
	</form>
</div>
</body>
</html>