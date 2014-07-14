package database

import org.hibernate.SessionFactory
import org.hibernate.cfg.Configuration
import org.hibernate.service.ServiceRegistryBuilder
import credentials.DatabaseCredentials
import org.hibernate.boot.registry.StandardServiceRegistryBuilder

object Database {

	val SessionFactory: SessionFactory = {
		val configuration = new Configuration()
		.configure()
		.setProperty("hibernate.connection.url", DatabaseCredentials.Url)
		.setProperty("hibernate.connection.username", DatabaseCredentials.Username)
		.setProperty("hibernate.connection.password", DatabaseCredentials.Password);
		val builder = new StandardServiceRegistryBuilder()
		.applySettings(configuration.getProperties());
		val factory = configuration.buildSessionFactory(builder.build());
		factory;
}
	val HibernateService = new HibernateService(SessionFactory);

}