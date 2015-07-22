package cn.edu.sjtu.dcl.graph.model;

public final class StartNode extends Vertex
{
	private String to;
	
	public StartNode(String name)
	{
		setName(name);
		setType(1);
	}
	
	public String getTo()
	{
		return to;
	}
	public void setTo(String to)
	{
		this.to = to;
	}
}
