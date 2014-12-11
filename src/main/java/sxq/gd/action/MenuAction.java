package sxq.gd.action;

import java.util.List;

import javax.annotation.Resource;

import sxq.gd.common.BaseAction;
import sxq.gd.common.ReturnInfo;
import sxq.gd.model.Menu;
import sxq.gd.service.MenuService;
import sxq.gd.utils.JSONUtils;
import sxq.gd.vo.MenuVo;

public class MenuAction extends BaseAction{
	@Resource
	private MenuService menuService;
	private Menu menu;
	
	public void getMenus(){
		List<MenuVo> vos = null;
		if(id == 0){
			vos= this.menuService.findRootMenu();
		}else{
			vos = this.menuService.findByParent(id);
		}
		
		JSONUtils.toJSON(vos);
	}
	public String addInput(){
		if(menu != null){
			menu = this.menuService.findById(menu.getId());
		}
		return "addInput";
	}
	public void add(){
		this.menuService.saveOrUpdate(menu);
	}
	public void delete(){
		ReturnInfo info = new ReturnInfo(true,"删除成功");
		try{
			this.menuService.delete(menu);
		}catch(Exception e){
			info.setFlag(false);
			info.setMsg("删除失败");
		}
		JSONUtils.toJSON(info);
	}
	public void getRootMenu(){
		JSONUtils.toJSON(this.menuService.rootMenuJson(id));
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
}
