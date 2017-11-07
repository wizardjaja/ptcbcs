package com.wizard.ptcbcs.result;

import java.util.List;
/**
 * 封装服务器返回数据
 * 包含：Model对象个数，页数，当前页page,每页显示个数rows,Model对象列表
 * @author wizard
 *
 * @param <T>
 */
public class ResultInfo<T> {

	private int count=0; //个数
	private int pageCount=0; //页数
	private int page=0;
	private int rows=0;
	private List<T> list=null;
	/**
	 * 得到并返回记录数
	 * @return
	 */
	public int getCount() {
		return count;
	}
	/**
	 * 设置记录数
	 * @return
	 */
	public void setCount(int count) {
		this.count = count;
	}
	/**
	 * 得到并返回分页数
	 * @return
	 */
	public int getPageCount() {
		return pageCount;
	}
	/**
	 * 设置分页数
	 * @return
	 */
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	/**
	 * 得到当前页数
	 * @return
	 */
	public int getPage() {
		return page;
	}
	/**
	 * 设置当前页数
	 * @return
	 */
	public void setPage(int page) {
		this.page = page;
	}
	/**
	 * 得到并返回每页显示个数
	 * @return
	 */
	public int getRows() {
		return rows;
	}
	/**
	 * 设置每页显示个数
	 * @return
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}
	/**
	 * 得到并返回对象数据
	 * @return
	 */
	public List<T> getList() {
		return list;
	}
	/**
	 * 设置对象数据
	 * @return
	 */
	public void setList(List<T> list) {
		this.list = list;
	}
	
}
