package cn.edu.sjtu.dcl.graph.model;

import java.util.Vector;

public final class ForkNode extends Vertex
{
	private Vector<String> child;
	
	public ForkNode(String name)
	{
		setName(name);
		setType(3);
		child = new Vector<String>();
	}
	
	public Vector<String> getChild()
	{
		return child;
	}
	public void setChild(Vector<String> child)
	{
		this.child = child;
	}
	
}
