package cn.nuist_bjxy.zhangzheng.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.nuist_bjxy.zhangzheng.bean.Discussbean;
import cn.nuist_bjxy.zhangzheng.bean.Filebean;
import cn.nuist_bjxy.zhangzheng.bean.Pagebean;
import cn.nuist_bjxy.zhangzheng.bean.Thesisbean;
import cn.nuist_bjxy.zhangzheng.dao.DiscussDao;
import cn.nuist_bjxy.zhangzheng.dao.FileDao;
import cn.nuist_bjxy.zhangzheng.dao.ThesisDao;

/**
 * Servlet implementation class CheckThesis
 */
@WebServlet("/CheckThesis")
public class CheckThesis extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckThesis() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//获取页面传过来的ID标识，0-论题审核；1-文件审核；2-评论审核
		String check_id = request.getParameter("CheckId");
		List<Pagebean> pagelist = new ArrayList<>();
		
		if (check_id.equals("0")) {

			pagelist = ThesisDao.pageList();
			
		} else if(check_id.equals("1")){
			
			pagelist = FileDao.pageList();
			
		}else if(check_id.equals("2")) {
			
			pagelist = DiscussDao.pageList();
			
		}else {
			
			response.getWriter().append("出错啦，正在为您返回...");
			response.setHeader("refresh", "3,jsp/admin.jsp");
		
		}
		
		//获取页数据
		int PageSize = pagelist.iterator().next().getPageSize(); //每页显示记录数
		int StartRow = pagelist.iterator().next().getStartRow(); //开始显示记录的编号
		int PageNo = pagelist.iterator().next().getPageNo();//需要显示的页数
		int CounterStart = pagelist.iterator().next().getCounterStart();//每页页码的初始值
		int CounterEnd = pagelist.iterator().next().getCounterEnd();//显示页码的最大值
		int RecordCount = pagelist.iterator().next().getRecordCount();//总记录数;
		int MaxPage = pagelist.iterator().next().getMaxPage();//总页数
		int PrevStart = pagelist.iterator().next().getPrevStart();//前一页
		int NextPage = pagelist.iterator().next().getNextPage();//下一页
		int LastRec = pagelist.iterator().next().getLastRec();
		int LastStartRecord = pagelist.iterator().next().getLastStartRecord();//最后一页开始显示记录的编号
		
		
		//获取需要显示的页数，由用户提交
		if(request.getParameter("PageNo")==null){ //如果为空，则表示第1页
		if(StartRow == 0){
			PageNo = StartRow + 1; //设定为1
		}
		}else{
			PageNo = Integer.parseInt(request.getParameter("PageNo")); //获得用户提交的页数
			StartRow = (PageNo - 1) * PageSize; //获得开始显示的记录编号
		}
		
		//因为显示页码的数量是动态变化的，假如总共有一百页，则不可能同时显示100个链接。而是根据当前的页数显示一定数量的页面链接，设置显示页码的初始值!!
		
		if(PageNo % PageSize == 0){
			CounterStart = PageNo - (PageSize - 1);
		}else{
			CounterStart = PageNo - (PageNo % PageSize) + 1;
		} 
		
		CounterEnd = CounterStart + (PageSize - 1);
		
			//获取总页数
			MaxPage = RecordCount % PageSize;
			
			if(RecordCount % PageSize == 0){
				MaxPage = RecordCount / PageSize;
			}else{
				
			 	MaxPage = RecordCount/PageSize+1;
			 
			}
			
			if(MaxPage == 0) {
				
				MaxPage = 1; //总页数最小为1页
				
			}
			
			request.setAttribute("PageSize", String.valueOf(PageSize));
			request.setAttribute("StartRow", String.valueOf(StartRow));
			request.setAttribute("PageNo",String.valueOf(PageNo));
			request.setAttribute("CounterStart", String.valueOf(CounterStart));
			request.setAttribute("CounterEnd",String.valueOf(CounterEnd));
			request.setAttribute("RecordCount", String.valueOf(RecordCount));
			request.setAttribute("MaxPage", String.valueOf(MaxPage));
			request.setAttribute("PrevStart", String.valueOf(PrevStart));
			request.setAttribute("NextPage", String.valueOf(NextPage));
			request.setAttribute("LastRec",String.valueOf(LastRec));
			request.setAttribute("LastStartRecord", String.valueOf(LastStartRecord));
		
		//System.out.println(pagelist.iterator().next().getPageSize());
			
			switch (check_id) {
				case "0":
					 List<Thesisbean>checkList = ThesisDao.checkList(StartRow,PageSize);
					
				     request.setAttribute("checkList", checkList);
				 
				     request.getRequestDispatcher("jsp/check.jsp").forward(request, response);
					break;
				case "1":
					 List<Filebean>checkFileList = FileDao.checkFileList(StartRow,PageSize);
						
				     request.setAttribute("checkFileList", checkFileList);
				 
				     request.getRequestDispatcher("jsp/checkFileList.jsp").forward(request, response);
					break;
				case "2":
					 List<Discussbean>checkDiscussList = DiscussDao.checkDiscussList(StartRow,PageSize);
						
				     request.setAttribute("checkDiscussList", checkDiscussList);
				 
				     request.getRequestDispatcher("jsp/checkDiscussList.jsp").forward(request, response);
					break;	
				default:
					response.getWriter().append("出错啦，正在为您返回...");
					response.setHeader("refresh", "3,jsp/admin.jsp");
					break;
			}
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
