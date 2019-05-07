<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传错误页面</title>

<script type="text/javascript">

	function back(){
		history.go(-1);	
	}
	
</script>

</head>
<body>
 
<h3>上传失败：</h3>
	<div>
		<a href="" onclick="back()" ><font size="2px" color="#4169E1">&lt;&lt;返回上一页</font></a>	
	</div>
	<br/>
<c:if test="${not empty errorMessage}">
    <%--<input type="text" id="errorMessage" value="${errorMessage}" style="color:red;" disabled="disabled">--%>
    <h6 style="color: red;">&nbsp;&nbsp;${errorMessage}</h6>
</c:if>

</body>
</html>