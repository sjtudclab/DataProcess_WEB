package cn.edu.sjtu.dcl.restful;

public class JsonResponse {
	
	private long id;
	private String msg;
	
	public JsonResponse()
	{
		
	}
	
	public long getId()
	{
		return id;
	}
	
	public String getMsg()
	{
		return msg;
	}
	
	public void setId(long iid)
	{
		this.id = iid;
	}
	
	public void setMsg(String mmsg)
	{
		this.msg = mmsg;
	}
}
