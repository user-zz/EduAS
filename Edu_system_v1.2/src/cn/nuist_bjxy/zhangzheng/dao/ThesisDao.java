package cn.nuist_bjxy.zhangzheng.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.nuist_bjxy.zhangzheng.bean.MysqlUser;
import cn.nuist_bjxy.zhangzheng.bean.Pagebean;
import cn.nuist_bjxy.zhangzheng.bean.Thesisbean;

/**
 * @author ZhengZheng
 * @description 
 * @date 下午8:09:07
 */

public class ThesisDao {

	/**
	 * @description 按条件遍历出论题并放入ThesisList中
	 * @param 开始显示记录的编号：StartRow,每页显示的记录数：PageSize
	 * 
	 */
	public static List<Thesisbean> checkList(int StartRow,int PageSize){
		
		List<Thesisbean> checkList = new ArrayList<>();
		
		MysqlUser mysqlUser=new MysqlUser();
		SqlConnect sqlConnect=new SqlConnect();
		
		Pagebean page = new Pagebean();
		List<Pagebean> pageList= new ArrayList<>();
		
		try {
				Connection conn;
				//使用connMysql()方法连接数据库
				conn=sqlConnect.connMysql(mysqlUser.getUri(), mysqlUser.getUsername(), mysqlUser.getPassword());
				Statement statement = conn.createStatement();
				
				//获取总记录数
				ResultSet rs = statement.executeQuery("SELECT count(*) from th_table");
				rs.next();
				
				
				pageList.add(page); //把page对象放入list中
				
				PreparedStatement ps = null;
				
				//按条件设置SQL语句
			
				String sql_select="SELECT Title,Thcontent,Thusername,Thtime,Thtype,Thno FROM th_table WHERE Thpass=?  LIMIT "+StartRow+","+PageSize;
				ps = conn.prepareStatement(sql_select);
				ps.setString(1, "F");
	
				ResultSet rSet=ps.executeQuery();
				
				while (rSet.next()) {
	               Thesisbean thbean = new Thesisbean();
	               	               
	               thbean.setTitle(rSet.getString(1));
	               thbean.setThcontent(rSet.getString(2));
	               thbean.setThusername(rSet.getString(3));
	               thbean.setThtime(rSet.getString(4));
	               thbean.setThtype(rSet.getString(5));
	               thbean.setThno(rSet.getString(6));
	               
	               checkList.add(thbean);
				}
	                conn.close();
	    			
				} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("系统故障，请联系开发人员");
			}
        return checkList;
    }
	
	/*
	 *@description 管理员按编号查看论题详情
	 *@param  参数：论题编号Thno
	 * 
	 * */
	public static List<Thesisbean> ThesisCheckRstList(String thno){
			List<Thesisbean> thesisCheckRstList = new ArrayList<>();
		
			MysqlUser mysqlUser=new MysqlUser();
			SqlConnect sqlConnect=new SqlConnect();
			
			try {
					Connection conn;
					//使用connMysql()方法连接数据库
					conn=sqlConnect.connMysql(mysqlUser.getUri(), mysqlUser.getUsername(), mysqlUser.getPassword());
					PreparedStatement ps = null;
					
					//按条件设置SQL语句
				
					String sql_select="SELECT Title,Thcontent,Thusername,Thtime,Thtype,Thpass FROM th_table WHERE Thno=? ";
					ps = conn.prepareStatement(sql_select);
					ps.setString(1, thno);
		
					ResultSet rSet=ps.executeQuery();
					
					while (rSet.next()) {
		               Thesisbean thbean = new Thesisbean();
		               	               
		               thbean.setTitle(rSet.getString(1));
		               thbean.setThcontent(rSet.getString(2));
		               thbean.setThusername(rSet.getString(3));
		               thbean.setThtime(rSet.getString(4));
		               thbean.setThtype(rSet.getString(5));
		               thbean.setThpass(rSet.getString(6));
		               
		               thesisCheckRstList.add(thbean);
					}
		                conn.close();
		    			
					} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("系统故障，请联系开发人员");
				}
	        return thesisCheckRstList;
	    }
	
	/*
	 *@description 通过审查
	 *@param 文件编号：Thno
	 * @return  boolean值
	 * */
	
	public static boolean isPassThesisCheck(String thno){
		
		MysqlUser mysqlUser=new MysqlUser();
		SqlConnect sqlConnect=new SqlConnect();
	
		try {
			//数据库连接
			Connection conn = sqlConnect.connMysql(mysqlUser.getUri(), mysqlUser.getUsername(), mysqlUser.getPassword());
			Statement statement = conn.createStatement();
			
			//向数据库中插入数据
			String updata_sql = " UPDATE th_table SET Thpass = 'T' WHERE Thno="+thno;
			statement.executeUpdate(updata_sql);
			
			conn.close();
			
			return true;
		} catch (Exception e) {
				// TODO: handle exception
				System.out.println("系统数据库故障，请联系管理员,注册失败!");
				e.printStackTrace();
				return false;	
			}
	}
	
	/**
	 * @description 查出总记录数并把Page对象放入list中
	 * 
	 */
	public static List<Pagebean> pageList(){
		
		SqlConnect sConnect = new SqlConnect();
		MysqlUser mUser = new MysqlUser();
		Pagebean page = new Pagebean();
		List<Pagebean> pageList= new ArrayList<>();
		
		try {
			
			//使用connMysql()方法连接数据库
			Connection connection=sConnect.connMysql(mUser.getUri(), mUser.getUsername(), mUser.getPassword());
			Statement statement=connection.createStatement();
			
			//获取总记录数
			ResultSet rs = statement.executeQuery("SELECT count(*) from th_table WHERE Thpass= 'F' ");
			rs.next();
			page.setRecordCount(rs.getInt(1));
			pageList.add(page); //把page对象放入list中
			
			connection.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("系统故障，请联系开发人员");
		}

		return pageList;
	} 
	
	
	 /*
     * 删除论题
     * */
    
    public static void remove(String thno) {
    	MysqlUser mysqlUser=new MysqlUser();
		SqlConnect sqlConnect=new SqlConnect();
		
		try {
			Connection conn;
			//使用connMysql()方法连接数据库
			conn=sqlConnect.connMysql(mysqlUser.getUri(), mysqlUser.getUsername(), mysqlUser.getPassword());

			String sql_1 = "delete from discuss_table where Th_no=?";
			String sql_2 = "delete from th_table where Thno=?";
			
			PreparedStatement ps_1 = conn.prepareStatement(sql_1);
			PreparedStatement ps_2 = conn.prepareStatement(sql_2);
			
			ps_1.setString(1, thno);
            ps_1.execute();
            
            ps_2.setString(1, thno);
            ps_2.execute();
            
            //conn.commit(); mysql默认开启了autocommit	
            conn.close();
            
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("系统故障，请联系开发人员");
		}
    }
    
    /*
	 *@description 按发布者查看论题
	 *@param  参数：发布者
	 * 
	 * */
    public static List<Thesisbean> SearchThesis(String thusername){
    	System.out.println("个人中心查看自己发布的论题");
    	List<Thesisbean> searchThesisRstList = new ArrayList<>();
    	
    	MysqlUser mysqlUser=new MysqlUser();
		SqlConnect sqlConnect=new SqlConnect();
		
		try {
				Connection conn;
				//使用connMysql()方法连接数据库
				conn=sqlConnect.connMysql(mysqlUser.getUri(), mysqlUser.getUsername(), mysqlUser.getPassword());
				PreparedStatement ps = null;
				
					String sql_select="SELECT Thno,Title,Thcontent,Thusername,Thtime,Thtype,Thclickrate FROM th_table WHERE Thusername=?";
					ps = conn.prepareStatement(sql_select);
					ps.setString(1, thusername);

					ResultSet rSet=ps.executeQuery();
					
					while (rSet.next()) {
			               Thesisbean thbean = new Thesisbean();
			               	
			               thbean.setThno(rSet.getString(1));
			               thbean.setTitle(rSet.getString(2));
			               thbean.setThcontent(rSet.getString(3));
			               thbean.setThusername(rSet.getString(4));
			               thbean.setThtime(rSet.getString(5));
			               thbean.setThtype(rSet.getString(6));
			               thbean.setThclickrate(rSet.getInt(7));
			               
			               searchThesisRstList.add(thbean);
						}
					     conn.close();
	    			
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("系统故障，请联系开发人员");
				}
    	return searchThesisRstList;
    }
    
    
    
    
    /*
	 *@description 按条件查看论题
	 *@param  参数：发布者 和 标题
	 * 
	 * */
 public static List<Thesisbean> SearchThesis(String title,String thusername){
    	
	 	System.out.println("条件搜索论题信息");
    	List<Thesisbean> searchThesisRstList = new ArrayList<>();
    	
    	MysqlUser mysqlUser=new MysqlUser();
		SqlConnect sqlConnect=new SqlConnect();
		
		try {
				Connection conn;
				//使用connMysql()方法连接数据库
				conn=sqlConnect.connMysql(mysqlUser.getUri(), mysqlUser.getUsername(), mysqlUser.getPassword());
				PreparedStatement ps = null;
				
				//按条件设置SQL语句
				if(title == null || title.equals("")) {
					
					String sql_select="SELECT Thno,Title,Thcontent,Thusername,Thtime,Thtype,Thclickrate FROM th_table WHERE Thusername=? AND Thpass='T'";
					ps = conn.prepareStatement(sql_select);
					ps.setString(1, thusername);

					ResultSet rSet=ps.executeQuery();
					
					while (rSet.next()) {
			               Thesisbean thbean = new Thesisbean();
			               	
			               thbean.setThno(rSet.getString(1));
			               thbean.setTitle(rSet.getString(2));
			               thbean.setThcontent(rSet.getString(3));
			               thbean.setThusername(rSet.getString(4));
			               thbean.setThtime(rSet.getString(5));
			               thbean.setThtype(rSet.getString(6));
			               thbean.setThclickrate(rSet.getInt(7));
			               
			               searchThesisRstList.add(thbean);
						}
					
				}else{
					
					String sql_select="SELECT Thno,Title,Thcontent,Thusername,Thtime,Thtype,Thclickrate FROM th_table WHERE Title=? AND Thpass='T'";
					ps = conn.prepareStatement(sql_select);
					ps.setString(1, title);
					ResultSet rSet=ps.executeQuery();
					
					while (rSet.next()) {
			               Thesisbean thbean = new Thesisbean();
			               	
			               thbean.setThno(rSet.getString(1));
			               thbean.setTitle(rSet.getString(2));
			               thbean.setThcontent(rSet.getString(3));
			               thbean.setThusername(rSet.getString(4));
			               thbean.setThtime(rSet.getString(5));
			               thbean.setThtype(rSet.getString(6));
			               thbean.setThclickrate(rSet.getInt(7));
			               
			               searchThesisRstList.add(thbean);
						}		
				}
	                conn.close();
	    			
				} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("系统故障，请联系开发人员");
			}
		
    	return searchThesisRstList;
    }
 
 public static List<Thesisbean> adminSearch(String title,String thusername){
 	
	 	System.out.println("条件搜索论题信息");
 	List<Thesisbean> searchThesisRstList = new ArrayList<>();
 	
 	MysqlUser mysqlUser=new MysqlUser();
		SqlConnect sqlConnect=new SqlConnect();
		
		try {
				Connection conn;
				//使用connMysql()方法连接数据库
				conn=sqlConnect.connMysql(mysqlUser.getUri(), mysqlUser.getUsername(), mysqlUser.getPassword());
				PreparedStatement ps = null;
				
				//按条件设置SQL语句
				if(title == null || title.equals("")) {
					
					String sql_select="SELECT Thno,Title,Thcontent,Thusername,Thtime,Thtype,Thclickrate FROM th_table WHERE Thusername=? AND Thpass='F'";
					ps = conn.prepareStatement(sql_select);
					ps.setString(1, thusername);

					ResultSet rSet=ps.executeQuery();
					
					while (rSet.next()) {
			               Thesisbean thbean = new Thesisbean();
			               	
			               thbean.setThno(rSet.getString(1));
			               thbean.setTitle(rSet.getString(2));
			               thbean.setThcontent(rSet.getString(3));
			               thbean.setThusername(rSet.getString(4));
			               thbean.setThtime(rSet.getString(5));
			               thbean.setThtype(rSet.getString(6));
			               thbean.setThclickrate(rSet.getInt(7));
			               
			               searchThesisRstList.add(thbean);
						}
					
				}else{
					
					String sql_select="SELECT Thno,Title,Thcontent,Thusername,Thtime,Thtype,Thclickrate FROM th_table WHERE Title=? AND Thpass='F'";
					ps = conn.prepareStatement(sql_select);
					ps.setString(1, title);
					ResultSet rSet=ps.executeQuery();
					
					while (rSet.next()) {
			               Thesisbean thbean = new Thesisbean();
			               	
			               thbean.setThno(rSet.getString(1));
			               thbean.setTitle(rSet.getString(2));
			               thbean.setThcontent(rSet.getString(3));
			               thbean.setThusername(rSet.getString(4));
			               thbean.setThtime(rSet.getString(5));
			               thbean.setThtype(rSet.getString(6));
			               thbean.setThclickrate(rSet.getInt(7));
			               
			               searchThesisRstList.add(thbean);
						}		
				}
	                conn.close();
	    			
				} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("系统故障，请联系开发人员");
			}
		
 	return searchThesisRstList;
 }
    	
}
	
