package cn.nuist_bjxy.zhangzheng.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.nuist_bjxy.zhangzheng.bean.Message;
import cn.nuist_bjxy.zhangzheng.bean.MysqlUser;
import cn.nuist_bjxy.zhangzheng.dao.SqlConnect;

/**
 * Servlet implementation class InputInformation
 */
@WebServlet("/InputInformation")
public class InputInformation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public String chat_record = "";  //定义聊天记录变量，此处为全局变量
	Message mess =  new  Message();
	String img_path = "";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InputInformation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		
		String filetype_img = (String)request.getSession().getAttribute("filetype_img");
		
	     if(filetype_img.equals("T")) {
	    	 
	    	 doPost(request, response);
	    	 
	     }else {

	 		String path = request.getContextPath();
	 		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	 		response.getWriter().append("亲爱的看官，请您先登录！<br/>3秒后将为您返回登录页面...<p>如果长时间未返回请点击<a href="+basePath+"jsp/login2.jsp>这里</a>返回</p>");
	 		response.setHeader("refresh", "3,"+basePath+"jsp/login2.jsp");
	 		
	    }

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html;charset=UTF-8"); 
        //此出注解是因为使用CodeFilter类过滤所有Servlet，转换编码
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        boolean flag = true;
        String input_textarea = request.getParameter("input_textarea");
        
        
        Date now = new Date();    //创建日期对象，及系统当前时间
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time = dateFormat.format( now ); //按照给定的日期格式获取系统当前时间
       // String t = (String)request.getSession().getAttribute("nameSession");  //获取登陆页面用户名
        
        //String t = "Z00001";  //测试用的用户名，正式应用应注掉
       String username = (String)request.getSession().getAttribute("username");
      // String userid = (String)request.getSession().getAttribute("userid");
       
     //将登录的用户信息存入Application列表
       ServletContext application=this.getServletContext();  //取得Application对象
       HttpSession session = request.getSession(); //获取session对象
       
       List<Object> users = (List<Object>)application.getAttribute("users");
       
       //如果该用户列表还不存在，实例化该用户列表 
       if (users == null) {
           users = new ArrayList<Object>();
       }
     /*  
       //将当前登陆用户名加入该用户列表 
       users.add(username);
       users.add(userid);
       */
       application.setAttribute("users", users);
       session.setAttribute("user", username);
       
      // response.sendRedirect("jsp/chat.jsp");
           
       //send传输过来的
       String to="";
       try{
           //被发送消息方
           List<Object> tmp = (List<Object>) application.getAttribute("users");
           String SS = request.getParameter("select");
           int a=Integer.parseInt(SS);
           if(a==0) to="所有人";
           else to=(String)tmp.get(a-2);
       }
       catch(Exception e){}

       List<Object> msgs = (List<Object>) application.getAttribute("msgs");
       if (msgs == null) {
           msgs = new ArrayList<Object>();
       }
       List<Object> to_sb = (List<Object>) application.getAttribute("private");
       if (to_sb == null) {
           to_sb = new ArrayList<Object>();
       }
       List<Object> from_sb = (List<Object>) application.getAttribute("S_private");
       if (from_sb == null) {
           from_sb = new ArrayList<Object>();
       }
       String msg = "";
       
        img_path = "/uploadFiles/"+(String)request.getSession().getAttribute("path");

       System.out.println("img_path:"+img_path);
       
       if (input_textarea != null && !input_textarea.equals("")) {

    	   msg = "<p>"+username + "（发送给 " + to + "）  :  " +input_textarea + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+time+"</p>" ;

       }else if(!img_path.equals("") && img_path != null) {
    	   
    	   to="所有人";
    	   msg = "<p>"+username + "（发送给 " + to + "）  :  "+time+"<br><img src='"+img_path+"' alt='哎呀，图片走失啦！' height='100' ></p>";
           System.out.println("msg:"+msg);
    	   request.getSession().setAttribute("path", "");
           flag = false;
       
       }else msg = null;
       
       if(msg != null && !msg.equals("")) {
    	   
    	   msgs.add(msg);
           to_sb.add(to);
           from_sb.add(username);

           //将列表数据存入application
           application.setAttribute("msgs", msgs);
           application.setAttribute("private", to_sb);
           application.setAttribute("S_private", from_sb);
           
       }
       

       //判断用户是否已登录
        if (isUser(username)) {
			doGet(request, response);
		} else {

	        //获取随机编号
	        Random random = new Random();  
	        int max = 100000; //设置最大值
	        int min = 1; //设置最小值
	        int Number =  random.nextInt(max)%(max-min+1) + min; 
	        
	        String randomNum = "190011"+Number;
	        //将相关数据存入bean
	        
	        mess.setMno(randomNum); 
	        mess.setMusername(username);
	        mess.setMessage(input_textarea);
	        mess.setMtime(time);
	        
	        //将数据存入数据库
	        if(flag) {
	        	saveMess(mess.getMno(), mess.getMusername(), mess.getMessage(), mess.getMtime());
	        }

	        //测试输出
	      //  System.out.println("Success!");
	        /*
	        //显示聊天记录
	        request.getSession().setAttribute("nameSession", username);
	        chat_record += username+":  "+input_textarea+"  "+time+"\n";   //聊天记录存储
	      //  System.out.println(chat_record+"\n"); //测试
	        request.setAttribute("input_textarea",chat_record); //将当前聊天输入内容存储
	    //    System.out.println(chat_record); //测试
	       */ 
	     	
	        request.getRequestDispatcher("jsp/chat_main.jsp").forward(request,response);  //跳转到当前聊天输入界面，即界面布局不变
	        
		}
		
	}
        
	protected  boolean  isUser(String  username ) {
		if(username == null) {
			return true;
			}
		else {
			return false;
		}
	}
	//存储聊天记录
	protected void saveMess(String mno,String musername,String mess,String mtime) {
		 //数据库操作demo
		MysqlUser mysqlUser=new MysqlUser();
		SqlConnect sqlConnect=new SqlConnect();
		try {
			Connection conn;
			//使用connMysql()方法连接数据库
			conn=sqlConnect.connMysql(mysqlUser.getUri(), mysqlUser.getUsername(), mysqlUser.getPassword());
			
			//插入数据
			String sql_insert="INSERT INTO m_table VALUES(?,?,?,?)";
			PreparedStatement pStatement=conn.prepareStatement(sql_insert);
			pStatement.setString(1, mno);
			pStatement.setString(2, musername);
			pStatement.setString(3, mess); //插入加密后的密码
			pStatement.setString(4, mtime);
			pStatement.executeUpdate();
			
		    conn.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("系统故障，请联系开发人员");
			
		}
	}

}
