package sxq.gd.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_design")
public class Design {
	private int id;						//id
	private Subject subject;			//所选课题
	private User user;					//选题学生
	private String progress;			//进度
	private String state = "0";			//当前状态---0：暂存 ；1：提交待审；2：审核通过	3：审核没通过
	private String createTime;
	private String updateTime;
	private String comment;				//备注问题
	
	public static final String STATUS_NOUSE = "0";	//未选题
	public static final String STATUS_SELECT = "1";	//选题
	public static final String STATUS_SELECT_SUCCESS = "2";	//选题成功
	public static final String STATUS_SAVE = "3";	//暂存
	public static final String STATUS_SUBMIT = "4";	//提交待审
	public static final String STATUS_PASS = "5";	//审核通过
	public static final String STATUS_GUIDANG = "6";//已归档

	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@OneToOne
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	@OneToOne
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getProgress() {
		return progress;
	}
	public void setProgress(String progress) {
		this.progress = progress;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

	
}
