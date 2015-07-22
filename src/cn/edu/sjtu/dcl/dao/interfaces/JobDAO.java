package cn.edu.sjtu.dcl.dao.interfaces;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import cn.edu.sjtu.dcl.dao.bean.Job;
import cn.edu.sjtu.dcl.util.Page;


@Transactional
public interface JobDAO extends BaseDAO<Job> {
	

}
