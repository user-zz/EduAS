function isCheckUserNull(){
		var username = document.getElementById('username').value;
		var pwd = document.getElementById('pwd').value;
		if(username == ''){
			alert("账号不能为空");
			return false;;
		}else if(pwd == ''){
			alert("密码不能为空");
			return false;
		}else return true;;
}