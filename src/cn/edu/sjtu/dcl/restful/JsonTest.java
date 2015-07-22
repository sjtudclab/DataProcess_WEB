package cn.edu.sjtu.dcl.restful;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.commons.io.IOUtils;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("json")
public class JsonTest {

	@GET
	@Path("get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTrackInJSON(@Context HttpServletRequest request, @PathParam("id") long id) {

		Enumeration<String> attrs = request.getSession(true).getAttributeNames();
		Cookie[] cookies = request.getCookies();
		for(Cookie c : cookies)
		{
			System.out.println(c.getName() + "=" + c.getValue());
		}
		while(attrs.hasMoreElements())
		{
			System.out.println("attr:----" + attrs.nextElement());
		}
		
		System.out.println("heeeeeeeeeeeeeeee");
		System.out.println(String.format("id : %d", id));

		JsonResponse resp = new JsonResponse();
		resp.setId(id + 1);
		resp.setMsg("hello my get json");

		return Response.status(200).entity(resp).build();
	}

	@POST
	@Path("post")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createTrackInJSON(JsonRequest req) {

		System.out.println(String.format("id : %d, msg: %s", req.getId(),
				req.getMsg()));

		JsonResponse resp = new JsonResponse();
		resp.setId(req.getId() + 1);
		resp.setMsg("hello my post json");

		return Response.status(201).entity(resp).build();
	}

	@POST
	@Path("upload2/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes("application/octet-stream")
	public Response putFile(@Context HttpServletRequest a_request,
	                         
	                         InputStream stream, @PathParam("id") long id) throws Throwable
	{
		System.out.println(String.format("from cliet id: %d" ,id));
		
		OutputStream outStream = new FileOutputStream(String.format("D:\\test\\%s", "autorun.inf"));
		IOUtils.copy(stream, outStream);
		
		JsonResponse resp = new JsonResponse();
		resp.setId(345);
		resp.setMsg("hello my upload json");
		
		return Response.status(200).entity(resp).build();
	}
	
	@POST
	@Path("upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response handleUpload(
			@FormDataParam("file") InputStream stream) throws Exception {
		//String fileName = idDisposition.getFileName();
		//System.out.println(String.format("upload id : %s, filename: %s", id, fileName));
		//System.out.println(String.format("FileName: %s", fileName));
		
		OutputStream outStream = new FileOutputStream(String.format("D:\\test\\%s", "autorun.inf"));
		IOUtils.copy(stream, outStream);
		
		JsonResponse resp = new JsonResponse();
		resp.setId(345);
		resp.setMsg("hello my upload json");
		
		return Response.status(200).entity(resp).build();
	}
}