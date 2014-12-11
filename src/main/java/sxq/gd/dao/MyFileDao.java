package sxq.gd.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import sxq.gd.common.BaseDao;
import sxq.gd.model.MyFile;
import sxq.gd.model.Subject;
import sxq.gd.vo.PagerVo;

@Component
public class MyFileDao extends BaseDao{
	@SuppressWarnings("unchecked")
	public PagerVo findFile(int offset,int pagesize,MyFile myFile){
		Criteria c = this.getSession().createCriteria(MyFile.class);
		if(myFile != null){
			if(myFile.getName()!=null){
				c = c.add(Restrictions.like("name", myFile.getName())); 
			}
			if(myFile.getType()!=null){
				c = c.add(Restrictions.eq("type", myFile.getType())); 
			}
			if(myFile.getCatelog()!=null){
				c = c.add(Restrictions.eq("catelog", myFile.getCatelog()));
			}
			if(myFile.getDuixiang() != 0){
				c = c.add(Restrictions.eq("duixiang", myFile.getDuixiang()));
			}
		}
		offset = (offset-1)*pagesize;
		List<Subject> rows = c.addOrder(Order.desc("createTime"))
				.setMaxResults(pagesize).setFirstResult(offset).list();
		Long total = (Long) c.setProjection(Projections.rowCount()).uniqueResult();
		PagerVo pager = new PagerVo();
		pager.setTotal(total);
		pager.setRows(rows);
		return pager;
	}
}
