package sxq.gd.action;

import javax.annotation.Resource;

import sxq.gd.common.BaseAction;
import sxq.gd.model.Role;
import sxq.gd.model.User;
import sxq.gd.model.UserRole;
import sxq.gd.service.UserRoleService;
import sxq.gd.utils.JSONUtils;
import sxq.gd.vo.PagerVo;

public class UserRoleAction extends BaseAction{
	
	/*@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;*/
	@Resource
	private UserRoleService userRoleService;
	
	private User user;
	private Role role;
	private UserRole userRole;
	
	
	public void getUserRoleMapping(){
		PagerVo pager = this.userRoleService.getUserRoleMapping(user.getId(),page, rows);
		JSONUtils.toJSON(pager);
	}
	
	public void setRole(){
		boolean flag = this.userRoleService.saveOrUpdate(userRole);
		JSONUtils.toJSON(flag?1:0);
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
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

}
