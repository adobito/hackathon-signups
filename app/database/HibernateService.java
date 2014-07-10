package database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateService {

	private ThreadLocal<Session> session;
	
	private SessionFactory sessionFactory;
	
	public HibernateService(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		session = new ThreadLocal<Session>();
	}
	
	public Session getCurrentSession(boolean openIfNecessary) {
		Session currSession = session.get();
		if(currSession == null && openIfNecessary || !currSession.isOpen()) {
			Session newSession = sessionFactory.openSession();
			session.set(newSession);
		}
		return session.get();
	}
	public void closeSessionIfNecessary(Session session) {
		
		if(session == null) {
			return;
		}
		
		if(session.isOpen() && session.isDirty()) {
			session.close();
			if(this.session.get() == session) {
				this.session.set(null);
			}
		}
	}

}
