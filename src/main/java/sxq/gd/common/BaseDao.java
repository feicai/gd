package sxq.gd.common;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;

public abstract class BaseDao {
	
	@Resource
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * 增加
	 * @param entity
	 */
	public void addEntity(Object entity){
		this.hibernateTemplate.save(entity);
	}
	/**
	 * 增加或修改
	 * @param entity
	 */
	public void addOrUpdateEntity(Object entity){
		this.hibernateTemplate.saveOrUpdate(entity);
	}
	/**
	 * 修改
	 * @param entity
	 */
	public void updateEntity(Object entity){
		this.hibernateTemplate.update(entity);
	}
	/**
	 * 查询相关实体类的所有对象
	 * @param entityClass	实体类类型
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findAll(Class<T> entityClass) {
		return getSession().createCriteria(entityClass).list();
	}
	
	public void deleteEntity(Object entity){
		this.hibernateTemplate.delete(entity);
	}
	/**
	 * 根据id查询实体类
	 * @param entityClass
	 * @param id
	 * @return
	 */
	public <T> T findById(Class<T> entityClass ,int id){
		return this.hibernateTemplate.load(entityClass, id);
	}
	
	/**
	 * 分页查询
	 * @param queryHql	hql查询语句
	 * @param params	查询参数
	 * @param offset	从第几条开始
	 * @param pagesize	每页的记录条数
	 * @return	
	 */
	public  List<?> findByPage(String queryHql ,Map<String , Object> params,int offset,int pagesize){
		Query query =  getSession().createQuery(queryHql);
		if (params != null) {
			Set<String> keySet = params.keySet();
			for (String key : keySet) {
				query.setParameter(key, params.get(key));
			}
		}
		offset = (offset-1)*pagesize;
		List<?> rows = query.setMaxResults(pagesize).setFirstResult(offset).list();
		return rows;
	}
	
	public Long findRowCount(String queryHql ,Map<String , Object> params){
		String countHql = getCountHql(queryHql);
		Query query = getSession().createQuery(countHql);
		
		if (params != null) {
			Set<String> keySet = params.keySet();
			for (String key : keySet) {
				query.setParameter(key, params.get(key));
			}
		}
		Long total =  (Long) query.uniqueResult();
		return total;
	}
	public Long findRowCount(String queryHql){
		return this.findRowCount(queryHql, null);
	}
	
	/**
	 * 分页查询
	 * @param queryHql	hql查询语句
	 * @param offset	从第几条开始
	 * @param pagesize	每页的记录条数
	 * @return	
	 */
	public  List<?> findByPage(String queryHql ,int offset,int pagesize){
		return this.findByPage(queryHql, null, offset, pagesize);
	}
	
	/**
	 * 得到Session
	 * @return
	 */
	public Session getSession(){
		return this.hibernateTemplate.getSessionFactory().openSession();
	}
	
	/**
	 * 根据查询hql语句得到查询列数的hql语句
	 * select a from Article a where a.title like ? --> select count(*) from
	 * Article a where a.title like ? 
	 * @param queryHql
	 * @return
	 */
	protected String getCountHql(String queryHql) {
		int index = queryHql.toLowerCase().indexOf("from");
		if (index == -1) {
			throw new RuntimeException("非法的查询语句【" + queryHql + "】");
		}
		return "select count(*) " + queryHql.substring(index);
	}
	
}
