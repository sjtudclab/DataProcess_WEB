package cn.edu.sjtu.dcl.util;

import java.util.List;

import cn.edu.sjtu.dcl.dao.bean.Job;

public class Page {
	
	 private long pageSize;
	    private long totalPage; 
	    private long rowCount;
	    private long currentPage;
	    private long prePage; 
	    private long nextPage;
	    private boolean hasNextPage;
	    private boolean hasPreviousPage;
		private List list;

	    public Page(int pageSize,int pno,long rowCount,List list) {
	        this.setPageSize(pageSize);
	        this.setCurrentPage(pno);
	        this.setPrePage(pno-1);
	        this.setNextPage(pno+1);
	        this.setList(list);
	        this.setRowCount(rowCount);
	        this.setTotalPage(rowCount/pageSize+1);
	        if(this.getPrePage()>0){
	        	this.setHasPreviousPage(true);
	        } else {
	        	this.setHasPreviousPage(false);
	        }
	        if(this.getNextPage()<=this.getTotalPage()){
	        	this.setHasNextPage(true);
	        } else {
	        	this.setHasNextPage(false);
	        }
	    }

	    public long getPageSize() {
	        return pageSize;
	    }

	    public void setPageSize(long pageSize) {
	        this.pageSize = pageSize;
	    }

	    public long getTotalPage() {
	        return totalPage;
	    }

	    public void setTotalPage(long totalPage) {
	        this.totalPage = totalPage;
	    }

	    public long getRowCount() {
	        return rowCount;
	    }

	    public void setRowCount(long rowCount) {
	        this.rowCount = rowCount;
	    }

	    public long getCurrentPage() {
	        return currentPage;
	    }

	    public void setCurrentPage(long currentPage) {
	        this.currentPage = currentPage;
	    }

	    public long getPrePage() {
	        return prePage;
	    }

	    public void setPrePage(long prePage) {
	        this.prePage = prePage;
	    }

	    public long getNextPage() {
	        return nextPage;
	    }

	    public void setNextPage(long nextPage) {
	        this.nextPage = nextPage;
	    }

	    public boolean isHasNextPage() {
	        return hasNextPage;
	    }

	    public void setHasNextPage(boolean hasNextPage) {
	        this.hasNextPage = hasNextPage;
	    }

	    public boolean isHasPreviousPage() {
	        return hasPreviousPage;
	    }

	    public void setHasPreviousPage(boolean hasPreviousPage) {
	        this.hasPreviousPage = hasPreviousPage;
	    }

	    public List getList() {
	        return list;
	    }

	    public void setList(List list) {
	        this.list = list;
	    }
	    

}
