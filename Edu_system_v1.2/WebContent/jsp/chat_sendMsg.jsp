<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import=" java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/file_js.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/file_styles.css" type="text/css" />
<style>
	input{
		width: 70px; 
		height: 25px;
		font-size: 15px; 
		color:white;
		background-color:#1E90FF;
		padding:0px;
		border:0px;
	}
</style>
</head>
<body>
<div>
	<form action="<%=request.getContextPath()%>/InputInformation" method="post" target="mainFrame">
		<table>
			<tr>
				<td>
					 <font>发送消息给：</font>
				  		<select name="select">
				  			<option value="0">所有人</option>
				  			<%
				                List<Object> users = (List<Object>) application.getAttribute("users");
				                for (int i = 0; i < users.size(); i++) { 
				                 String username = (String) users.get(i); 
				             %>
				            <option value="<%=i+2 %>"><%=username %></option>    
		                	<%  i++; } %>
			  		</select>
			  		<%
			  			for(int i=0;i<8;i++){
			  		%>
			  		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			  		<%
			  			}
			  		%>
			  		<input type="submit" value="发送" >
				</td>
			</tr>
			<tr>
				<td>
				  <textarea  cols="94" rows="5"  name="input_textarea"></textarea>
				</td>
			</tr>
		</table>
	</form>
</div>
<div>
	<form action="<%=request.getContextPath()%>/FileUpload?filetype_img=T" name="fileform" method="post" target="mainFrame" onSubmit="return isFileNull();" enctype="multipart/form-data">
		<font size="1" color="#FF1493">请选择您想要发送的图片：</font><br/>
		<input type="file" class="file-btn" name="upfile"  />
		<input type="submit" value="发送图片" />
		
	</form>
</div>
</body>
</html>