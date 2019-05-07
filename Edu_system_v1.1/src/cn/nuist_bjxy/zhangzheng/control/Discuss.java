package cn.nuist_bjxy.zhangzheng.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.nuist_bjxy.zhangzheng.bean.Discussbean;
import cn.nuist_bjxy.zhangzheng.bean.MysqlUser;
import cn.nuist_bjxy.zhangzheng.dao.DiscussDao;
import cn.nuist_bjxy.zhangzheng.dao.SqlConnect;

/**
 * Servlet implementation class Discuss
 */
@WebServlet("/Discuss")
public class Discuss extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Discussbean d_bean = new  Discussbean();
    public String discuss_record = ""; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Discuss() {
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
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		//获取页面数据
		String th_no = request.getParameter("Thno");  //得到论题的编号
		String discuss_value = request.getParameter("discuss_value");
		String d_title =request.getParameter("d_title"); 
		String d_username = (String)session.getAttribute("username");  //评论者
		
		/*
		//测试输出
		System.out.println(d_title);
		System.out.println(d_username);
		System.out.println(discuss_value);
		*/
		
		//获取随机编号
        Random random = new Random();  
        int max = 100000; //设置最大值
        int min = 1; //设置最小值
        int Number =  random.nextInt(max)%(max-min+1) + min; 
        
        String d_no = "19001"+Number;
		
      //获取当前时间
      	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
      	String date = df.format(new Date());
        
		//将数据存入bean
        d_bean.setD_no(d_no);
        d_bean.setTh_no(th_no);
		d_bean.setD_mess(discuss_value);
		d_bean.setD_title(d_title);
		d_bean.setD_username(d_username);
		d_bean.setD_time(date);
		
		//判断数据是否存入了数据库
		 if (isSaveMess()) {
			
			 //显示聊天记录
		//        String username = (String) session.getAttribute("username");  
		        
		 //       discuss_record += username+":  "+discuss_value+"\n";   //评论记录存储
		      //  System.out.println(chat_record+"\n"); //测试
			 List<Discussbean> discsList = DiscussDao.seeDiscsList(th_no);
			 request.setAttribute("discsList", discsList);
			 
		    //    request.setAttribute("discuss_mess",discuss_record); //将当前评论输入内容存储
		    //    System.out.println(chat_record); //测试
		        request.getRequestDispatcher("jsp/discuss.jsp").forward(request,response);  //跳转到当前评论输入界面，即界面布局不变
		        /*
			response.getWriter().append("发布成功，正在为您返回...");
			response.setHeader("refresh", "3;URL='jsp/discuss.jsp' ");
			*/
		}else 	doGet(request, response);
	}
		
		//将数据插入数据库
		protected boolean isSaveMess() {
				
				SqlConnect sConnect = new SqlConnect();
				MysqlUser mUser = new MysqlUser();
				
				try {
					//数据库连接
					Connection conn = sConnect.connMysql(mUser.getUri(), mUser.getUsername(), mUser.getPassword()); 
				
					//向数据库中插入数据
					String insert_sql = "INSERT INTO discuss_table VALUES(?,?,?,?,?,?,?)";
					PreparedStatement pStatement=conn.prepareStatement(insert_sql);
					pStatement.setString(1, d_bean.getD_no());
					pStatement.setString(2, d_bean.getTh_no());
					pStatement.setString(3,d_bean.getD_title());
					pStatement.setString(4, d_bean.getD_username());
					pStatement.setString(5, d_bean.getD_mess());
					pStatement.setString(6, d_bean.getD_time());
					pStatement.setString(7, d_bean.getD_pass());
					
					pStatement.executeUpdate();
					
				//	System.out.println("评论成功！");
					
					conn.close();
					return true;
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("系统故障，请联系管理员,评论失败!");
					e.printStackTrace();
					return false;
				}
	}
}
