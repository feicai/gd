package sxq.gd.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import sxq.gd.common.BaseDao;
import sxq.gd.model.Subject;
import sxq.gd.vo.PagerVo;

@Component
public class SubjectDao extends BaseDao{
	@SuppressWarnings("unchecked")
	public PagerVo findSubject(int offset,int pagesize,Subject subject){
		Criteria c = this.getSession().createCriteria(Subject.class);
		if(subject != null){
			if(subject.getStatus()!=null){
				c = c.add(Restrictions.in("status", subject.getStatus().split(","))); 
			}
			if(subject.getCreateUser()!=null){
				c = c.add(Restrictions.eq("createUser.id", subject.getCreateUser().getId()));
			}
		}
		offset = (offset-1)*pagesize;
		List<Subject> rows = c.setMaxResults(pagesize).setFirstResult(offset).list();
		Long total = (Long) c.setProjection(Projections.rowCount()).uniqueResult();
		PagerVo pager = new PagerVo();
		pager.setTotal(total);
		pager.setRows(rows);
		return pager;
	}
}
