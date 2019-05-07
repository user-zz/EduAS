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
import cn.nuist_bjxy.zhangzheng.bean.Thesisbean;
import cn.nuist_bjxy.zhangzheng.dao.DiscussDao;
import cn.nuist_bjxy.zhangzheng.dao.ThesisDao;

/**
 * Servlet implementation class SearchThesis
 */
@WebServlet("/SearchThesis")
public class SearchThesis extends HttpServlet {
	private static final long serialVersionUID = 1L;
     Thesisbean thbean = new Thesisbean();
     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchThesis() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		
		String thno = request.getParameter("Thno");
		String  id = request.getParameter("ID");
		
		List<Thesisbean> thesisRstList = new ArrayList<>();
		
		thesisRstList = ThesisDao.ThesisCheckRstList(thno);
		
		session.setAttribute("thesisRstList", thesisRstList);
		
		//如果id=1，则为普通用户的操作
		if(id == null) {

			request.getRequestDispatcher("jsp/thesisRstList.jsp").forward(request, response);
		
		}else if(id.equals("1") ) {
			
			session.setAttribute("Thno", thno);
			
			 List<Discussbean> discsList = DiscussDao.seeDiscsList(thno);
			 request.setAttribute("discsList", discsList);
			 
			request.getRequestDispatcher("jsp/discuss.jsp").forward(request, response);
			
		}else if(id.equals("selfSearch")) {
			
			String thusername = request.getParameter("Thusername");
			List<Thesisbean> selfSearchThesisRstList = ThesisDao.SearchThesis(thusername);
			session.setAttribute("selfSearchThesisRstList", selfSearchThesisRstList);

			request.getRequestDispatcher("jsp/selfSearchThesisRstList.jsp").forward(request, response);
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		List<Thesisbean> thesisSearchRstList =new ArrayList<>();
		
		String condition_id = request.getParameter("condition_id");
		String title = "";
		String thusername = "";
		
		//condition_id:1--按标题查找；2--按发布者查找
		if(condition_id.equals("1")) {
			
			title = request.getParameter("condition");
			
		}else if(condition_id.equals("2")) {
			
			thusername = request.getParameter("condition");
			
		}else {
			
			response.getWriter().append("出错啦！请点击<a href='jsp/searchNews.jsp' target='mainFrame'>此处</a>返回");
			
		}		
	
			System.out.println(title+"--"+thusername);
			
			thesisSearchRstList = ThesisDao.SearchThesis(title, thusername);
			request.setAttribute("thesisSearchRstList", thesisSearchRstList);
			request.getRequestDispatcher("jsp/searchNews.jsp").forward(request, response);
	}

}
