package database

import org.hibernate.SessionFactory
import org.hibernate.cfg.Configuration
import credentials.DatabaseCredentials
import org.hibernate.service.ServiceRegistryBuilder

object Database {

	val SessionFactory: SessionFactory = {
		val configuration = new Configuration()
		.configure()
		.setProperty("hibernate.connection.url", DatabaseCredentials.Url)
		.setProperty("hibernate.connection.username", DatabaseCredentials.Username)
		.setProperty("hibernate.connection.password", DatabaseCredentials.Password);
		val serviceRegistry = new ServiceRegistryBuilder()
		.applySettings(configuration.getProperties())
		.build();
		configuration.buildSessionFactory(serviceRegistry);
};

}