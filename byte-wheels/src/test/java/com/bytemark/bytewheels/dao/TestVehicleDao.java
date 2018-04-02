package com.bytemark.bytewheels.dao;

import java.util.List;

import org.junit.Test;

import com.bytemark.bytewheels.rs.model.Vehicle;

public class TestVehicleDao {
	VehicleDao dao = new VehicleDaoImpl();

	@Test
	public void testGetAllVehicles() throws DaoException {
		List<Vehicle> list = dao.getAllVehicle();
		assert (list != null && list.size() > 0);
	}

	@Test
	public void getAvailableVehicle() throws DaoException {
		List<Vehicle> list = dao.getAvailableVehicle("2018-04-02", "2018-04-06", "large");
		assert (list != null && list.size() > 0);
	}
	@Test
	public void getVehicleById() throws DaoException {
		Vehicle vehicle = dao.getVehicleById(1);
		assert (vehicle.getId()==1);
	}
	@Test
	public void getVehicleByCategory() throws DaoException {
		List<Vehicle> list = dao.getVehicleByCategory("large");
		assert (list != null && list.size() > 0);
	}

	
}
