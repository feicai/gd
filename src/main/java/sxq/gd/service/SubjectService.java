package sxq.gd.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.activiti.engine.task.Task;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import sxq.gd.dao.SubjectDao;
import sxq.gd.model.Subject;
import sxq.gd.model.User;
import sxq.gd.utils.DateUtils;
import sxq.gd.vo.PagerVo;

@Component
public class SubjectService {
	@Resource
	private SubjectDao subjectDao;
	@Resource
	private ActivitiService activitiService;
	@Resource
	private RoleService roleService;
	
	public void save(Subject subject){
		this.subjectDao.addEntity(subject);
	}
	public void update(Subject subject){
		this.subjectDao.updateEntity(subject);
	}
	/**
	 * 添加或修改课题，并且开始流程实例或者完成任务
	 * @param subject
	 */
	public void saveOrUpdate(Subject subject){
		Map<String, Object> vars = new HashMap<String, Object>();
		vars.put("teacher", roleService.findByName("teacher").getId()+"");
		vars.put("manager", roleService.findByName("manager").getId()+"");
		vars.put("leader", roleService.findByName("leader").getId()+"");
		if(subject.getId() == 0){
			HttpSession session = ServletActionContext.getRequest().getSession();
			User user = (User) session.getAttribute("LOGIN_USER");		//得到当前登录的用户
			subject.setCreateUser(user);							
			subject.setCreateTime(DateUtils.getCurrentTime());
			this.subjectDao.addEntity(subject);
			
		}else{
			this.subjectDao.updateEntity(subject);
		}
		vars.put("desc", subject.getName());
		//如果提交
		if(Subject.STATUS_SUBMIT.equals(subject.getStatus())){
			Task task = this.activitiService.queryTask("gd_subject", subject.getId()+"");
			if(task ==  null){
				this.activitiService.startProcessInstance("gd_subject", subject.getId()+"", vars);
			}
			task = this.activitiService.queryTask("gd_subject", subject.getId()+"");
			this.activitiService.completeTask(task.getId());
		}
	}
	
	public void delete(Subject subject){
		this.subjectDao.deleteEntity(subject);
	}
	public Subject findById(int id){
		return this.subjectDao.findById(Subject.class, id);
	}
	public List<Subject> findAll(){
		return this.subjectDao.findAll(Subject.class);
	}

	public PagerVo findByPage(int offset,int pagesize,Subject subject){
		return this.subjectDao.findSubject(offset, pagesize,subject);
	}
}
