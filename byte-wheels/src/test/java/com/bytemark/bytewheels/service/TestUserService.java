package com.bytemark.bytewheels.service;

import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class TestUserService {
	@Test
	public void testpickUpVehicle(){
		Client client = Client.create();
		WebResource webResource = client.resource("http://localhost:8080/byte-wheels/users/pickup/1");
		ClientResponse response = webResource.accept("application/json").put(ClientResponse.class);
		assert(response.getStatus()==200);
	}

}
