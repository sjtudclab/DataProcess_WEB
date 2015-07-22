package cn.edu.sjtu.dcl.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import cn.edu.sjtu.dcl.dao.bean.Job;
import cn.edu.sjtu.dcl.dao.bean.Template;
import cn.edu.sjtu.dcl.dao.interfaces.IDB_Template;

public class DB_Template implements IDB_Template
{
	private DB_Conn db_conn = new DB_Conn();
	private Vector<Template> templates = new Vector<Template>();
	public Template get(long id) throws Exception
	{
		db_conn.ConnectDB();
		ResultSet rs = null;
		try
		{
			String sql = "select * from DATA_PROCESS_TEMPLATE_TABLE where id ='" + id + "'";
			rs = db_conn.sm.executeQuery(sql);
			Template template = null;
			if(rs.next())
			{
				template = new Template();
				template.setId(rs.getLong("id"));
				template.setName(rs.getString("name"));
				template.setPath(rs.getString("path"));
				template.setDescription(rs.getString("description"));
				return template;
			}
			return template;
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
	
	public Template get(String name) throws Exception
	{
		db_conn.ConnectDB();
		ResultSet rs = null;
		try
		{
			String sql = "select * from DATA_PROCESS_TEMPLATE_TABLE where name ='"
					+ name + "'";
			rs = db_conn.sm.executeQuery(sql);
			Template template = null;
			if(rs.next())
			{
				template = new Template();
				template.setId(rs.getLong("id"));
				template.setName(rs.getString("name"));
				template.setPath(rs.getString("path"));
				template.setDescription(rs.getString("description"));
				return template;
			}
			return template;
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
	
	public List<Template> getTemplatesByDeveloper(long developerId) throws Exception
	{
		db_conn.ConnectDB();
		ResultSet rs = null;
		List<Template> templates = new ArrayList<Template>();
		try
		{
			String sql = "select * from DATA_PROCESS_TEMPLATE_TABLE where DEVELOPER_ID = '" + developerId + "'";
			rs = db_conn.sm.executeQuery(sql);
			
			while(rs.next())
			{
				Template template = new Template();
				template.setId(rs.getLong("id"));
				template.setContent(rs.getString("CONTENT"));
				template.setDescription(rs.getString("DESCRIPTION"));
				template.setName(rs.getString("NAME"));
				template.setPath(rs.getString("PATH"));
				template.setUploadTime(rs.getDate("UPLOAD_TIME"));
				templates.add(template);
			}
			return templates;
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
	
	public Vector<Template> getAll() throws Exception
	{
		db_conn.ConnectDB();
		ResultSet rs = null;
		try
		{
			String sql = "select * from DATA_PROCESS_TEMPLATE_TABLE";
			rs = db_conn.sm.executeQuery(sql);
			Template template = null;
			while(rs.next())
			{
				template = new Template();
				template.setId(rs.getInt("id"));
				template.setName(rs.getString("name"));
				template.setPath(rs.getString("path"));
				template.setDescription(rs.getString("description"));
				templates.add(template);
			}
			return templates;
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

	public void addTemplate(Template mrt) throws SQLException
	{
		try
		{
			String sql = "insert into DATA_PROCESS_TEMPLATE_TABLE(name, path, description) values (?, ?, ?)";
			db_conn.ConnectPreparedDB(sql);
			db_conn.ps.setString(1, mrt.getName());
			db_conn.ps.setString(2, mrt.getPath());
			db_conn.ps.setString(3, mrt.getDescription());
			db_conn.ps.executeUpdate();
		} 
		catch(SQLException SqlE)
		{
			SqlE.printStackTrace();
			throw SqlE;
		}
		finally
		{
			db_conn.CloseDB();
		}
	}

	public void deleteTemplate(long id) throws SQLException
	{
		try
		{
			String sql = "delete from DATA_PROCESS_TEMPLATE_TABLE where id = ?";
			db_conn.ConnectPreparedDB(sql);
			db_conn.ps.setLong(1, id);
			db_conn.ps.executeUpdate();
		} 
		catch(SQLException SqlE)
		{
			SqlE.printStackTrace();
			throw SqlE;
		}
		finally
		{
			db_conn.CloseDB();
		}
	}

	public void updateTemplate(Template mrt) throws SQLException
	{
		try
		{
			String sql = "update DATA_PROCESS_TEMPLATE_TABLE set name = ?, description = ? where id = ?";
			db_conn.ConnectPreparedDB(sql);
			db_conn.ps.setString(1, mrt.getName());
			db_conn.ps.setString(2, mrt.getDescription());
			db_conn.ps.executeUpdate();
		} 
		catch(SQLException SqlE)
		{
			SqlE.printStackTrace();
			throw SqlE;
		}
		finally
		{
			db_conn.CloseDB();
		}
	}
	
}
