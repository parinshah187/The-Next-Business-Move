package edu.sjsu.cmpe295.api;

import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import edu.sjsu.cmpe295.dao.UserDao;

@Path("/user")
public class UserApis {

	/*@GET
	@Path("/{parameter}")
	public Response responseMsg( @PathParam("parameter") String parameter,
			@DefaultValue("Nothing to say") @QueryParam("value") String value) {

		String output = "Hello from: " + parameter + " : " + value;
		return Response.status(200).entity(output).build();
	}*/
	
	UserDao userDao = new UserDao();
	
	@GET
	@Path("/{id}")
	public Response findrecById( @PathParam("id") String userId) {

		List<String> recList = userDao.findRecosById(userId);
		return Response.status(200).entity(recList.toString()).build();
	}
	
}
