package sxq.gd.vo;

import org.activiti.engine.task.Task;

public class TaskInfo {
	private String id;
	private String name;
	private String desc;
	private String type;
	private String processDefinitionId;
	private String formKey;
	private String businesskey;
	private String processInstanceId;
	
	
	public TaskInfo(){}
	public TaskInfo(Task task){
		id = task.getId();
		name = task.getName();
		desc = task.getDescription();
		processDefinitionId = task.getProcessDefinitionId();
		processInstanceId = task.getProcessInstanceId();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getProcessDefinitionId() {
		return processDefinitionId;
	}
	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}
	public String getFormKey() {
		return formKey;
	}
	public void setFormKey(String formKey) {
		this.formKey = formKey;
	}
	public String getBusinesskey() {
		return businesskey;
	}
	public void setBusinesskey(String businesskey) {
		this.businesskey = businesskey;
	}
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	
	
}
