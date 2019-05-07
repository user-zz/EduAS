package cn.nuist_bjxy.zhangzheng.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.nuist_bjxy.zhangzheng.bean.Discussbean;
import cn.nuist_bjxy.zhangzheng.dao.DiscussDao;

/**
 * Servlet implementation class SearchDscs
 */
@WebServlet("/SearchDscs")
public class SearchDscs extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchDscs() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		String d_no = request.getParameter("Dno");
		
		List<Discussbean> dscsRstList = new ArrayList<>();
		
		dscsRstList = DiscussDao.DscsCheckRstList(d_no);
		
		session.setAttribute("dscsRstList", dscsRstList);
		request.getRequestDispatcher("jsp/dscsRstList.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
