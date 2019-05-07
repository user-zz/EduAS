<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String username = (String)session.getAttribute("username");
	if(username == null){
		out.print("<h3>请您先登录,正在为您返回登录页面，请稍后...</h3><br/>");
		response.setHeader("refresh", "2,../login.jsp");
	}
    %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>

<style>
	.txt_style{
		width:150px;
		height:20px;
	}
	.div1{
		border-left:3px solid #C0C0C0;
		width:80%;
		height:100% ;
	}
</style>

</head>
<body>
	<div class="div1">
		<div align="center">
			<h5>---查找学习资料---</h5>
		</div><br/>
			<form action="../ListUploadFiles" method="post">
			<span>
				<font size="2">按上传者查找&nbsp;&nbsp;</font><input class="txt_style" type="text" name="fileusername" />
			</span>&nbsp;&nbsp;
			<span>
			<font size="2">按类别查找&nbsp;&nbsp;</font>
			<select name="filetype">
				    <option value="" selected>请选择</option>
				    <option value="" >其他</option>
				    <option value="公共基础">公共基础</option>
				    <option value="计算机、互联网">计算机、互联网</option>
				    <option value="语言文学">语言文学</option>
				    <option value="大气科学">大气科学</option>
				    <option value="金融会计">金融会计</option>
				    <option value="理学">理学</option>
				    <option value="哲学">哲学</option>
			</select></span>&nbsp;&nbsp;
			<span>
				<input type="submit" value="搜索"/>&nbsp;&nbsp;<input type="reset" value="取消"/>
			</span>
			</form>
		</div>
</body>
</html>