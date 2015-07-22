package cn.edu.sjtu.dcl.rest.exception;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RestInnerExceptionMapper implements ExceptionMapper<RestInnerException>{

	@Override
	public Response toResponse(RestInnerException e) {
		// TODO Auto-generated method stub
		return Response.status(Status.INTERNAL_SERVER_ERROR).build();
	}
	
}
