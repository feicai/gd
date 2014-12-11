package sxq.gd.service;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sxq.gd.vo.PagerVo;
import sxq.gd.vo.ProcessDefinitionInfo;
import sxq.gd.vo.ProcessInstanceInfo;
import sxq.gd.vo.TaskInfo;

@Component
public class ActivitiService {
	@Autowired
	private ProcessEngineFactoryBean processEngine;
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;
	//@Autowired
	//private HistoryService historyService;
	@Autowired
	private FormService formService;
	/**
	 * 根据流程定义key和业务主键来查询唯一的任务
	 * @param processDefinitionKey
	 * @param bussinessKey
	 * @return
	 */
	public Task queryTask(String processDefinitionKey,String bussinessKey){
		List<Task> tasks = this.taskService.createTaskQuery()
			.processDefinitionKey(processDefinitionKey)
			.processInstanceBusinessKey(bussinessKey)
			.list();
		if(tasks.size() == 1){
			return tasks.get(0);
		}else{
			return null;
		}
	}
	/**
	 * 查询当前用户的代办任务
	 * @param processDefinitionKey
	 * @param userId
	 * @return
	 */
	public List<TaskInfo> queryCurrentTasks(String userId){
		List<TaskInfo> taskInfos = new ArrayList<TaskInfo>();
		//候选人任务
		List<Task> ts1 = this.taskService.createTaskQuery()
			.taskCandidateUser(userId).list();
		for(Task t :ts1){
			TaskInfo info = new TaskInfo(t);
			String formKey = this.formService.getTaskFormKey(t.getProcessDefinitionId(), t.getTaskDefinitionKey());
			ProcessInstance processInstance = this.runtimeService.
					createProcessInstanceQuery().processInstanceId(t.getProcessInstanceId()).singleResult();
			info.setBusinesskey(processInstance.getBusinessKey());
			info.setFormKey(formKey);
			String desc = (String) this.taskService.getVariable(t.getId(), "desc");
			info.setDesc(desc);
			info.setType("candidate");
			taskInfos.add(info);
		}
		//本人任务
		List<Task> ts2 = this.taskService.createTaskQuery()
				.taskAssignee(userId).list();
		for(Task t :ts2){
			TaskInfo info = new TaskInfo(t);
			String formKey = this.formService.getTaskFormKey(t.getProcessDefinitionId(), t.getTaskDefinitionKey());
			ProcessInstance processInstance = this.runtimeService.
					createProcessInstanceQuery().processInstanceId(t.getProcessInstanceId()).singleResult();
			info.setBusinesskey(processInstance.getBusinessKey());
			info.setFormKey(formKey);
			String desc = (String) this.taskService.getVariable(t.getId(), "desc");
			info.setDesc(desc);
			info.setType("assignee");
			taskInfos.add(info);
		}
		return taskInfos;
	}
	/**
	 * 开始一个流程实例
	 * @param processDefinitionKey	流程定义key
	 * @param businessKey			业务主键
	 * @param vars					流程变量
	 */
	public ProcessInstance startProcessInstance(String processDefinitionKey,String businessKey,Map<String, Object> vars){
		ProcessInstance processInstance  =this.runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey, vars);
		return processInstance;
	}
	/**
	 * 签收任务
	 * @param taskId	任务Id
	 * @param userId	用户Id
	 */
	public void claim(String taskId,String userId){
		this.taskService.claim(taskId, userId);
	}
	/**
	 * 完成任务
	 * @param taskId	任务ID
	 * @param vars		流程变量
	 */
	public void completeTask(String taskId,Map<String, Object> vars){
		this.taskService.complete(taskId, vars);
	}
	/**
	 * 指定任务执行人
	 */
	public void setAssignee(Task task,String userId){
		task.setAssignee(userId);
		this.taskService.saveTask(task);
	}
	/**
	 * 完成任务
	 * @param taskId	任务ID
	 */
	public void completeTask(String taskId){
		this.taskService.complete(taskId);
	}
	/**
	 * 得到表单名称
	 * @param taskId
	 * @return
	 */
	public String queryFormKey(String taskId){
		return (String) this.formService.getRenderedTaskForm(taskId);
	}
	/**
	 * 查询节点信息
	 */
	public List<ActivityImpl> queryActivityList(String processDefinitionId){
		ProcessDefinitionEntity processDefinition = 
				(ProcessDefinitionEntity) ((RepositoryServiceImpl) this.repositoryService)
				.getDeployedProcessDefinition(processDefinitionId);
		List<ActivityImpl> activitiList = processDefinition.getActivities();
		return activitiList;
	} 
	
	/**
	 * 部署流程
	 */
	public Deployment deployProcess(String path) {
		Deployment deployment = repositoryService.createDeployment()
				.name("gd_subject")
				.addClasspathResource(path)
				.deploy();
		return deployment;
	}
	/**
	 * 删除流程定义
	 */
	public void undeployProcess(String deploymentId) {
		this.repositoryService.deleteDeployment(deploymentId);
	}
	public ProcessDefinition queryProcessDefinitionByKey(String processDefinitionKey){
		ProcessDefinition pdf = repositoryService
				.createProcessDefinitionQuery().processDefinitionKey("")
				.singleResult();
		return pdf;
	}
	
	/**
	 * 得到流程图
	 * @param processDefinitionId	流程定义ID
	 * @return
	 * @throws Exception
	 */
	public BufferedImage getProcessImage(String processInstanceId) throws Exception{
		//查询流程实例
		ProcessInstance processInstance = this.runtimeService
				.createProcessInstanceQuery()
				.processInstanceId(processInstanceId).singleResult();
		//查询流程定义
		ProcessDefinition pd = this.repositoryService.createProcessDefinitionQuery().
				processDefinitionId(processInstance.getProcessDefinitionId()).singleResult();
		//查询当前节点
		ActivityImpl currentActivity = this.getCurrentActivity(processInstanceId);
		
		InputStream is = this.repositoryService.getResourceAsStream(pd.getDeploymentId(), pd.getDiagramResourceName());
		BufferedImage image = ImageIO.read(is);
		BufferedImage sourceImage = new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_RGB);
		Graphics g = sourceImage.getGraphics();
		g.drawImage(image, 0, 0, null);
		g.setColor(Color.RED);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(1.6f));
		g.drawRoundRect(currentActivity.getX(),currentActivity.getY(),currentActivity.getWidth(), currentActivity.getHeight(), 20	, 20);
		g.dispose();
		return sourceImage;
	}
	/**
	 * 得到流程节点信息
	 * @param processDefinitionId		流程定义ID
	 * @return
	 */
	public List<ActivityImpl> getActivities(String processDefinitionId){
		ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) this.repositoryService)
				.getDeployedProcessDefinition(processDefinitionId);
		List<ActivityImpl> activities = processDefinition.getActivities();
		return activities;
	}
	
	/**
	 * 得到当前的流程节点
	 * @param processInstanceId
	 * @return
	 */
	public ActivityImpl getCurrentActivity(String processInstanceId){
		Execution execution = runtimeService.createExecutionQuery().executionId(processInstanceId).singleResult();//执行实例
		Object property = null;
		try {
			property = PropertyUtils.getProperty(execution, "activityId");
		} catch (Exception e) {
			e.printStackTrace();
		}
		//查询流程实例
		ProcessInstance processInstance = this.runtimeService
				.createProcessInstanceQuery()
				.processInstanceId(processInstanceId).singleResult();
		String activityId = "";
		if (property != null) {
			activityId = property.toString();
		}
		List<ActivityImpl> as = this.getActivities(processInstance.getProcessDefinitionId());
		for(ActivityImpl a : as){
			if(a.getId().equals(activityId)){
				return a;
			}
		}
		return null;
	}
	/*
	有乱码
	public InputStream getProcessImage2(String processInstanceId) throws IOException{
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
	    BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
	    List<String> activeActivityIds = runtimeService.getActiveActivityIds(processInstanceId);
	    ProcessEngineImpl defaultProcessEngine = (ProcessEngineImpl) ProcessEngines.getDefaultProcessEngine();
	    Context.setProcessEngineConfiguration(defaultProcessEngine.getProcessEngineConfiguration());
	    Context.setProcessEngineConfiguration( processEngine.getProcessEngineConfiguration());
	    InputStream imageStream = ProcessDiagramGenerator.generateDiagram(bpmnModel, "png", activeActivityIds);
	    return imageStream;
	}*/
	/**
	 * 流程实例列表
	 * @param offset
	 * @param pagesize
	 * @return
	 */
	public PagerVo getProcessInstanceList(int offset,int pagesize){
		List<ProcessInstance> processInstanceList = this.runtimeService
				.createProcessInstanceQuery().listPage(offset-1, pagesize);
		List<ProcessInstanceInfo> vos = new ArrayList<ProcessInstanceInfo>();
		for(ProcessInstance pi:processInstanceList){
			ProcessInstanceInfo vo = new ProcessInstanceInfo();
			vo.setId(pi.getId());
			vo.setBusinessKey(pi.getBusinessKey());
			vo.setProcessDefinitionId(pi.getProcessDefinitionId());
			vo.setStatus(pi.isSuspended()?"挂起":"激活");
			vos.add(vo);
		}
		Long count = this.runtimeService.createProcessInstanceQuery().count();
		PagerVo pagerVo = new PagerVo();
		pagerVo.setRows(vos);
		pagerVo.setTotal(count);
		return pagerVo;
	}
	/**
	 * 流程定义列表
	 * @param offset
	 * @param pagesize
	 * @return
	 */
	public PagerVo getProcessDefinitionList(int offset,int pagesize){		
		List<ProcessDefinition> processDefinitionList = this.repositoryService
				.createProcessDefinitionQuery().listPage(offset-1, pagesize);
		List<ProcessDefinitionInfo> vos = new ArrayList<ProcessDefinitionInfo>();
		for(ProcessDefinition pd : processDefinitionList){
			ProcessDefinitionInfo vo = new ProcessDefinitionInfo();
			vo.setId(pd.getId());
			vo.setKey(pd.getKey());
			vo.setName(pd.getName());
			vo.setCategory(pd.getCategory());
			vo.setVersion(pd.getVersion()+"");
			vo.setStatus(pd.isSuspended()?"挂起":"激活");
			vos.add(vo);
		}
		Long count = this.repositoryService.createProcessDefinitionQuery().count();
		PagerVo pagerVo = new PagerVo();
		pagerVo.setRows(vos);
		pagerVo.setTotal(count);
		return pagerVo;
	}
	
	
}
