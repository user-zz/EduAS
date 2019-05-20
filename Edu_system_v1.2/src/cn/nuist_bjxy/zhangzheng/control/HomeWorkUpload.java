package cn.nuist_bjxy.zhangzheng.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HomeWorkUpload
 */
@WebServlet("/HomeWorkUpload")
public class HomeWorkUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeWorkUpload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		//获取文件id,普通文件id默认为0，作业文件id为1
        String fid = request.getParameter("fid");
        String filetype = request.getParameter("filetype"); //类别
        String stumajor= request.getParameter("stumajor"); //院系
        String input_stumajor = request.getParameter("input_stumajor"); //自定义院系
        String stuclass= request.getParameter("stuclass"); //专业或班级
        String input_stuclass = request.getParameter("input_stuclass");  //自定义班级
        String stuname = request.getParameter("stuName"); //上传者真实姓名
        String teaname = request.getParameter("teaName"); //接收教师姓名
        
        request.getSession().setAttribute("fid",fid );
        
        request.getSession().setAttribute("filetype",filetype );
        request.getSession().setAttribute("stumajor", stumajor);
        request.getSession().setAttribute("input_stumajor",input_stumajor );
        request.getSession().setAttribute("stuclass", stuclass);
        request.getSession().setAttribute("input_stuclass", input_stuclass);
        request.getSession().setAttribute("stuname", stuname);
        request.getSession().setAttribute("teaname", teaname);
        
        request.getRequestDispatcher("jsp/fileupload.jsp").forward(request, response);
        
	}

}
