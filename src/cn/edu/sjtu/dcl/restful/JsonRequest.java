package cn.edu.sjtu.dcl.restful;

public class JsonRequest {

	private long id;
	private String msg;

	public JsonRequest() {

	}

	public long getId() {
		return id;
	}

	public void setId(long iid) {
		id = iid;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String mmsg) {
		this.msg = mmsg;
	}
}
