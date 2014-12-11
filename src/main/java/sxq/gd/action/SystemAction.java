package sxq.gd.action;

import sxq.gd.common.BaseAction;

/**
 * 系统设置
 * @author SXQ
 */
public class SystemAction extends BaseAction{
	/**
	 * 跳转到主题设置界面
	 * @return
	 */
	public String theme(){
		String[] ts = {
				//"black",
				//"bootstrap",
				"cupertino",
				"dark-hive",
				"default",
				"pepper-grinder","sunny",
				"metro-blue","metro-gray","metro-green","metro-orange","metro-red"};
		request.setAttribute("ts", ts);
		return "theme";
	}
	/**
	 * 设置主题
	 * @return
	 */
	public void setTheme(){
		String theme = request.getParameter("theme");
		session.setAttribute("theme", theme);
	}
}
