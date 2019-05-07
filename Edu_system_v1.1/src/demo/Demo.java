package demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import cn.nuist_bjxy.zhangzheng.bean.MysqlUser;
import cn.nuist_bjxy.zhangzheng.bean.Userbean;
import cn.nuist_bjxy.zhangzheng.dao.SqlConnect;
import cn.nuist_bjxy.zhangzheng.encryption.Encryption;

public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Userbean userbean=new Userbean();
		
		System.out.println("用户名："+userbean.getUsername());
		/*
		//加密demo
				Encryption encryption=new Encryption();
				String pass1="zz123456！";
				String password = encryption.createPassword(pass1); //加密
				System.out.println("加密后的密码是："+password);
				
				//密码验证
				String pass2 ="123456！";
				if (encryption.authenticatePassword(password, pass2)) {
					System.out.println("123456！是正确的密码。");
				}else if(encryption.authenticatePassword(password,pass1)){
					System.out.println("zz123456！是正确的密码。");
				}else {
					System.out.println("无法验证密码真伪！！！");
				}
				
	 //数据库操作demo
		MysqlUser mysqlUser=new MysqlUser();
		SqlConnect sqlConnect=new SqlConnect();
		try {
			Connection conn;
			//使用connMysql()方法连接数据库
			conn=sqlConnect.connMysql(mysqlUser.getUri(), mysqlUser.getUsername(), mysqlUser.getPassword());
			Statement stmt=conn.createStatement();
		
			//插入数据
			String sql_insert="INSERT INTO user_table VALUES(?,?,?,?,?,?,?)";
			PreparedStatement pStatement=conn.prepareStatement(sql_insert);
			pStatement.setString(1, "Z18860962097");
			pStatement.setString(2, "20152308037");
			pStatement.setString(3, password); //插入加密后的密码
			pStatement.setString(4, "18860962001");
			pStatement.setString(5, "政哥_01");
			pStatement.setString(6,null);
			pStatement.setInt(7, 2);
			pStatement.executeUpdate();
			
			//查找数据
			String sql_select="SELECT * FROM user_table WHERE Username='ZZ001' ";
			ResultSet rSet=stmt.executeQuery(sql_select);
			while (rSet.next()) {
				System.out.println("Username:"+rSet.getString(1));
				System.out.println("Uno:"+rSet.getString(2));
				System.out.println("Password:"+rSet.getString(3));
				System.out.println("Phonenum:"+rSet.getString(4));
				System.out.println("Nickname:"+rSet.getString(5));
				System.out.println("E-mail:"+rSet.getString(6));
				System.out.println("ID:"+rSet.getString(7));
			}
		    conn.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("系统故障，请联系开发人员");
		}
		*/
		
	}
}
