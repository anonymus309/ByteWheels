package com.bytemark.bytewheels.service;

import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class TestVehicleService {

	@Test
	public void testGetAllVehicle() {
		assert (getResponse("http://localhost:8080/byte-wheels/vehicles") == 200);
	}

	@Test
	public void testGetVehicleById() {
		assert (getResponse("http://localhost:8080/byte-wheels/vehicles/1") == 200);
	}

	@Test
	public void getVehicleByCategory() {
		assert (getResponse("http://localhost:8080/byte-wheels/vehicles/query/?category=compact") == 200);
	}
	@Test
	public void getVehicleAvailability() {
		assert (getResponse("http://localhost:8080/byte-wheels/vehicles/available/?from=2018-04-02&to=2018-04-02") == 200);
	}
	@Test
	public void getVehicleAvailabilityByCategory() {
		assert (getResponse("http://localhost:8080/byte-wheels/vehicles/available/query/?from=2018-04-02&to=2018-04-02&category=compact") == 200);
	}
	private int getResponse(String url) {
		Client client = Client.create();
		WebResource webResource = client.resource(url);
		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
		return response.getStatus();
	}
}
