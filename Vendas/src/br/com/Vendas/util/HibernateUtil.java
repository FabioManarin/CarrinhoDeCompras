package br.com.Vendas.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();
	
	private static SessionFactory buildSessionFactory() {
		try{
			//Cria uma conexão apartir do hibernet.cfg.xml
			Configuration configuration = new Configuration();
			configuration.configure();
			ServiceRegistry serviceResgistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			SessionFactory sessionFactory = configuration.buildSessionFactory(serviceResgistry);
			
			return sessionFactory;
			
			//return new Configuration().configure().buildSessionFactory(new StandardServiceRegistryBuilder().build());
		}
		catch (Throwable ex){
			//Mensagem de erro ao conectar
			System.err.println("Erro na conexão " + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
