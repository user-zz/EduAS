function check_startpwd(){
			var startpwd = document.getElementById('startpwd').value;
			
			if( startpwd.length<6){
				alert(" 您输入的密码有误！");
			}
			document.getElementById("startpwd_msg").style.color="";
		}

function check_pwd(){
			var pwd1 = document.getElementById('pwd1').value;
			var pwd2 = document.getElementById('pwd2').value;
		    if(pwd1!=pwd2){
				alert("两次输入的密码不一致")
			}else if(pwd2.length<6){
				alert("密码的长度不能少于6位");	
			}
		 document.getElementById("pwd2_msg").style.color="";
		}

function isCheckNull(){
			var phonenum = document.getElementById('phonenum').value;
			if(phonenum == ''){
				alert("手机号码不能为空");
				return false;
			}else return true;
		}


function fun1(x){
	document.getElementById(x).style.color="#FF0000";
}
function fun2(x){
	document.getElementById(x).style.color="";
}
