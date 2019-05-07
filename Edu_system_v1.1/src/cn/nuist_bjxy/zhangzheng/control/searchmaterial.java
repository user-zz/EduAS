package cn.nuist_bjxy.zhangzheng.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.nuist_bjxy.zhangzheng.bean.Filebean;

/**
 * Servlet implementation class searchmaterial
 */
@WebServlet("/searchmaterial")
public class searchmaterial extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public searchmaterial() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		
		response.getWriter().append("Served at: Page is Error");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		//获取文件名 或 文件类型
		String searchname = request.getParameter("searchname");
		String searchtype = request.getParameter("searchtype");
		
		Filebean filebean = new Filebean();
		filebean.setFname(searchname);
		filebean.setFtype(searchtype);
		
		request.setAttribute("filebean", filebean);
		
		if (searchtype.equals("") &&searchname.equals("")) {
			doGet(request, response);
		} else if(searchname != ""&&searchtype.equals("")) {
			
			//按searchname来查找
			request.getRequestDispatcher("jsp/searchMatrlRst.jsp").forward(request, response);	//带着数据，必须使用消息转发
		}else if(searchtype != "" && searchname.equals("")) {
			
			//按searchtype来查找
			request.getRequestDispatcher("jsp/searchMatrlRst.jsp").forward(request, response); 	
		}else {
			
			//按联合条件查找
			request.getRequestDispatcher("jsp/searchMatrlRst.jsp").forward(request, response);	
		}
	}
}
