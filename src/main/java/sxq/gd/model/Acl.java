package sxq.gd.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_acl")
public class Acl {
	public static final String MENU = "menu"; 
	public static final String RESOURCE = "resource"; 
	private int id;
	private int roleId;
	private String resourceType;
	private int resourceId;
	private boolean aclState;
	private int aclTriState;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getResourceType() {
		return resourceType;
	}
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	public int getResourceId() {
		return resourceId;
	}
	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}
	
	public int getAclTriState() {
		return aclTriState;
	}
	public void setAclTriState(int aclTriState) {
		this.aclTriState = aclTriState;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public boolean isAclState() {
		return aclState;
	}
	public void setAclState(boolean aclState) {
		this.aclState = aclState;
	}
	
	
}
