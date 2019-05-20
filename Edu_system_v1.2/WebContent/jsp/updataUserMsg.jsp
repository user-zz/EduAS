<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>

<script>

	function Edit(id){
		document.getElementById(id).readOnly = false;
	}

</script>
<style type="text/css">
	
	input {
         outline:none;
         background:transparent;
         border:none;
         outline:medium;
   }
   .table_style{
		width: 60%; 
		max-width: 100%;
		margin-bottom: 40px;
		border-collapse:collapse;
	}
	.btn1{
		width:40px;
		height:30px;
		color:#4169E1;
		background-color:#FFFFFF;
		padding:0px;
		border:0px;
	}
	.btn2{
		width:80px;
		height:25px;
		color:#FFFFFF;
		background-color:#4169E1;
		padding:0px;
		border:0px;
	}
</style>

</head>
<body>
<div align="center">
	<h5>个人基本信息</h5>
	<hr width="90%"/>
</div>
<div>
	<form action="<%=request.getContextPath()%>/UpdataUserMsg?OP=editusermsg" method="post">
		<c:if test="${not empty userMsgList}">
	        <c:forEach items="${userMsgList}" var="user_msg_bean">
				<table class="table_style" align="center" border="1">
					<tr>
						<td width="15%">用户名</td>
						<td width="30%" colspan="2">
							<input type="text" readonly="readonly" value="${user_msg_bean.username}" id="test" name="username" />
						</td>
					</tr>
					<tr>
						<td>学工号</td>
						<td colspan="2">
							<input type="text" readonly="readonly" value="${user_msg_bean.uno}" id="test" name="uno" />
						</td>
					</tr>
					<tr>
						<td>姓名</td>
						<td colspan="2">
							<input type="text" readonly="readonly" value="${user_msg_bean.name}" id="test" name="name" />
						</td>
					</tr>
					<tr>
						<td>专业或院系</td>
						<td>
							<input type="text" readonly="readonly" value="${user_msg_bean.major}" id="major" name="major" />
						</td>
						<td align="center">
							<button type="button" class="btn1" onclick="Edit('major')">修改</button>
						</td>
					</tr>
					<tr>
						<td>班级</td>
						<td>
							<input type="text" readonly="readonly" value="${user_msg_bean.userclass}" id="userclass" name="userclass" />
						</td>
						<td align="center">
							<button type="button" class="btn1" onclick="Edit('userclass')">修改</button>
						</td>
					</tr>
					<tr>
						<td>昵称</td>
						<td>
							<input type="text" readonly="readonly" value="${user_msg_bean.nickname}" id="nickname" name="nickname" />
						</td>
						<td align="center">
							<button type="button" class="btn1" onclick="Edit('nickname')">修改</button>
						</td>
					</tr>
					<tr>
						<td>电话号码</td>
						<td>
							<input type="text" readonly="readonly" value="${user_msg_bean.phonenum}" id="phonenum" name="phonenum" />
						</td>
						<td align="center">
							<button type="button" class="btn1" onclick="Edit('phonenum')">修改</button>
						</td>
					</tr>
					<tr>
						<td>电子邮箱</td>
						<td>
							<input type="text" readonly="readonly" value="${user_msg_bean.email}" id="email" name="email" />
						</td>
						<td align="center">
							<button type="button" class="btn1" onclick="Edit('email')">修改</button>
						</td>
					</tr>
					<tr>
						<td>账户身份</td>
						<td colspan="2">
							<input type="text" readonly="readonly" value="${user_msg_bean.id}" id="test" name="id" />
						</td>
					</tr>
					<tr>
						<td align="center" colspan="3">
							<button type="submit" class="btn2" >提交</button>
						</td>
					</tr>
				</table>
			 </c:forEach>
	    </c:if>
	</form>
</div>
</body>
</html>