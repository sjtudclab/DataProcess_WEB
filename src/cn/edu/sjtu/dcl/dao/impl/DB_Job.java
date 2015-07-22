package cn.edu.sjtu.dcl.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import cn.edu.sjtu.dcl.dao.bean.Job;
import cn.edu.sjtu.dcl.dao.interfaces.IDB_Job;

public class DB_Job implements IDB_Job
{
	private DB_Conn db_conn = new DB_Conn();
	//private Vector<Job> jobs = new Vector<Job>();
	public Job get(String jobname) throws Exception
	{
		db_conn.ConnectDB();
		ResultSet rs = null;
		Job job = null;
		try
		{
			String sql = "select * from DATA_PROCESS_JOB_TABLE where name ='"
					+ jobname + "'";
			rs = db_conn.sm.executeQuery(sql);
		
			if(rs.next())
			{
				job = new Job();
				job.setId(rs.getLong("id"));
				job.setName(rs.getString("name"));
				job.setPath(rs.getString("path"));
				job.setMapper(rs.getString("mapper"));
				job.setReducer(rs.getString("reducer"));
				job.setInputFormat(rs.getString("inputformat"));
				job.setOutputKey(rs.getString("output_key"));
				job.setOutputValue(rs.getString("output_value"));
				job.setParameters(rs.getString("parameters"));
				job.setIterative(rs.getBoolean("iterative"));
				job.setMapType(rs.getBoolean("MapType"));
				job.setDescription(rs.getString("description"));
				return job;
			}
			return job;
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
	
	public Job get(long jobid) throws Exception
	{
		System.out.println("get some job");
		db_conn.ConnectDB();
		ResultSet rs = null;
		try
		{
			String sql = "select * from DATA_PROCESS_JOB_TABLE where id ='"
					+ jobid + "'";
			rs = db_conn.sm.executeQuery(sql);
			Job job = null;
			if(rs.next())
			{
				job = new Job();
				job.setId(rs.getLong("id"));
				job.setName(rs.getString("name"));
				job.setPath(rs.getString("path"));
				job.setMapper(rs.getString("mapper"));
				job.setReducer(rs.getString("reducer"));
				job.setOutputKey(rs.getString("output_key"));
				job.setOutputValue(rs.getString("output_value"));
				job.setParameters(rs.getString("parameters"));
				job.setIterative(rs.getBoolean("iterative"));
				job.setMapType(rs.getBoolean("MapType"));
				job.setDescription(rs.getString("description"));
				return job;
			}
			return job;
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
	
	public List<Job> getJobsByDeveloper(long developerId) throws Exception
	{
		System.out.println("get some job by developer");
		db_conn.ConnectDB();
		ResultSet rs = null;
		List<Job> jobs = new ArrayList<Job>();
		try
		{
			String sql = "select * from DATA_PROCESS_JOB_TABLE where DEVELOPER_ID = '" + developerId + "'";
			rs = db_conn.sm.executeQuery(sql);
			Job job = null;
			while(rs.next())
			{
				job = new Job();
				job.setId(rs.getLong("id"));
				job.setName(rs.getString("name"));
				job.setPath(rs.getString("path"));
				job.setMapper(rs.getString("mapper"));
				job.setReducer(rs.getString("reducer"));
				job.setOutputKey(rs.getString("output_key"));
				job.setOutputValue(rs.getString("output_value"));
				job.setParameters(rs.getString("parameters"));
				job.setIterative(rs.getBoolean("iterative"));
				job.setMapType(rs.getBoolean("MapType"));
				job.setDescription(rs.getString("description"));
				jobs.add(job);
			}
			return jobs;
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
	
	public List<Job> getAll() throws Exception
	{
		System.out.println("get all job");
		
		db_conn.ConnectDB();
		ResultSet rs = null;
		List<Job> jobs = new ArrayList<Job>();
		try
		{
			String sql = "select * from DATA_PROCESS_JOB_TABLE";
			rs = db_conn.sm.executeQuery(sql);
			Job job = null;
			while(rs.next())
			{
				job = new Job();
				job.setId(rs.getLong("id"));
				job.setName(rs.getString("name"));
				job.setPath(rs.getString("path"));
				job.setMapper(rs.getString("mapper"));
				job.setReducer(rs.getString("reducer"));
				job.setOutputKey(rs.getString("output_key"));
				job.setOutputValue(rs.getString("output_value"));
				job.setParameters(rs.getString("parameters"));
				job.setIterative(rs.getBoolean("iterative"));
				job.setMapType(rs.getBoolean("MapType"));
				job.setDescription(rs.getString("description"));
				jobs.add(job);
			}
			return jobs;
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

	public void addJob(Job mrj)
	{
		try
		{
			String sql = "insert into DATA_PROCESS_JOB_TABLE(name, isMapType, path, mapper, reducer, inputformat, mapinputkey, mapinputvalue, mapoutputkey, mapoutputvalue, outputkey, outputvalue, iterative, parameters, description) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			db_conn.ConnectPreparedDB(sql);
			db_conn.ps.setString(1, mrj.getName());
			db_conn.ps.setInt(2, mrj.isMapType() ? 1 : 0);
			db_conn.ps.setString(3, mrj.getPath());
			db_conn.ps.setString(4, mrj.getMapper());
			db_conn.ps.setString(5, mrj.getReducer());
			db_conn.ps.setString(11, mrj.getOutputKey());
			db_conn.ps.setString(12, mrj.getOutputValue());
			db_conn.ps.setInt(13, mrj.isIterative() ? 1 : 0);
			db_conn.ps.setString(14, mrj.getParameters());
			db_conn.ps.setString(15, mrj.getDescription());
			db_conn.ps.executeUpdate();
		} 
		catch(SQLException SqlE)
		{
			SqlE.printStackTrace();
		}
		finally
		{
			db_conn.CloseDB();
		}
	}

	public void deleteJob(long id)
	{
		try
		{
			String sql = "delete from DATA_PROCESS_JOB_TABLE where id = ?";
			db_conn.ConnectPreparedDB(sql);
			db_conn.ps.setLong(1, id);
			db_conn.ps.executeUpdate();
		} 
		catch(SQLException SqlE)
		{
			SqlE.printStackTrace();
		}
		finally
		{
			db_conn.CloseDB();
		}
	}

	public void updateJob(Job mrj)
	{
		try
		{
			String sql = "update DATA_PROCESS_JOB_TABLE set name = ?, isMapType = ?, path = ?, mapper = ?, reducer = ?, inputformat = ?, mapinputkey = ?, mapinputvalue = ?, mapoutputkey = ?, mapoutputvalue = ?, outputkey = ?, outputvalue = ?, iterative = ?, parameters = ?, description = ? where id = ?";
			db_conn.ConnectPreparedDB(sql);
			db_conn.ps.setString(1, mrj.getName());
			db_conn.ps.setInt(2, mrj.isMapType() ? 1 : 0);
			db_conn.ps.setString(3, mrj.getPath());
			db_conn.ps.setString(4, mrj.getMapper());
			db_conn.ps.setString(5, mrj.getReducer());
			db_conn.ps.setString(11, mrj.getOutputKey());
			db_conn.ps.setString(12, mrj.getOutputValue());
			db_conn.ps.setInt(13, mrj.isIterative() ? 1 : 0);
			db_conn.ps.setString(14, mrj.getParameters());
			db_conn.ps.setString(15, mrj.getDescription());
			db_conn.ps.setLong(16, mrj.getId());
			db_conn.ps.executeUpdate();
		}
		catch(SQLException SqlE)
		{
			SqlE.printStackTrace();
		}
		finally
		{
			db_conn.CloseDB();
		}
	}
}
