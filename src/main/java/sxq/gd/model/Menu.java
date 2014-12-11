package sxq.gd.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="t_menu")
public class Menu {
	private int id;
	private String name;
	private String url;
	private Menu parent;
	private int orderNumber = 0;
	private boolean flag = false;
	private Set<Menu> subMenu = new HashSet<Menu>();
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	public Menu getParent() {
		return parent;
	}
	public void setParent(Menu parent) {
		this.parent = parent;
	}
	@OneToMany(mappedBy="parent",fetch=FetchType.LAZY)
	@OrderBy("orderNumber")
	public Set<Menu> getSubMenu() {
		return subMenu;
	}
	public void setSubMenu(Set<Menu> subMenu) {
		this.subMenu = subMenu;
	}
	@OrderBy
	public int getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
	@Transient
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
}
