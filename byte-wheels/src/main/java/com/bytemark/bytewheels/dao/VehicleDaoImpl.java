package com.bytemark.bytewheels.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.bytemark.bytewheels.common.ByteFactory;
import com.bytemark.bytewheels.common.Constants;
import com.bytemark.bytewheels.hibernate.model.Vehicles;
import com.bytemark.bytewheels.hibernate.util.HibernateUtil;
import com.bytemark.bytewheels.rs.model.Vehicle;

public class VehicleDaoImpl implements VehicleDao {

	public Vehicle getVehicleById(Integer id) throws DaoException {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Vehicle vehicle = null;
		try (Session session = factory.openSession()) {
			String hql = "Select new Vehicles(v.id,v.model,v.dailyPrice,v.category,v.quantity) from Vehicles v where v.id= :VEHICLE_ID";
			Query<Vehicles> qry = session.createQuery(hql, Vehicles.class);
			qry.setParameter("VEHICLE_ID", id);
			vehicle = convertToVehicle(qry.getResultList()).get(0);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return vehicle;
	}

	public List<Vehicle> getAllVehicle() throws DaoException {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		List<Vehicle> list = new ArrayList<Vehicle>();
		try (Session session = factory.openSession()) {// select particular
														// values from tables
			String hql = "Select new Vehicles(v.id,v.model,v.dailyPrice,v.category,v.quantity) from Vehicles v";
			Query<Vehicles> qry = session.createQuery(hql, Vehicles.class);
			list = convertToVehicle(qry.getResultList());

		} catch (Exception e) {
			throw new DaoException(e);
		}
		return list;
	}

	public List<Vehicle> getVehicleByCategory(String category) throws DaoException {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		List<Vehicle> list = null;
		try (Session session = factory.openSession()) {
			String hql = "Select new Vehicles(v.id,v.model,v.dailyPrice,v.category,v.quantity) from Vehicles v where category = :VEHICLE_CATEGORY";
			Query<Vehicles> qry = session.createQuery(hql, Vehicles.class);
			qry.setParameter("VEHICLE_CATEGORY", category.toLowerCase());
			list = convertToVehicle(qry.getResultList());
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return list;

	}

	public List<Vehicle> getAvailableVehicle(String from, String to, String category) throws DaoException {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		List<Vehicle> list;
		try (Session session = factory.openSession()) {
			String sql = "select * from Vehicles v where not exists(select * from orders r where r.vehicle_id = v.vehicle_id and (( :START_DATE >= r.pick_up_date and :START_DATE< r.drop_off_date)or(:END_DATE= r.pick_up_date and :END_DATE <  r.drop_off_date)))";
			if (category != null) {
				sql = sql + "and v.category= :CATEGORY";
			}
			Query<Vehicles> qry = session.createNativeQuery(sql, Vehicles.class);
			qry.setParameter("START_DATE", from);
			qry.setParameter("END_DATE", to);
			if (category != null) {
				qry.setParameter("CATEGORY", category);
			}
			list = convertToVehicle(qry.getResultList());
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return list;
	}

	private List<Vehicle> convertToVehicle(List<Vehicles> vehicles) {
		List<Vehicle> list = new ArrayList<Vehicle>();
		if (vehicles != null && vehicles.size() > 0) {
			Vehicle vehicle = null;
			for (Vehicles v : vehicles) {
				vehicle = (Vehicle) ByteFactory.getInstance(Constants.VEHICLE);
				vehicle.setId(v.getId());
				vehicle.setModel(v.getModel());
				vehicle.setCategory(v.getCategory());
				vehicle.setDailyPrice(v.getDailyPrice());
				vehicle.setQuantity(v.getQuantity());
				list.add(vehicle);
			}
		}
		return list;

	}

	// public static void main(String[] args) throws DaoException {
	// VehicleDaoImpl i = new VehicleDaoImpl();
	// Gson g= new Gson();
	// String str=g.toJson(i.getAvailableVehicle("2018-04-02", "2018-04-02",
	// null));
	// System.out.println(str);
	// }

}
