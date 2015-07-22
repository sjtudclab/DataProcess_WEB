package cn.edu.sjtu.dcl.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import cn.edu.sjtu.dcl.dao.bean.Job;
import cn.edu.sjtu.dcl.dao.bean.User;
import cn.edu.sjtu.dcl.dao.interfaces.JobDAO;
import cn.edu.sjtu.dcl.service.interfaces.JobService;
import cn.edu.sjtu.dcl.util.Page;
import cn.edu.sjtu.dcl.util.SearchUtil;


public class JobServiceImpl implements JobService {

	private JobDAO jobDAO;

	@Override
	public Page getPage(int pno, int pageSize, Map options) {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(Job.class);
		if (options != null) {
			Set<Integer> keys = options.keySet();
			for (Integer key : keys) {
				if (key.equals(new Integer(1))) {
					System.out.println((String) options.get(key));
					criteria.add(Restrictions.ilike(SearchUtil.jobMap.get(key),
							(String) options.get(key), MatchMode.ANYWHERE));
				} else if (key.equals(new Integer(2))) {

				} else if (key.equals(new Integer(3))) {
					criteria.createCriteria(SearchUtil.jobMap.get(key),
							Criteria.LEFT_JOIN).add(
							Restrictions.ilike("name",
									(String) options.get(key),
									MatchMode.ANYWHERE));
				}
			}
		}
		return jobDAO.findPageByCreiteria(pno, pageSize, criteria);
	}

	public JobDAO getJobDAO() {
		return jobDAO;
	}

	public void setJobDAO(JobDAO jobDAO) {
		this.jobDAO = jobDAO;
	}

	@Override
	public Job getJobById(long id) {
		// TODO Auto-generated method stub

		return this.getJobDAO().get(id);
	}

	@Override
	public void createJob(Job job) {
		// TODO Auto-generated method stub
		jobDAO.save(job);
	}

	@Override
	public boolean deleteJob(long id, User user) {
		// TODO Auto-generated method stub
		System.out.println(jobDAO);
		System.out.println(jobDAO.get(id));
		long ownerId = jobDAO.get(id).getDeveloper().getId();
		if (ownerId == user.getId()) {
			jobDAO.delete(id);
			return true;
		} else
			return false;
	}

	@Override
	public List<Job> getAll() {
		// TODO Auto-generated method stub
		System.out.println("heeeeeeeeeeeeeeeee");

		List<Job> jobs = jobDAO.loadAll();
		return jobs;
	}

	@Override
	public void updateJob(Job job) {
		// TODO Auto-generated method stub
		jobDAO.update(job);
	}

}
