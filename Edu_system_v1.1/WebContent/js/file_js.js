function isFileNull(){
		
		var file=document.fileform.upfile.value; 
        if(file == ""){
            alert("上传内容不能为空！");
            return false;
        }else{
        	return true;
        }
}
