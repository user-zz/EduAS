package cn.nuist_bjxy.zhangzheng.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.nuist_bjxy.zhangzheng.bean.MysqlUser;
import cn.nuist_bjxy.zhangzheng.bean.SystemMsgbean;

/**
 * @author ZhengZheng
 * @description 系统公告数据处理
 * @date 上午9:37:29
 */

public class SystemMsgDao {
	
	public static List<SystemMsgbean> systemMsgList (){
		List<SystemMsgbean> sysmsglist = new ArrayList<>();
		
		MysqlUser mysqlUser=new MysqlUser();
		SqlConnect sqlConnect=new SqlConnect();
		
		try {
				Connection conn;
				//使用connMysql()方法连接数据库
				conn=sqlConnect.connMysql(mysqlUser.getUri(), mysqlUser.getUsername(), mysqlUser.getPassword());

				PreparedStatement ps = null;
				
				//按条件设置SQL语句
			
				String sql_select="SELECT MsgNo,MsgUsername,MsgTime,MsgContent FROM sysMsg_table ORDER BY MsgTime DESC LIMIT 0,5";
				ps = conn.prepareStatement(sql_select);

				ResultSet rSet=ps.executeQuery();
				
				while (rSet.next()) {
					SystemMsgbean sysmsgbean = new SystemMsgbean();
					sysmsgbean.setMsgno(rSet.getString(1));
					sysmsgbean.setMsgusername(rSet.getString(2));
					sysmsgbean.setMsgtime(rSet.getString(3));
					sysmsgbean.setMsgcontent(rSet.getString(4));
					sysmsglist.add(sysmsgbean);
					
				}
	                conn.close();
	    			
				} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("系统故障，请联系开发人员");
			}
		
		return sysmsglist;
	}
	
	/*
	 * @description 公告存库
	 * @param 传入一个系统公告bean
	 * @return 存储状态
	 * 
	 * */
	public static boolean saveSystemMsg(SystemMsgbean sysmsgbean) {
		
		SqlConnect sConnect = new SqlConnect();
		MysqlUser mUser = new MysqlUser();

		try {
			//数据库连接
			Connection conn = sConnect.connMysql(mUser.getUri(), mUser.getUsername(), mUser.getPassword()); 
		
			//向数据库中插入数据
			String insert_sql = "INSERT INTO sysMsg_table VALUES(?,?,?,?)";
			PreparedStatement pStatement=conn.prepareStatement(insert_sql);
			
			pStatement.setString(1, sysmsgbean.getMsgno());
			pStatement.setString(2, sysmsgbean.getMsgusername());
			pStatement.setString(3, sysmsgbean.getMsgtime()); 
			pStatement.setString(4, sysmsgbean.getMsgcontent());
			pStatement.executeUpdate();

			conn.close();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("系统故障，请联系管理员,注册失败!");
			e.printStackTrace();
			return false;
		}
	}
	
}
