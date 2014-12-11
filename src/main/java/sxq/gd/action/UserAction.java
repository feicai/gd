package sxq.gd.action;

import javax.annotation.Resource;

import sxq.gd.common.BaseAction;
import sxq.gd.common.ReturnInfo;
import sxq.gd.model.Role;
import sxq.gd.model.User;
import sxq.gd.model.UserRole;
import sxq.gd.service.UserRoleService;
import sxq.gd.service.UserService;
import sxq.gd.utils.JSONUtils;
import sxq.gd.vo.PagerVo;

public class UserAction extends BaseAction{
	@Resource
	private UserService userService;
	@Resource
	private UserRoleService userRoleService;
	
	private User user;
	private Role role;
	private UserRole userRole;
	
	/**
	 * 得到用户分页信息
	 */
	public void getUsers(){
		PagerVo pager = this.userService.findByPage(page, rows);
		JSONUtils.toJSON(pager);
	}
	
	@Override
	public String addInput(){
		if(id!=0){
			user = this.userService.findById(id);
		}
		return "addInput";
	}
	
	public void add(){
		String msg = "添加用户成功！";
		boolean flag = true;
		try{
			this.userService.saveOrUpdate(user);
		}catch(Exception e){
			msg = "添加用户失败！";
			flag= false;
		}
		JSONUtils.toJSON(new ReturnInfo(flag,msg));
	}
	public void delete(){
		String msg = "删除用户成功！";
		boolean flag = true;
		try{
			this.userService.delete(user);
		}catch(Exception e){
			msg = "删除用户失败！";
			flag= false;
		}
		JSONUtils.toJSON(new ReturnInfo(flag,msg));
		
	}
	
	public void setRole(){
		this.userRoleService.saveOrUpdate(userRole);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

}
