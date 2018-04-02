package com.bytemark.bytewheels.dao;

import java.util.List;

import com.bytemark.bytewheels.rs.model.Vehicle;

public interface VehicleDao {
	public Vehicle getVehicleById(Integer id) throws DaoException;

	public List<Vehicle> getAllVehicle() throws DaoException;

	public List<Vehicle> getVehicleByCategory(String category) throws DaoException;

	public List<Vehicle> getAvailableVehicle(String from, String to, String category) throws DaoException;

}
