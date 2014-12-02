package edu.sjsu.cmpe295.api;

import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import edu.sjsu.cmpe295.dao.BusinessDao;

@Path("/biz")
public class BusinessApis {

	@GET
	@Path("/{id}")
	public Response findBizById( @PathParam("id") String id) {
		BusinessDao bizDao = new BusinessDao();
		String bizObj = bizDao.findBusinessById(id);
		return Response.status(200).entity(bizObj).build();
	}
	
	@GET
	@Path("/")
	public Response findAllBiz(){ // @PathParam("all") String parameter) {
		BusinessDao bizDao = new BusinessDao();
		List<String> bizList = bizDao.findAllBusiness();
		return Response.status(200).entity(bizList.toString()).build();
	}

}
