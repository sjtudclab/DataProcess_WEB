package cn.edu.sjtu.dcl.graph.model;

public final class JoinNode extends Vertex
{
	private String to;
	
	public JoinNode(String name)
	{
		setName(name);
		setType(4);
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
