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
<title>发布论题</title>
</head>
<body>
<h3>发布论题</h3>
<div>
	<form action="../SendTh" method="post">
		<div>
			<span><font>标题：</font></span><span><input type="text" size="40px" name="Tname"  /></span>
			<span><font>类型：</font></span>
			<span>
				<select>
					<option value="公共基础">公共基础</option>
					<option value="语言文学">语言文学</option>
					<option value="计算机、互联网" >计算机、互联网</option>
					<option value="金融学" >金融学</option>
					<option value="管理学" >管理学</option>
					<option value="法学" >法学</option>
					<option value="理学" >理学</option>
					<option value="大气科学" >大气科学</option>
					<option value="其他" >其他</option>
				</select>	
			</span>
		</div>
		<div>
			<span><font>主题简述：</font></span><br/>
			<span><textarea rows="20px" cols="80px" name="Thtxt" ></textarea></span>
		</div>
		<div>
			<input type="submit" value="发布" />&nbsp;&nbsp;&nbsp;
			<input type="reset" value="取消" />
		</div>
	</form>
</div>
</body>
</html>