<%@page import="cn.nuist_bjxy.zhangzheng.dao.SqlConnect"%>
<%@page import="cn.nuist_bjxy.zhangzheng.bean.MysqlUser"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="mysql_user_bean" class="cn.nuist_bjxy.zhangzheng.bean.MysqlUser"></jsp:useBean>

<%
 //驱动程序名，比较旧了，如果你用mysql5,自己改。
String driverName="com.mysql.cj.jdbc.Driver";
String userName="root";//数据库用户名
String userPasswd="123456";//密码

String dbName="eduas_db";//数据库名

String tableName="user_table"; //表名

//连接字符串

MysqlUser mUser = new MysqlUser();
SqlConnect sConnect = new SqlConnect();

//每页显示记录数
int PageSize = 4; //每页显示记录数
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
<title>分页显示记录</title>
<link rel="stylesheet" href="style.css" type="text/css">
<style type="text/css">

.STYLE13 {font-family: "幼圆"}
.STYLE18 {font-size: 14px}

</style>

</head>
<%
//使用connMysql()方法连接数据库
	Connection connection=sConnect.connMysql(mUser.getUri(), mUser.getUsername(), mUser.getPassword());
	Statement statement=connection.createStatement();
//获取总记录数
ResultSet rs = statement.executeQuery("select count(*) from user_table");
rs.next();
RecordCount = rs.getInt(1);

//rs = statement.executeQuery("SELECT usercode,username,password,comcode,flag_level,flag_status FROM zyd_user ORDER BY usercode DESC LIMIT "+StartRow+", "+PageSize);
rs = statement.executeQuery("SELECT Username,Uno,Phonenum FROM user_table  LIMIT "+StartRow+", "+PageSize);

//获取总页数
MaxPage = RecordCount % PageSize;
if(RecordCount % PageSize == 0){
	MaxPage = RecordCount / PageSize;
}else{
 MaxPage = RecordCount/PageSize+1;
}
%>
<body class="UsePageBg STYLE13 STYLE18">

<table width="100%" border="0" bordercolor="#000000">
<tr>
<th align="center" width="24%"><span class="STYLE13">分页显示记录</span></th>

</tr>
</table>
<br>
<table width="100%" border="1" bordercolor="#000000">
<tr>
<th width="20%" align="center" valign="middle" bordercolor="#000000" class="InternalHeader STYLE13 " >序号</th>
<th width="25%" align="center" valign="middle" bordercolor="#000000" class="InternalHeader STYLE13 " >用户名</th>
<th width="25%" align="center" valign="middle" bordercolor="#000000" class="InternalHeader STYLE13 " >学号</th>
<th width="30%" align="center" valign="middle" bordercolor="#000000" class="InternalHeader STYLE13 " >电话号码</th>
</tr>
<%
int i = 1;
while (rs.next()) {
 int bil = i + (PageNo-1)*PageSize;
%>
<tr>
<td align="center" valign="middle" bordercolor="#000000" ><span class="STYLE13"><%=i%></span></td>
 <td align="center" valign="middle" bordercolor="#000000" ><span class="STYLE13"><%=rs.getString(1)%></span></td>
<td align="center" valign="middle" bordercolor="#000000"  ><span class="STYLE13"><%=rs.getString(2)%></span></td>
<td align="center" valign="middle" bordercolor="#000000"  ><span class="STYLE13"><%=rs.getString(3)%></span></td>
 </tr>
<%
 i++;
}%>
</table>
<br>
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
			 out.print("<a href=main_data.jsp?PageNo=1>第一页</a>: ");
			 out.print("<a href=main_data.jsp?PageNo="+PrevStart+">前一页</a>");
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
			 out.print("<a href=main_data.jsp?PageNo="+c+">"+c+"</a>");
			}else{
			 out.print("<a href=main_data.jsp?PageNo="+c+">"+c+"</a> ,");
			 }
			}else{
			if(PageNo == MaxPage){
			out.print(c);
			break;
			}else{
			out.print("<a href=main_data.jsp?PageNo="+c+">"+c+"</a>");
			 break;
			}
			 }
			}
			
			out.print("]");;
			
			if(PageNo < MaxPage){ //如果当前页不是最后一页，则显示下一页链接
			 NextPage = PageNo + 1;
			 out.print("<a href=main_data.jsp?PageNo="+NextPage+">下一页</a>");
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
			 out.print("<a href=main_data.jsp?PageNo="+MaxPage+">最后一页</a>");
			
			}
			 out.print("</font>");
		%>
		</div>
	</td>
</tr>
</table>

<%
rs.close();
statement.close();
connection.close();
%>

</body>
</html>
