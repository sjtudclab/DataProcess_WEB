package cn.edu.sjtu.dcl.restful;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import cn.edu.sjtu.dcl.dao.impl.DB_User;
import cn.edu.sjtu.dcl.rest.exception.RestInnerException;
import cn.edu.sjtu.dcl.restful.message.LoginRespMessage;
import cn.edu.sjtu.dcl.util.EncodeUtil;

@Path("user")
public class RestUserService {
	
	@POST
	@Path("login/{username}/{pwd}")
	@Produces(MediaType.APPLICATION_JSON)
	public LoginRespMessage Login(@PathParam("username") String username, @PathParam("pwd") String pwd) throws RestInnerException
	{
		DB_User dbUser = new DB_User();
		LoginRespMessage resp = new LoginRespMessage();
		try {
			resp.setValid(dbUser.validateUser(username, EncodeUtil.getMD5Code(pwd)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RestInnerException("invalid user with name:" + username + " pwd:" + pwd);
		}
		
		return resp;
	}
}
