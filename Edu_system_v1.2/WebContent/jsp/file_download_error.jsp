<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>下载错误页面</title>
</head>
<body>
 
<h3>下载失败：</h3>
<c:if test="${not empty errorMessage}">
    <%--<input type="text" id="errorMessage" value="${errorMessage}" style="color:red;" disabled="disabled">--%>
    <h4 style="color: red;">${errorMessage}</h4>
</c:if>

<p>点击<a href="searchMatrlRst.jsp" target="mainFrame" >这里</a></p>

</body>
</html>