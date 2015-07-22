package cn.edu.sjtu.dcl.dao.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;


import cn.edu.sjtu.dcl.dao.bean.Job;
import cn.edu.sjtu.dcl.dao.interfaces.JobDAO;
import cn.edu.sjtu.dcl.util.Page;


@Transactional
public class JobDAOImpl extends BaseDAOImpl<Job> implements JobDAO {



}
