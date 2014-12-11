package sxq.gd.vo;

import sxq.gd.model.Menu;

public class MenuVo {
	public MenuVo(Menu menu){
		this.id = menu.getId();
		this.name = menu.getName();
		this.url = menu.getUrl();
		this.orderNumber = menu.getOrderNumber();
		
		if(menu.getParent()!=null){
			this.parentId = menu.getParent().getId();
		}else{
			this.state = "closed";
		}
		/*if(menu.getSubMenu().size()>0){
			this.state = "closed";
		}*/
	}
	private int id;
	private String name;
	private String url;
	private int parentId;
	private int orderNumber;
	private String state;
	private String aclState;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public int getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAclState() {
		return aclState;
	}
	public void setAclState(String aclState) {
		this.aclState = aclState;
	}
	
	
}
