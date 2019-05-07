package cn.nuist_bjxy.zhangzheng.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.nuist_bjxy.zhangzheng.bean.MysqlUser;
import cn.nuist_bjxy.zhangzheng.bean.Thesisbean;
import cn.nuist_bjxy.zhangzheng.dao.SqlConnect;


/**
 * Servlet implementation class Publish
 */
@WebServlet("/Publish")
public class Publish extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Thesisbean thesisbean = new Thesisbean();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Publish() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().append("填写的信息出错啦 ! 3秒后为您返回注册页面...");
		response.setHeader("refresh", "3;URL='jsp/publish.jsp'"); //延时5秒返回注册页面
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		//获取页面数据
		String title = request.getParameter("title");
		String thtype = request.getParameter("thtype");
		String thcontent = request.getParameter("thcontent");
		String username =(String) request.getSession().getAttribute("username");
		
		//获取当前时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String date = df.format(new Date());
		
		/*
		//设置随机编号
		int rd = (int)(100+Math.random()*(10000000-100+1)); //100~10000000之间的随机数
		String thno = String.valueOf(rd);
		*/
		 //将数据存入bean
		//获取随机编号
        Random random = new Random();  
        int max = 1000000; //设置最大值
        int min = 1; //设置最小值
        int randomNumber =  random.nextInt(max)%(max-min+1) + min; 
        String thno = "19001"+randomNumber;
        
        //将数据存入bean
		thesisbean.setThno(thno);
		thesisbean.setTitle(title);
		thesisbean.setThusername(username); //设置发布者
		thesisbean.setThtype(thtype);
		thesisbean.setThcontent(thcontent);
		thesisbean.setThtime(date);
		
		System.out.println(thesisbean.getThno());
		System.out.println(thesisbean.getTitle());
		System.out.println(thesisbean.getThusername());
		System.out.println(thesisbean.getThcontent());
		System.out.println(thesisbean.getThtime());
		System.out.println(thesisbean.getThtype());
		
		//判断数据是否存入了数据库
		 if (isUserRegiste()) {
			response.getWriter().append("发布成功，正在为您返回...");
			response.setHeader("refresh", "3;URL='jsp/publish.jsp' ");
		}else 	doGet(request, response);
	}
		
		//将数据插入数据库，完成注册
		protected boolean isUserRegiste() {
				
				SqlConnect sConnect = new SqlConnect();
				MysqlUser mUser = new MysqlUser();
				
				try {
					//数据库连接
					Connection conn = sConnect.connMysql(mUser.getUri(), mUser.getUsername(), mUser.getPassword()); 
				
					//向数据库中插入数据
					String insert_sql = "INSERT INTO th_table VALUES(?,?,?,?,?,?,?,?)";
					PreparedStatement pStatement=conn.prepareStatement(insert_sql);
					pStatement.setString(1,thesisbean.getThno());
					pStatement.setString(2, thesisbean.getTitle());
					pStatement.setString(3, thesisbean.getThusername()); 
					pStatement.setString(4, thesisbean.getThcontent());
					pStatement.setString(5, thesisbean.getThtime());
					pStatement.setString(6,thesisbean.getThtype());
					pStatement.setInt(7,thesisbean.getThclickrate());
					pStatement.setString(8, thesisbean.getThpass());
					pStatement.executeUpdate();
					
					System.out.println("成功发布！");
					
					conn.close();
					return true;
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("系统故障，请联系管理员,注册失败!");
					e.printStackTrace();
					return false;
				}
	
	}

}
