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
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/edua-logo.ico" />
<title>上传作业 | 教学辅助系统</title>

<style type="text/css">
	.div1{
		background:#1E90FF;
		color:#FFFFFF;
	}
</style>

<script type="text/javascript">
	
	function changColor(x){

		document.getElementById(x).style.color="#32CD32";
	}
	
</script>

</head>
<body>
<div>
	<div class="div1" align="center">
		<h4>请完成上传前的准备工作</h4>
	</div>
	<div align="left" >
		<p>
			<b><font color="#32CD32" >上传作业>></font></b>
			<b ><font id="T1" color="#C0C0C0">选择文件类别>></font></b>
			<b><font id="T2" color="#C0C0C0">选择您的专业>></font></b>
			<b><font id="T3" color="#C0C0C0">选择您的班级>></font></b>
			<b><font id="T4" color="#C0C0C0">请输入教师名字>></font></b>
			<b><font id="T5" color="#C0C0C0">请确认您的真实姓名</font></b>				
		</p>
	</div><br/>
	<div align="center" >
		<form action="<%=request.getContextPath()%>/HomeWorkUpload" method="post" >
			<table border="0">
				<tr>
					<td>
						<font size="1">文件类别</font>
					</td>
					<td colspan="2">
						<select name="filetype"style="width:100px;font-size:15px" onblur="changColor('T1')">
						    <option value="" selected>---请选择---</option>
						    <option value="其他" >其他</option>
						    <option value="公共基础">公共基础</option>
						    <option value="计算机、互联网">计算机、互联网</option>
						    <option value="语言文学">语言文学</option>
						    <option value="大气科学">大气科学</option>
						    <option value="金融会计">金融会计</option>
						    <option value="理学">理学</option>
						    <option value="哲学">哲学</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<font size="1">所在院系</font>
					</td>
					<td>
						<select name="stumajor" style="width:100px;font-size:15px" onblur="changColor('T2')">
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
					<td>
						<font size="1" color="#D3D3D3">自定义</font><input type="text" size="20px" name="input_stumajor" />
					</td>
				</tr>
				<tr>
					<td>
						<font size="1">所在专业或班级</font>
					</td>
					<td>
						<select name="stuclass" style="width:100px;font-size:15px" onblur="changColor('T3')">
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
					<td>
						<font size="1" color="#D3D3D3">自定义</font><input type="text" size="20" name="input_stuclass" />
					</td>
				</tr>
				<tr>
					<td>
						<font size="1">请输入教师名字</font>
					</td>
					<td>
						<input type="text" size="20" name="teaName" onfocus="changColor('T4')" />
					</td>
				</tr>
				<tr>
					<td>
						<font size="1">请输入您的真实姓名</font>
					</td>
					<td>
						<input type="text" size="20" name="stuName" onfocus="changColor('T5')" />
						<input type="hidden" size="3"  name="fid" value="1" />
					</td>
				</tr>
				<tr>
					<td colspan="3" align="center">
						<input  type="submit" value="下一步" />
					</td>
				</tr>
			</table>	
		</form>
	</div>
</div>
</body>
</html>