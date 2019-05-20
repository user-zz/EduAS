package cn.nuist_bjxy.zhangzheng.bean;

/**
 * @author ZhengZheng
 * @description 系统公告bean
 * @date 上午9:33:22
 */

public class SystemMsgbean {
	private String msgno; //编号
	private String msgusername; //发布者
	private String msgtime; //发布时间
	private String msgcontent; //内容
	
	public String getMsgno() {
		return msgno;
	}
	public void setMsgno(String msgno) {
		this.msgno = msgno;
	}
	public String getMsgusername() {
		return msgusername;
	}
	public void setMsgusername(String msgusername) {
		this.msgusername = msgusername;
	}
	public String getMsgtime() {
		return msgtime;
	}
	public void setMsgtime(String msgtime) {
		this.msgtime = msgtime;
	}
	public String getMsgcontent() {
		return msgcontent;
	}
	public void setMsgcontent(String msgcontent) {
		this.msgcontent = msgcontent;
	}
	
	
}
