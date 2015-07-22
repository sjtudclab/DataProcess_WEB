package cn.edu.sjtu.dcl.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.sjtu.dcl.dao.bean.Job;
import cn.edu.sjtu.dcl.dao.bean.User;
import cn.edu.sjtu.dcl.dao.interfaces.BaseDAO;
import cn.edu.sjtu.dcl.util.Page;


@Transactional
public class BaseDAOImpl<T> extends HibernateDaoSupport implements BaseDAO<T> {

	protected Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass()
			.getGenericSuperclass()).getActualTypeArguments()[0];

	public void delete(Long id) {
		T obj = this.load(id);
		getHibernateTemplate().delete(obj);
	}

	public T get(Long id) {
		return (T) getHibernateTemplate().get(entityClass, id);
	}

	public Long save(T obj) {
		return (Long) getHibernateTemplate().save(obj);
	}

	public void update(T obj) {
		getHibernateTemplate().update(obj);
	}

	public void delete(T obj) {
		getHibernateTemplate().delete(obj);
	}

	public void merge(T obj) {
		getHibernateTemplate().merge(obj);
	}

	public T load(Long id) {
		return (T) getHibernateTemplate().load(entityClass, id);
	}

	public List<T> loadAll() {
		return getHibernateTemplate().loadAll(entityClass);
	}

	public int countByCriteria(final DetachedCriteria detachedCriteria) {
		Long count = getHibernateTemplate().execute(
				new HibernateCallback<Long>() {
					public Long doInHibernate(Session session)
							throws HibernateException {
						Criteria criteria = detachedCriteria
								.getExecutableCriteria(session);
						return (Long)criteria.setProjection(Projections.rowCount())
								.uniqueResult();
					}
				});
		return count.intValue();
	}

	public List<T> findByCriteria(DetachedCriteria detachedCriteria) {
		return getHibernateTemplate().findByCriteria(detachedCriteria);
	}

	public List<T> findByCriteria(DetachedCriteria detachedCriteria, int first,
			int max) {
		return getHibernateTemplate().findByCriteria(detachedCriteria, first,
				max);
	}

	public List<T> findByExample(T obj) {
		return getHibernateTemplate().findByExample(obj);
	}

	public List<T> findByHql(String hql, Object[] params) {
		Query query = getHibernateSession().createQuery(hql);
		if (null != params && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		return query.list();
	}

	public Session getHibernateSession() {
		return SessionFactoryUtils.getSession(getHibernateTemplate()
				.getSessionFactory(), false);
	}

	@Override
	public int updateByHql(String hql, Object[] params) {
		Query query = getHibernateSession().createQuery(hql);
		if (null != params && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		return query.executeUpdate();
	}
	
	@Override
	public int updateBySql(String sql, Object[] params) {
		Query query = getHibernateSession().createSQLQuery(sql);
		if (null != params && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		return query.executeUpdate();
	}

	@Override
	public int updateColumns(Long id, String[] columns, Object[] params) {
		if (null != columns && columns.length > 0 && null != params
				&& columns.length == params.length) {
			StringBuilder sb = new StringBuilder();
			sb.append("update ").append(entityClass.getName()).append(" set ");
			for (int i = 0; i < columns.length; i++) {
				sb.append(columns[i]).append("=?");
				if (i < columns.length - 1) {
					sb.append(",");
				}
			}
			sb.append(" where id=?");
			Query query = getHibernateSession().createQuery(sb.toString());
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
			query.setParameter(columns.length, id);
			return query.executeUpdate();
		}
		return -1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page findPageByCreiteria(int pno, int pageSize, final DetachedCriteria detachedCriteria) {
		// TODO Auto-generated method stub
		final int startIndex=(pno-1)*pageSize;
		final int size=pageSize;
		final int pageNo=pno;
		return(Page) getHibernateTemplate().execute(new HibernateCallback(){
            public Object doInHibernate(Session session)throws HibernateException {
                    Criteria criteria = detachedCriteria.getExecutableCriteria(session);
                    long totalCount=criteria.list().size();
                    List items = criteria.setFirstResult(startIndex).setMaxResults(size).list();

                    Page ps = new Page(size,pageNo,totalCount,items);
                    return ps; 
            }
    }); 
	}	
}