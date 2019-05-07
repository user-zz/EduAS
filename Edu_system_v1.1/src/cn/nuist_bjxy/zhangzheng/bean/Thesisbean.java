package cn.nuist_bjxy.zhangzheng.bean;

/**
 * @author ZhengZheng
 * @description 
 * @date 下午9:45:19
 */

public class Thesisbean {
	private String thno; //论题编号
	private String title; //论题标题
	private String thusername; //发布者
	private String thcontent; //内容
	private String thtime; //发布时间
	private String thtype; //类别
	private int thclickrate; //点击量
	private String thpass = "F"; //审核标识，默认为F，表示未审核或未通过
	
	
	public String getThno() {
		return thno;
	}
	public void setThno(String thno) {
		this.thno = thno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getThusername() {
		return thusername;
	}
	public void setThusername(String thusername) {
		this.thusername = thusername;
	}
	public String getThcontent() {
		return thcontent;
	}
	public void setThcontent(String thcontent) {
		this.thcontent = thcontent;
	}
	public String getThtime() {
		return thtime;
	}
	public void setThtime(String thtime) {
		this.thtime = thtime;
	}
	public String getThtype() {
		return thtype;
	}
	public void setThtype(String thtype) {
		this.thtype = thtype;
	}
	public int getThclickrate() {
		return thclickrate;
	}
	public void setThclickrate(int thclickrate) {
		this.thclickrate = thclickrate;
	}
	public String getThpass() {
		return thpass;
	}
	public void setThpass(String thpass) {
		this.thpass = thpass;
	}
	
	
}
