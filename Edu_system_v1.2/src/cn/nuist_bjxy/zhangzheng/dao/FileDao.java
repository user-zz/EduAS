package cn.nuist_bjxy.zhangzheng.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.nuist_bjxy.zhangzheng.bean.Filebean;
import cn.nuist_bjxy.zhangzheng.bean.MysqlUser;
import cn.nuist_bjxy.zhangzheng.bean.Pagebean;

/**
 * @author ZhengZheng
 * @description 
 * @date 下午2:59:07
 */

public class FileDao {
	
	public static void add(Filebean fbean) {
		MysqlUser mysqlUser=new MysqlUser();
		SqlConnect sqlConnect=new SqlConnect();
		try {
			Connection conn;
			//使用connMysql()方法连接数据库
			conn=sqlConnect.connMysql(mysqlUser.getUri(), mysqlUser.getUsername(), mysqlUser.getPassword());
			//Statement stmt=conn.createStatement();
		
			//插入数据
			String sql_insert="INSERT INTO f_table VALUES(?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pStatement=conn.prepareStatement(sql_insert);
			
			pStatement.setInt(1, fbean.getFno());
			pStatement.setString(2, fbean.getFusername());
			pStatement.setString(3,fbean.getFname());
			pStatement.setString(4, fbean.getFpath()); 
			pStatement.setString(5,fbean.getFtype());
			pStatement.setString(6, fbean.getDownloadcount());
			pStatement.setString(7,fbean.getFtime());
			pStatement.setDouble(8, fbean.getFsize());
			pStatement.setString(9, fbean.getFid());
			pStatement.setString(10, fbean.getFpass());
			pStatement.executeUpdate();
			conn.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("系统故障，请联系开发人员");
		}
    }
 
	/*
	 * 遍历出所有文件
	 * */
	
    public static List<Filebean> list() {
    	MysqlUser mysqlUser=new MysqlUser();
		SqlConnect sqlConnect=new SqlConnect();
		List<Filebean> fileList = new ArrayList<>();
		try {
			Connection conn;
			//使用connMysql()方法连接数据库
			conn=sqlConnect.connMysql(mysqlUser.getUri(), mysqlUser.getUsername(), mysqlUser.getPassword());
			Statement stmt=conn.createStatement();
			
			//查找数据
			String sql_select="SELECT Fno,Fusername,Fname,Ftype,Fsize,Fid FROM f_table WHERE Fpass='T' "; //标识T 表示已通过审核
			ResultSet rSet=stmt.executeQuery(sql_select);
			
			while (rSet.next()) {
               Filebean filebean = new Filebean();
               
                filebean.setFno(rSet.getInt(1));
                filebean.setFusername(rSet.getString(2));
                filebean.setFname(rSet.getString(3));
                filebean.setFtype(rSet.getString(4));
                filebean.setFsize(new BigDecimal(rSet.getDouble(5) / 1024).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                filebean.setFid(rSet.getString(6));
                     
                fileList.add(filebean);
			}
                conn.close();
    			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("系统故障，请联系开发人员");
			}
 
        return fileList;
 
    }
 
    public static List<Filebean> selectList(String filetype,String fileusername) {
    	MysqlUser mysqlUser=new MysqlUser();
		SqlConnect sqlConnect=new SqlConnect();
		List<Filebean> fileList = new ArrayList<>();
		try {
			Connection conn;
			//使用connMysql()方法连接数据库
			conn=sqlConnect.connMysql(mysqlUser.getUri(), mysqlUser.getUsername(), mysqlUser.getPassword());
			PreparedStatement ps = null;
			
			//按条件设置SQL语句
			if (filetype.equals("")) {
				String sql_select="SELECT Fno,Fusername,Fname,Ftype,Fsize FROM f_table WHERE Fusername=? AND Fpass='T' ";
				ps = conn.prepareStatement(sql_select);
				ps.setString(1, fileusername);
				
			} else if(fileusername.equals("")){	
				String sql_select="SELECT Fno,Fusername,Fname,Ftype,Fsize FROM f_table WHERE Ftype=? AND Fpass='T' ";
				ps = conn.prepareStatement(sql_select);
				ps.setString(1, filetype);	
			}else {
				String sql_select="SELECT Fno,Fusername,Fname,Ftype,Fsize FROM f_table WHERE Fusername=? AND Ftype=? AND Fpass='T' ";
				ps = conn.prepareStatement(sql_select);
				ps.setString(1, fileusername);
				ps.setString(2, filetype);	
				
			}
			
			ResultSet rSet=ps.executeQuery();
			
			while (rSet.next()) {
               Filebean filebean = new Filebean();
               
                filebean.setFno(rSet.getInt(1));
                filebean.setFusername(rSet.getString(2));
                filebean.setFname(rSet.getString(3));
                filebean.setFtype(rSet.getString(4));
                filebean.setFsize(new BigDecimal(rSet.getDouble(5) / 1024).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                
                fileList.add(filebean);
			}
                conn.close();
    			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("系统故障，请联系开发人员");
			}
        return fileList;
    }
    
    /*
     * 删除文件
     * */
    
    public static void remove(int id) {
    	MysqlUser mysqlUser=new MysqlUser();
		SqlConnect sqlConnect=new SqlConnect();
		try {
			Connection conn;
			//使用connMysql()方法连接数据库
			conn=sqlConnect.connMysql(mysqlUser.getUri(), mysqlUser.getUsername(), mysqlUser.getPassword());

			String sql_1 = "delete from hw_table where HW_no=?";
			String sql_2 = "delete from f_table where Fno=?";
			PreparedStatement ps_1 = conn.prepareStatement(sql_1);
			PreparedStatement ps_2 = conn.prepareStatement(sql_2);
			
            ps_1.setInt(1,id);
            ps_1.execute();
            
            ps_2.setInt(1,id);
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
    * @description 查找出待审核的文件材料
    * @param 传入两个实现分页查询的参数
    * @return 返回一个文件列表
    * 
    * */ 
    
    public static List<Filebean>  checkFileList(int StartRow,int PageSize){
    
    	List<Filebean> filelist = new ArrayList<>();
    	
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
				ResultSet rs = statement.executeQuery("SELECT count(*) from f_table");
				rs.next();
				
				
				pageList.add(page); //把page对象放入list中
				
				PreparedStatement ps = null;
				
				//按条件设置SQL语句
			
				String sql_select="SELECT Fno,Fusername,Fname,Ftype,Ftime FROM f_table WHERE Fpass=?  LIMIT "+StartRow+","+PageSize;
				ps = conn.prepareStatement(sql_select);
				ps.setString(1, "F");
	
				ResultSet rSet=ps.executeQuery();
				
				while (rSet.next()) {
	               Filebean filebean = new Filebean();
	               	               
	               filebean.setFno(rSet.getInt(1));
	               filebean.setFusername(rSet.getString(2));
	               filebean.setFname(rSet.getString(3));
	               filebean.setFtype(rSet.getString(4));
	               filebean.setFtime(rSet.getString(5));
	               
	               filelist.add(filebean);
				}
	                conn.close();
	    			
				} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("系统故障，请联系开发人员");
			}
   
    	return filelist;
    }
   
    /*
	 *@description 通过审查
	 *@param 文件编号：Fno
	 * @return  boolean值
	 * */
	
	public static boolean isPassFileCheck(String fno){
		
		MysqlUser mysqlUser=new MysqlUser();
		SqlConnect sqlConnect=new SqlConnect();
	
		try {
			//数据库连接
			Connection conn = sqlConnect.connMysql(mysqlUser.getUri(), mysqlUser.getUsername(), mysqlUser.getPassword());
			Statement statement = conn.createStatement();
			
			//向数据库中插入数据
			String updata_sql = " UPDATE f_table SET Fpass = 'T' WHERE Fno="+fno;
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
			ResultSet rs = statement.executeQuery("SELECT count(*) from f_table WHERE Fpass= 'F' ");
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
     * load 
     * 
     * 实现文件下载所需的资源
     * 
     * */
    public static Filebean load(int fno) {
    	MysqlUser mysqlUser=new MysqlUser();
		SqlConnect sqlConnect=new SqlConnect();
		Filebean filebean = new Filebean();
		PreparedStatement ps = null;
		
		try {
			Connection conn;
			//使用connMysql()方法连接数据库
			conn=sqlConnect.connMysql(mysqlUser.getUri(), mysqlUser.getUsername(), mysqlUser.getPassword());
			
			//查找数据
			String sql_select="SELECT Fname,Fpath,Fid FROM f_table WHERE Fno=? ";
			ps = conn.prepareStatement(sql_select);
			ps.setInt(1, fno);
			ResultSet rSet=ps.executeQuery();
			
			while (rSet.next()) {
                filebean.setFname(rSet.getString(1));
                filebean.setFpath(rSet.getString(2));
                filebean.setFid(rSet.getString(3));
			}
                conn.close();
    			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("系统故障，请联系开发人员");
			}
        return filebean;
    }
    
}

