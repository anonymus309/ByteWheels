package com.bytemark.bytewheels.common;

import com.bytemark.bytewheels.dao.OrderDaoImpl;
import com.bytemark.bytewheels.dao.UserDaoImpl;
import com.bytemark.bytewheels.dao.VehicleDaoImpl;
import com.bytemark.bytewheels.hibernate.model.Orders;
import com.bytemark.bytewheels.rs.model.Vehicle;
import com.bytemark.bytewheels.service.ServiceError;
import com.google.gson.Gson;

public class ByteFactory {

	public ByteFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public static Object getInstance(String str){
		if(str.equals(Constants.VEHICLE_DAO)){
			return new VehicleDaoImpl();
		}
		if(str.equals(Constants.GSON)){
			return new Gson();
		}
		if(str.equals(Constants.SERVICE_ERROR)){
			return new ServiceError();
		}
		if(str.equals(Constants.VEHICLE)){
			return new Vehicle();
		}
		if(str.equals(Constants.ORDERS)){
			return new Orders();
			
		}
		if(str.equals(Constants.ORDER_DAO)){
			return new OrderDaoImpl();
		}
		if(str.equals(Constants.USER_DAO)){
			return new UserDaoImpl();
		}
		return null;
	}

}
