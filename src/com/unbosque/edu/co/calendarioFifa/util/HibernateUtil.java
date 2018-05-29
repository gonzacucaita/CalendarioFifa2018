package com.unbosque.edu.co.calendarioFifa.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * The Class HibernateUtil.
 */
public class HibernateUtil {

	/** The session factory. */
	private static SessionFactory sessionFactory;

	static {
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		sessionFactory = configuration.buildSessionFactory(builder.build());
//		@SuppressWarnings("deprecation")
//		AnnotationConfiguration ac = new AnnotationConfiguration();
//		ac.addAnnotatedClass(Usuario.class);
//		sessionFactory = ac.configure().buildSessionFactory();
	}
	
	
	/**
	 * Gets the session factory.
	 *
	 * @return the session factory
	 */
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
