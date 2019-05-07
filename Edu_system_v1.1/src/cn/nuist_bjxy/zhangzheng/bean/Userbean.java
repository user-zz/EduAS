package cn.nuist_bjxy.zhangzheng.bean;

/**
 * @author ZhengZheng
 * @description 用户表信息
 * @date 下午3:41:19
 */

public class Userbean {
	private String username; //用户名
	private String uno; //学工号
	private String  password; //密码
	private String phonenum; //电话号码
	private String nickname; //昵称
	private String  email; //电子邮箱
	private int id; //身份ID
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUno() {
		return uno;
	}
	public void setUno(String uno) {
		this.uno = uno;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
