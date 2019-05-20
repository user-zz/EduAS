package cn.nuist_bjxy.zhangzheng.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.nuist_bjxy.zhangzheng.bean.Filebean;
import cn.nuist_bjxy.zhangzheng.dao.FileDao;
import cn.nuist_bjxy.zhangzheng.dao.HomeworkDao;

/**
 * Servlet implementation class ListUploadedFilesServlet
 */
@WebServlet("/ListUploadFiles")
public class ListUploadFiles extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListUploadFiles() {
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
		response.setContentType("text/html;charset=utf-8");
		
		 //个人中心传过来的数据
		 String ID = "";
	     String search_ID = request.getParameter("ID");
	     
	     if(search_ID == null || search_ID.equals("")) {
	    	 ID = "allFileList";
	     }else {
	    	 ID = search_ID;
	     }
	     
	     String fusername = request.getParameter("Fusername");
	     String ftype = "";
	     
	     System.out.println("ID:"+ID+"fusername:"+fusername);
	     
	     if(ID.equals("selfSearch")) {
	    	 System.out.println("开始查询");
	    	 //自己查看文件列表
	    	 List<Filebean> selfSearchFileList = FileDao.selectList(ftype, fusername);
	    	 
	    	 request.setAttribute("fileList", selfSearchFileList);
	    	 request.getRequestDispatcher("jsp/selfSearchFileList.jsp").forward(request, response);
	    	 
	     }
	     
	     if(ID.equals("allFileList")){
	    	 response.getWriter().append("文件上传成功！");
	    	 
	    	//返回全部文件列表
			 List<Filebean> fileList = FileDao.list(); 
		        
		     request.setAttribute("fileList", fileList);
		     
		     request.getRequestDispatcher("jsp/list.jsp").forward(request, response);
		     
	     }
	     

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//避免中文乱码
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
			String op = request.getParameter("OP");
			String filetype = request.getParameter("filetype");
			String fileusername = request.getParameter("fileusername");
			String stuclass = request.getParameter("stuclass");
			String stumajor = request.getParameter("stumajor");
			String stuname = request.getParameter("stuname");
			String teaname = request.getParameter("teaname");
			
			if(stumajor == null || stumajor.equals("")) {
				stumajor = request.getParameter("input_stumajor");
			}
			
			if (stuclass == null || stuclass.equals("")) {
				stuclass = request.getParameter("input_stuclass");
			}
			
			System.out.println("op:"+op);
			
			List<Filebean> fileList = null;
					
			//查找作业
			if (op.equals("searchHw")) {
				System.out.println("查找作业");
				if(stuclass.equals("") && stumajor.equals("")) {
					fileList = FileDao.list();
				}else {
					fileList = HomeworkDao.hwSelectList(stumajor,stuclass,teaname,stuname);
				}
			} 
			
			//查找共享文件
			if (op.equals("searchMtrl") ) {
				System.out.println("查共享文件");
				if (filetype.equals("") && fileusername.equals("")) {
					
					 fileList = FileDao.list(); 
					
				} else  fileList = FileDao.selectList(filetype,fileusername);
			}
				
			request.setAttribute("fileList", fileList);
			System.out.println("查找成功，返回结果..."); 
		    request.getRequestDispatcher("jsp/list.jsp").forward(request, response);

		
	}

}
