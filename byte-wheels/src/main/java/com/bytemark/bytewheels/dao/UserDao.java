package com.bytemark.bytewheels.dao;

import com.bytemark.bytewheels.hibernate.model.Users;

public interface UserDao {

	public Users getUserById(Integer userId) throws DaoException ;
	public Integer pickUpVehicle(Integer orderId)throws DaoException;
	
}
