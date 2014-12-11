package sxq.gd.service;

import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.springframework.stereotype.Component;

import sxq.gd.dao.RoleDao;
import sxq.gd.model.Role;
import sxq.gd.vo.PagerVo;

@Component
public class RoleService {
	@Resource
	private RoleDao roleDao;
	
	@Resource
	private IdentityService identityService;
	
	public void save(Role role){
		this.roleDao.addEntity(role);
		//同步数据到Activiti Identify模块
		Group g = this.identityService.newGroup(role.getId()+"");
		this.identityService.saveGroup(g);
	}
	public void saveOrUpdate(Role role){
		if(role.getId() == 0){
			this.save(role);
		}else{
			this.roleDao.updateEntity(role);
		}
	}
	
	public void delete(Role role){
		this.roleDao.deleteEntity(role);
		//同步数据到Activiti Identify模块
		this.identityService.deleteGroup(role.getId()+"");
	}
	public Role findById(int id){
		return this.roleDao.findById(Role.class, id);
	}
	public List<Role> findAll(){
		return this.roleDao.findAll(Role.class);
	}
	@SuppressWarnings("unchecked")
	public PagerVo findByPage(int offset,int pagesize){
		String queryHql = "from Role";
		List<Role> roles = (List<Role>) this.roleDao.findByPage(queryHql, offset, pagesize);
		Long total = this.roleDao.findRowCount(queryHql);
		PagerVo pager = new PagerVo();
		pager.setTotal(total);
		pager.setRows(roles);
		return pager;
	}
	@SuppressWarnings("unchecked")
	public Role findByName(String roleEName){
		String queryHql = "from Role r where r.ename='"+roleEName+"'";
		List<Role> roles = (List<Role>) this.roleDao.findByPage(queryHql, 1, 10);
		
		return roles.get(0);
	}
}
