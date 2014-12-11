package sxq.gd.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.IdentityService;
import org.springframework.stereotype.Component;

import sxq.gd.dao.RoleDao;
import sxq.gd.dao.UserRoleDao;
import sxq.gd.model.Role;
import sxq.gd.model.UserRole;
import sxq.gd.vo.PagerVo;
import sxq.gd.vo.UserRoleVo;

@Component
public class UserRoleService {
	@Resource
	private UserRoleDao userRoleDao;
	@Resource
	private IdentityService identityService;
	
	@Resource
	private RoleDao roleDao;
	
	public void save(UserRole userRole){
		this.userRoleDao.addEntity(userRole);
		//同步数据到Activiti Identify模块
		this.identityService.createMembership(userRole.getUser().getId()+"", userRole.getRole().getId()+"");
	}
	
	public void update(UserRole userRole){
		this.userRoleDao.updateEntity(userRole);
		if (userRole.isFlag()) {
			//同步数据到Activiti Identify模块
			this.identityService.createMembership(userRole.getUser().getId()+"", userRole.getRole().getId()+"");
		}else{
			this.identityService.deleteMembership(userRole.getUser().getId()+"", userRole.getRole().getId()+"");
		}
	}
	
	public boolean saveOrUpdate(UserRole userRole){
		if(findUserRole(userRole)==null){
			userRole.setFlag(true);
			this.save(userRole);
			return true;
		}else{
			userRole = findUserRole(userRole);
			userRole.setFlag(userRole.isFlag()?false:true);
			this.update(userRole);
			return userRole.isFlag();
		}
	}
	
	@SuppressWarnings("unchecked")
	public UserRole findUserRole(int userId,int roleId){
		String queryHql = "from UserRole t where t.user.id=:userId and t.role.id=:roleId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("roleId", roleId);
		List<UserRole> userRoles = (List<UserRole>) this.userRoleDao.findByPage(queryHql,params, 0, 100);
		if(userRoles.size() > 0){
			return userRoles.get(0);
		}
		return null;
	}
	public UserRole findUserRole(UserRole userRole){
		return this.findUserRole(userRole.getUser().getId(), userRole.getRole().getId());
	}
	
	//得到用户的角色列表
	@SuppressWarnings("unchecked")
	public List<Role> userRoles(int userId){
		String queryHql = "from UserRole t where t.user.id=:userId and t.flag=true";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		List<UserRole> userRoles = (List<UserRole>) this.userRoleDao.findByPage(queryHql,params, 0, 100);
		List<Role> roles = new ArrayList<Role>();
		for(UserRole ur :userRoles){
			roles.add(ur.getRole());
		}
		return roles;
	}
	
	
	//得到用户的角色信息
	@SuppressWarnings("unchecked")
	public PagerVo getUserRoleMapping(int userId,int offset,int pagesize){
		String queryHql = "from Role";
		List<Role> roles = (List<Role>) this.roleDao.findByPage(queryHql, offset, pagesize);
		Long total = this.roleDao.findRowCount(queryHql);
		List<UserRoleVo> vos = new ArrayList<UserRoleVo>(); 
		for(Role role:roles){
			UserRoleVo vo = new UserRoleVo();
			vo.setRoleId(role.getId());
			vo.setCname(role.getCname());
			vo.setEname(role.getEname());
			vo.setDescription(role.getDescription());
			UserRole userRole = findUserRole(userId,role.getId());
			if(userRole==null || !userRole.isFlag()){
				vo.setFlag(false);
			}else{
				vo.setFlag(true);
			}
			vos.add(vo);
		}
		
		PagerVo pager = new PagerVo();
		pager.setTotal(total);
		pager.setRows(vos);
		return pager;
	}
	
}
