package cn.edu.sjtu.dcl.dao.impl;

import org.springframework.transaction.annotation.Transactional;

import cn.edu.sjtu.dcl.dao.bean.ModelRelation;
import cn.edu.sjtu.dcl.dao.interfaces.ModelRelationDAO;


@Transactional
public class ModelRelationDAOImpl extends BaseDAOImpl<ModelRelation> implements ModelRelationDAO {

}
