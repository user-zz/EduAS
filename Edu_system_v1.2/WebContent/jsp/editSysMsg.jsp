<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<style type="text/css">
	textarea{
		   outline:none; 
		   resize:none; 
		   background:transparent; 
		   overflow-x:hidden;
	}
</style>

</head>
<body bgcolor="#FAEBD7" onload="ReSuccess()" >
<div align="center" >
	<h5>发布系统公告</h5><hr>
	<br>
	<form action="<%=request.getContextPath()%>/EditSysMsg" method="post" >
		<table>
			<tr>
				<td>
					编辑内容：<br><font color="#D3D3D3"><textarea rows="10" cols="50" name="msgcontent"></textarea></font>
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" value="发布"  >&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" value="取消"/>
				</td>
			</tr>
		</table>
	</form>
</div>
<div>
	<input type="hidden" id="success" value="<%=request.getAttribute("message") %>" />
</div>
</body>
<script type="text/javascript">
	function ReSuccess(){
		var success = document.getElementById("success").value;
		if(success == "公告发布成功！"){
			alert("操作结果："+success);
		}
	}	
</script>

</html>