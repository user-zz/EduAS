package cn.nuist_bjxy.zhangzheng.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.nuist_bjxy.zhangzheng.bean.Discussbean;
import cn.nuist_bjxy.zhangzheng.bean.Pagebean;
import cn.nuist_bjxy.zhangzheng.bean.Thesisbean;
import cn.nuist_bjxy.zhangzheng.dao.DiscussDao;
import cn.nuist_bjxy.zhangzheng.dao.ThesisDao;

/**
 * Servlet implementation class SearchThesis
 */
@WebServlet("/SearchThesis")
public class SearchThesis extends HttpServlet {
	private static final long serialVersionUID = 1L;
     Thesisbean thbean = new Thesisbean();
     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchThesis() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		
		String thno = request.getParameter("Thno");
		String  id = request.getParameter("ID");
		
		List<Thesisbean> thesisRstList = new ArrayList<>();
		
		thesisRstList = ThesisDao.ThesisCheckRstList(thno);
		
		session.setAttribute("thesisRstList", thesisRstList);
		
		//如果id=1，则为普通用户的操作
		if(id == null || id.equals("")) {

			request.getRequestDispatcher("jsp/thesisRstList.jsp").forward(request, response);
		
		}else if(id.equals("1") ) {
			
			session.setAttribute("Thno", thno);
			
			 List<Discussbean> discsList = DiscussDao.seeDiscsList(thno);
			 request.setAttribute("discsList", discsList);
			 
			request.getRequestDispatcher("jsp/discuss.jsp").forward(request, response);
			
		}else if(id.equals("selfSearch")) {
			
			String thusername = request.getParameter("Thusername");
			List<Thesisbean> selfSearchThesisRstList = ThesisDao.SearchThesis(thusername);
			session.setAttribute("selfSearchThesisRstList", selfSearchThesisRstList);

			request.getRequestDispatcher("jsp/selfSearchThesisRstList.jsp").forward(request, response);
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		List<Thesisbean> thesisSearchRstList =new ArrayList<>();
		
		String condition_id = request.getParameter("condition_id");
		String title = "";
		String thusername = "";
		
		//condition_id:1--按标题查找；2--按发布者查找
		if(condition_id.equals("1")) {
			
			title = request.getParameter("condition");
			
		}else if(condition_id.equals("2")) {
			
			thusername = request.getParameter("condition");
			
		}else {
			
			response.getWriter().append("出错啦！请点击<a href='jsp/searchNews.jsp' target='mainFrame'>此处</a>返回");
			
		}		
		String ID = "";
		ID = request.getParameter("ID");
		if(ID == null || ID.equals("")) {
			System.out.println(title+"--"+thusername);
			
			thesisSearchRstList = ThesisDao.SearchThesis(title, thusername);
			request.setAttribute("thesisSearchRstList", thesisSearchRstList);
			request.getRequestDispatcher("jsp/searchNews.jsp").forward(request, response);
		}
		if(ID.equals("adminSearch")) {
			List<Pagebean> pagelist = new ArrayList<>();
			pagelist = ThesisDao.pageList();
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
			
			 List<Thesisbean>checkList = ThesisDao.adminSearch(title, thusername);
				
		     request.setAttribute("checkList", checkList);
		 
		     request.getRequestDispatcher("jsp/check.jsp").forward(request, response);
		}
		/*
			System.out.println(title+"--"+thusername);
			
			thesisSearchRstList = ThesisDao.SearchThesis(title, thusername);
			request.setAttribute("thesisSearchRstList", thesisSearchRstList);
			request.getRequestDispatcher("jsp/searchNews.jsp").forward(request, response);
			*/
	}

}
