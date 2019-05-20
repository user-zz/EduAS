<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    String username = (String)session.getAttribute("username");
	if(username == null){
		out.print("<h3>请您先登录,正在为您返回登录页面，请稍后...</h3><br/>");
		response.setHeader("refresh", "3,../login.jsp");
	}
    %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件列表</title>

<style type="text/css">
	.table_style{
		width: 60%; 
		max-width: 100%;
		margin-bottom: 40px;
		border-collapse:collapse;
	}
</style>

</head>
<body>
<div align="center">
	<h4>文件列表</h4>
</div>
<div align="center">
	<table class="table_style" border="1" bordercolor="#4169E1" >
    <tr>
        <th width="30%" >文件名</th>
        <th width="10%" >文件大小（KB）</th>
        <th width="10%" >上传者</th>
        <th width="10%">操作</th>
    </tr>
    <c:if test="${not empty fileList}">
        <c:forEach items="${fileList}" var="acc">
            <tr>
                <td>${acc.fname}</td>
                <td>${acc.fsize}</td>
                <td>${acc.fusername}</td>
                <td>
	              <!--   <a href="">删除</a>  -->
	                <a href="<%=request.getContextPath()%>/DownloadUploadedFile?id=${acc.fno}">下载</a>
	           	</td>
            </tr>
        </c:forEach>
    </c:if>
</table>
</div>

</body>
</html>