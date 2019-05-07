package cn.nuist_bjxy.zhangzheng.dao;

import java.sql.Connection;
import java.sql.Statement;

import cn.nuist_bjxy.zhangzheng.bean.MysqlUser;

/**
 * @author ZhengZheng
 * @description  个人账户相关操作
 * @date 下午6:22:42
 */

public class UserDao {

	/*
	 *@description 重置密码
	 *@param 用户名，原密码，欲修改的密码，绑定的手机号
	 * @return true or false
	 * 
	 * */
	
	public static boolean ResetPwd(String username, String startpwd, String pwd, String phonenum) {
		
		SqlConnect sConnect = new SqlConnect();
		MysqlUser mUser = new MysqlUser();
				
		try {
			//数据库连接
			Connection conn = sConnect.connMysql(mUser.getUri(), mUser.getUsername(), mUser.getPassword()); 
			Statement statement = conn.createStatement();
			
			//向数据库中插入数据
			String updata_sql = " UPDATE user_table SET Password = '"+pwd+"' WHERE Username= '"+username+"' AND Password= '"+startpwd+"'AND Phonenum= '"+phonenum+"' " ;
			statement.executeUpdate(updata_sql);
			
			conn.close();
			
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("修改密码出错啦！");
			return false;
		}
	}
}
