<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
    
    <title>My JSP 'test.jsp' starting page</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="content-type" content="text/html; charset=gb2312">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
 <style type="text/css">
        *
        {
            margin: 0;
            padding: 0;
        }
        body
        {
            font-size: 15px;
            padding: 00px;
        }
        .menu
        {
            width: 500px;
            border-bottom: solid 1px gray;
        }
        .menu h3
        {
            border: solid 1px gray;
            height: 30px;
            line-height: 30px;
            padding-left: 10px;
            padding:0 5px;
            border-bottom: none;
            cursor: pointer;
           
        }
        .menu p
        {
            border-left: solid 1px gray;
            border-right: solid 1px gray;
            padding: 20px 0;
            padding-left: 5px;
        }
        .changecolor{background-color:red;}
    </style>
    <script src="js/jquery-1.4.2.min.js" type="text/javascript"></script>
    <script type="text/javascript">
          $(function () {
            $(".menu p:not(:first)").hide();
            $(".menu h3").css("background", "#ccc");       
            $(".menu h3").hover(function () { $(this).css("background-color", "gray").siblings("h3").css
("background-color", "#ccc");});
            $(".menu h3").mouseleave(function () { 
               $(".menu h3").css("background", "#ccc");}); //离开时将其变为原来颜色
            var index = $(".menu h3").index(this);
            $(".menu h3").click(function () { $(this).next("p").slideToggle().siblings("p").slideUp(); });
        });
    </script>


</head>
<body>
<div class="menu">
        <h3>
            我的好友</h3>
        <p>
   <a href="index.jsp">周杰伦</a><br/><br/>
   <a href="index.jsp">周杰伦</a><br/><br/>
   <a href="index.jsp">周杰伦</a><br/><br/>
   <a href="index.jsp">周杰伦</a><br/><br/>
   <a href="index.jsp">周杰伦</a><br/><br/>
   <a href="index.jsp">周杰伦</a><br/><br/>
   <a href="index.jsp">周杰伦</a><br/><br/>
   <a href="index.jsp">周杰伦</a><br/>
            </p>
        <h3>
            我的朋友</h3>
        <p>
   <a href="index.jsp">李连杰</a><br/><br/>
   <a href="index.jsp">李连杰</a><br/><br/>
   <a href="index.jsp">李连杰</a><br/><br/>
   <a href="index.jsp">李连杰</a><br/><br/>
   <a href="index.jsp">李连杰</a><br/><br/>
   <a href="index.jsp">李连杰</a><br/><br/>
   <a href="index.jsp">李连杰</a><br/><br/>
   <a href="index.jsp">李连杰</a><br/>
         </p>
        <h3>
            陌生人</h3>
        <p>
   <a href="index.jsp">比尔盖茨</a><br/><br/>
   <a href="index.jsp">比尔盖茨</a><br/><br/>
   <a href="index.jsp">比尔盖茨</a><br/><br/>
   <a href="index.jsp">比尔盖茨</a><br/><br/>
   <a href="index.jsp">比尔盖茨</a><br/><br/>
   <a href="index.jsp">比尔盖茨</a><br/><br/>
   <a href="index.jsp">比尔盖茨</a><br/>
          </p>
    </div>

</body>
</html>