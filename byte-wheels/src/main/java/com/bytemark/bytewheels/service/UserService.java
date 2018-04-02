package com.bytemark.bytewheels.service;


import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.bytemark.bytewheels.common.ByteFactory;
import com.bytemark.bytewheels.common.Constants;
import com.bytemark.bytewheels.dao.DaoException;
import com.bytemark.bytewheels.dao.UserDao;
import com.google.gson.Gson;

@Path("/users")
public class UserService {
	
	@PUT
	@Path("/pickup/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response pickUpVehicle(@PathParam("id") Integer Orderid) {
		Response response = null;
		String data = null;
		Gson gson = (Gson) ByteFactory.getInstance(Constants.GSON);
		try {
			UserDao dao = (UserDao) ByteFactory.getInstance(Constants.USER_DAO);
			Integer rent = dao.pickUpVehicle(Orderid);
			data="Rent to be paid:$"+rent;
			data=gson.toJson(data);
			response = Response.status(200).entity(data).build();
		} catch (DaoException e) {
			ServiceError error = (ServiceError) ByteFactory.getInstance(Constants.SERVICE_ERROR);
			error.setErrorId(500);
			error.setMessage(e.getMessage());
			data = gson.toJson(error, ServiceError.class);
			response = Response.status(500).entity(data).build();
		}
		return response;

	}

}
