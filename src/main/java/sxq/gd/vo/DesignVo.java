package sxq.gd.vo;

import sxq.gd.model.Design;

public class DesignVo {
	public DesignVo(){}
	public DesignVo(Design design){
		this.id = design.getId()+"";
		this.progress = design.getProgress()+"%";
		this.comment = design.getComment();
		this.subjectId = design.getSubject().getId()+"";
		this.subjectName = design.getSubject().getName();
		this.studentId = design.getUser().getId()+"";
		this.studentCode = design.getUser().getCode()+"";
		this.studentName = design.getUser().getName();
		this.createTime = design.getCreateTime();
		this.updateTime = design.getUpdateTime();
	}
	private String id;
	private String subjectId;
	private String subjectName;
	private String studentId;
	private String studentCode;
	private String studentName;
	private String createTime;
	private String progress;
	private String updateTime;
	private String comment;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getProgress() {
		return progress;
	}
	public void setProgress(String progress) {
		this.progress = progress;
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
	public String getStudentCode() {
		return studentCode;
	}
	public void setStudentCode(String studentCode) {
		this.studentCode = studentCode;
	}
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
}
