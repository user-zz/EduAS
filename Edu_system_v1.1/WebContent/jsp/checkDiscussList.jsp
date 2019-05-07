<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" import="java.*" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	int PageSize = 7; //每页显示记录数
	int StartRow = 0; //开始显示记录的编号
	int PageNo=0;//需要显示的页数
	int CounterStart=0;//每页页码的初始值
	int CounterEnd=0;//显示页码的最大值
	int RecordCount=0;//总记录数;
	int MaxPage=1;//总页数
	int PrevStart=0;//前一页
	int NextPage=0;//下一页
	int LastRec=0;
	int LastStartRecord=0;//最后一页开始显示记录的编号
	try{
		
		//获取页码信息
		PageSize = Integer.parseInt((String)request.getAttribute("PageSize"));  //每页显示记录数
		StartRow = Integer.parseInt((String)request.getAttribute("StartRow"));  //开始显示记录的编号
		PageNo = Integer.parseInt((String)request.getAttribute("PageNo"));  //需要显示的页数
		CounterStart = Integer.parseInt((String)request.getAttribute("CounterStart"));  //每页页码的初始值
		CounterEnd = Integer.parseInt((String)request.getAttribute("CounterEnd"));  //显示页码的最大值
		RecordCount = Integer.parseInt((String)request.getAttribute("RecordCount"));  //总记录数;
		MaxPage = Integer.parseInt((String)request.getAttribute("MaxPage"));  //总页数
		PrevStart =Integer.parseInt((String)request.getAttribute("PrevStart"));  //前一页
		NextPage = Integer.parseInt((String)request.getAttribute("NextPage"));  //下一页
		LastRec = Integer.parseInt((String)request.getAttribute("LastRec"));
		LastStartRecord = Integer.parseInt((String)request.getAttribute("LastStartRecord"));  //最后一页开始显示记录的编号
		
	}catch(Exception e){
		e.printStackTrace();
		out.print("<p>页码信息读取错误，请联系开发人员！</p>");
	}
	
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>

<style type="text/css">
	.STYLE13 {font-family: "幼圆"}
	.STYLE18 {font-size: 14px}
	.div1{
		width:80%;
		height:100% ;
	}
	
</style>

</head>

<body  class="STYLE13 STYLE18" bgcolor="#FFEFD5">
	<div align="center" class="div1" >
		<div>
			<h4>待审核数据</h4>
			<hr width="90%" />
		</div>
		<div>
			<table width="80%" border="0">
				<tr>
					<th width="20%">论题名</th>
					<th width="30%">评论内容</th>
					<th width="10%">评论者</th>
					<th width="20%">操作</th>
				</tr>
				<c:if test="${not empty checkDiscussList}">
			        <c:forEach items="${checkDiscussList}" var="acc">
			            <tr>
			                <td align="center">${acc.d_title}</td>
			                <td>${acc.d_mess}</td>
			                <td align="center">${acc.d_username}</td>
			                <td align="center">
								<span><a href="<%=request.getContextPath()%>/SearchDscs?Dno=${acc.d_no}"><font color="#4169E1">查看</font></a></span>
								<span><a href="<%=request.getContextPath()%>/PassCheck?Dno=${acc.d_no}"><font color="#4169E1">通过</font></a></span>
				           		<span><a href="<%=request.getContextPath()%>/CheckDelete?Dno=${acc.d_no}"><font color="#4169E1">删除</font></a></span>
				           	</td>
			            </tr>
			        </c:forEach>
			    </c:if>			   
			</table>
			<br /><br /><br />
			<table width="83%" border="0" >
				<tr>
					<td width="33%" align="left">
					    <span class="STYLE13"><%="总共"+RecordCount+"条记录 - 当前页："+PageNo+"/"+MaxPage %> </span>
					</td>
					<td colspan="4" class="STYLE13" align="right">
						<div>
							<%
								out.print("<font size='1'>");
							//显示第一页或者前一页的链接
							//如果当前页不是第1页，则显示第一页和前一页的链接
							if(PageNo != 1){
								PrevStart = PageNo - 1;
								out.print("<a href="+request.getContextPath()+"/CheckThesis?PageNo=1 target='admin_mainFrame'>第一页</a>: ");
								out.print("<a href="+request.getContextPath()+"/CheckThesis?PageNo="+PrevStart+" target='admin_mainFrame'>前一页</a>");
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
									 	out.print("<a href="+request.getContextPath()+"/CheckThesis?PageNo="+c+" target='admin_mainFrame'>"+c+"</a>");
									}else{
									 	out.print("<a href="+request.getContextPath()+"/CheckThesis?PageNo="+c+" target='admin_mainFrame'>"+c+"</a> ,");
									}
								}else{
									if(PageNo == MaxPage){
										out.print(c);
										break;
									}else{
										out.print("<a href="+request.getContextPath()+"/CheckThesis?PageNo="+c+" target='admin_mainFrame'>"+c+"</a>");
									 	break;
									}
								 }
							}
							out.print("]");
							if(PageNo < MaxPage){ //如果当前页不是最后一页，则显示下一页链接
							      NextPage = PageNo + 1;
							      out.print("<a href="+request.getContextPath()+"/CheckThesis?PageNo="+NextPage+" target='admin_mainFrame'>下一页</a>");
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
								 out.print("<a href="+request.getContextPath()+"/CheckThesis?PageNo="+MaxPage+" target='admin_mainFrame'>最后一页</a>");
								
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