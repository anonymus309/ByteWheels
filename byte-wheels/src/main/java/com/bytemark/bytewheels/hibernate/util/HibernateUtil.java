package com.bytemark.bytewheels.hibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.bytemark.bytewheels.hibernate.model.Orders;
import com.bytemark.bytewheels.hibernate.model.Users;
import com.bytemark.bytewheels.hibernate.model.Vehicles;

public class HibernateUtil {

	private static SessionFactory sessionFactory = null;

	public static SessionFactory getSessionFactory() {
		if (sessionFactory != null)
			return sessionFactory;

		Configuration cfg = new Configuration();
		cfg.configure();

		// register all entity classes with session factory annotated based
		cfg.addAnnotatedClass(Vehicles.class);
		cfg.addAnnotatedClass(Orders.class);
		cfg.addAnnotatedClass(Users.class);

		StandardServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties())
				.build();

		sessionFactory = cfg.buildSessionFactory(registry);
		return sessionFactory;

	}
}