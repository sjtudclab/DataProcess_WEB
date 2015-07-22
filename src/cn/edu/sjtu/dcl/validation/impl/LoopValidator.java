package cn.edu.sjtu.dcl.validation.impl;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

import cn.edu.sjtu.dcl.graph.impl.GraphBuilder;
import cn.edu.sjtu.dcl.graph.model.DAG;
import cn.edu.sjtu.dcl.graph.model.Vertex;
import cn.edu.sjtu.dcl.validation.IValidator;

public class LoopValidator implements IValidator
{
	public boolean validate(String jcdlPath)
	{
		return !detectLoop(jcdlPath);
	}
	private boolean detectLoop(String jcdlPath)
	{
		GraphBuilder gb = new GraphBuilder();
		gb.build(jcdlPath);
		DAG dag = gb.getDAG();
		return !topSort(dag);
	}
	
	private boolean topSort(DAG dag)
	{
		boolean flag = true;
		int vertexnum = dag.getVertex().size();
		Vector<Vertex> sorted = new Vector<Vertex>();
		Queue<Vertex> queue = new LinkedList<Vertex>();
		Vector<Vertex> allVertex = dag.getVertex();
		Vector<String> adj = null;
		Vertex vertex = null;
		for(Vertex v : allVertex)
		{
			if(getInDegree(v, dag) == 0)
			{
				//System.out.println(v.getName() + " indegreee is 0");
				queue.add(v);
				sorted.add(v);
			}
		}
		while(!queue.isEmpty())
		{
			vertex = queue.poll();
			dag.delVertex(vertex);
			adj = vertex.getAdjacency();
			for(String s : adj)
			{
				Vertex sv = dag.getVertex(s);
				if(getInDegree(sv, dag) == 0)
				{
					queue.add(sv);
					sorted.add(sv);
				}
			}
		}
		if(sorted.size() != vertexnum)
		{
			flag = false;
		}
		return flag;
	}
	
	private int getInDegree(Vertex v, DAG dag)
	{
		int degree = 0;
		Vector<Vertex> vertexset = dag.getVertex();
		for(Vertex vertex : vertexset)
		{
			Vector<String> adj = vertex.getAdjacency();
			for(String s : adj)
			{
				if(s.equals(v.getName()))
				{
					degree++;
				}
			}
		}
		return degree;
	}
	
	public static void main(String[] args)
	{
		String test = "/home/hadoop/test.xml";
		LoopValidator lv = new LoopValidator();
		System.out.println(lv.validate(test));
	}
}
