package sxq.gd.action;

import java.util.List;

import javax.annotation.Resource;

import sxq.gd.common.BaseAction;
import sxq.gd.model.Menu;
import sxq.gd.model.User;
import sxq.gd.service.AclService;
import sxq.gd.service.UserService;

public class LoginAction extends BaseAction{

	private String userCode;
	private String password;
	private User user;
	@Resource
	private UserService userService;
	@Resource
	private AclService aclService;
	private String errorMessage;
	/**
	 * 处理登陆信息
	 * @return
	 */
	public String login(){
		this.user = this.userService.finfByUserCode(userCode);
		if(user == null){
			this.errorMessage = "用户不存在！";
			return "error";
		}
		if(!this.password.equals(this.user.getPassword())){
			this.errorMessage = "密码错误！";
			return "error";
		}
		this.session.setAttribute("LOGIN_USER", this.user);
		//得到用户的菜单
		List<Menu> menus = this.aclService.userMenus(this.user.getId());
		this.session.setAttribute("menus", menus);
		session.setAttribute("theme", "cupertino");
		return "success";
	}
	
	public String logout(){
		this.session.invalidate();
		return "success";
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


}
