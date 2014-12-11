package sxq.gd.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import sxq.gd.common.BaseDao;
import sxq.gd.model.Design;
import sxq.gd.vo.PagerVo;

@Component
public class DesignDao extends BaseDao{
	@SuppressWarnings("unchecked")
	public PagerVo findDesign(int offset,int pagesize,Design design,Integer[] subjectIds){
		Criteria c = this.getSession().createCriteria(Design.class);
		if(subjectIds!= null){
			c = c.add(Restrictions.in("subject.id",subjectIds)); 
		}
		if(design != null){
			if(design.getState()!=null && design.getState()!="0"){
				c = c.add(Restrictions.in("state", design.getState().split(","))); 
			}
			if(design.getUser() != null){
				c = c.add(Restrictions.eq("user.id", design.getUser().getId())); 
			}
		}
		offset = (offset-1)*pagesize;
		List<Design> rows = c.setMaxResults(pagesize).setFirstResult(offset).list();
		Long total = (Long) c.setProjection(Projections.rowCount()).uniqueResult();
		PagerVo pager = new PagerVo();
		pager.setTotal(total);
		pager.setRows(rows);
		return pager;
	}

	public PagerVo findDesign(int offset,int pagesize,Design design){
		return this.findDesign(offset, pagesize, design, null);
	}
}
