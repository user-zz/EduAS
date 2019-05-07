package cn.nuist_bjxy.zhangzheng.bean;

public class MysqlUser {
	private String uri="jdbc:mysql://localhost/eduas_db?serverTimezone=UTC&characterEncoding=UTF-8"; //数据库地址
	private String username="root"; //数据库用户名
	private String  password="123456"; //数据库密码
	
	public MysqlUser() {
		
	}

	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
