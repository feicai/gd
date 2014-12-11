package sxq.gd.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_approve")
public class Approve {
	private int id;
	private String advice;	//审批意见
	private String result;	//审批结果
	private String catelog;	//审批模块
	private int duixiang;	//审批对象
	private String common;	//备注
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAdvice() {
		return advice;
	}
	public void setAdvice(String advice) {
		this.advice = advice;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
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
	public String getCommon() {
		return common;
	}
	public void setCommon(String common) {
		this.common = common;
	}
	
}
