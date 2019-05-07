<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.Arrays"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<style type="text/css">
	
	.div1{
			border-left:3px solid #C0C0C0;
			width:80%;
			height:100% ;
		}
		
	.div2{
		background:#A9A9A9;
		
	}
	
    #div3{ 
			position:relative;
			width:800px;
		  	height:200px;
	    	overflow:hidden; 
     }
     
	#div4{
	  		position:absolute;
	  }
	  
	  li{ 
	  		float:left;
	  		list-style-type:none;
	  		padding:3px;
	  }
	  
	  img{
	  		border:none;
	  }
	  
	  #div5 li a:hover{
	  		top:-10px;
	  }
	  
	  a{
	  		position:relative;
	  }
	  	  
</style>

<script>

     window.onload = function () {
         var odiv2 = document.getElementById('div4');
         var ali = odiv2.getElementsByTagName('li');
         var aspeed = -5; 
         var timer = null;
         odiv2.innerHTML += odiv2.innerHTML;
         odiv2.style.width = ali[0].offsetWidth * ali.length + 'px';
         odiv2.onmouseover = function (){ 
      	 	clearInterval(timer); 
         };
         function a() {
              timer = setInterval(function () {
                  odiv2.style.left = odiv2.offsetLeft + aspeed + 'px';  //控制滑动速度和方向，aspeed的数值越大速度越快，“-5”代表向左滑动，“5”代表向右滑动
                  if (odiv2.offsetLeft < -odiv2.offsetWidth / 2) {
                      odiv2.style.left = '0px';
                  }
              }, 100);
          };
          odiv2.onmouseout = a;
          a();
      }
     
</script>

</head>

<body bgcolor="#FFEBD5">
<div class="div1">
	<div class="div2">
		&nbsp;<b><font color="#DC143C" size="2">精彩展示</font></b>
	</div>
	<div id='div3'>
		<div id='div4'>
			<ul>
				<li><a href="#"><img src="images/index_main_1.JPG" style="height:200px"/></a></li>
				<li><a href="#"><img src="images/index_main_2.JPG" style="height:200px" /></a></li>  
			</ul>
		</div>
	</div>
</div>
</body>
</html>