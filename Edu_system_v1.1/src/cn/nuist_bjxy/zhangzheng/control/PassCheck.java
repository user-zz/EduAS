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
 * Servlet implementation class PassCheck
 */
@WebServlet("/PassCheck")
public class PassCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PassCheck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		
		String fno = request.getParameter("Fno");
		String dno = request.getParameter("Dno");
		String thno = request.getParameter("Thno");
		
		if(thno!= null && ThesisDao.isPassThesisCheck(thno)) {
			
			response.getWriter().append("操作成功！请点击<a href='CheckThesis?CheckId=0' target='admin_mainFrame'>此处</a>返回");
			
			
		}else if(fno != null && FileDao.isPassFileCheck(fno)) {
			
			response.getWriter().append("操作成功！请点击<a href='CheckThesis?CheckId=1' target='admin_mainFrame'>此处</a>返回");
			
		} else if(dno != null && DiscussDao.isPassDscsCheck(dno)) {
			
			response.getWriter().append("操作成功！请点击<a href='CheckThesis?CheckId=2' target='admin_mainFrame'>此处</a>返回");
			
		}else doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		
		response.getWriter().append("出错啦!，请点击<a href='jsp/admin.jsp'>此处</a>返回");
		
	}

}
