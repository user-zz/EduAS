<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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
<title>作业下载 | 教学辅助系统</title>

<style>
	.txt_style{
		width:150px;
		height:20px;
	}
</style>

<script type="text/javascript">
	
	
	
</script>

</head>
<body>
	<div>
		<div align="center">
			<h4>查找学生作业</h4>
			<hr width="80%"/>
		</div>
		<div align="center">
			<form action="<%=request.getContextPath()%>/ListUploadFiles?OP=searchHw" method="post">	
				<table width="80%">
					<tr>
						<td align="left" width="15%"><font color="red">*</font><font size="1">院系</font></td>
						<td width="20%">
							<select name="stumajor" style="width:100px;font-size:15px" >
							    <option value="" selected>---请选择---</option>
							    <option value="物联网工程学院">物联网工程学院</option>
							    <option value="环境科学学院">环境科学学院</option>
							    <option value="自动化控制学院">自动化控制学院</option>
							    <option value="电子信息技术学院">电子信息技术学院</option>
							    <option value="经济与贸易系">经济与贸易系</option>
							    <option value="理学系">理学系</option>
							    <option value="法学系">法学系</option>
							    <option value="外语系">外语系</option>    
							</select>
						</td>
						<td align="left" >
							<font size="1" color="#A9A9A9">自定义：</font><input type="text" size="20px" name="input_stumajor" id="randomnum" />
						</td>
					</tr>
					<tr>
						<td align="left"><font color="red">*</font><font size="1">专业或班级</font></td>
						<td>
							<select name="stuclass" style="width:100px;font-size:15px">
							    <option value="" selected>---请选择---</option>
							    <option value="15计科班">15计科班</option>
							    <option value="15软工班">15软工班</option>
							    <option value="软件工程专业">软件工程专业</option>
							    <option value="国际贸易与经济专业">国际贸易与经济专业</option>
							    <option value="环境工程专业">环境工程专业</option>
							    <option value="大气科学专业">大气科学专业</option>
							    <option value="物联网专业">物联网专业</option>
							</select>
						</td>
						<td align="left">
							<font size="1" color="#A9A9A9">自定义：</font><input type="text" size="20" name="input_stuclass" />
						</td>
					</tr>
					<tr>
						<td align="left"><font size="1">&nbsp;&nbsp;学生姓名</font><font size="1" color="#A9A9A9">（选填）</font></td>
						<td>
							<input type="text" size="20" name="stuname" />
						</td>
					</tr>
					<tr>
						<td align="left"><font color="red">*</font><font size="1">教师姓名</font></td>
						<td>
							<input type="text" size="20" name="teaname" />
						</td>
					</tr>
					<tr>
						<td></td>
						<td>
							<input type="submit" value="搜索"/>&nbsp;&nbsp;
							&nbsp;&nbsp;<input type="reset" value="取消"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>