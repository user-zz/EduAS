<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    <%
    String username = (String)session.getAttribute("username");
	if(username == null){
		out.print("<h3>请您先登录,正在为您返回登录页面，请稍后...</h3><br/>");
		response.setHeader("refresh", "3,../login.jsp");
	}
    %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/edua-logo.ico" />
<title>发表评论</title>
<style type="text/css">
	.STYLE13 {font-family: "幼圆"}
	.STYLE18 {font-size: 14px}
	textarea{
		overflow-x:hidden;
		wrap:hard;
	}
	.div1{
		background-color:#4169E1;
	}
	.textA{
		overflow-x: hidden;
		background-attachment: fixed;  
		background-repeat: no-repeat;   
		border-style: solid; 
		border-color: #FFFFFF;
		wrap:soft;
	}
	
</style>
</head>

<body>
				<!-- 获取会话中的数据 -->
				<%
					String thno = (String)session.getAttribute("Thno");
				%>
	<div class="div1">
			<h5><font size="3">论题详情</font></h5>
	</div>
	<form action="Discuss" method="post">
		<div class="STYLE13 STYLE18">
				<table width="50%" border="0" >
					<c:if test="${not empty thesisRstList}">
	        		<c:forEach items="${thesisRstList}" var="thbean">
							<tr>
								<td align="left" valign="top">
									<p>
										<span></span>
										<font size="1">标题：<input type="text" size="30px" name="d_title" value="${thbean.title}" readonly /></font>
									</p>
									<p>
										<font size="1">发布者：${thbean.thusername}</font>
									</p>
									<p>
										<font size="1">发布时间：${thbean.thtime}</font>
									</p>
									<p>
										<font size="1">论题内容：</font>
									</p>
									<p>
										<textarea rows="10px" cols="70px" >&nbsp;${thbean.thcontent}</textarea>
									</p>
								</td>
								<td align="left" valign="top">
									<p align="center"><font size="2" color="black"><b>——&gt;&gt;&gt;评论展示区&lt;&lt;&lt;——</b></font></p>
									<p>
										<c:if test="${not empty discsList}">
											<textarea rows="15px" cols="70px" class="textA" disabled >
						        				<c:forEach items="${discsList}" var="discsbean">
						     						&nbsp;&nbsp;&nbsp;&nbsp;——&nbsp;评论时间：${discsbean.d_time}&nbsp;——
						        					${discsbean.d_username}：${discsbean.d_mess}
												</c:forEach>
											</textarea>
			    						</c:if>
									</p>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<textarea rows="4px" cols="70px" name="discuss_value"></textarea>
									<input type="hidden" name="Thno" value="<%=thno %>"/>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<input type="submit" value="评论" />&nbsp;&nbsp;<input type="reset" value="清空" />
								</td>
							</tr>
						</c:forEach>
	    			</c:if>
				</table>
		    </div>
	</form>

</body>
</html>