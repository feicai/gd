package sxq.gd.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import sxq.gd.dao.AclDao;
import sxq.gd.dao.MenuDao;
import sxq.gd.model.Acl;
import sxq.gd.model.Menu;
import sxq.gd.model.Role;
import sxq.gd.vo.MenuVo;

@Component
public class AclService {
	@Resource
	private AclDao aclDao;
	@Resource
	private MenuDao menuDao;
	@Resource 
	UserRoleService userRoleService;
	public void save(Acl acl){
		this.aclDao.addEntity(acl);
	}
	public void update(Acl acl){
		this.aclDao.updateEntity(acl);
	}
	
	//保存或修改Acl
	public boolean saveOrUpdate(Acl acl){
		if(findRoleMenu(acl)==null){
			acl.setAclState(true);
			this.save(acl);
			return true;
		}else{
			acl = findRoleMenu(acl);
			acl.setAclState(acl.isAclState()?false:true);
			this.update(acl);
			return acl.isAclState();
		}
	}
	//查询角色的菜单映射
	@SuppressWarnings("unchecked")
	public Acl findRoleMenu(int menuId,int roleId){
		String queryHql = "from Acl t where t.resourceId=:menuId and t.roleId=:roleId and t.resourceType='menu'";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("menuId", menuId);
		params.put("roleId", roleId);
		List<Acl> acls = (List<Acl>) this.aclDao.findByPage(queryHql, params, 0, 100);
		if(acls.size()>0){
			return acls.get(0);
		}
		return null;
	}
	
	/*//得到用户对应的菜单列表
	public List<Menu> userMenus(int userId){
		List<Role> roles  = this.userRoleService.userRoles(userId);
		String str  = "";
		for(Role role : roles){
			str += role.getId()+","; 
		}
		str = str.substring(0, str.length()-1);
		String queryHql = "from Acl t where t.roleId in ("+str+") and t.resourceType='menu' and t.aclState=true";
		System.out.println(queryHql);
		@SuppressWarnings("unchecked")
		List<Acl> acls = (List<Acl>) this.aclDao.findByPage(queryHql, 0, 100);
		List<Menu> menus = new ArrayList<Menu>();
		for(Acl acl : acls){
			Menu menu = this.menuDao.findById(Menu.class, acl.getResourceId());
			System.out.println(menu.getName());
			menus.add(menu);
		}
		return menus;
	}
	*/
	@SuppressWarnings("unchecked")
	public List<Menu> userMenus(int userId){
		List<Role> roles  = this.userRoleService.userRoles(userId);
		String queryHql = "from Menu m where m.parent is null or m.parent.id=0 order by m.orderNumber";
		List<Menu> menus =  (List<Menu>) this.menuDao.findByPage(queryHql, 0, 100);//得到根菜单
		
		for(Menu menu :menus){
			if(!menu.getSubMenu().isEmpty()){		//如果根菜单有子菜单
				Set<Menu> subMenus = menu.getSubMenu();	//得到子菜单
				Set<Menu> subMenus2 = new HashSet<Menu>();
				boolean flag = false;
				for (Iterator<Menu> iterator = subMenus.iterator(); iterator.hasNext();) {
					Menu subMenu = (Menu) iterator.next();
					for(Role r :roles){
						//如果子菜单符合则设置flag为true
						if(findRoleMenu(subMenu.getId(),r.getId())!=null){
							subMenu.setFlag(true);
							flag = true;
							subMenus2.add(subMenu);
							break;
						}
					}
				}
				menu.setFlag(flag);
				menu.setSubMenu(subMenus2);
			}
		}	
		return menus;
	}
	
	
	public Acl findRoleMenu(Acl acl){
		return this.findRoleMenu(acl.getResourceId(),acl.getRoleId());
	}
	
	@SuppressWarnings("unchecked")
	public List<MenuVo> getRoleMenuMapping(int roleId,int parentId){
		String queryHql = "from Menu m where";
		if(parentId==0){
			queryHql = queryHql +" m.parent is null or m.parent.id=0";
		}else{
			queryHql = queryHql +" m.parent.id="+parentId;
		}
		List<Menu> menus =  (List<Menu>) this.menuDao.findByPage(queryHql, 0, 20);
		List<MenuVo> vos = new ArrayList<MenuVo>();
		for(Menu m :menus){
			MenuVo vo = new MenuVo(m);
			Acl acl = findRoleMenu(m.getId(),roleId);
			if(acl == null || !acl.isAclState()){
				vo.setAclState("0");
			}else{
				vo.setAclState("1");
			}
			vos.add(vo);
		}
		return vos;
	}
	
	public void delete(Acl acl){
		this.aclDao.deleteEntity(acl);
	}

}
