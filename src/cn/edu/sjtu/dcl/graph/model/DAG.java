package cn.edu.sjtu.dcl.graph.model;

import java.util.Vector;

public class DAG
{
	private Vector<Vertex> vertex;
	private int numEdge;
	private Vector<Integer> Mark;
	private static int nextmark = 0;
	
	public DAG()
	{
		vertex = new Vector<Vertex>();
		Mark = new Vector<Integer>();
	}
	public int n()//get the number of vertices
	{
		return vertex.size();
	}
	public int e()//get the number of edges
	{
		return numEdge;
	}
	
	public Vector<Vertex> getVertex()
	{
		return vertex;
	}
	
	public void addVertex(Vertex v)
	{
		vertex.add(v);
	}
	public void delVertex(Vertex v)
	{
		for(Vertex ver : vertex)
		{
			if(ver.getName().equals(v.getName()))
			{
				vertex.remove(ver);
				break;
			}
		}
	}
	public Vertex getVertex(String name)
	{
		Vertex result = null;
		for(Vertex v : vertex)
		{
			if(v.getName().equalsIgnoreCase(name))
			{
				result = v;
				break;
			}
		}
		return result;
	}
	public Edge first(String v)//the first edge from the vertex v
	{
		nextmark = 0;
		String a = getVertex(v).getFirst();
		if (a == null)
			return null;
		return new Edge(v, a);
	}
	
	public Edge next(Edge w)//get next edge for a vertex
	{
		nextmark++;
		if(nextmark >= getVertex(w.getv1()).getAdjacency().size())
			return null;
		return new Edge(w.getv1(), getVertex(w.getv1()).getAdjacency().elementAt(nextmark));
	}
	
	public boolean isEdge(Edge e)//determine whether e is an edge
	{
		if(e == null)
			return false;
		Vertex temp = getVertex(e.getv1());
		return temp.toVertex(e.getv2());
	}
	
	public boolean isEdge(String i, String j)//determine whether e is an edge
	{
		return getVertex(i).toVertex(j);
	}
	
	public String v1(Edge w)//get the start vertex
	{
		return w.getv1();
	}
	public String v2 (Edge w)//get the end vertex
	{
		return w.getv2();
	}
	
	public void setEdge(String i, String j)//set edge
	{
		if(!isEdge(i, j))
		{
			getVertex(j).addVertex(j);
			numEdge++;
		}
	}
	
	public void setEdge(Edge w)//set edge
	{
		if(!isEdge(w))
		{
			getVertex(w.getv1()).addVertex(w.getv2());
			numEdge++;
		}
	}
	
	public void delEdge(String i, String j)//delete edge w
	{
		if(isEdge(i, j))
		{
			getVertex(i).delVertex(j); 
			numEdge--;
		}
	}
	
	public void delEdge(Edge w)//delete edge (i, j)
	{
		if(isEdge(w))
		{
			getVertex(w.getv1()).delVertex(w.getv2());
			numEdge--;
		}
	}
	
	public void setMark(int v, int val)//set the state
	{
		Mark.set(v, val);
	}
	
	public int getMark(int v)//get the state
	{
		return Mark.get(v);
	}
	
	public void addDenpen(String a, String b)
	{
		Vertex va = getVertex(a);
		Vertex vb = getVertex(b);
		if((va.getType() == 0) && (vb.getType() == 0))
		{
			((MRNode)va).addDependecy(b);
		}
	}
}

