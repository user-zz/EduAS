package cn.nuist_bjxy.zhangzheng.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.nuist_bjxy.zhangzheng.bean.MysqlUser;
import cn.nuist_bjxy.zhangzheng.bean.UserMsgbean;

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
	
	public static List<UserMsgbean> userMsgList(String username) {
		List<UserMsgbean> usermsglist = new ArrayList<>();
		UserMsgbean userMsgbean = new UserMsgbean();
		
		MysqlUser mysqlUser=new MysqlUser();
		SqlConnect sqlConnect=new SqlConnect();
		String uno = "";
		
		try {
				Connection conn;
				//使用connMysql()方法连接数据库
				conn=sqlConnect.connMysql(mysqlUser.getUri(), mysqlUser.getUsername(), mysqlUser.getPassword());
				PreparedStatement ps = null;
				
				//按条件设置SQL语句
			
				String sql_select_1="SELECT Username,Uno,Phonenum,Nickname,Email,ID FROM user_table WHERE Username=? ";
				ps = conn.prepareStatement(sql_select_1);
				ps.setString(1, username);
	
				ResultSet rSet_1=ps.executeQuery();
				
				while (rSet_1.next()) {
	               
	               userMsgbean.setUsername(rSet_1.getString(1));
	               userMsgbean.setUno(rSet_1.getString(2));
	               userMsgbean.setPhonenum(rSet_1.getString(3));
	               userMsgbean.setNickname(rSet_1.getString(4));
	               userMsgbean.setEmail(rSet_1.getString(5));
	               String id = rSet_1.getString(6);
	               
	               if(id.equals("0")) {
	            	    userMsgbean.setId("管理员");
	               }
					if(id.equals("1")) {
						userMsgbean.setId("教师");
				    }
					if(id.equals("2")) {
						userMsgbean.setId("学生");
					}
					
	               uno = rSet_1.getString(2);
	 
				}
				
				//按条件设置SQL语句
				
				String sql_select_2="SELECT Name,Major,Class FROM usermsg_table WHERE Uno=? ";
				ps = conn.prepareStatement(sql_select_2);
				ps.setString(1, uno);
	
				ResultSet rSet_2=ps.executeQuery();
				
				while (rSet_2.next()) {
	               
	               userMsgbean.setName(rSet_2.getString(1));
	               userMsgbean.setMajor(rSet_2.getString(2));
	               userMsgbean.setUserclass(rSet_2.getString(3));
	     
				}
					usermsglist.add(userMsgbean);
	                conn.close();
	    			
				} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("系统故障，请联系开发人员");
			}
		
		return usermsglist;
	}
	
	/*
	 * @description 编辑个人信息
	 * @param 用户信息bean
	 * 
	 * */
	
	public static boolean  editUserMsg(UserMsgbean userMsgbean) {
		SqlConnect sConnect = new SqlConnect();
		MysqlUser mUser = new MysqlUser();
				
		try {
			//数据库连接
			Connection conn = sConnect.connMysql(mUser.getUri(), mUser.getUsername(), mUser.getPassword()); 
			Statement statement = conn.createStatement();
			System.out.println("phonenum:"+userMsgbean.getPhonenum());
			System.out.println("nickname:"+userMsgbean.getNickname());
			//向数据库中修改数据
			String updata_sql_1 = " UPDATE user_table SET Phonenum = '"+userMsgbean.getPhonenum()+"',Nickname='"+userMsgbean.getNickname()+"',Email='"+userMsgbean.getEmail()+"' WHERE Username= '"+userMsgbean.getUsername()+"'";
			statement.executeUpdate(updata_sql_1);
			
			//向数据库中修改数据
			String updata_sql_2 = " UPDATE usermsg_table SET Major = '"+userMsgbean.getMajor()+"',Class='"+userMsgbean.getUserclass()+"' WHERE Uno= '"+userMsgbean.getUno()+"'" ;
			statement.executeUpdate(updata_sql_2);
			
			conn.close();
			
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("修改个人信息出错啦！");
			return false;
		}

	}
	
}
