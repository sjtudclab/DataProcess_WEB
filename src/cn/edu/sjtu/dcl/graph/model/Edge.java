package cn.edu.sjtu.dcl.graph.model;


public class Edge
{
	private String v1;//the start vertex's index
	private String v2;//the end vertex's index
	public Edge(String v, String w)
	{
		v1 = v;
		v2 = w;
	}
	
	public String getv1()//get the first node of the edge
	{
		return v1;
	}
	
	public String getv2()//get the other node of the edge
	{
		return v2;
	}
	
	public void setv1(String i)//set the first node of the edge
	{
		v1 = i;
	}
	
	public void setv2(String i)//set the other node of the edge
	{
		v2 = i;
	}
}
