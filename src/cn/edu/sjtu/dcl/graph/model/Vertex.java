package cn.edu.sjtu.dcl.graph.model;

import java.util.*;

public class Vertex
{
	private String name;
	private Vector<String> adjacency = new Vector<String>();//the adjacency list
	private int mark = 0;
	private int type;
	private Vector<String> dependencies = new Vector<String>();
	public Vertex(){}
	
	public Vertex(String n)
	{
		name = n;
	}
	
	public void setType(int type)
	{
		this.type = type;
	}
	public int getType()
	{
		return type;//0,mr;1,start;2,end;3,fork;4,join;5,switch
	}
	public Vector<String> getDependencies()
	{
		return dependencies;
	}

	public void addDependecy(String dependency)
	{
		dependencies.add(dependency);
	}
	
	public void addVertex(String name)
	{
		adjacency.add(name);
	}
	
	public boolean delVertex(String name)
	{
		return adjacency.removeElement(name);
	}
	
	public boolean toVertex(String w)//check whether the vertex w is in the adjacency list
	{
		return adjacency.contains(w);
	}
	
	public String getFirst()//get the first next edge from this vertex
	{
		if(adjacency.isEmpty())
			return null;
		return adjacency.firstElement();
	}
	
	public void setName(String n)//set the name of the vertex
	{
		name = n;
	}
	
	public String getName()//get the name of the vertex
	{
		return name;
	}
	
	public void setMark(int m)//set the state of the vertex
	{
		mark = m;
	}
	
	public int getMark()//get the state of the vertex
	{
		return mark;
	}
	
	public Vector<String> getAdjacency()//get the adjacency list of this vertex
	{
		return adjacency;
	}
	
	/*public static void main(String[] args)
	{
		Vertex a = new Vertex(1);
		a.setVertexWeight(1);
		a.addVertex(3,0);
		a.addVertex(8,1);
		a.addVertex(6,2);
		a.setWeight(1, 5);
		for(int i = 0; i < a.adjacency.size(); i++)
			System.out.println(a.getWeight(i));
		System.out.println(a.getVertexWeight());
		System.out.println(a.toVertex(31));
		System.out.println(a.getWeight(a.adjacency.indexOf(8)));
		//a.delVertex(8);
		System.out.println(a.adjacency.contains(8));
	}*/
}
