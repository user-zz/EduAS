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
		//doPost(request, response);
		
		List<Filebean> fileList = FileDao.list(); 
	        
	     request.setAttribute("fileList", fileList);
	     
	     //String filetype_img = (String)request.getSession().getAttribute("filetype_img");
	    // if(filetype_img.equals("T")) {
	    //	 request.getRequestDispatcher("jsp/chat_sendMsg.jsp").forward(request, response);
	   //  }else {
	    	 request.getRequestDispatcher("jsp/list.jsp").forward(request, response);
	 //    }
	     
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 //获取文件列表
		////////////////////////////////////////////////////
     //   List<Filebean> fileList = FileDao.list(); 
        
      //  request.setAttribute("fileList", fileList);
 
    //    request.getRequestDispatcher("jsp/list.jsp").forward(request, response);

		//////////////////////////////////////////////////////
		
		String filetype = request.getParameter("filetype");
		String fileusername = request.getParameter("fileusername");
		String fid = request.getParameter("fid");
		String stuclass = request.getParameter("stuclass");
		String stumajor = request.getParameter("stumajor");
		
		System.out.println("fid:"+fid);

		
		if(stumajor == null ) {
			stumajor = request.getParameter("input_stumajor");
		}
		
		if (stuclass == null) {
			stuclass = request.getParameter("input_stuclass");
		}
		
		List<Filebean> fileList = null;
				
		if (fid != null) {
			if(stuclass.equals("") && stumajor.equals("")) {
				fileList = FileDao.list();
			}else {
				fileList = HomeworkDao.hwSelectList(stumajor,stuclass);
			}
		} 
		
		if (fid == null) {
			if (filetype.equals("") && fileusername.equals("")) {
				
				 fileList = FileDao.list(); 
				
			} else  fileList = FileDao.selectList(filetype,fileusername);
		}
			
		request.setAttribute("fileList", fileList);
		 
	    request.getRequestDispatcher("jsp/list.jsp").forward(request, response);
	}

}
