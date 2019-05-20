package cn.nuist_bjxy.zhangzheng.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.nuist_bjxy.zhangzheng.bean.MysqlUser;
import cn.nuist_bjxy.zhangzheng.bean.Userbean;
import cn.nuist_bjxy.zhangzheng.dao.SqlConnect;
import cn.nuist_bjxy.zhangzheng.encryption.Encryption;

/**
 * Servlet implementation class Registe
 */
@WebServlet(asyncSupported = true, description = "This servlet is register", urlPatterns = { "/Registe" })
public class Registe extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Userbean userbean = new Userbean();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registe() {
        super();
        // TODO Auto-generated constructor stub

    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().append("该用户已存在或您填写的信息不正确 ，注册失败! 3秒后为您返回注册页面...");
		response.setHeader("refresh", "5;URL='regist.jsp'"); //延时5秒返回注册页面
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//避免中文乱码
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//获取页面表单信息
		String username= request.getParameter("username"); //获取用户名
		String uno= request.getParameter("uno"); //获取学号
		String password= request.getParameter("pwd"); //获取密码
		String phonenum= request.getParameter("phonenum"); //获取电话号码
		//String identity_num= request.getParameter("identity_num"); //获取验证码
		String str = request.getParameter("identity"); //获取身份
		int userid = Integer.parseInt(str); 
		
		//将数据存入bean中
		userbean.setUsername(username);
		userbean.setUno(uno);
		userbean.setPassword(password);
		userbean.setPhonenum(phonenum);
		userbean.setId(userid);
	
		//判断数据是否存入了数据库
		 if (isUserRegiste()) {	 
			response.getWriter().append("注册成功，正在为您返回首页...");
			response.setHeader("refresh", "3;URL='login.jsp' ");
		}else 	doGet(request, response);
	}
	
	//将数据插入数据库，完成注册
	protected boolean isUserRegiste() {
			Encryption encryption = new Encryption();
			SqlConnect sConnect = new SqlConnect();
			
			MysqlUser mUser = new MysqlUser();
			
			//将密码加密
			String password = encryption.createPassword(userbean.getPassword()); 
			
			try {
				//数据库连接
				Connection conn = sConnect.connMysql(mUser.getUri(), mUser.getUsername(), mUser.getPassword()); 
			
				//向数据库中插入数据
				String insert_sql = "INSERT INTO user_table VALUES(?,?,?,?,?,?,?)";
				PreparedStatement pStatement=conn.prepareStatement(insert_sql);
				pStatement.setString(1, userbean.getUsername());
				pStatement.setString(2, userbean.getUno());
				pStatement.setString(3, password); //插入加密后的密码
				pStatement.setString(4, userbean.getPhonenum());
				pStatement.setString(5, userbean.getNickname());
				pStatement.setString(6,userbean.getEmail());
				pStatement.setInt(7, userbean.getId());
				pStatement.executeUpdate();
				
			//	System.out.println("注册成功！");
				
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
