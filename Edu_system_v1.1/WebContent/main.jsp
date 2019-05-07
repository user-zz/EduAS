<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="InputInformation" method=post>
	  <textarea  cols="105" rows="20" name="show_textarea"><%=request.getAttribute("input_textarea")%></textarea>
	  <br>
	  <textarea  cols="105" rows="3"  name="input_textarea"></textarea><br>
	  <input type="submit" value="发送" name="button_one" 
	   style="width: 100px; height: 30px;font-size: 20px;"><br>
	  </form>

</body>
</html>