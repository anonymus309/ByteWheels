package com.bytemark.bytewheels.service;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.bytemark.bytewheels.common.ByteFactory;
import com.bytemark.bytewheels.common.Constants;
import com.bytemark.bytewheels.dao.DaoException;
import com.bytemark.bytewheels.dao.OrderDao;
import com.google.gson.Gson;

@Path("/orders")
public class OrderService {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response reserveOrder(@FormParam("vehicle_id") Integer vehicleId, @FormParam("from") String pickUpDate,
			@FormParam("to") String dropOffDate,@FormParam("user_id") Integer userId) {
		Response response = null;
		String data = null;
		
		try {
			OrderDao dao = (OrderDao) ByteFactory.getInstance(Constants.ORDER_DAO);
			Boolean res = dao.reserveVehicle(pickUpDate, dropOffDate, userId, vehicleId);
			if (!res) {
				throw new DaoException("vehicle not avialble");
			}
			response = Response.status(201).build();
		} catch (DaoException e) {
			Gson gson = (Gson) ByteFactory.getInstance(Constants.GSON);
			ServiceError error = (ServiceError) ByteFactory.getInstance(Constants.SERVICE_ERROR);
			error.setErrorId(500);
			error.setMessage(e.getMessage());
			data = gson.toJson(error, ServiceError.class);
			response = Response.status(500).entity(data).build();
		}
		return response;
	}
	
	@PUT
	@Path("{id}/confirm")
	@Produces(MediaType.APPLICATION_JSON)
	public Response confirmOrder(@PathParam("id") Integer orderId) {
		Response response = null;
		String data = null;
		
		try {
			OrderDao dao = (OrderDao) ByteFactory.getInstance(Constants.ORDER_DAO);
			dao.confirmOrder(orderId);
			response = Response.status(200).build();
		} catch (DaoException e) {
			Gson gson = (Gson) ByteFactory.getInstance(Constants.GSON);
			ServiceError error = (ServiceError) ByteFactory.getInstance(Constants.SERVICE_ERROR);
			error.setErrorId(500);
			error.setMessage(e.getMessage());
			data = gson.toJson(error, ServiceError.class);
			response = Response.status(500).entity(data).build();
		}
		return response;
	}
}
