package silence.util;
/**
* @author 作者：连慧
* @date 创作时间：2017年7月24日 上午11:51:39
* 分页工具类
*/
public class PageSupport {
	//总页数
	private int totalPageCount = 1;
	//总记录数
	private int totalAttendanceRecordCount = 0;
	//当前页码-来自于用户输入
	private int currentPageNo = 1;
	//页面容量
	private int pageSize = 0;
	
	public int getTotalPageCount() {
		return totalPageCount;
	}
	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	public int getTotalAttendanceRecordCount() {
		return totalAttendanceRecordCount;
	}
	public void setTotalAttendanceRecordCount(int totalAttendanceRecordCount) {
		if(totalAttendanceRecordCount>0){
			this.totalAttendanceRecordCount = totalAttendanceRecordCount;
			//设置总页数
			this.setTotalPageCountByRs();
		}
	}
	public int getCurrentPageNo() {
		return currentPageNo;
	}
	public void setCurrentPageNo(int currentPageNo) {
		if(currentPageNo<1){
		  this.currentPageNo = 1;
		}else if(currentPageNo>totalPageCount){
			currentPageNo=totalPageCount;
		}else {
			this.currentPageNo = currentPageNo;
		}
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		if(pageSize>0){
			this.pageSize = pageSize;
		}
	}
	//计算总页数
	public void setTotalPageCountByRs() {
		this.totalPageCount=(totalAttendanceRecordCount+pageSize-1)/pageSize;
	}
}
