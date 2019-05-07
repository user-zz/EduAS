package cn.nuist_bjxy.zhangzheng.bean;

/**
 * @author ZhengZheng
 * @description 
 * @date 下午1:58:59
 */

public class Message {
	private String mno; //编号
	private  String message; //内容
	private  String musername; //发送者
	private  String   mtime; //时间
	
	
	public String getMno() {
		return mno;
	}

	public void setMno(String mno) {
		this.mno = mno;
	}

	public String getMusername() {
		return musername;
	}

	public void setMusername(String musername) {
		this.musername = musername;
	}

	public String getMtime() {
		return mtime;
	}

	public void setMtime(String mtime) {
		this.mtime = mtime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
