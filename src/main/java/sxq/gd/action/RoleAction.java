package sxq.gd.action;

import javax.annotation.Resource;

import sxq.gd.common.BaseAction;
import sxq.gd.common.ReturnInfo;
import sxq.gd.model.Role;
import sxq.gd.service.RoleService;
import sxq.gd.utils.JSONUtils;
import sxq.gd.vo.PagerVo;

public class RoleAction extends BaseAction{
	@Resource
	private RoleService roleService;
	
	private Role role;
	/**
	 * 得到用户分页信息
	 */
	public void getRoles(){
		PagerVo pager = this.roleService.findByPage(page, rows);
		JSONUtils.toJSON(pager);
	}
	@Override
	public String addInput(){
		if(id!=0){
			role = this.roleService.findById(id);
		}
		return "addInput";
	}
	
	public void add(){
		String msg = "添加角色成功！";
		boolean flag = true;
		try{
			this.roleService.saveOrUpdate(role);
		}catch(Exception e){
			msg = "添加角色失败！";
			flag= false;
		}
		JSONUtils.toJSON(new ReturnInfo(flag,msg));
	}
	public void delete(){
		String msg = "删除角色成功！";
		boolean flag = true;
		try{
			this.roleService.delete(role);
		}catch(Exception e){
			msg = "删除角色失败！";
			flag= false;
		}
		JSONUtils.toJSON(new ReturnInfo(flag,msg));
	}
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}

	
	

}
