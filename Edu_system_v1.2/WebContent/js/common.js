function getRandemNum(){
		var MIN = 1000; //最小值
		var MAX = 10000000; //最大值
		
		var Range = MAX-MIN;
		var Rand = Math.random();
		var num = MIN + Math.round(Rand * Range);
		
		//获取组件id,并设置其值
		var randomnum = document.getElementById("randomnum");
		
		randomnum.value=num;
		
	}