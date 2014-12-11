package sxq.gd.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import sxq.gd.common.BaseDao;
import sxq.gd.model.User;

@Component
public class UserDao extends BaseDao{
	public User findByCode(String code){
		String hql = "from User u where u.code=:code";
		Query query =  getSession().createQuery(hql);
		query.setParameter("code", code);
		return (User) query.uniqueResult();
	}
}
