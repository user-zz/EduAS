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
<title>作业下载</title>

<style>
	.txt_style{
		width:150px;
		height:20px;
	}
	.div1{
		border-left:3px solid #C0C0C0;
		width:80%;
		height:100% ;
	}
</style>

</head>
<body>
	<div class="div1" >
		<div  align="center" >
			<h4>---查找学生作业---</h4>
		</div>
		<div>
			<form action="../ListUploadFiles" method="post">
				<p>
					<font size="2">选择院系&nbsp;</font>
						<select name="stumajor" style="width:100px;font-size:15px" >
							    <option value="" selected>---请选择---</option>
							    <option value="公共基础">物联网工程学院</option>
							    <option value="理学">环境科学学院</option>
							    <option value="哲学">自动化控制学院</option>
							    <option value="哲学">电子信息技术学院</option>
							    <option value="计算机、互联网">经济与贸易系</option>
							    <option value="语言文学">理学系</option>
							    <option value="大气科学">法学系</option>
							    <option value="金融会计">外语系</option>    
						</select>
						<font size="1" color="#D3D3D3">若无对应值，请在这里输入</font><input type="text" size="20px" name="input_stumajor" />
				</p><br/>
				<p>
					<font size="2">选择班级&nbsp;</font>
					<select name="stuclass" style="width:100px;font-size:15px">
							    <option value="" selected>---请选择---</option>
							    <option value="公共基础">15计科1班</option>
							    <option value="计算机、互联网">15计科2班</option>
							    <option value="语言文学">软件工程专业</option>
							    <option value="大气科学">国际贸易与经济专业</option>
							    <option value="金融会计">环境工程专业</option>
							    <option value="理学">大气科学专业</option>
							    <option value="哲学">物联网专业</option>
					</select>
					<font size="1" color="#D3D3D3">若无对应值，请在这里输入</font><input type="text" size="20" name="input_stuclass" />
				</p><br/>
				<p>
						<font size="2">文件ID&nbsp;</font>	
						<input type="text" size="3"  name="fid" value="1" readonly />		
						<font size="1" color="#D3D3D3">默认值，不可输入</font>
				</p><br/>
				<p>
					<input type="submit" value="搜索"/>&nbsp;&nbsp;<input type="reset" value="取消"/>
				</p>
			</form>
		</div>
	</div>
</body>