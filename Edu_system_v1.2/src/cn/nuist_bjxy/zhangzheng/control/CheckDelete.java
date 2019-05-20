package cn.nuist_bjxy.zhangzheng.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.nuist_bjxy.zhangzheng.dao.DiscussDao;
import cn.nuist_bjxy.zhangzheng.dao.FileDao;
import cn.nuist_bjxy.zhangzheng.dao.ThesisDao;

/**
 * Servlet implementation class Delete
 */
@WebServlet("/CheckDelete")
public class CheckDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		
		int fno = 0;
		if(request.getParameter("Fno") != null) {
			fno = Integer.parseInt(request.getParameter("Fno"));
		}
		
		String thno = request.getParameter("Thno");
		String dno = request.getParameter("Dno");
		
		if(fno != 0) {
			
			FileDao.remove(fno);
			response.getWriter().append("删除成功！请点击<a href='CheckThesis?CheckId=1' >此处</a>返回");
						
		}else if(thno != null) {
			
			ThesisDao.remove(thno);
			FileDao.remove(fno);
			response.getWriter().append("删除成功！请点击<a href='CheckThesis?CheckId=0'>此处</a>返回");
			
		}else if (dno != null) {
			
			DiscussDao.remove(dno);
			FileDao.remove(fno);
			response.getWriter().append("删除成功！请点击<a href='CheckThesis?CheckId=2'>此处</a>返回");
			
		}else doPost(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
