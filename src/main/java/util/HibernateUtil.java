package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	public static SessionFactory getSessionFactory() {
		try {
			Configuration configuration = new Configuration();
			configuration.configure("hibernate.cfg.xml");
			return configuration.buildSessionFactory();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
