package sxq.gd.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * 选题信息
 * @author SXQ
 */
@Entity
@Table(name="t_subject")
public class Subject {
	private int id ;			//课题编号
	private String name;		//课题名称
	private String description;	//描述
	private String req;			//要求
	private String type;		//类型
	private String froms;		//课题来源
	private String workload;	//工作量
	private String diff;		//难度
	private String advice;		//教研组意见
	private String status;		//状态
	private User createUser;	//创建人
	private String createTime;	//创建时间
	//状态常量
	public static final String STATUS_NOUSE = "0";	//作废
	public static final String STATUS_SAVE = "1";	//暂存
	public static final String STATUS_SUBMIT = "2";	//提交待审
	public static final String STATUS_PASS = "3";	//审核通过
	public static final String STATUS_GUIDANG = "4";//已归档
	public static final String STATUS_SELECT = "5";	//已选择
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getReq() {
		return req;
	}
	public void setReq(String req) {
		this.req = req;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFroms() {
		return froms;
	}
	public void setFroms(String froms) {
		this.froms = froms;
	}
	public String getWorkload() {
		return workload;
	}
	public void setWorkload(String workload) {
		this.workload = workload;
	}
	public String getDiff() {
		return diff;
	}
	public void setDiff(String diff) {
		this.diff = diff;
	}
	public String getAdvice() {
		return advice;
	}
	public void setAdvice(String advice) {
		this.advice = advice;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	
}
