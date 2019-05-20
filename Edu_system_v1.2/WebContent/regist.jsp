<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/edua-logo.ico" />
		<title>注册新用户</title>
		<jsp:include page="header.jsp"></jsp:include>
		<link rel="stylesheet" type="text/css" href="css/regist_styles.css" >
		<script type="text/javascript" src="js/regist_js.js"></script>
	</head>
	<body>
		<div align="center" >
			<form action="Registe" method="post"  name="regist_form"  onSubmit="return isCheckNull();" >
				<div align="center" class="mui-input-row">
				<div align="center">
					<span><font size="4px" color="#4169E1">请填写注册信息</font></span>
				</div>
				<br/>
				<table bgcolor="#FFFFFF" border="0">
					<tr>
						<td align="right">用户名：</td>
						<td ><input type="text"  class="txt1" name="username"  size="30" maxlength="12"  id="username" onblur="check_username()" /></td>
						<td><font size="1px" color="#D3D3D3">请设置6~12位的用户名，且必须以大写字母开始</font></td>
					</tr>
					<tr>
					<tr>
						<td align="right">绑定学工号：</td>
						<td ><input type="text"  class="txt1" name="uno"  size="30"  id="uno"  onblur="check_uno()"></td>
						<td><font size="1px" color="#D3D3D3">请输入学号或工号</font></td>
					</tr>
					<tr>
						<td align="right">密码：</td>
						<td><input type="password"  class="txt1" size="30" maxlength="16" id="pwd1" /></td>
						<td><font size="1px" color="#D3D3D3">请设置6~16位长度的密码</font></td>
					</tr>
					<tr>
						<td align="right">确认密码：</td>
						<td><input type="password"  class="txt1" size="30" maxlength="16" name="pwd" id="pwd2" onblur="check_pwd()" /></td>
						<td><font size="1px" color="#D3D3D3">请输入与上面一致的密码</font></td>
					</tr>
					<tr>
						<td align="right">绑定手机号码：</td>
						<td><input type="text"  class="txt1" name="phonenum" id="phonenum" size="30"  /></td>
					</tr>
					<tr>
						<td align="right">验证码：</td>
						<td><input type="text" class="txt2" name="identity_num" />
								<input type="button" name="identity_num_btn" value="获取验证码"/>
						</td>
					</tr>
					<tr>
						<td align="right">您的身份是：</td>
						<td>
							<input type="radio" name="identity" value="2" checked/><font size="1px">学生&nbsp;</font>
							<input type="radio" name="identity" value="1" /><font size="1px">教师&nbsp;</font>
							<input type="hidden" name="identity" value="0" /><font size="1px" ></font>
						</td>
					</tr>
					</table>
				</div><br/>
				<div align="center">
						    <input type="submit" class="btn1_styles"  value="提交"/>&nbsp;&nbsp;
							<input type="reset" class="btn2_styles" value="取消"/>
				</div>
			</form>
		</div>
		<div>
			<jsp:include page="footer.jsp"></jsp:include>
		</div>
	</body>
</html>