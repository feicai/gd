package sxq.gd.action;

import java.awt.image.BufferedImage;
import java.util.List;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import sxq.gd.common.BaseAction;
import sxq.gd.service.ActivitiService;
import sxq.gd.utils.JSONUtils;
import sxq.gd.vo.PagerVo;
import sxq.gd.vo.TaskInfo;

public class ProcessAction  extends BaseAction{
	
	@Resource
	private ActivitiService activitiService;
	
	private String taskId;
	private String processDefinitionId;
	private String processInstanceId;
	public String taskList(){
		return "taskList";
	}
	/**
	 * 代办任务列表
	 */
	public void taskListData(){
		String userId = this.getCurrentUserId()+"";
		List<TaskInfo> infos = this.activitiService.queryCurrentTasks(userId);
		JSONUtils.toJSON(infos);
	}
	public void getProcessDefinitionList(){
		PagerVo vo = this.activitiService.getProcessDefinitionList(page, rows);
		JSONUtils.toJSON(vo);
	}
	public void getProcessInstanceList(){
		PagerVo vo = this.activitiService.getProcessInstanceList(page, rows);
		JSONUtils.toJSON(vo);
	}
	/**
	 * 跳转到查看流程图页面
	 * @return
	 */
	public String viewProcessImage(){
		return "viewProcessImage";
	}
	/**
	 * 跳转到流程定义列表页面
	 * @return
	 */
	public String processDefinitionList(){
		return "processDefinitionList";
	}
	/**
	 * 跳转到流程实例列表页面
	 * @return
	 */
	public String processInstanceList(){
		return "processInstanceList";
	}
	/**
	 * 输出跟踪流程图
	 * @throws Exception
	 */
	public void getProcessImage() throws Exception{	
		response.setContentType("image/png");
		BufferedImage image = this.activitiService.getProcessImage(processInstanceId);
		ImageIO.write(image, "PNG", response.getOutputStream());
	}
	/**
	 * 签收任务
	 */
	public void claim(){
		this.activitiService.claim(taskId, this.getCurrentUserId()+"");
	}
	
	public String getProcessDefinitionId() {
		return processDefinitionId;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
}
