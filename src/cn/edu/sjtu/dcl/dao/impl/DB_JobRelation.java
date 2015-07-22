package cn.edu.sjtu.dcl.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import cn.edu.sjtu.dcl.dao.bean.JobRelation;

public class DB_JobRelation{
	
	private DB_Conn db_conn = new DB_Conn();
	
	public JobRelation get(long jobid) throws Exception
	{
		db_conn.ConnectDB();
		ResultSet rs = null;
		try
		{
			String sql = "select * from DATA_PROCESS_JOBRELATION_TABLE where id ='"
					+ jobid + "'";
			rs = db_conn.sm.executeQuery(sql);
			JobRelation jobRelation = null;
			if(rs.next())
			{
				jobRelation = new JobRelation();
				jobRelation.setId(rs.getLong("id"));
				jobRelation.setTemplateId(rs.getLong("TemplateId"));
				jobRelation.setJobIdOnHadoop(rs.getString("JobIdOnHadoop"));
				
				return jobRelation;
			}
			return jobRelation;
		}
		catch(SQLException SqlE)
		{
			SqlE.printStackTrace();
			throw SqlE;
		} 
		catch(Exception E)
		{
			E.printStackTrace();
			throw E;
		} 
		finally
		{
			db_conn.CloseDB();
		}
	}
	
	public void MapJobRelation(long templateId, String jobIdOnHadoop, long ownerId) throws SQLException
	{
		String sql = "insert into DATA_PROCESS_JOBRELATION_TABLE(templateId, JobIdOnHadoop, ownerId) values (?, ?, ?)";
		db_conn.ConnectPreparedDB(sql);
		try {
			db_conn.ps.setLong(1, templateId);
			db_conn.ps.setString(2, jobIdOnHadoop);
			db_conn.ps.setLong(3, ownerId);
			db_conn.ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}finally{
			db_conn.CloseDB();
		}
	}
}
