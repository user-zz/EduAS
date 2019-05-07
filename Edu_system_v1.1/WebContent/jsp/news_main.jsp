<%@page import="cn.nuist_bjxy.zhangzheng.bean.Thesisbean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" import="java.sql.*" %>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<%@page import="cn.nuist_bjxy.zhangzheng.dao.SqlConnect"%>  
<%@page import="cn.nuist_bjxy.zhangzheng.bean.MysqlUser"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%

	SqlConnect sConnect = new SqlConnect();
	MysqlUser mUser = new MysqlUser();
	
	List<Thesisbean> thList = new ArrayList<>();
	
	//每页显示记录数
	int PageSize = 7; //每页显示记录数
	int StartRow = 0; //开始显示记录的编号
	int PageNo=0;//需要显示的页数
	int CounterStart=0;//每页页码的初始值
	int CounterEnd=0;//显示页码的最大值
	int RecordCount=0;//总记录数;
	int MaxPage=0;//总页数
	int PrevStart=0;//前一页
	int NextPage=0;//下一页
	int LastRec=0;
	int LastStartRecord=0;//最后一页开始显示记录的编号
	
	//获取需要显示的页数，由用户提交
	if(request.getParameter("PageNo")==null){ //如果为空，则表示第1页
	if(StartRow == 0){
		PageNo = StartRow + 1; //设定为1
	}
	}else{
		PageNo = Integer.parseInt(request.getParameter("PageNo")); //获得用户提交的页数
		StartRow = (PageNo - 1) * PageSize; //获得开始显示的记录编号
	}
	
	//因为显示页码的数量是动态变化的，假如总共有一百页，则不可能同时显示100个链接。而是根据当前的页数显示
	//一定数量的页面链接
	
	//设置显示页码的初始值!!
	if(PageNo % PageSize == 0){
		CounterStart = PageNo - (PageSize - 1);
	}else{
		CounterStart = PageNo - (PageNo % PageSize) + 1;
	} 
		CounterEnd = CounterStart + (PageSize - 1);
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<jsp:useBean id="thbean" class="cn.nuist_bjxy.zhangzheng.bean.Thesisbean"></jsp:useBean>
<jsp:useBean id="mysql_user_bean" class="cn.nuist_bjxy.zhangzheng.bean.MysqlUser"></jsp:useBean>
 
<style type="text/css">
	.STYLE13 {font-family: "幼圆"}
	.STYLE18 {font-size: 14px}
	.div1{
		width:80%;
		height:100% ;
	}
</style>

</head>

	<%
			try{
				//使用connMysql()方法连接数据库
				Connection connection=sConnect.connMysql(mUser.getUri(), mUser.getUsername(), mUser.getPassword());
				Statement statement=connection.createStatement();
				
				//获取总记录数
				ResultSet rs = statement.executeQuery("SELECT count(*) from th_table");
				rs.next();
				RecordCount = rs.getInt(1);
			
				rs = statement.executeQuery("SELECT Title,Thusername,Thcontent,Thtime,Thclickrate,Thtype,Thno FROM th_table  LIMIT "+StartRow+", "+PageSize);
				
				while(rs.next()){
					Thesisbean thesisbean = new Thesisbean();
					
					thesisbean.setTitle(rs.getString(1));
					thesisbean.setThusername(rs.getString(2));
					thesisbean.setThcontent(rs.getString(3));
					thesisbean.setThtime(rs.getString(4));
					thesisbean.setThclickrate(rs.getInt(5));
					thesisbean.setThtype(rs.getString(6));
					thesisbean.setThno(rs.getString(7));
					
					thList.add(thesisbean);
				}
				connection.close();
			}catch(SQLException e){
				e.printStackTrace();
				out.print("系统故障，请联系我们！");
			}
			
			session.setAttribute("thList", thList);
			
			//获取总页数
			MaxPage = RecordCount % PageSize;
			
			if(RecordCount % PageSize == 0){
				MaxPage = RecordCount / PageSize;
			}else{
				
			 	MaxPage = RecordCount/PageSize+1;
			 
			}
	%>

<body  class="STYLE13 STYLE18" bgcolor="#FFEFD5">
	<div align="center" class="div1" >
		<div>
			<h4>资讯内容</h4>
			<hr width="90%" />
		</div>
		<div>
			<table width="80%" border="0">
				<tr>
					<th align="center" width="8%">论题名</th>
					<th align="center" width="23%">论题内容</th>
					<th align="center" width="10%">发布者</th>
					<th align="center" width="10%">发布时间</th>
					<th align="center" width="8%">论题类别</th>
					<th align="center" width="5%">点击量</th>
					<th align="center" width="10%">操作</th>
				</tr>
				<c:if test="${not empty thList}">
			        <c:forEach items="${thList}" var="acc">
			            <tr>
			                <td align="center">${acc.title}</td>
			                <td align="center">${acc.thcontent}</td>
			                <td align="center">${acc.thusername}</td>
			                <td align="center">${acc.thtime}</td>
			                <td align="center">${acc.thtype}</td>
			                <td align="center">${acc.thclickrate}</td>
			                <td align="center">
								<span><a href="<%=request.getContextPath()%>/SearchThesis?Thno=${acc.thno}&ID=1"><font color="#4169E1">查看并评论</font></a></span>
							</td>
			            </tr>
			        </c:forEach>
			    </c:if>
				
			</table>
			<br /><br /><br />
			<table width="100%" border="0" >
			<tr>
				<td width="50%">
				    <span class="STYLE13"><%="总共"+RecordCount+"条记录 - 当前页："+PageNo+"/"+MaxPage %> </span>
				</td>
				<td class="STYLE13">
					<div align="center">
						<%
							out.print("<font size=3>");
						//显示第一页或者前一页的链接
						//如果当前页不是第1页，则显示第一页和前一页的链接
						if(PageNo != 1){
							PrevStart = PageNo - 1;
							out.print("<a href=news_main.jsp?PageNo=1 target='mainFrame'>第一页</a>: ");
							out.print("<a href=news_main.jsp?PageNo="+PrevStart+" target='mainFrame'>前一页</a>");
						}
							out.print("[");
			
						//打印需要显示的页码
						for(int c=CounterStart;c<=CounterEnd;c++){
						 if(c <MaxPage){
						 if(c == PageNo){
						 if(c %PageSize == 0){
						 	out.print(c);
						}else{
						 	out.print(c+" ,");
						 }
						}else if(c % PageSize == 0){
						 	out.print("<a href=news_main.jsp?PageNo="+c+" target='mainFrame'>"+c+"</a>");
						}else{
						 	out.print("<a href=news_main.jsp?PageNo="+c+" target='mainFrame'>"+c+"</a> ,");
						 }
						}else{
						if(PageNo == MaxPage){
							out.print(c);
						break;
						}else{
							out.print("<a href=news_main.jsp?PageNo="+c+" target='mainFrame'>"+c+"</a>");
						 break;
						}
						 }
						}
						
						out.print("]");;
						
						if(PageNo < MaxPage){ //如果当前页不是最后一页，则显示下一页链接
						      NextPage = PageNo + 1;
						      out.print("<a href=news_main.jsp?PageNo="+NextPage+" target='mainFrame'>下一页</a>");
						}
						
						//同时如果当前页不是最后一页，要显示最后一页的链接
						if(PageNo < MaxPage){
						 	  LastRec = RecordCount % PageSize;
						 if(LastRec == 0){
							  LastStartRecord = RecordCount - PageSize;
							 }
						else{
						 	 LastStartRecord = RecordCount - LastRec;
						}
						
							 out.print(":");
							 out.print("<a href=news_main.jsp?PageNo="+MaxPage+" target='mainFrame'>最后一页</a>");
							
						}
						 out.print("</font>");
					%>
					</div>
				</td>
			</tr>
			</table>
		</div>
	</div>
</body>
</html>