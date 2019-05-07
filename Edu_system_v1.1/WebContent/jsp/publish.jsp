<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String username = (String)session.getAttribute("username");
	if(username == null){
		out.print("<h3>请您先登录,正在为您返回登录页面，请稍后...</h3><br/>");
		response.setHeader("refresh", "3,../login.jsp");
	}
	
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
     
	<div>
		<div align="center">
			<h4>编辑论题</h4>	
		</div>
		<div align="center" >
			<form action="../Publish" method="post" >
				<table border="0" >
					<tr>
						<td align="left"  colspan="2">
							<font color="red">*</font>标题：<input type="text" width="25px" name="title" />
						</td>
					</tr>
					<tr>
							<td align="left" colspan="2">
								<font color="red">*</font>类别：
								<select name="thtype"  >
										<option>请选择</option>
										<option>全部</option>
										<option>公共基础</option>
										<option>语言文学</option>
										<option>计算机、互联网技术</option>
										<option>金融学</option>
										<option>会计学</option>
										<option>法学</option>
										<option>其他</option>
									</select>
							</td>
					</tr>
					<tr>
						<td align="left"  colspan="2" >
							<p><font color="red">*</font>内容：</p>
							<p>
								<textarea rows="15px" cols="70px" name="thcontent" ></textarea>
							</p>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center" >
							<input type="submit" value="发布" size="10px" />&nbsp;&nbsp;&nbsp;
							<input type="reset" value="取消" size="10px"  />
						</td>
					</tr>
				</table>
			</form>
		</div>	
	</div>
</body>
</html>