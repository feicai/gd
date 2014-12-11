package sxq.gd.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;

import sxq.gd.common.BaseAction;
import sxq.gd.common.ReturnInfo;
import sxq.gd.model.MyFile;
import sxq.gd.service.MyFileService;
import sxq.gd.utils.JSONUtils;
import sxq.gd.vo.PagerVo;

public class MyFileAction extends BaseAction{
	@Resource
	private MyFileService myFileService;
	
	private MyFile file;

	private File upload;
	private String uploadContentType;
	private String uploadFileName;
	/**
	 * 得到附件分页信息
	 */
	public void getFiles(){
		PagerVo pager = this.myFileService.findByPage(page, rows,file);
		JSONUtils.toJSON(pager);
	}
	
	public String uploadPage(){
		return "uploadPage";
	}
	
	public void upload(){
		String msg = "上传成功！";
		boolean flag = true;
		
		if(file == null) file = new MyFile();
		try {
			this.file.setContent(IOUtils.toByteArray(new FileInputStream(upload)));
			String suffix = this.uploadFileName.substring(this.uploadFileName.lastIndexOf(".")+1);
			String fileName = this.uploadFileName.substring(0,this.uploadFileName.lastIndexOf("."));
			this.file.setName(fileName);

			this.file.setSuffix(suffix);
			this.file.setType(this.uploadContentType);
			this.file.setSize(upload.length());
			this.myFileService.save(file);
		} catch (Exception e) {
			msg = "上传失败！";
			flag = false;
		}
		
		JSONUtils.toJSON(new ReturnInfo(flag,msg));
	}
	public String download() throws Exception{
		this.file = this.myFileService.findById(id);

		this.file.setName(new String(file.getName().getBytes(), "ISO-8859-1"));
	
		response.reset();
		response.setContentType("application/x-download;charset=GBK");
		response.setHeader("Content-Disposition", "attachment;filename="+this.file.getName()+"."+this.file.getSuffix());
		
		try {
			response.getOutputStream().write(file.getContent());
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public void delete(){
		String msg = "删除文件成功！";
		boolean flag = true;
		try{
			this.myFileService.delete(file);
		}catch(Exception e){
			msg = "删除文件失败！";
			flag= false;
		}
		JSONUtils.toJSON(new ReturnInfo(flag,msg));
		
	}

	public void update(){
		String name = request.getParameter("name");
		this.file = this.myFileService.findById(id);
		this.file.setName(name);
		this.myFileService.update(file);
	}
	public String renamePage(){
		this.file = this.myFileService.findById(id);
		return "renamePage";
	}
	
	public MyFile getFile() {
		return file;
	}

	public void setFile(MyFile file) {
		this.file = file;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	

}
