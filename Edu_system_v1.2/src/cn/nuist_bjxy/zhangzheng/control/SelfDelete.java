package cn.nuist_bjxy.zhangzheng.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.nuist_bjxy.zhangzheng.dao.FileDao;
import cn.nuist_bjxy.zhangzheng.dao.ThesisDao;

/**
 * Servlet implementation class SelfDelete
 */
@WebServlet("/SelfDelete")
public class SelfDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelfDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String op = request.getParameter("OP");
		System.out.println("op:"+op);
		if(op.equals("thdel")) {
			//删除论题
			String thno = request.getParameter("TH_NO");
			System.out.println("thno:"+thno);
			ThesisDao.remove(thno);
			System.out.println("删除自己上传的论题...");
			response.getWriter().append("论题删除成功！");
			
		}else if(op.equals("filedel")) {
			//删除文件
			String fileno = request.getParameter("FILE_NO");
			int fno = Integer.parseInt(fileno);
			System.out.println("fno:"+fno);
			FileDao.remove(fno);
			System.out.println("删除自己上传的文件...");
			response.getWriter().append("文件删除成功！");
			
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
