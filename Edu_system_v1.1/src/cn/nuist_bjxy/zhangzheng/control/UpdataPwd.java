package cn.nuist_bjxy.zhangzheng.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.nuist_bjxy.zhangzheng.bean.MysqlUser;
import cn.nuist_bjxy.zhangzheng.bean.Userbean;
import cn.nuist_bjxy.zhangzheng.dao.SqlConnect;
import cn.nuist_bjxy.zhangzheng.dao.UserDao;
import cn.nuist_bjxy.zhangzheng.encryption.Encryption;

/**
 * Servlet implementation class UpdataPwd
 */
@WebServlet("/UpdataPwd")
public class UpdataPwd extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Userbean userbean = new Userbean();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdataPwd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;UTF-8");

		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String id = (String)request.getParameter("id");
		String username = (String)request.getParameter("username");
		String startpwd = (String)request.getParameter("startpwd"); //原始密码
		String pwd = (String)request.getParameter("pwd");
		String phonenum = (String)request.getParameter("phonenum");

		if(id.equals("resetpwd")) {
			//将密码加密
			Encryption encryption = new Encryption();
			String password = encryption.createPassword(pwd); 
			String startpassword = encryption.createPassword(startpwd);
			
			if(UserDao.ResetPwd(username, startpassword, password, phonenum)) {
				
				response.getWriter().append("密码修改成功！请点击<a href='jsp/changePassword.jsp' target='userCenter_mainFrame'>此处</a>返回");
			}else {
				
				response.getWriter().append("系统故障！请点击<a href='jsp/changePassword.jsp' target='userCenter_mainFrame'>此处</a>返回");
				
			}
			
		}else if(!id.equals("resetpwd")){
			//修改数据库中的密码
			
			if(isUpdataPwd(username,pwd,phonenum)){
				//返回登录页面
				response.getWriter().append("密码重置成功，正在为您返回登录页面...");
				response.setHeader("refresh", "3,login.jsp");
				
			}else {
				
				response.getWriter().append("系统出现故障，请重试或联系管理员,现在为您返回...");
				response.setHeader("refresh","2,jsp/findpwd.jsp");
				
			}
		}
		
	}
	
	protected boolean isUpdataPwd(String username,String pwd,String phonenum) {
		
		SqlConnect sConnect = new SqlConnect();
		Encryption encryption = new Encryption();
		MysqlUser mUser = new MysqlUser();
		
		//将密码加密
		String password = encryption.createPassword(pwd); 
		
		try {
			//数据库连接
			Connection conn = sConnect.connMysql(mUser.getUri(), mUser.getUsername(), mUser.getPassword()); 
			Statement statement = conn.createStatement();
			
			//向数据库中插入数据
			String updata_sql = " UPDATE user_table SET Password = '"+password+"' WHERE Username= '"+username+"' AND Phonenum= '"+phonenum+"' " ;
			statement.executeUpdate(updata_sql);
			
			conn.close();
			
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("该用户不存在或系统数据库故障，请联系管理员,密码重置失败!");
			e.printStackTrace();
			return false;	
			}
	}
		

}
