package cn.nuist_bjxy.zhangzheng.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.nuist_bjxy.zhangzheng.bean.TempUserbean;
import cn.nuist_bjxy.zhangzheng.dao.TempUserDao;

/**
 * Servlet implementation class ChangeId
 */
@WebServlet("/ChangeId")
public class ChangeId extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeId() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String oprat = request.getParameter("op"); //操作
		String select = request.getParameter("select"); //选择
		String condition = request.getParameter("condition");  //条件
		String username = "";
		String uno = "";
		String message = "";
		if(oprat.equals("reaserch")) {
			//搜索用户
			if(select.equals("0")) {
				//按用户名
				username = condition ;
		
			}else if(select.equals("1")) {
				//按教师工号
				uno = condition ;
				
			}else {
				//测试信息
				System.out.println("输入信息为空");
			}
			
			List<TempUserbean> tempuserlist = TempUserDao.TempUserList(username,uno);
			
			request.setAttribute("tempuserlist", tempuserlist);
			request.getRequestDispatcher("jsp/givePower.jsp").forward(request, response);
			
		}else if(oprat.equals("givepower")) {
			//授权管理员
			String Username = request.getParameter("Username");
			System.out.println("授权账号为："+Username);
			message = "授权成功！";
			System.out.println(message);
			request.setAttribute("message", message);
			request.getRequestDispatcher("jsp/givePower.jsp").forward(request, response);
			
		}else if(oprat.equals("removepower")) {
			//移除管理员
			String Username = request.getParameter("Username");
			System.out.println("已移除"+Username+"的管理员身份");
			message = "移除成功！";
			request.setAttribute("message", message);
			request.getRequestDispatcher("jsp/givePower.jsp").forward(request, response);
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
