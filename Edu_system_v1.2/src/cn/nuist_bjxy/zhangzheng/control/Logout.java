package cn.nuist_bjxy.zhangzheng.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//解决乱码问题
		response.setContentType("text/html;charset=UTF-8"); 
	    request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	        
	    ServletContext application=this.getServletContext();  //取得Application对象
	    HttpSession session = request.getSession();  //获取session对象
	    
		 //从applicaton作用域中取出用户列表  
	    
        List<Object> users = (List<Object>)application.getAttribute("users");
        String username = (String) session.getAttribute("username");
        
        //从该用户列表中移除该用户  
        users.remove(username);
        application.setAttribute("users", users);
        //注销该用户的会话
        session.invalidate();
 
		response.getWriter().append("用户已注销，欢迎您下次访问本系统...");
		response.setHeader("refresh", "3,index.jsp");
  
	}

}
