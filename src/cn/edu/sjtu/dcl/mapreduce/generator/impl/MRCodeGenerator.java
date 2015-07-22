package cn.edu.sjtu.dcl.mapreduce.generator.impl;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Properties;
import java.util.Vector;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import cn.edu.sjtu.dcl.graph.impl.GraphBuilder;
import cn.edu.sjtu.dcl.graph.model.MRNode;
import cn.edu.sjtu.dcl.mapreduce.generator.ICodeGenerator;

public class MRCodeGenerator implements ICodeGenerator
{
	GraphBuilder gb = new GraphBuilder();
	public void generate(String jcdl, String templatePath, String className, String namenode, String jobtracker)
	{
		try
		{
			generateMRCode(jcdl, templatePath, className, namenode, jobtracker);
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
	
	public void generateMRCode(String jcdl, String templatePath, String className, String namenode, String jobtracker) throws IOException
	{
		Properties p = new Properties();
		p.setProperty("resource.loader", "class");
        p.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        VelocityEngine ve = new VelocityEngine();
        ve.init(p);
		VelocityContext context = new VelocityContext();
		gb.build(jcdl);
		gb.recogDepen();
		Vector<MRNode> mrjobs = gb.getMRNode();
        context.put("mrjobs", mrjobs);
        context.put("namenode", namenode);
        context.put("jobtracker", jobtracker);
        context.put("classname", className);
        Template template =  null;
        try
        {
        	template = ve.getTemplate(templatePath);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(context.get("classname") + ".java")));
        if(template != null)
        	template.merge(context, writer);
        writer.flush();
        writer.close();
	}
	
	public String[] getLibs()
	{
		Vector<String> libs = gb.getLibs();
		String[] s = new String[libs.size()];
		libs.toArray(s);
		return s;
	}
}
