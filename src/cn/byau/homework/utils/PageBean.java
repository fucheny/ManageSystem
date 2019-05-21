package cn.byau.homework.utils;

import java.util.List;

/**
 * 这个Bean用于分页显示
 * 
 * 包括5项内容： 当前页的的学生数据集合 总的记录数 总的页数 当前页 每页显示的记录数
 * 
 * @author
 *
 */

public class PageBean<T> { // 这里使用泛型，不仅限于学生信息的分页，也可以是老师等等其他的。如果T改为Student，则只能用于学生分页

	private int currentPage; // 当前页数
	private int totalPage; // 总页数
	private int pageSize; // 每页记录数
	private int totalSize; // 总记录数
	private List<T> list; // 当前页学生的数据集合

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> listt) {
		this.list = listt;
	}

	public PageBean() {
		super();
		// TODO Auto-generated constructor stub
	}

}
