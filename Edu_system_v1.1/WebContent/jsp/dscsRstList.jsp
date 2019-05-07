<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<base href="<%=basePath%>">
<title>详情结果页</title>

<style type="text/css">
	
	textarea {
		background-color: #FFEFD5;
	}
	
	a{
		text-decoration:none;
	}
	
</style>

</head>
<body bgcolor="#FFEFD5">
			<a href="javascript:history.go(-1);" target="admin_mainFrame"><font size="2px" color="#4169E1">&lt;&lt;返回</font></a>	
		<div  align="center">
			<span></span>
			<span></span>
			<h5><font size="3">评论内容详情</font></h5>
			<hr />
		</div>
		
		<div>
				<table border="0" >
					<c:if test="${not empty dscsRstList}">
	        		<c:forEach items="${dscsRstList}" var="dscsbean">
							<tr>
								<th align="left"><font size="1">标题：${dscsbean.d_title}</font></th>
							</tr>
							<tr>
								<th align="left"><font size="1">评论者：${dscsbean.d_username}</font></th>
							</tr>
							<tr>
								<th align="left"><font size="1">评论时间：${dscsbean.d_time}</font></th>
							</tr>
							<tr>
								<th align="left" colspan="2"><font size="1">论题内容：</font></th>
							</tr>
							<tr>
								<td colspan="2">
									<textarea rows="10" cols="100" >&nbsp;${dscsbean.d_mess}</textarea>
								</td>
							</tr>
						</c:forEach>
	    			</c:if>
				</table><br/><br/><br/>
			</div>
</body>
</html>