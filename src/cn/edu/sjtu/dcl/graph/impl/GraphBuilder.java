package cn.edu.sjtu.dcl.graph.impl;


import java.io.File;
import java.io.IOException;
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
import cn.edu.sjtu.dcl.graph.ADTBuilder;
import cn.edu.sjtu.dcl.graph.model.*;

public class GraphBuilder implements ADTBuilder
{
	private Vector<MRNode> mrjobs;
	private Vector<String> libs;
	private DAG graph;
	
	public GraphBuilder()
	{
		mrjobs = new Vector<MRNode>();
		libs = new Vector<String>();
		graph = new DAG();
	}
	public void build(String path)
	{
		try
		{
			buildGraph(path);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public void buildGraph(String path) throws Exception
	{
		libs.clear();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		DocumentBuilder builder = dbf.newDocumentBuilder();
		Document doc = builder.parse(new File(path));
		doc.normalize();
		Element root = doc.getDocumentElement();
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
						StartNode start = new StartNode("start");
						start.setTo(to);
						graph.addVertex(start);
						start.addVertex(to);
					}
					else if(link.getNodeName().equalsIgnoreCase("job"))
					{
						int iteration = 1;
						name = link.getAttributes().getNamedItem("name").getNodeValue();
						long jobid = Long.parseLong(link.getAttributes().getNamedItem("jobid").getNodeValue());
						to = link.getChildNodes().item(link.getChildNodes().getLength() - 2).getAttributes().getNamedItem("to").getNodeValue();
						iterative = Boolean.parseBoolean(link.getAttributes().getNamedItem("iterative").getNodeValue());
						if(iterative)
							iteration = Integer.parseInt(link.getAttributes().getNamedItem("iterationtimes").getNodeValue());
						MRNode mr = new MRNode(name);
						mr.setTo(to);
						mr.addVertex(to);
						mr.setJobId(jobid);
						mr.setIterative(iterative);
						mr.setIterationtimes(iteration);
						Job job = db.get(jobid);
						mr.setPath(job.getPath());
						mr.setMapper(job.getMapper());
						mr.setReducer(job.getReducer());
						mr.setInputformat(job.getInputFormat());
						mr.setMapinputkey(job.getMapInputKey());
						mr.setMapinputvalue(job.getMapInputValue());
						mr.setMapoutputkey(job.getMapOutputKey());
						mr.setMapoutputvalue(job.getMapOutputValue());
						mr.setOutputKey(job.getOutputKey());
						mr.setOutputValue(job.getOutputValue());
						libs.add(job.getPath());
						for(Node node = link.getFirstChild(); node != null; node = node.getNextSibling())
						{
							if(node.getNodeType() == Node.ELEMENT_NODE)
							{
								if(node.getNodeName().equalsIgnoreCase("inputpath"))
								{
									mr.setInputPath(node.getFirstChild().getNodeValue());
								}
								else if(node.getNodeName().equalsIgnoreCase("outputpath"))
								{
									mr.setOutputPath(node.getFirstChild().getNodeValue());
								}
								else if(node.getNodeName().equalsIgnoreCase("parameters"))
								{
									NodeList p = node.getChildNodes();
									if(p != null)
									{
										int j = 1;
										while(j < p.getLength())
										{
											mr.getParameters().put(p.item(j).getFirstChild().getNodeValue(), p.item(j+2).getFirstChild().getNodeValue());
											j += 4;
										}
									}
								}
							}
						}
						graph.addVertex(mr);
					}
					else if(link.getNodeName().equalsIgnoreCase("fork"))
					{
						name = link.getAttributes().getNamedItem("name").getNodeValue();
						ForkNode fork = new ForkNode(name);
						for(Node node = link.getFirstChild(); node != null; node = node.getNextSibling())
						{
							if(node.getNodeType() == Node.ELEMENT_NODE)
							{
								fork.getChild().add(node.getAttributes().getNamedItem("to").getNodeValue());
								fork.addVertex(node.getAttributes().getNamedItem("to").getNodeValue());
							}
						}
						graph.addVertex(fork);
					}
					else if(link.getNodeName().equalsIgnoreCase("join"))
					{
						name = link.getAttributes().getNamedItem("name").getNodeValue();
						to = link.getChildNodes().item(1).getAttributes().getNamedItem("to").getNodeValue();
						JoinNode join = new JoinNode(name);
						join.setTo(to);
						join.addVertex(to);
						graph.addVertex(join);
					}
//					else if(link.getNodeName().equalsIgnoreCase("decision"))
//					{
//						name = link.getAttributes().getNamedItem("name").getNodeValue();
//						DecisionNode decision = new DecisionNode(name);
//						Node switchnode = link.getFirstChild().getNextSibling();
//						for(Node node = switchnode.getFirstChild(); node != null; node = node.getNextSibling())
//						{
//							if(node.getNodeType() == Node.ELEMENT_NODE)
//							{
//								if(node.getNodeName().equalsIgnoreCase("case"))
//								{
//									decision.addCase(node.getTextContent(), node.getAttributes().getNamedItem("to").getNodeValue());
//									decision.addVertex(node.getAttributes().getNamedItem("to").getNodeValue());
//								}
//								else
//								{
//									decision.addCase("default", node.getAttributes().getNamedItem("to").getNodeValue());
//									decision.addVertex(node.getAttributes().getNamedItem("to").getNodeValue());
//								}
//							}
//						}
//						graph.addVertex(decision);
//					}
					else if(link.getNodeName().equalsIgnoreCase("end"))
					{
						name = link.getAttributes().getNamedItem("name").getNodeValue();
						EndNode end = new EndNode(name);
						graph.addVertex(end);
					}
				}
			}
		}
	}
	
	public void recogDepen()
	{
		for(int i = 0; i < graph.n(); i++)
		{
			Vertex v = graph.getVertex().get(i);
			int type = v.getType();
			switch(type)
			{
				case 0 : //mrnode
						for(String ver : v.getAdjacency())
						{
							Vertex ve = graph.getVertex(ver);
							ve.addDependecy(v.getName());
						}
						mrjobs.add((MRNode)v);
						break;
				case 1 : //start
						break;
				case 2 : //end
						break;
				case 3 : //fork
						for(String ver : v.getAdjacency())
						{
							Vertex ve = graph.getVertex(ver);
							for(String d : v.getDependencies())
							{
								ve.addDependecy(d);
							}
						}
						break;
				case 4 : //join
						for(String ver : v.getAdjacency())
						{
							Vertex ve = graph.getVertex(ver);
							for(String d : v.getDependencies())
							{
								ve.addDependecy(d);
							}
						}
						break;
//				case 5 : //switch
//						break;
				default : System.out.println("error");
			}
		}
	}
	
	public Vector<MRNode> getMRNode()
	{
		return mrjobs;
	}
	
	public Vector<String> getLibs()
	{
		return libs;
	}
	
	public DAG getDAG()
	{
		return graph;
	}
}
