<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
<title></title>
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
            overflow-y:auto;
        }
        a{
        	text-decoration:none;
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
        .div0{
        	background:"#C0C0C0";
        }
         .div1{
			background:#1E90FF;
			color:#FFFFFF;
		}
    </style>
    <script src="js/jquery-1.4.2.min.js" type="text/javascript"></script>
    <script type="text/javascript">
          $(function () {
            $(".menu p:not(:first)").hide();
            $(".menu h3").css("background", "#ccc");       
            $(".menu h3").hover(function () { 
            	$(this).css("background-color", "gray").siblings("h3").css
				("background-color", "#ccc");
            });
            $(".menu h3").mouseleave(function () { 
               $(".menu h3").css("background", "#ccc");
            }); //离开时将其变为原来颜色
            var index = $(".menu h3").index(this);
            $(".menu h3").click(function () { 
            	$(this).next("p").slideToggle().siblings("p").slideUp(); 
            });
        });
    </script>

</head>
<body bgcolor="#DCDCDC">
<div>
		<br>
		<div class="div0">
			<h4>
		          欢迎管理员：<%=(String)session.getAttribute("username") %>
		     </h4>
		</div><br/>
		<div align="center" class="div1" >
			<h3>相关操作</h3>
		</div>
		<div class="menu">
		       <h3>审核材料</h3>
		           <p>
						<a href="<%=request.getContextPath()%>/CheckThesis?CheckId=0" target="admin_mainFrame"><font color="#4169E1">论题材料审核</font></a><br/>
						<a href="<%=request.getContextPath()%>/CheckThesis?CheckId=1" target="admin_mainFrame"><font color="#4169E1">文件材料审核</font></a><br/>
						<a href="<%=request.getContextPath()%>/CheckThesis?CheckId=2" target="admin_mainFrame"><font color="#4169E1">评论材料审核</font></a><br/>
					</p> 	
		        <h3>系统管理</h3>
			        <p>
			        	<a href="<%=request.getContextPath()%>/jsp/editSysMsg.jsp" target="admin_mainFrame"><font color="#4169E1">发布系统公告</font></a><br/>
			        	<a href="<%=request.getContextPath()%>/jsp/givePower.jsp" target="admin_mainFrame"><font color="#4169E1">用户权限管理</font></a>
					</p>
		         <h3>拓展功能</h3>
			        <p>
			        	迭代中..
			        </p>
		 </div>
</div>
</body>
</html>