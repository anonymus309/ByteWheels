package com.bytemark.bytewheels.dao;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.bytemark.bytewheels.common.ByteFactory;
import com.bytemark.bytewheels.common.Constants;
import com.bytemark.bytewheels.hibernate.model.Orders;
import com.bytemark.bytewheels.hibernate.model.Users;
import com.bytemark.bytewheels.hibernate.model.Vehicles;
import com.bytemark.bytewheels.hibernate.util.HibernateUtil;
import com.bytemark.bytewheels.rs.model.Vehicle;

public class OrderDaoImpl implements OrderDao {

	@Override
	public boolean reserveVehicle(String pickUpDate, String dropOffDate, Integer userId, Integer vehicleId)
			throws DaoException {
		boolean result = false;
		VehicleDao vehicleDao = (VehicleDao) ByteFactory.getInstance(Constants.VEHICLE_DAO);
		List<Vehicle> list = vehicleDao.getAvailableVehicle(pickUpDate, dropOffDate, null);
		result = list.stream().anyMatch(p -> p.getId() == vehicleId);
		if (result) {
			SessionFactory factory = HibernateUtil.getSessionFactory();
			try (Session session = factory.openSession()) {
				Transaction tx = session.beginTransaction();
				Orders order = (Orders) ByteFactory.getInstance(Constants.ORDERS);
				order.setDropOffDate(formatDate(dropOffDate));
				order.setPaid(false);
				order.setPicked(false);
				order.setPickUpDate(formatDate(pickUpDate));
				order.setProcessed(false);
				Integer price=vehicleDao.getVehicleById(vehicleId).getDailyPrice()*calculateDays(pickUpDate, dropOffDate);
				order.setRentCost(price);
				order.setReturned(false);
				order.setPaid(false);
				Vehicles v= session.get(Vehicles.class, vehicleId);
				order.setVehicles(v);
				Users user=session.get(Users.class,userId);
				order.setUsers(user);
				session.save(order);
				tx.commit();
			} catch (Exception e) {
				throw new DaoException(e);
			}

		}
		return result;
	}

	@Override
	public boolean confirmOrder(Integer orderId) throws DaoException {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		try (Session session = factory.openSession()) {
			Transaction tx = session.beginTransaction();
			Orders order=session.get(Orders.class, orderId);
			order.setProcessed(true);
			session.update(order);
			tx.commit();
		} catch (Exception e) {
			throw new DaoException(e);
		}
		
		return false;
	}
	public static Integer calculateDays(String from,String to) {
	    LocalDate start = LocalDate.parse(from);
	    LocalDate end = LocalDate.parse(to);
	    Period diff=Period.between(start, end);
	    return (diff.getDays()+1);
	}
	

	private  Date formatDate(String date)  {
		return Date.valueOf(date);
	}

}
