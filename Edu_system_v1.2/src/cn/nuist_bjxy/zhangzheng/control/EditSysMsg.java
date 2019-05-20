package cn.nuist_bjxy.zhangzheng.control;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.nuist_bjxy.zhangzheng.bean.SystemMsgbean;
import cn.nuist_bjxy.zhangzheng.dao.SystemMsgDao;

/**
 * Servlet implementation class EditSysMsg
 */
@WebServlet("/EditSysMsg")
public class EditSysMsg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditSysMsg() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String msgcontent = request.getParameter("msgcontent"); //获取页面公告内容
		String msgusername = (String)request.getSession().getAttribute("username"); 
		
        Date now = new Date();    //创建日期对象，及系统当前时间
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String msgtime = dateFormat.format( now ); //按照给定的日期格式获取系统当前时间
		
        //获取随机编号
        Random random = new Random();  
        int max = 100000; //设置最大值
        int min = 1; //设置最小值
        int Number =  random.nextInt(max)%(max-min+1) + min; 
        String msgno = "19002"+Number;
		
        SystemMsgbean sysmsgbean = new SystemMsgbean();
        sysmsgbean.setMsgno(msgno);
        sysmsgbean.setMsgusername(msgusername);
        sysmsgbean.setMsgtime(msgtime);
        sysmsgbean.setMsgcontent(msgcontent);
        
        if(SystemMsgDao.saveSystemMsg(sysmsgbean)) {
        	
        	System.out.println("公告发布成功！");
        	
        	List<SystemMsgbean> sysmsglist = SystemMsgDao.systemMsgList();
        	request.getSession().setAttribute("sysmsglist", sysmsglist);
        	
        	String  message = "公告发布成功！";
        	request.setAttribute("message", message);
        	
        	request.getRequestDispatcher("jsp/editSysMsg.jsp").forward(request,response); 
        	
        }else {
        	
        	System.out.println("系统故障！");
        	
        }
        
	}

}
