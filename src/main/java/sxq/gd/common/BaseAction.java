package sxq.gd.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import sxq.gd.model.User;

public abstract class BaseAction {
	
	protected HttpServletRequest request ;
	protected HttpServletResponse response;
	protected HttpSession session;
	
	protected int id;
	protected int page;		//页号
	protected int rows;		//每页记录数
	
	public BaseAction(){
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		session = request.getSession();
	}
	
	public int getCurrentUserId(){
		return ((User)this.session.getAttribute("LOGIN_USER")).getId();
	}
	public User getCurrentUser(){
		return (User)this.session.getAttribute("LOGIN_USER");
	}
	/**
	 * 跳转到信息列表页面
	 * @return
	 */
	public String list(){
		return "list";
	}	
	/**
	 * 跳转到信息添加或修改页面
	 * @return
	 */
	public String addInput(){
		return "addInput";
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
