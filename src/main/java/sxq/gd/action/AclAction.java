package sxq.gd.action;

import java.util.List;

import javax.annotation.Resource;

import sxq.gd.common.BaseAction;
import sxq.gd.model.Acl;
import sxq.gd.service.AclService;
import sxq.gd.utils.JSONUtils;
import sxq.gd.vo.MenuVo;

public class AclAction extends BaseAction{
	
	@Resource
	private AclService aclService;
	private Acl acl;
	private int roleId;
	private int userId;
	//角色的菜单
	public void getRoleMenuMapping(){
		List<MenuVo> vos = this.aclService.getRoleMenuMapping(roleId,id);
		JSONUtils.toJSON(vos);
	}
	//授权
	public void auth(){
		acl.setRoleId(roleId);
		acl.setResourceType(Acl.MENU);
		boolean flag = this.aclService.saveOrUpdate(acl);
		JSONUtils.toJSON(flag?1:0);
	}
	
	public String menuList(){
		return "menuList";
	}

	public Acl getAcl() {
		return acl;
	}

	public void setAcl(Acl acl) {
		this.acl = acl;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
}
