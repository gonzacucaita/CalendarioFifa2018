package com.unbosque.edu.co.calendarioFifa.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

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
	
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
