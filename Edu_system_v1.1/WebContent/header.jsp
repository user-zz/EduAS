<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
  <!-- 获取绝对路径 -->
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/edua-logo.ico" />
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<meta name="author" content="me">
	<title></title>
	<link rel="stylesheet" type="text/css" href="css/header_styles.css">
	<style type="text/css">
		a{
			text-decoration:none;
			color:#FFFFFF;
		}
		div{
			margin:0;  /* 外边距*/
			border:0;  /* 边框*/
			padding:0;  /* 内补白*/
		}
		.bground_style{
			background-color:#F0FFFF;
			background-image:url("images/header_bg.jpg");		
			background-repeat:no-repeat;  /* 图片不平铺*/
			background-size:100% 100%; 
			background-attachment:scroll;  /* 图片滚动*/
		}
		.header_navigation_style{
			width: 100%; 
			height: 13%; 
			background: #6495ED; 
			z-index:9999;
			clear:both; 
		}
	</style>
</head>
<body>
	<div>
		<div class="bground_style">
			<br/><br/><br/><br/>
		</div><br/>
		<div align="center" class="header_navigation_style" >
		
		<!-- target="_top" 可以使超链接重新加载整个页面 -->
		
			<span><img src="images/home.ico"><a href="index.jsp"  target="_top">&nbsp;&nbsp;首页&nbsp;&nbsp;|</a></span>
			<span><a href="jsp/news.jsp" target="_top">&nbsp;&nbsp;最新资讯&nbsp;&nbsp;|</a></span>
			<span><a href="jsp/learnMtrl.jsp"  target="_top">&nbsp;&nbsp;学习资料&nbsp;&nbsp;|</a></span>
			<span><a href="jsp/chat.jsp"  target="_top">&nbsp;&nbsp;互动大厅&nbsp;&nbsp;|</a></span>
			<span><a href="jsp/userCenter.jsp" target="_top">&nbsp;&nbsp;个人中心&nbsp;&nbsp;|</a></span>
			<span><a href="login.jsp" target="_top">&nbsp;&nbsp;登录&nbsp;|&nbsp;</a><a href="regist.jsp"  target="_blank">注册</a></span>
			<span><a href="jsp/logout.jsp" target="_top">|&nbsp;&nbsp;注销</a></span>
		
		</div>
	</div>
	<br/>
</body>
</html>