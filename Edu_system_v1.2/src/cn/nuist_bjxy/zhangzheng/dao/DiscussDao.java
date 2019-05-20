package cn.nuist_bjxy.zhangzheng.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.nuist_bjxy.zhangzheng.bean.Discussbean;
import cn.nuist_bjxy.zhangzheng.bean.MysqlUser;
import cn.nuist_bjxy.zhangzheng.bean.Pagebean;

/**
 * @author ZhengZheng
 * @description  处理评论信息
 * @date 下午3:01:50
 */

public class DiscussDao {
	
	/*
	 * @description 查询出待审核的评论内容
	 * @param 传入两个分页查询需要的参数
	 * @return 返回一个评论消息列表
	 * 
	 * */
	public static List<Discussbean> checkDiscussList(int StartRow,int PageSize){
		
		List<Discussbean> discusslist = new ArrayList<>();
		
		MysqlUser mysqlUser=new MysqlUser();
		SqlConnect sqlConnect=new SqlConnect();
		
	//	Pagebean page = new Pagebean();
	//	List<Pagebean> pageList= new ArrayList<>();
		
		try {
				Connection conn;
				//使用connMysql()方法连接数据库
				conn=sqlConnect.connMysql(mysqlUser.getUri(), mysqlUser.getUsername(), mysqlUser.getPassword());
			/*	Statement statement = conn.createStatement();
			
				//获取总记录数
				ResultSet rs = statement.executeQuery("SELECT count(*) from discuss_table");
				rs.next();
				
				
				pageList.add(page); //把page对象放入list中
				
				*/
				PreparedStatement ps = null;
				
				//按条件设置SQL语句
			
				String sql_select="SELECT D_no,D_title,D_username,D_mess FROM discuss_table WHERE D_pass=?  LIMIT "+StartRow+","+PageSize;
				ps = conn.prepareStatement(sql_select);
				ps.setString(1, "F");
	
				ResultSet rSet=ps.executeQuery();
				
				while (rSet.next()) {
	               Discussbean discussbean = new Discussbean();
	               	               
	               discussbean.setD_no(rSet.getString(1));
	               discussbean.setD_title(rSet.getString(2));
	               discussbean.setD_username(rSet.getString(3));
	               discussbean.setD_mess(rSet.getString(4));
	               
	               discusslist.add(discussbean);
	              
				}
	                conn.close();
	    			
				} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("系统故障，请联系开发人员");
			}
		
		return discusslist;
	}
	
	/*
	 *@description 通过审查
	 *@param 文件编号：Dno
	 * @return  boolean值
	 * */
	
	public static boolean isPassDscsCheck(String dno){
		
		MysqlUser mysqlUser=new MysqlUser();
		SqlConnect sqlConnect=new SqlConnect();
	
		try {
			//数据库连接
			Connection conn = sqlConnect.connMysql(mysqlUser.getUri(), mysqlUser.getUsername(), mysqlUser.getPassword());
			Statement statement = conn.createStatement();
			
			//向数据库中插入数据
			String updata_sql = " UPDATE discuss_table SET D_pass = 'T' WHERE D_no="+dno;
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
			ResultSet rs = statement.executeQuery("SELECT count(*) from discuss_table WHERE D_pass= 'F' ");
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
	
	public static List<Discussbean> DscsCheckRstList(String d_no){
		
		List<Discussbean> dscsRstList = new ArrayList<>();
		
		MysqlUser mysqlUser=new MysqlUser();
		SqlConnect sqlConnect=new SqlConnect();
		
		try {
				Connection conn;
				//使用connMysql()方法连接数据库
				conn=sqlConnect.connMysql(mysqlUser.getUri(), mysqlUser.getUsername(), mysqlUser.getPassword());
				PreparedStatement ps = null;
				
				//按条件设置SQL语句
			
				String sql_select="SELECT D_title,D_username,D_mess,D_time FROM discuss_table WHERE D_no=? ";
				ps = conn.prepareStatement(sql_select);
				ps.setString(1, d_no);
	
				ResultSet rSet=ps.executeQuery();
				
				while (rSet.next()) {
	               Discussbean dscsbean = new Discussbean();
	               
	               dscsbean.setD_title(rSet.getString(1));
	               dscsbean.setD_username(rSet.getString(2));
	               dscsbean.setD_mess(rSet.getString(3));
	               dscsbean.setD_time(rSet.getString(4));
	               
	               dscsRstList.add(dscsbean);
				}
	                conn.close();
	    			
				} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("系统故障，请联系开发人员");
			}
	
		return dscsRstList;
	}
	
	 /*
     * 删除评论
     * */
    
    public static void remove(String d_no) {
    	MysqlUser mysqlUser=new MysqlUser();
		SqlConnect sqlConnect=new SqlConnect();
		try {
			Connection conn;
			//使用connMysql()方法连接数据库
			conn=sqlConnect.connMysql(mysqlUser.getUri(), mysqlUser.getUsername(), mysqlUser.getPassword());

			String sql = "delete from discuss_table where D_no=?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, d_no);
            ps.execute();
            
            //conn.commit(); mysql默认开启了autocommit	
            conn.close();
            
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("系统故障，请联系开发人员");
		}
    }
    
    public static List<Discussbean> seeDiscsList(String thno){
    	
    	List<Discussbean> discsList = new ArrayList<>();
    	
    	MysqlUser mysqlUser=new MysqlUser();
		SqlConnect sqlConnect=new SqlConnect();
		
		try {
				Connection conn;
				//使用connMysql()方法连接数据库
				conn=sqlConnect.connMysql(mysqlUser.getUri(), mysqlUser.getUsername(), mysqlUser.getPassword());
				PreparedStatement ps = null;
				
				//按条件设置SQL语句
			
				String sql_select="SELECT D_username,D_mess,D_time FROM discuss_table WHERE Th_no=? ";
				ps = conn.prepareStatement(sql_select);
				ps.setString(1, thno);
	
				ResultSet rSet=ps.executeQuery();
				
				while (rSet.next()) {
	               Discussbean discsbean = new Discussbean();
	               
	               discsbean.setD_username(rSet.getString(1));
	               discsbean.setD_mess(rSet.getString(2));
	               discsbean.setD_time(rSet.getString(3));
	               
	               discsList.add(discsbean);
				}
	                conn.close();
	    			
				} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("系统故障，请联系开发人员");
			}
		
    	return discsList;
    }
   
}
