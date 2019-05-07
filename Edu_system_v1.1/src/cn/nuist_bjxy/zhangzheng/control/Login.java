package cn.nuist_bjxy.zhangzheng.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.nuist_bjxy.zhangzheng.bean.MysqlUser;
import cn.nuist_bjxy.zhangzheng.dao.SqlConnect;
import cn.nuist_bjxy.zhangzheng.encryption.Encryption;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String selected_pwd = null;
	int selected_userid ;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("index.jsp");
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
		
		SqlConnect sConnect = new SqlConnect();
		MysqlUser mUser = new MysqlUser();
		
		//获取登录信息
		String username = request.getParameter("username");
		String password = request.getParameter("pwd");
		String ID = request.getParameter("ID");
		int userid = Integer.parseInt(ID); //将string类型转化为int型
		
		//将密码加密
		Encryption encryption = new Encryption();
		String pwd = encryption.createPassword(password);
		
	//	System.out.println(username+"\n"+password);
		HttpSession session = request.getSession();
		
	//	getServletContext().setAttribute("username", username);
		
		session.setAttribute("username", username);
		session.setAttribute("userid", ID);
		
	//	System.out.println("username:"+getServletContext().getAttribute(username));
	//	System.out.println("session 的 username:"+username);
		
		//判断用户名是否为空
        if (username.trim().length() == 0) {
            //设置提示信息  
            request.setAttribute("msg", "请您先登录");
            request.getRequestDispatcher("jsp/login2.jsp").forward(request,response);
            return;
        }
		//将登录的用户信息存入Application列表
	       ServletContext application=this.getServletContext();  //取得Application对象
	       
	       List<Object> users = (List<Object>)application.getAttribute("users"); //从applicaton作用域中取出用户列表
	  	       
	       //如果该用户列表还不存在，实例化该用户列表 
	       if (users == null) {
	           users = new ArrayList<Object>();
	       }
	       
	   	String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

	       
	        //查看当前列表中是否包含当前的登陆用户  
	        if (users.contains(username)&&isLogin(pwd,userid)) {
	            //设置提示信息  
	        	/*
	            request.setAttribute("msg", "该用户已登录");
	            request.getRequestDispatcher("login.jsp").forward(request,response);
	            */
	        	response.getWriter().append("该用户已登录，请重新确认您的用户名，正在为您返回登录页面...");
	        	response.setHeader("refresh", "3,"+basePath+"jsp/login2.jsp");
	            return;
	        }
	        /*
	       //将当前登陆用户名加入该用户列表 
	       users.add(username);
	       users.add(ID);
	       application.setAttribute("users", users);
	    	       */
		
		
		try {
		
					//数据库连接
					Connection conn = sConnect.connMysql(mUser.getUri(), mUser.getUsername(), mUser.getPassword());
					
					
					//查找数据
					String sql_select = "SELECT Password,ID FROM user_table WHERE Username = ? ";
					PreparedStatement stmt = conn.prepareStatement(sql_select );
					stmt.setString(1, username);
					
					ResultSet rSet = stmt.executeQuery();
					while (rSet.next()) {
						selected_pwd = rSet.getString(1);
						selected_userid = rSet.getInt(2);
					}
				    conn.close();
				    System.out.println("数据查询成功，正在登录...");
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					System.out.println("查询数据失败！");
				}
		
			//判断密码是否正确
			if(!isLogin(pwd,userid)) {
				response.getWriter().append("您输入的用户名或密码有误!将在3秒后为您返回登录页面...<br/>若长时间未返回请点击<a href='login.jsp'>这里</a>");
				response.setHeader("refresh","3,'login.jsp' ");
			}else if(1 == userid){
				//将当前登陆用户名加入该用户列表 
			       users.add(username);
			       users.add(ID);
			       application.setAttribute("users", users);
			    	      
				response.sendRedirect("jsp/teacher.jsp");
			}else if (2 == userid) {
				//将当前登陆用户名加入该用户列表 
			       users.add(username);
			       users.add(ID);
			       application.setAttribute("users", users);
			    	      
				response.sendRedirect("jsp/student.jsp");
			}else if(0 == userid) {
				//将当前登陆用户名加入该用户列表 
			       users.add(username);
			       users.add(ID);
			       application.setAttribute("users", users);
			    	      
				response.sendRedirect("jsp/admin.jsp");
			}else doGet(request, response);
			
			
	}
	
	protected boolean isLogin(String pwd , int userid) {
		if(pwd.equals(selected_pwd) && selected_userid == userid) {
			return true;
		}else return false;
	}
}

