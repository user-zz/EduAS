<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<!-- 获取绝对路径 -->
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<style type="text/css">
	.STYLE13 {font-family: "幼圆"}
	.STYLE18 {font-size: 14px}
	.btn1{
		width:80px;
		height:20px;
		color:#FFFFFF;
		background-color:#1E90FF;
		padding:2px;
		border:1px;
	}
</style>

<script type="text/javascript">
	
	function isCheckConditionNull(){
		var condition = document.getElementById('condition').value;
		if(condition == ''){
			alert("搜索条件不能为空！");
			return false;
		}
		else return true;
	}

</script>

</head>
<body>
	<div>
		<span><font size="1" color="#1E90FF">温馨提示：请选择任意一种条件进行检索</font></span>
	</div><br/>
	<div>
		<form action="<%=request.getContextPath()%>/SearchThesis" method="post" onSubmit="return isCheckConditionNull();">
			<table>
				<tr>
					<td>
							<select name="condition_id">
								<option value="" >&nbsp;&nbsp;---请选择---</option>
								<option value="1">标题</option>
								<option value="2">发布者</option>
							</select>
					</td>
					<td>
						<input type="text" name="condition" id="condition"  size="30px" />
					</td>
					<td>
						<input type="submit" class="btn1"  value="搜索"  />
					</td>
				</tr>
			</table>
		</form>
	</div>
		<c:if test="${not empty thesisSearchRstList}">
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
						<c:forEach items="${thesisSearchRstList}" var="thbean">
						<tr>
							<td width="10%" valign="middle" bordercolor="#000000"><span class="STYLE13">${thbean.title}</span></td>
							<td width="30%" valign="middle" bordercolor="#000000"><span class="STYLE13">${thbean.thcontent}</span></td>
							<td align="center" width="5%" valign="middle" bordercolor="#000000"><span class="STYLE13">${thbean.thusername}</span></td>
							<td align="center" width="5%" valign="middle" bordercolor="#000000"><span class="STYLE13">${thbean.thtime}</span></td>
							<td align="center" width="5%" valign="middle" bordercolor="#000000"><span class="STYLE13">${thbean.thtype}</span></td>
							<td align="center" width="5%" valign="middle" bordercolor="#000000"><span class="STYLE13">${thbean.thclickrate}</span></td>
							<td align="center" width="5%" valign="middle" bordercolor="#000000"><a href="<%=request.getContextPath()%>/SearchThesis?Thno=${thbean.thno}&ID=1"><font color="#4169E1">查看并评论</font></a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
	</c:if>
</body>
</html>