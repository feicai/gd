package sxq.gd.service;

import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.UserQuery;
import org.springframework.stereotype.Component;

import sxq.gd.dao.UserDao;
import sxq.gd.model.User;
import sxq.gd.vo.PagerVo;

@Component
public class UserService {
	@Resource
	private UserDao userDao;
	
	@Resource
	private IdentityService identityService;
	public void save(User user){
		this.userDao.addEntity(user);
		//同步数据到Activiti Identify模块
		UserQuery userQuery = identityService.createUserQuery();
        List<org.activiti.engine.identity.User> activitiUsers = userQuery.userId(user.getId()+"").list();
        if(activitiUsers.size()==0){
        	org.activiti.engine.identity.User u = this.identityService.newUser(user.getId()+"");
        	identityService.saveUser(u);
        }
	}
	public void update(User user){
		this.userDao.updateEntity(user);
		//同步数据到Activiti Identify模块
		UserQuery userQuery = identityService.createUserQuery();
		List<org.activiti.engine.identity.User> activitiUsers = userQuery.userId(user.getId()+"").list();
		if(activitiUsers.size()==0){
			org.activiti.engine.identity.User u = this.identityService.newUser(user.getId()+"");
			identityService.saveUser(u);
		}
	}
	public void saveOrUpdate(User user){
		if(user.getId() == 0){
			this.save(user);
		}else{
			this.update(user);
		}
	}
	
	public void delete(User user){
		this.userDao.deleteEntity(user);
		//同步数据到Activiti Identify模块
		this.identityService.deleteUser(user.getId()+"");
	}
	public User findById(int id){
		return this.userDao.findById(User.class, id);
	}
	public List<User> findAll(){
		return this.userDao.findAll(User.class);
	}
	public User finfByUserCode(String code){
		return this.userDao.findByCode(code);
	}
	@SuppressWarnings("unchecked")
	public PagerVo findByPage(int offset,int pagesize){
		String queryHql = "from User";
		List<User> users = (List<User>) this.userDao.findByPage(queryHql, offset, pagesize);
		Long total = this.userDao.findRowCount(queryHql);
		PagerVo pager = new PagerVo();
		pager.setTotal(total);
		pager.setRows(users);
		return pager;
	}
}
