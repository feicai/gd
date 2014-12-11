package sxq.gd.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import sxq.gd.dao.DesignDao;
import sxq.gd.model.Design;
import sxq.gd.model.Subject;
import sxq.gd.model.User;
import sxq.gd.utils.DateUtils;
import sxq.gd.vo.DesignVo;
import sxq.gd.vo.PagerVo;

@Component
public class DesignService {
	@Resource
	private SubjectService subjectService;
	@Resource
	private DesignDao designDao;
	
	
	public void save(Design design){
		design.setCreateTime(DateUtils.getCurrentTime());
		this.designDao.addEntity(design);
	}
	public void update(Design design){
		design.setUpdateTime(DateUtils.getCurrentTime());
		this.designDao.updateEntity(design);
	}
	
	public void delete(Design design){
		this.designDao.deleteEntity(design);
	}
	public Design findById(int id){
		return this.designDao.findById(Design.class, id);
	}
	public List<Subject> findAll(){
		return this.designDao.findAll(Subject.class);
	}
	
	public PagerVo findDesign(int offset,int pagesize,Design design){
		return this.designDao.findDesign(offset, pagesize, design);
	}
	/**
	 * 根据当前用户查找课题
	 * @param currentUser
	 * @return
	 */
	public Design findDesignByUser(){
		User currentUser = (User) ServletActionContext.getRequest().getSession().getAttribute("LOGIN_USER");
		Design design = new Design();
		design.setUser(currentUser);
		PagerVo pager = this.findDesign(1, 10, design);
		if(pager.getTotal()>0){
			design = (Design) pager.getRows().get(0);
		}else{
			design = null;
		}
		return design;
	}
	
	@SuppressWarnings("unchecked")
	public PagerVo getDesignsByTeacher(int offset,int pagesize,Design design,User teacher){
		Subject subject = new Subject();
		subject.setCreateUser(teacher);
		PagerVo subjectVo = this.subjectService.findByPage(0, 100, subject);
		if(subjectVo.getTotal() > 0){
			int length = Integer.parseInt(subjectVo.getTotal()+"");
			Integer[] subjectIds = new Integer[length];
			List<Subject> subjects = (List<Subject>) subjectVo.getRows();
			for(int i=0;i<length;i++){
				Subject s  = subjects.get(i);
				subjectIds[i] = s.getId();
			}
			
			PagerVo pagerVo= this.designDao.findDesign(offset, pagesize, design, subjectIds);
			List<Design> designs =(List<Design>) pagerVo.getRows();
			List<DesignVo> vos = new ArrayList<DesignVo>();
			for(Design d:designs){
				DesignVo vo = new DesignVo(d);
				vos.add(vo);
			}
			pagerVo.setRows(vos);
			return pagerVo;
		}else{
			return null;
			
		}
	}
	
}
