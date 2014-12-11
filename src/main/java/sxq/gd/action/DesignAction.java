package sxq.gd.action;

import javax.annotation.Resource;

import sxq.gd.common.BaseAction;
import sxq.gd.common.ReturnInfo;
import sxq.gd.model.Approve;
import sxq.gd.model.Design;
import sxq.gd.model.User;
import sxq.gd.service.ApproveService;
import sxq.gd.service.DesignService;
import sxq.gd.utils.JSONUtils;
import sxq.gd.vo.PagerVo;

public class DesignAction extends BaseAction{
	
	@Resource
	private DesignService designService;
	@Resource
	private ApproveService approveService;
	
	private Design design;
	private Approve approve;
	
	/**
	 * 得到毕业设计分页信息
	 */
	public void getFiles(){
		PagerVo pager = this.designService.findDesign(page, rows,design);
		JSONUtils.toJSON(pager);
	}
	
	/**
	 * 跳转到选题页面
	 * @return
	 */
	public String select(){
		this.design = this.designService.findDesignByUser();
		return "select";
	}
	/**
	 * 跳转到选题审核列表
	 * @return
	 */
	public String selectApproveList(){
		return "selectApproveList";
	}
	/**
	 * 跳转到选题审核页面
	 * @return
	 */
	public String selectApprove(){
		this.design = this.designService.findDesignByUser();
		return "selectApprove";
	}
	/**
	 * 跳转到表单页面
	 * @return
	 */
	public String inputForm(){
		this.design = this.designService.findDesignByUser();
		return "inputForm";
	}
	
	public void add(){
		String msg = "选题成功！";
		boolean flag = true;
		try{
			User currentUser = (User) this.session.getAttribute("LOGIN_USER");
			design.setUser(currentUser);
			design.setState(Design.STATUS_SUBMIT);
			this.designService.save(design);
		}catch(Exception e){
			msg = "选题失败！";
			flag= false;
		}
		JSONUtils.toJSON(new ReturnInfo(flag,msg));
	}
	/**
	 * 查看毕业设计
	 * @return
	 */
	public String viewSubject(){
		this.design = this.designService.findById(id);
		return "viewSubject";
	}
	
	/**
	 * 跳转到毕业设计管理页面
	 * @return
	 */
	public String manageDesign(){
		this.design = this.designService.findDesignByUser();
		return "manageDesign";
	}
	/**
	 * 跳转到毕业设计审核页面
	 * @return
	 */
	public String approvePage(){
		this.design = this.designService.findDesignByUser();
		return "approvePage";
	}
	/**
	 * 课题审核信息
	 * @return
	 */
	public String approveInfoList(){
		this.design = this.designService.findDesignByUser();
		return "approveInfoList";
	}
	/**
	 * 提交毕业设计
	 */
	public void submit(){
		this.design = this.designService.findById(id);
		this.design.setComment(request.getParameter("comment"));
		this.design.setProgress(request.getParameter("progress"));
		this.design.setState(request.getParameter("state"));
		this.designService.update(design);
	}
	/**
	 * 毕业设计审核界面
	 * @return
	 */
	public String approve(){
		this.design = this.designService.findById(id);
		return "approve";
	}
	public String approveList(){
		return "approveList";
	}
	public void getDesignsByTeacher(){
		User user = this.getCurrentUser();
		PagerVo pager = this.designService.getDesignsByTeacher(page, rows, design, user);
		JSONUtils.toJSON(pager);
	}
	
	public void doApprove(){
		this.design = this.designService.findById(id);
		String type = this.request.getParameter("type");
		//如果是选题审核
		if("select".equals(type)){
			//如果审核通过
			if(approve.getResult() == "1"){
				this.design.setState(Design.STATUS_SELECT_SUCCESS);
			}else{
				this.design.setState(Design.STATUS_NOUSE);
			}
		}else{
			if(approve.getResult() == "1"){
				this.design.setState(Design.STATUS_PASS);
			}else{
				this.design.setState(Design.STATUS_SAVE);
			}
		}
		this.designService.update(design);
		this.approveService.save(approve);
	}
	
	public void getApproveList(){
		PagerVo pager = this.approveService.findByPage(page, rows, approve);
		JSONUtils.toJSON(pager);
	}
	
	public void delete(){
		String msg = "删除文件成功！";
		boolean flag = true;
		try{
			this.designService.delete(design);
		}catch(Exception e){
			msg = "删除文件失败！";
			flag= false;
		}
		JSONUtils.toJSON(new ReturnInfo(flag,msg));
		
	}

	public void update(){
		this.designService.update(design);
	}

	public Design getDesign() {
		return design;
	}

	public void setDesign(Design design) {
		this.design = design;
	}

	public Approve getApprove() {
		return approve;
	}

	public void setApprove(Approve approve) {
		this.approve = approve;
	}
	
}
