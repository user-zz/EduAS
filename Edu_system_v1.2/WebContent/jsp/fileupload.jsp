<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 <%
    String username = (String)session.getAttribute("username");
	if(username == null){
		out.print("<h4>请您先登录,正在为您返回登录页面，请稍后...</h4><br/>");
		response.setHeader("refresh", "3,../login.jsp");
	}
    %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>选择文件 | 教学辅助系统</title>
</head>

<body background="#808080">
<div>
	<form action="<%=request.getContextPath()%>/FileUpload?filetype_img=F" method="post" enctype="multipart/form-data">
		<p><font size="2" color="#C71585">请选择您要上传的文件</font></p>
	   <span><input type="file" size="30" name="upfile" /></span>
	   <br />
	    <input  type="submit" value="上传" />
	</form>
</div>
<br/>
<div>
 	<c:if test="${not empty errorMessage}">
    	<input type="text" id="errorMessage" value="${errorMessage}" style="color:red;" disabled="disabled">
	</c:if>
</div>
</body>
</html>