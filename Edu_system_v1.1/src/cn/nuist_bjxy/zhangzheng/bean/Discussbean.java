package cn.nuist_bjxy.zhangzheng.bean;

/**
 * @author ZhengZheng
 * @description 
 * @date 下午5:22:02
 */

public class Discussbean {
	private String d_no;  //评论编号
	private String th_no; //论题编号
	private String d_title;  //论题名
	private String d_username; //评论者
	private String d_mess; //内容
	private String d_time; //时间
	private String d_pass = "F"; //审核情况
	
	
	public String getTh_no() {
		return th_no;
	}
	public void setTh_no(String th_no) {
		this.th_no = th_no;
	}
	public String getD_time() {
		return d_time;
	}
	public void setD_time(String d_time) {
		this.d_time = d_time;
	}
	public String getD_pass() {
		return d_pass;
	}
	public void setD_pass(String d_pass) {
		this.d_pass = d_pass;
	}
	public String getD_no() {
		return d_no;
	}
	public void setD_no(String d_no) {
		this.d_no = d_no;
	}
	public String getD_mess() {
		return d_mess;
	}
	public void setD_mess(String d_mess) {
		this.d_mess = d_mess;
	}
	public String getD_title() {
		return d_title;
	}
	public void setD_title(String d_title) {
		this.d_title = d_title;
	}
	public String getD_username() {
		return d_username;
	}
	public void setD_username(String d_username) {
		this.d_username = d_username;
	}
	
}
