package sxq.gd.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import sxq.gd.dao.MyFileDao;
import sxq.gd.model.MyFile;
import sxq.gd.utils.DateUtils;
import sxq.gd.vo.PagerVo;

@Component
public class MyFileService {
	@Resource
	private MyFileDao myFileDao;
	
	public void save(MyFile myFile){
		myFile.setCreateTime(DateUtils.getCurrentTime());
		this.myFileDao.addEntity(myFile);
	}
	public void update(MyFile myFile){
		this.myFileDao.updateEntity(myFile);
	}

	
	public void delete(MyFile myFile){
		this.myFileDao.deleteEntity(myFile);
	}
	public MyFile findById(int id){
		return this.myFileDao.findById(MyFile.class, id);
	}
	public List<MyFile> findAll(){
		return this.myFileDao.findAll(MyFile.class);
	}

	public PagerVo findByPage(int offset,int pagesize,MyFile myFile){
		return this.myFileDao.findFile(offset, pagesize,myFile);
	}
}
