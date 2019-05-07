package cn.nuist_bjxy.zhangzheng.bean;

/**
 * @author ZhengZheng
 * @description 
 * @date 下午8:54:40
 */

public class Pagebean {
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
	
	
	public int getPageSize() {
		return PageSize;
	}
	public void setPageSize(int pageSize) {
		PageSize = pageSize;
	}
	public int getStartRow() {
		return StartRow;
	}
	public void setStartRow(int startRow) {
		StartRow = startRow;
	}
	public int getPageNo() {
		return PageNo;
	}
	public void setPageNo(int pageNo) {
		PageNo = pageNo;
	}
	public int getCounterStart() {
		return CounterStart;
	}
	public void setCounterStart(int counterStart) {
		CounterStart = counterStart;
	}
	public int getCounterEnd() {
		return CounterEnd;
	}
	public void setCounterEnd(int counterEnd) {
		CounterEnd = counterEnd;
	}
	public int getRecordCount() {
		return RecordCount;
	}
	public void setRecordCount(int recordCount) {
		RecordCount = recordCount;
	}
	public int getMaxPage() {
		return MaxPage;
	}
	public void setMaxPage(int maxPage) {
		MaxPage = maxPage;
	}
	public int getPrevStart() {
		return PrevStart;
	}
	public void setPrevStart(int prevStart) {
		PrevStart = prevStart;
	}
	public int getNextPage() {
		return NextPage;
	}
	public void setNextPage(int nextPage) {
		NextPage = nextPage;
	}
	public int getLastRec() {
		return LastRec;
	}
	public void setLastRec(int lastRec) {
		LastRec = lastRec;
	}
	public int getLastStartRecord() {
		return LastStartRecord;
	}
	public void setLastStartRecord(int lastStartRecord) {
		LastStartRecord = lastStartRecord;
	}
	
	
}
