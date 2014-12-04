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

	@GET
	@Path("/{id}")
	public Response finduserById( @PathParam("id") String userId) {
		UserDao userDao = new UserDao();
		String user = userDao.findUserById(userId);
		return Response.status(200).entity(user).build();
	}
	
/*	@GET
	@Path("/rec/{id}")
	public Response findrecById( @PathParam("id") String userId) {
		UserDao userDao = new UserDao();
		String user = userDao.findRecosById(userId);
		return Response.status(200).entity(user).build();
	}*/
	
}
