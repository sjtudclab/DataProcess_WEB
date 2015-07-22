package cn.edu.sjtu.dcl.rest.exception;

public class RestInnerException extends Exception {

	public RestInnerException(Throwable e) {
		// TODO Auto-generated constructor stub
		super(e);
	}
	
	public RestInnerException(String msg)
	{
		super(msg);
	}
	
	public RestInnerException(String msg, Throwable e)
	{
		super(msg, e);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 5044700185201286256L;
	
}
