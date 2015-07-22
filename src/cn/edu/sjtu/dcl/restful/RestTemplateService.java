package cn.edu.sjtu.dcl.restful;

import java.io.File;
import java.util.List;

import cn.edu.sjtu.dcl.dao.bean.Template;
import cn.edu.sjtu.dcl.dao.impl.DB_JobRelation;
import cn.edu.sjtu.dcl.dao.impl.DB_Template;
import cn.edu.sjtu.dcl.oozie.creator.impl.OozieAppCreator;
import cn.edu.sjtu.dcl.oozie.executor.impl.OozieExecutor;
import cn.edu.sjtu.dcl.oozie.parser.impl.OozieParser;
import cn.edu.sjtu.dcl.rest.exception.RestInnerException;
import cn.edu.sjtu.dcl.util.PropertiesHandler;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.xml.parsers.ParserConfigurationException;

import org.jboss.resteasy.spi.NotFoundException;

@Path("template")
public class RestTemplateService {

	@GET
	@Path("byId/{id}")
	@Produces("application/json")
	public Template GetById(@PathParam("id") long id) throws RestInnerException {
		if (id < 0) {
			throw new IllegalArgumentException("id should not be negative");
		}

		Template template = null;

		try {
			DB_Template db = new DB_Template();
			template = db.get(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RestInnerException("inner error when get template by id:"
					+ id, e);
		}

		if (null == template) {
			throw new NotFoundException("template with id:" + id + " not found");
		}

		return template;
	}

	@Path("all")
	@GET
	@Produces("application/json")
	public List<Template> ListAll() throws RestInnerException {
		List<Template> templates = null;
		try {
			DB_Template db = new DB_Template();
			templates = db.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RestInnerException("inner error when get all templates",
					e);
		}

		return templates;
	}

	@GET
	@Path("byDeveloper/{developerId}")
	@Produces("application/json")
	public List<Template> ListAllByUser(
			@PathParam("developerId") long developerId)
			throws RestInnerException {
		if (developerId < 0) {
			throw new IllegalArgumentException("userId should not be negative");
		}

		List<Template> templates = null;
		try {
			DB_Template db = new DB_Template();
			templates = db.getTemplatesByDeveloper(developerId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RestInnerException("inner error when get all templates",
					e);
		}

		return templates;
	}

	@POST
	@Path("execute/{id}")
	@Produces("application/json")
	public void ExecuteTemplate(@PathParam("id") long id)
			throws RestInnerException {
		if (id < 0) {
			throw new IllegalArgumentException(
					"template id should not be negative");
		}

		Template template = null;

		try {
			DB_Template db = new DB_Template();
			template = db.get(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RestInnerException("inner error when get template by id:"
					+ id, e);
		}

		if (null == template) {
			throw new NotFoundException("template with id:" + id + " not found");
		}

		String JOBTRACKER = PropertiesHandler.getValue("cluster.conf",
				"jobtracker");
		String NAMENODE = PropertiesHandler
				.getValue("cluster.conf", "namenode");

		String sysTemp = System.getProperty("java.io.tmpdir");

		String appPath = sysTemp + File.separator + System.currentTimeMillis();
		File dir = new File(appPath);
		dir.mkdirs();

		OozieParser op = null;
		try {
			op = new OozieParser(template.getPath(), JOBTRACKER, NAMENODE);
			String wfName = appPath + File.pathSeparator + "workflow.xml";

			op.parse(template.getPath(), wfName);
			OozieAppCreator oc = new OozieAppCreator();
			oc.create(wfName, op.getLibs(), appPath + "/app");

			OozieExecutor oz = new OozieExecutor(NAMENODE, appPath);
			oz.employ(appPath);
			String jobId = oz.execute(appPath);

			DB_JobRelation db = new DB_JobRelation();
			db.MapJobRelation(id, jobId, -1);

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RestInnerException("inner error when running the job:"
					+ id, e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RestInnerException("inner error when running the job:"
					+ id, e);
		}
	}
}
