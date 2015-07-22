package cn.edu.sjtu.dcl.oozie.parser.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import cn.edu.sjtu.dcl.dao.bean.Job;
import cn.edu.sjtu.dcl.dao.impl.DB_Job;
import cn.edu.sjtu.dcl.oozie.parser.IParser;
import cn.edu.sjtu.dcl.util.PropertiesHandler;
import cn.edu.sjtu.dcl.xml.XMLHandler;

public class OozieParser implements IParser
{
	private DocumentBuilderFactory dbf;
	private DocumentBuilder builder;
	private Document doc;
	private final String JOBTRACKER;
	private final String NAMENODE;
	private Vector<String> libs;
	private final String namespace = "uri:oozie:workflow:0.1";
	public OozieParser(String path, String namenode, String jobtracker) throws ParserConfigurationException
	{
		dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		builder = dbf.newDocumentBuilder();
		//JOBTRACKER = PropertiesHandler.getValue("conf.properties", "jobtracker");
		//NAMENODE = PropertiesHandler.getValue("conf.properties", "namenode");
		NAMENODE = namenode;
		JOBTRACKER = jobtracker;
		libs = new Vector<String>();
		//doc = builder.parse(new File(this.path));
	}
	public void parse(String in, String out) throws Exception
	{
		libs.clear();
		doc = builder.parse(new File(in));
		doc.normalize();
		Document doc2 = builder.newDocument();
		Element ooroot=doc2.createElement("workflow-app");
		doc2.appendChild(ooroot); 
		Element root = doc.getDocumentElement();
		ooroot.setAttribute("name", root.getAttribute("name"));
		ooroot.setAttribute("xmlns", namespace);
		NodeList nodes = root.getChildNodes();
		Node link = null;
		boolean iterative;
		String to;
		String name;
		DB_Job db = new DB_Job();
		if(nodes != null)
		{
			for(int i = 0; i < nodes.getLength(); i++)
			{
				link = nodes.item(i);
				if(link.getNodeType() == Node.ELEMENT_NODE)
				{
					if(link.getNodeName().equalsIgnoreCase("start"))
					{
						to = link.getChildNodes().item(1).getAttributes().getNamedItem("to").getNodeValue();
						Element start = doc2.createElement("start");
						start.setAttribute("to", to);
						ooroot.appendChild(start);
					}
					else if(link.getNodeName().equalsIgnoreCase("job"))
					{
						int iteration = 1;
						name = link.getAttributes().getNamedItem("name").getNodeValue();
						long jobid = Long.parseLong(link.getAttributes().getNamedItem("jobid").getNodeValue());
						Job job = db.get(jobid);
						libs.add(job.getPath());
						to = link.getChildNodes().item(link.getChildNodes().getLength() - 2).getAttributes().getNamedItem("to").getNodeValue();
						iterative = Boolean.parseBoolean(link.getAttributes().getNamedItem("iterative").getNodeValue());
						if(iterative)
							iteration = Integer.parseInt(link.getAttributes().getNamedItem("iterationtimes").getNodeValue());
						for(int iter = 0; iter < iteration; iter++)
						{
							Element action = doc2.createElement("action");
							Element mapreduce = doc2.createElement("map-reduce");
							Element ok = doc2.createElement("ok");
							Element error = doc2.createElement("error");
							Element jobtracker = doc2.createElement("job-tracker");
							Element namenode = doc2.createElement("name-node");
							Element prepare = doc2.createElement("prepare");
							Element delete = doc2.createElement("delete");
							
							if(iteration <= 1)
							{
								action.setAttribute("name", name);
								ok.setAttribute("to", to);
							}
							else
							{
								if(iter == 0)
								{
									action.setAttribute("name", name);
									ok.setAttribute("to", name + "iteration" + (iter + 2));
								}
								else if(iter == (iteration - 1))
								{
									action.setAttribute("name", name + "iteration" + (iter + 1));
									ok.setAttribute("to", to);
								}
								else
								{
									action.setAttribute("name", name + "iteration" + (iter + 1));
									ok.setAttribute("to", name + "iteration" + (iter + 2));
								}
							}
							jobtracker.setTextContent(JOBTRACKER);
							namenode.setTextContent(NAMENODE);
							error.setAttribute("to", "fail");
							action.appendChild(mapreduce);
							action.appendChild(ok);
							action.appendChild(error);
							mapreduce.appendChild(jobtracker);
							mapreduce.appendChild(namenode);
							prepare.appendChild(delete);
							mapreduce.appendChild(prepare);
							
							Element conf = doc2.createElement("configuration");
							mapreduce.appendChild(conf);
							addProperty(doc2, conf, "mapred.mapper.new-api", "true");
							addProperty(doc2, conf, "mapred.reducer.new-api", "true");
							addProperty(doc2, conf, "mapreduce.map.class", job.getMapper());
							addProperty(doc2, conf, "mapreduce.reduce.class", job.getReducer());
							addProperty(doc2, conf, "mapred.input.format.class", job.getInputFormat());
							addProperty(doc2, conf, "mapred.mapoutput.key.class", job.getMapOutputKey());
							addProperty(doc2, conf, "mapred.mapoutput.value.class", job.getMapOutputValue());
							addProperty(doc2, conf, "mapred.output.key.class", job.getOutputKey());
							addProperty(doc2, conf, "mapred.output.value.class", job.getOutputValue());
							for(Node node = link.getFirstChild(); node != null; node = node.getNextSibling())
							{
								if(node.getNodeType() == Node.ELEMENT_NODE)
								{
									if(node.getNodeName().equalsIgnoreCase("inputpath"))
									{
										addProperty(doc2, conf, "mapred.input.dir", node.getFirstChild().getNodeValue());
									}
									else if(node.getNodeName().equalsIgnoreCase("outputpath"))
									{
										delete.setAttribute("path", NAMENODE + "/user/${wf:user()}/" + node.getFirstChild().getNodeValue());
										addProperty(doc2, conf, "mapred.output.dir", node.getFirstChild().getNodeValue());
									}
									else if(node.getNodeName().equalsIgnoreCase("parameters"))
									{
										NodeList p = node.getChildNodes();
										if(p != null)
										{
											int j = 1;
											while(j < p.getLength())
											{
												addProperty(doc2, conf, p.item(j).getFirstChild().getNodeValue(), 
														p.item(j+2).getFirstChild().getNodeValue());
												j += 4;
											}
										}
									}
									//ooroot.appendChild(action);
								}
							}
							ooroot.appendChild(action);
						}
					}
					else if(link.getNodeName().equalsIgnoreCase("fork"))
					{
						name = link.getAttributes().getNamedItem("name").getNodeValue();
						Element fork = doc2.createElement("fork");
						fork.setAttribute("name", name);
						Element path;
						for(Node node = link.getFirstChild(); node != null; node = node.getNextSibling())
						{
							if(node.getNodeType() == Node.ELEMENT_NODE)
							{
								path = doc2.createElement("path");
								path.setAttribute("start", node.getAttributes().getNamedItem("to").getNodeValue());
								fork.appendChild(path);
							}
						}
						ooroot.appendChild(fork);
					}
					else if(link.getNodeName().equalsIgnoreCase("join"))
					{
						name = link.getAttributes().getNamedItem("name").getNodeValue();
						to = link.getChildNodes().item(1).getAttributes().getNamedItem("to").getNodeValue();
						Element join = doc2.createElement("join");
						join.setAttribute("name", name);
						join.setAttribute("to", to);
						ooroot.appendChild(join);
					}
//					else if(link.getNodeName().equalsIgnoreCase("decision"))
//					{
//						name = link.getAttributes().getNamedItem("name").getNodeValue();
//						Element decision = doc2.createElement("decision");
//						decision.setAttribute("name", name);
//						Element sw = doc2.createElement("switch");
//						Node switchnode = link.getFirstChild().getNextSibling();
//						Element switchel;
//						for(Node node = switchnode.getFirstChild(); node != null; node = node.getNextSibling())
//						{
//							if(node.getNodeType() == Node.ELEMENT_NODE)
//							{
//								switchel = doc2.createElement(node.getNodeName());
//								switchel.setAttribute("to", node.getAttributes().getNamedItem("to").getNodeValue());
//								switchel.setTextContent(node.getTextContent());
//								sw.appendChild(switchel);
//							}
//						}
//						decision.appendChild(sw);
//						ooroot.appendChild(decision);
//					}
					else if(link.getNodeName().equalsIgnoreCase("end"))
					{
						Element kill = doc2.createElement("kill");
						kill.setAttribute("name", "fail");
						Element message = doc2.createElement("message");
						message.setTextContent("Map/Reduce failed, error message[${wf:errorMessage()}]");
						kill.appendChild(message);
						ooroot.appendChild(kill);
						name = link.getAttributes().getNamedItem("name").getNodeValue();
						Element end = doc2.createElement("end");
						end.setAttribute("name", name);
						ooroot.appendChild(end);
					}
				}
			}
		}
				
		FileOutputStream outStream = null;
		try
		{
			outStream = new FileOutputStream(out);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		OutputStreamWriter outWriter=new OutputStreamWriter(outStream);
		XMLHandler.callWriteXMLFile(doc2, outWriter,"utf-8");
		try
		{
			outWriter.close();
			outStream.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void addProperty(Document doc, Element parent, String propertyName, String propertyValue)
	{
		Element property = doc.createElement("property");
		Element name = doc.createElement("name");
		Element value = doc.createElement("value");
		name.setTextContent(propertyName);
		value.setTextContent(propertyValue);
		property.appendChild(name);
		property.appendChild(value);
		parent.appendChild(property);
	}
	
	public Vector<String> getLibs()
	{
		return libs;
	}
}
