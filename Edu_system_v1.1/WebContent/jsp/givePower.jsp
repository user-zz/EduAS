<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.btn1{
		width:60px;
		height:23px;
		color:black;
		background-color:#33CC66;
		padding:0px;
		border:0px;
	}
	.STYLE13{
		font-family: "幼圆";
		font-size: 14px
	}
</style>
<script>

function isNull(){
	var select = document.getElementById('select').value;
	var condition = document.getElementById('condition').value;
	if(select == '' || condition == ''){
		alert("查找条件不能为空");
		return false;
	}else return true;
	
}

</script>
</head >
<body bgcolor="#FAEBD7">
<br>
	<div align="center">
		<form action="<%=request.getContextPath()%>/ChangeId?op=reaserch" method="post" onSubmit="return isNull()">
			<table>
				<tr>
					<td>
						<font>查找条件：</font>
						<select name="select" id="select">
							<option value="">---请选择---</option>
							<option value="0">&nbsp;&nbsp;用户名</option>
							<option value="1">&nbsp;教师工号</option>
						</select>	
						<input type="text" name="condition" id="condition"  size="30px" />
						<input type="submit" class="btn1"  value="查找"  />
					</td>
				</tr>
			</table>
		</form>
		
		<!-- 结果展示 -->
	</div>
	<c:if test="${not empty thesisSearchRstList}">
			 <div align="center" class="STYLE13">
			 	<hr width="90%" />
				<h5>搜索结果</h5>
		     </div>
			 <div align="center">
				<table width="95%">
					<tr>
						<th align="center" width="15%" >用户名或账户</th>
						<th align="center" width="15%" >真实姓名</th>
						<th align="center" width="30%">院系或专业</th>
						<th align="center" width="15%">用户身份</th>
						<th align="center">操作</th>
					</tr>
					<c:forEach items="${tempUserList}" var="tempuserbean">
						<tr>
							<td valign="middle"  bordercolor="#000000"><span class="STYLE13">${tempuserbean.tempusername}</span></td>
							<td valign="middle"  bordercolor="#000000"><span class="STYLE13">${tempuserbean.temprealname}</span></td>
							<td align="center"  valign="middle" bordercolor="#000000"><span class="STYLE13">${tempuserbean.tempMajor}</span></td>
							<td align="center"  valign="middle" bordercolor="#000000"><span class="STYLE13">${tempuserbean.tempid}</span></td>
							<td align="center"  valign="middle" bordercolor="#000000">
								<a href="<%=request.getContextPath()%>/ChangeId?op=give&Username=${tempuserbean.tempusername}">授权管理员</a>&nbsp;&nbsp;
								<a href="<%=request.getContextPath()%>/ChangeId?op=remove&Username=${tempuserbean.tempusername}">移除管理员</a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
	</c:if>
</body>
</html>