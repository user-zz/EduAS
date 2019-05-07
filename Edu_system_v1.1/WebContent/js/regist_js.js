function check_username(){
			var username = document.getElementById('username').value;
			var firstchar = username.substring(0,1);
			if(firstchar<'A'){
				alert("用户名必须以大写字母开头");
			}else if(firstchar>'Z'){
				alert("用户名必须以大写字母开头");
			}else if( username.length<6){
				alert(" 用户名不得少于6位");
			}
		}
		
		function check_pwd(){
			var pwd1 = document.getElementById('pwd1').value;
			var pwd2 = document.getElementById('pwd2').value;
		    if(pwd1!=pwd2){
				alert("两次输入的密码不一致")
			}else if(pwd2.length<6){
				alert("密码的长度不能少于6位");	
			}
		}
		
		function isCheckNull(){
			var uno = document.getElementById('uno').value;
			var phonenum = document.getElementById('phonenum').value;
			if(uno == ''){
				alert("学工号不能为空");
				return false;
			}else if(phonenum == ''){
				alert("手机号码不能为空");
				return false;
			}else return true;
		}