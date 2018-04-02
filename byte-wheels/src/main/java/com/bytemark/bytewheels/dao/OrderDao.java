package com.bytemark.bytewheels.dao;

public interface OrderDao {
    public boolean reserveVehicle(String pickUpDate,String dropOffDate,Integer userId,Integer vehicleId)throws DaoException;
    public boolean confirmOrder(Integer orderId)throws DaoException;;
}
