package sxq.gd.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.task.Task;

import sxq.gd.common.BaseAction;
import sxq.gd.common.ReturnInfo;
import sxq.gd.model.Approve;
import sxq.gd.model.Subject;
import sxq.gd.service.ActivitiService;
import sxq.gd.service.ApproveService;
import sxq.gd.service.SubjectService;
import sxq.gd.utils.JSONUtils;
import sxq.gd.vo.PagerVo;

public class SubjectAction extends BaseAction{
	@Resource
	private SubjectService subjectService;
	@Resource
	private ApproveService approveService;
	@Resource
	private ActivitiService activitiService;
	
	private Subject subject;
	private Approve approve;
	
	/**
	 * 得到毕业设计选题分页信息
	 */
	public void getSubjects(){
		PagerVo pager = this.subjectService.findByPage(page, rows,subject);
		JSONUtils.toJSON(pager);
	}
	
	@Override
	public String addInput(){
		if(id!=0){
			subject = this.subjectService.findById(id);
		}
		return "addInput";
	}
	
	public void add(){
		String msg = "添加选题成功！";
		boolean flag = true;
		try{
			this.subjectService.saveOrUpdate(subject);
		}catch(Exception e){
			msg = "添加选题失败！";
			flag= false;
		}
		JSONUtils.toJSON(new ReturnInfo(flag,msg));
	}
	public void delete(){
		String msg = "删除选题成功！";
		boolean flag = true;
		try{
			this.subjectService.delete(subject);
		}catch(Exception e){
			msg = "删除选题失败！";
			flag= false;
		}
		JSONUtils.toJSON(new ReturnInfo(flag,msg));
		
	}
	/**
	 * 跳转到审核页面
	 */
	public String approve(){
		return "approve";
	}
	/**
	 * 跳转到审核页面
	 */
	public String approveInput(){
		subject = this.subjectService.findById(id);
		return "approveInput"; 
	}
	/**
	 * 审核提交
	 */
	public void approveSubmit(){
		boolean pass = true;
		subject = this.subjectService.findById(approve.getDuixiang());
		if(approve.getResult().endsWith("1")){		//通过
			subject.setStatus(Subject.STATUS_PASS);
			this.subjectService.update(subject);
		}else if(approve.getResult().endsWith("0")){	//不通过
			subject.setStatus(Subject.STATUS_SAVE);
			this.subjectService.update(subject);
			pass = false;
		}
		//流程变量
		Map<String, Object> vars = new HashMap<String, Object>();
		vars.put("pass", pass);
		//完成任务
		Task task = this.activitiService.queryTask("gd_subject", approve.getDuixiang()+""); 
		this.activitiService.completeTask(task.getId(), vars);
		//如果不通过则指定任务执行人为录入人
		if(!pass){
			task = this.activitiService.queryTask("gd_subject", approve.getDuixiang()+"");
			this.activitiService.setAssignee(task, subject.getCreateUser().getId()+"");
		}
		
		//保存审核信息
		this.approveService.save(approve);
	}
	/**
	 * 跳转到可以归档的课题列表
	 * @return
	 */
	public String guidang(){
		return "guidang";
	}
	/**
	 * 跳转到归档页面
	 */
	public String guidangInput(){
		subject = this.subjectService.findById(id);
		return "guidangInput"; 
	}
	/**
	 * 归档提交处理
	 */
	public void guidangSubmit(){
		subject = this.subjectService.findById(subject.getId());
		subject.setStatus(Subject.STATUS_GUIDANG);
		this.subjectService.update(subject);
		Task task = this.activitiService.queryTask("gd_subject", subject.getId()+""); 
		this.activitiService.completeTask(task.getId());
		
	}
	
	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Approve getApprove() {
		return approve;
	}

	public void setApprove(Approve approve) {
		this.approve = approve;
	}
	

}
