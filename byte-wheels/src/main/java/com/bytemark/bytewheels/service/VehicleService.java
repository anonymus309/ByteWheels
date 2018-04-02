package com.bytemark.bytewheels.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.bytemark.bytewheels.common.ByteFactory;
import com.bytemark.bytewheels.common.Constants;
import com.bytemark.bytewheels.dao.DaoException;
import com.bytemark.bytewheels.dao.VehicleDao;
import com.google.gson.Gson;

@Path("/vehicles")
public class VehicleService {
	VehicleDao dao = (VehicleDao) ByteFactory.getInstance(Constants.VEHICLE_DAO);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllVehicle() {
		Gson gson = (Gson) ByteFactory.getInstance(Constants.GSON);
		String data = null;
		Response response = null;
		try {
			data = gson.toJson(dao.getAllVehicle());
			response = Response.status(200).entity(data).build();
		} catch (Exception e) {
			ServiceError error = (ServiceError) ByteFactory.getInstance(Constants.SERVICE_ERROR);
			error.setErrorId(500);
			error.setMessage(e.getMessage());
			data = gson.toJson(error, ServiceError.class);
			response = Response.status(500).entity(data).build();
		}
		return response;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response getVehicle(@PathParam("id") Integer id) {
		Gson gson = (Gson) ByteFactory.getInstance(Constants.GSON);
		String data = null;
		Response response = null;
		try {
			data = gson.toJson(dao.getVehicleById(id));
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

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/query")
	public Response getVehicleByCategory(@QueryParam("category") String category) {
		Gson gson = (Gson) ByteFactory.getInstance(Constants.GSON);
		String data = null;
		Response response = null;
		try {
			data = gson.toJson(dao.getVehicleByCategory(category));
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

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/available")
	public Response getAvailableVehicles(@QueryParam("from") String pickUpDate, @QueryParam("to") String dropOffDate) {
		Gson gson = (Gson) ByteFactory.getInstance(Constants.GSON);
		String data = null;
		Response response = null;
		try {
			data = gson.toJson(dao.getAvailableVehicle(pickUpDate, dropOffDate, null));
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

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/available/query")
	public Response getAvailableVehiclesByCategory(@QueryParam("category") String category,
			@QueryParam("from") String pickUpDate, @QueryParam("to") String dropOffDate) {
		Gson gson = (Gson) ByteFactory.getInstance(Constants.GSON);
		String data = null;
		Response response = null;
		try {
			data = gson.toJson(dao.getAvailableVehicle(pickUpDate, dropOffDate, category));
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
