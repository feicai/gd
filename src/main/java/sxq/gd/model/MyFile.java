package sxq.gd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="t_myfile")
public class MyFile {
	private int id;			//ID
	private String name;	//文件名
	private String type;	//文件类型
	private String suffix;	//文件后缀
	private long size;		//文件大小
	private String catelog;	//审批模块
	private int duixiang;	//审批对象
	private byte[] content;		//内容
	private User createUser;
	private String createTime;	//创建时间
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}

	
	@Column(length=10*1024*1024)
	public byte[] getContent() {
		return content;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	@ManyToOne
	public User getCreateUser() {
		return createUser;
	}
	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}
	
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCatelog() {
		return catelog;
	}
	public void setCatelog(String catelog) {
		this.catelog = catelog;
	}
	public int getDuixiang() {
		return duixiang;
	}
	public void setDuixiang(int duixiang) {
		this.duixiang = duixiang;
	}
}
