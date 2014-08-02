package database.dao

import database.dto.University
import database.Database
import org.hibernate.criterion.Restrictions
import scala.collection.JavaConversions._

object UniversityDao {
	private val hibernateService = Database.HibernateService;
	def getUniversity(id: Int): Option[University] = {
			val session = hibernateService.getCurrentSession(true);
			val criteria = session.createCriteria(classOf[University])
					.add(Restrictions.eq(University.UNIVERSITY_ID, id))
					.setCacheable(true);
			val university = criteria.uniqueResult().asInstanceOf[University];
			hibernateService.closeSessionIfNecessary(session);
			Option(university);
	}
	def getUniversity(name: String): Option[University] = {
			val session = hibernateService.getCurrentSession(true);
			val criteria = session.createCriteria(classOf[University])
					.add(Restrictions.eq(University.NAME, name))
					.setCacheable(true);
			val university = criteria.uniqueResult().asInstanceOf[University];
			hibernateService.closeSessionIfNecessary(session);
			Option(university);
	}
	def getUniversities(name: String): List[University] = {
			Nil;
	}
	def getUniversities(): List[University] = {
			val session = hibernateService.getCurrentSession(true);
			val criteria = session.createCriteria(classOf[University])
					.setCacheable(true);
			val universities = criteria.list().asInstanceOf[java.util.List[University]].toList;
			hibernateService.closeSessionIfNecessary(session);
			universities;
	}
	def addUniversity(name: String): Option[University] = {
			val session = hibernateService.getCurrentSession(true);
			val university = new University();
			university.setName(name);
			val transaction = session.beginTransaction();
			session.save(university);
			transaction.commit();
			session.close();
			Option(university); //not exactly correct, can never be None
	}


}