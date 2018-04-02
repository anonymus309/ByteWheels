package com.bytemark.bytewheels.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.bytemark.bytewheels.hibernate.model.Orders;
import com.bytemark.bytewheels.hibernate.model.Users;
import com.bytemark.bytewheels.hibernate.util.HibernateUtil;

public class UserDaoImpl implements UserDao {

	@Override
	public Users getUserById(Integer userId) throws DaoException {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Users user = null;
		try (Session session = factory.openSession()) {
			user = session.get(Users.class, userId);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return user;
	}

	@Override
	public Integer pickUpVehicle(Integer orderId) throws DaoException {
		Integer resp = null;
		SessionFactory factory = HibernateUtil.getSessionFactory();
		try (Session session = factory.openSession()) {
			Transaction tx = session.beginTransaction();
			Orders order = session.get(Orders.class, orderId);
			if (order.isProcessed()) {// check is the order is processed
										// otherwise throw exception
				order.setPicked(true);
				resp = order.getRentCost();
				session.update(order);
				tx.commit();
			} else {
				throw new DaoException("order confirmation is required before pickup");
			}
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return resp;
	}

}
