package cn.nuist_bjxy.zhangzheng.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.nuist_bjxy.zhangzheng.bean.UserMsgbean;
import cn.nuist_bjxy.zhangzheng.dao.UserDao;

/**
 * Servlet implementation class EditUserMsg
 */
@WebServlet("/UpdataUserMsg")
public class UpdataUserMsg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdataUserMsg() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String op = request.getParameter("OP");
		String user = (String)request.getSession().getAttribute("username");
		
		if(op.equals("searchUser")) {
			List<UserMsgbean> userMsgList =  UserDao.userMsgList(user);
			request.getSession().setAttribute("userMsgList", userMsgList);
			
			request.getRequestDispatcher("jsp/updataUserMsg.jsp").forward(request, response);
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String op = request.getParameter("OP");
		
		//编辑个人信息
		if(op.equals("editusermsg")) {
			
			String username = request.getParameter("username"); //用户名
			String uno = request.getParameter("uno"); //学工号
			String name = request.getParameter("name"); //姓名
			String major = request.getParameter("major"); //专业或院系
		 	String userclass = request.getParameter("userclass"); //班级
			String nickname = request.getParameter("nickname");  //昵称
			String phonenum = request.getParameter("phonenum"); //电话号码
			String email = request.getParameter("email"); //邮箱
			String id = request.getParameter("id"); //身份
			
			System.out.println("nickname:"+nickname);
			System.out.println("phonenum:"+phonenum);
			
			UserMsgbean userMsgbean = new UserMsgbean();
			userMsgbean.setUsername(username);
			userMsgbean.setUno(uno);
			userMsgbean.setName(name);
			userMsgbean.setMajor(major);
			userMsgbean.setUserclass(userclass);
			userMsgbean.setNickname(nickname);
			userMsgbean.setPhonenum(phonenum);
			userMsgbean.setEmail(email);
			userMsgbean.setId(id);
			
			if(UserDao.editUserMsg(userMsgbean)) {
				System.out.println("修改信息成功...");
				response.getWriter().append("个人信息修改成功!");
			}
			
		}
		
	}

}
