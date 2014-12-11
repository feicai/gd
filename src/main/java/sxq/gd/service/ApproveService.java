package sxq.gd.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import sxq.gd.dao.ApproveDao;
import sxq.gd.model.Approve;
import sxq.gd.model.Subject;
import sxq.gd.vo.PagerVo;

@Component
public class ApproveService {
	@Resource
	private ApproveDao approveDao;
	
	public void save(Approve approve){
		this.approveDao.addEntity(approve);
	}
	
	public void delete(Approve approve){
		this.approveDao.deleteEntity(approve);
	}
	public Subject findById(int id){
		return this.approveDao.findById(Subject.class, id);
	}
	public List<Subject> findAll(){
		return this.approveDao.findAll(Subject.class);
	}

	@SuppressWarnings("unchecked")
	public PagerVo findByPage(int offset,int pagesize,Approve approve){
		String queryHql = "from Approve t where 1=1 ";
		if(approve != null){
			if(approve.getCatelog()!= null){
				queryHql +=  (" and t.catelog='"+approve.getCatelog()+"' ");
			}
			if(approve.getDuixiang()!= 0){
				queryHql +=  (" and t.duixiang="+approve.getDuixiang());
			}
		}
		List<Approve> approves = (List<Approve>) this.approveDao.findByPage(queryHql, offset, pagesize);
		Long total = this.approveDao.findRowCount(queryHql);
		PagerVo pager = new PagerVo();
		pager.setTotal(total);
		pager.setRows(approves);
		return pager;
	}
}
