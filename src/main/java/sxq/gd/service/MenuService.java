package sxq.gd.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import sxq.gd.dao.MenuDao;
import sxq.gd.model.Menu;
import sxq.gd.vo.MenuVo;

@Component
public class MenuService {
	@Resource
	private MenuDao menuDao;
	
	public void saveOrUpdate(Menu menu){
		if(menu.getParent()!=null&&menu.getParent().getId()==0){
			menu.setParent(null);
		}
		if(menu.getId() == 0){
			this.menuDao.addEntity(menu);
		}else{
			this.menuDao.updateEntity(menu);
		}
	}
	public void update(Menu menu){
		this.menuDao.updateEntity(menu);
	}
	public void delete(Menu menu){
		this.menuDao.deleteEntity(menu);
	}
	public Menu findById(int id){
		return this.menuDao.findById(Menu.class, id);
	}
	public List<Menu> findAll(){
		return this.menuDao.findAll(Menu.class);
	}

	@SuppressWarnings("unchecked")
	public List<MenuVo> findRootMenu(){
		String queryHql = "from Menu m where m.parent is null or m.parent.id=0";
		List<Menu> menus =  (List<Menu>) this.menuDao.findByPage(queryHql, 0, 20);
			
		return this.getMenuVos(menus);
	}
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> rootMenuJson(int id){
		String queryHql = "from Menu m where m.parent is null or m.parent.id=0";
		List<Menu> menus =  (List<Menu>) this.menuDao.findByPage(queryHql, 0, 20);
		
		List<Map<String, Object>> info = new ArrayList<Map<String,Object>>();
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("id", 0);
		root.put("name", "==根目录==");
		info.add(root);
		for(Menu m:menus){
			Map<String, Object> vo = new HashMap<String, Object>();
			vo.put("id", m.getId());
			vo.put("name", m.getName());
			if(m.getId()==id){
				vo.put("selected", true);
			}
			info.add(vo);
		}
		return info;
		
	}
	@SuppressWarnings("unchecked")
	public List<MenuVo> findByParent(int parentId){
		String queryHql = "from Menu m where m.parent.id =:id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", parentId);
		List<Menu> menus =  (List<Menu>) this.menuDao.findByPage(queryHql,params, 0, 20);
		return this.getMenuVos(menus);
	}
	
	public List<MenuVo> getMenuVos(List<Menu> menus){
		List<MenuVo> vos = new ArrayList<MenuVo>();
		for(Menu m :menus){
			MenuVo vo = new MenuVo(m);
			vos.add(vo);
		}
		return vos;
	}
}
