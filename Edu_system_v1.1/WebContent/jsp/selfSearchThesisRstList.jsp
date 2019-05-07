<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	
	.STYLE13 {font-family: "幼圆"}
	.STYLE18 {font-size: 14px}

</style>

</head>
<body>
	<div>
		<c:if test="${not empty selfSearchThesisRstList}">
			 <div align="center">
			 	<hr width="90%" />
				<h5>搜索结果</h5>
		     </div>
			 <div align="center" class="STYLE13 STYLE18">
				<table width="83%" border="0">
						<tr>
							<th width="10%">论题名</th>
							<th width="25%">论题内容</th>
							<th width="10%">发布者</th>
							<th width="10%">发布时间</th>
							<th width="5%">论题类别</th>
							<th width="8%">点击量</th>
							<th width="5%">操作</th>
						</tr>
						<c:forEach items="${selfSearchThesisRstList}" var="thbean">
							<tr>
								<td width="10%" valign="middle" bordercolor="#000000"><span class="STYLE13">${thbean.title}</span></td>
								<td width="30%" valign="middle" bordercolor="#000000"><span class="STYLE13">${thbean.thcontent}</span></td>
								<td align="center" width="5%" valign="middle" bordercolor="#000000"><span class="STYLE13">${thbean.thusername}</span></td>
								<td align="center" width="5%" valign="middle" bordercolor="#000000"><span class="STYLE13">${thbean.thtime}</span></td>
								<td align="center" width="5%" valign="middle" bordercolor="#000000"><span class="STYLE13">${thbean.thtype}</span></td>
								<td align="center" width="5%" valign="middle" bordercolor="#000000"><span class="STYLE13">${thbean.thclickrate}</span></td>
								<td align="center" width="5%" valign="middle" bordercolor="#000000"><a href="<%=request.getContextPath()%>/CheckDelete?Thno=${thbean.thno}&ID=1"><font color="#4169E1">删除</font></a></td>
							</tr>
						</c:forEach>
				</table>
			</div>
	</c:if>
	</div>
</body>
</html>