package cn.nuist_bjxy.zhangzheng.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.nuist_bjxy.zhangzheng.bean.Filebean;
import cn.nuist_bjxy.zhangzheng.bean.Homeworkbean;
import cn.nuist_bjxy.zhangzheng.bean.MysqlUser;

/**
 * @author ZhengZheng
 * @description 
 * @date 下午4:30:08
 */

public class HomeworkDao {
	
	public static void hwAdd(Homeworkbean hwbean) {
		MysqlUser mysqlUser=new MysqlUser();
		SqlConnect sqlConnect=new SqlConnect();
		try {
			Connection conn;
			//使用connMysql()方法连接数据库
			conn=sqlConnect.connMysql(mysqlUser.getUri(), mysqlUser.getUsername(), mysqlUser.getPassword());
			//Statement stmt=conn.createStatement();
		
			//插入数据
			String sql_insert="INSERT INTO hw_table VALUES(?,?,?,?,?)";
			PreparedStatement pStatement=conn.prepareStatement(sql_insert);
			
			pStatement.setInt(1, hwbean.getHw_no());
			pStatement.setString(2, hwbean.getHw_stumajor());
			pStatement.setString(3,hwbean.getHw_stuclass());
			pStatement.setString(4,hwbean.getHw_stuname()); 
			pStatement.setString(5, hwbean.getHw_teaname());
			
			pStatement.executeUpdate();
			conn.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("系统故障，请联系开发人员");
		}
    }
	
	/*
	 * 按条件查询相关作业
	 * 
	 **/
	
	 public static List<Filebean> hwSelectList(String stumajor,String stuclass,String teaname,String stuname){
	    	MysqlUser mysqlUser=new MysqlUser();
			SqlConnect sqlConnect=new SqlConnect();
			List<Filebean> fileList = new ArrayList<>();
			int i,max;
			
			try {
				
				Connection conn;
				//使用connMysql()方法连接数据库
				conn=sqlConnect.connMysql(mysqlUser.getUri(), mysqlUser.getUsername(), mysqlUser.getPassword());
				PreparedStatement ps = null;
				Statement statement=conn.createStatement();
				
				//获取总记录数
				ResultSet rs = statement.executeQuery("SELECT count(*) from hw_table");
				rs.next();
				max = rs.getInt(1);
				
				//根据条件查询
				if(stuname.equals("") || stuname == null) {
					System.out.println("stuname:"+stuname);
					System.out.println("teaname:"+teaname);
					for(i=0; i<max ; i++) {

						String sql_select = "SELECT Fno,Fusername,Fname,Ftype,Fsize FROM f_table WHERE Fno=(SELECT HW_no FROM hw_table WHERE HW_teaName=? AND HW_stuMajor=? AND HW_stuClass=? LIMIT "+i+",1 )";
						ps = conn.prepareStatement(sql_select);
						ps.setString(1, teaname);
						ps.setString(2, stumajor);
						ps.setString(3, stuclass);
						System.out.println("开始查找作业...");
						ResultSet rSet = ps.executeQuery();
						
						while (rSet.next()) {
				               Filebean filebean = new Filebean();
				               
				                filebean.setFno(rSet.getInt(1));
				                filebean.setFusername(rSet.getString(2));
				                filebean.setFname(rSet.getString(3));
				                filebean.setFtype(rSet.getString(4));
				                filebean.setFsize(new BigDecimal(rSet.getDouble(5) / 1024).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				                
				                fileList.add(filebean);
				                System.out.println("查找成功，返回列表");
							}
					}
					
				}else{
					
					for(i=0; i<max ; i++){

						String sql_select= "SELECT Fno,Fusername,Fname,Ftype,Fsize FROM f_table WHERE Fno=(SELECT HW_no FROM hw_table WHERE HW_stuName=? AND HW_teaName=? AND HW_stuMajor=? AND HW_stuClass=? LIMIT 0,1)";
						ps = conn.prepareStatement(sql_select);
						ps.setString(1, stuname);
						ps.setString(2, teaname);
						ps.setString(3, stumajor);
						ps.setString(4, stuclass);
	
						ResultSet rSet = ps.executeQuery();
						
						while (rSet.next()) {
					           Filebean filebean = new Filebean();
					           
					           System.out.println("test1");
					           filebean.setFno(rSet.getInt(1));
					           filebean.setFusername(rSet.getString(2));
					           filebean.setFname(rSet.getString(3));
					           filebean.setFtype(rSet.getString(4));
					           filebean.setFsize(new BigDecimal(rSet.getDouble(5) / 1024).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
					           System.out.println("test2");
					           fileList.add(filebean);
					           System.out.println("查找成功，返回列表");
						}
					}
				}
	                conn.close();	
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("系统故障，请联系开发人员");
				}
	        return fileList;
	    }
	
}
