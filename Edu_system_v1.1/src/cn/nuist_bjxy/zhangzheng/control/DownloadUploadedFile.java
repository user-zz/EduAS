package cn.nuist_bjxy.zhangzheng.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.nuist_bjxy.zhangzheng.bean.Filebean;
import cn.nuist_bjxy.zhangzheng.dao.FileDao;

/**
 * Servlet implementation class DownloadUploadedFile
 */
@WebServlet("/DownloadUploadedFile")
public class DownloadUploadedFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadUploadedFile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//拿到文件ID
        int fileId = Integer.valueOf(request.getParameter("id"));
        System.out.println("fileId:"+fileId);
        
        Filebean filebean = FileDao.load(fileId);
        String filePath = filebean.getFpath();
        String fileName = filebean.getFname();
        String errorMessage = "";
        //判断文件是否存在
        File file = new File(filePath);
        if (file.exists()) {
            //设置响应头
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            //创建文件输入流
            FileInputStream is = new FileInputStream(filePath);
            //创建输出流
            OutputStream os = response.getOutputStream();
            //创建缓冲区
            byte buffer[] = new byte[1024];
            int len = 0;
            //写数据到浏览器
            while ((len = is.read(buffer)) > 0) {
                os.write(buffer, 0, len);
            }
            //关闭流
            is.close();
            os.close();
        } else {
            errorMessage = "下载失败，文件：" + fileName + " 不存在";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("jsp/file_download_error.jsp").forward(request, response);
            
        }

	}

}
