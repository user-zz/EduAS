package cn.nuist_bjxy.zhangzheng.dao;

import java.sql.*;

public class SqlConnect {
	
	public Connection connMysql(String uri , String username, String password) {
		
		String diverclassurl="com.mysql.cj.jdbc.Driver"; //这个是MySQL6以后的最新驱动的url
		
			//数据库驱动加载
		try {
			
			Class.forName(diverclassurl);
			System.out.println("驱动加载成功");
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("驱动加载异常");
			e.printStackTrace();
		}
			//连接数据库
		try {
			
			//返回连接对象
			return	 DriverManager.getConnection(uri,username,password);
		
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("数据访问异常");
			e.printStackTrace();
			return null;
		}
	}
}
