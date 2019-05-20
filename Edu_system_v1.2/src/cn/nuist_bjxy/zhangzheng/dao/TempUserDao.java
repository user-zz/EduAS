package cn.nuist_bjxy.zhangzheng.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.nuist_bjxy.zhangzheng.bean.MysqlUser;
import cn.nuist_bjxy.zhangzheng.bean.TempUserbean;

/**
 * @author ZhengZheng
 * @description  处理管理员对用户的临时操作
 * @date 下午5:09:42
 */

public class TempUserDao {
	
	/*
	 * @description 将所需用户数据取出
	 * @param 传入用户名或教工号
	 * @return 返回一个列表结果
	 * 
	 * */
	public static List<TempUserbean> TempUserList (String username , String uno){
		List<TempUserbean> tempuserlist = new ArrayList<>();
		TempUserbean tempUserbean = new TempUserbean();
		
		MysqlUser mysqlUser=new MysqlUser();
		SqlConnect sqlConnect=new SqlConnect();
		
		try {
				Connection conn;
				//使用connMysql()方法连接数据库
				conn=sqlConnect.connMysql(mysqlUser.getUri(), mysqlUser.getUsername(), mysqlUser.getPassword());
				PreparedStatement ps = null;
				
				//先按工号查找
				if(username.equals("") || username == null) {
					
					String sql_select_1="SELECT Username,ID FROM user_table WHERE Uno=? ";
					ps = conn.prepareStatement(sql_select_1);
					ps.setString(1, uno);
					
					ResultSet rSet_1=ps.executeQuery();
					
					while (rSet_1.next()) {
						
		               tempUserbean.setTempusername(rSet_1.getString(1));
		               String id = rSet_1.getString(2);
		               //通过id判断身份
		               if(id.equals("0")) {
		            	   
			               tempUserbean.setTempid("管理员");
			               
		               }else if(id.equals("1")) {
		            	   
		            	   tempUserbean.setTempid("教师");
		            	   
		               }else if(id.equals("2")) {
		            	   
		            	   tempUserbean.setTempid("学生");
		            	   
		               }
					}
					
					String sql_select_2="SELECT Name,Major FROM usermsg_table WHERE Uno=? ";
					ps = conn.prepareStatement(sql_select_2);
					ps.setString(1, uno);
					
					ResultSet rSet_2=ps.executeQuery();
					
					while (rSet_2.next()) {
						
		               tempUserbean.setTemprealname(rSet_2.getString(1));
		               tempUserbean.setTempMajor(rSet_2.getString(2));
		               System.out.println("test:"+rSet_2.getString(1)+rSet_2.getString(1));
					}
					
					//先按用户名查找
				}else if(uno.equals("") || uno == null) {
					
					String sql_select_3="SELECT Uno,ID FROM user_table WHERE Username=? ";
					ps = conn.prepareStatement(sql_select_3);
					ps.setString(1, username);
					
					ResultSet rSet_3=ps.executeQuery();
					
					while (rSet_3.next()) {
						
		               tempUserbean.setTempusername(rSet_3.getString(1)); //工号
		               String id = rSet_3.getString(2); //ID
		              
		               //通过id判断身份
		               if(id.equals("0")) {
		            	   
			               tempUserbean.setTempid("管理员");
			               
		               }else if(id.equals("1")) {
		            	   
		            	   tempUserbean.setTempid("教师");
		            	   
		               }else if(id.equals("2")) {
		            	   
		            	   tempUserbean.setTempid("学生");
		            	   
		               }
		               
		               uno = rSet_3.getString(1);

					}
					
					String sql_select_4="SELECT Name,Major FROM usermsg_table WHERE Uno=? ";
					ps = conn.prepareStatement(sql_select_4);
					ps.setString(1, uno);
					
					ResultSet rSet_2=ps.executeQuery();
					
					while (rSet_2.next()) {
						
		               tempUserbean.setTemprealname(rSet_2.getString(1));
		               tempUserbean.setTempMajor(rSet_2.getString(2));

					}				
				}

					tempuserlist.add(tempUserbean);
	                conn.close();
	    			
				} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("该用户不存在，请确认你的输入是否正确！");
			}
		
		return tempuserlist;
	}
}
