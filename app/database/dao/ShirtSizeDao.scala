package database.dao

import database.Database
import scala.collection.JavaConversions._
import org.hibernate.criterion.Restrictions
import database.dto.ShirtSize
import database.dto.University

object ShirtSizeDao {
	private val hibernateService = Database.HibernateService;

	def getShirtSize(id: Int): Option[ShirtSize] = {
			val session = hibernateService.getCurrentSession(true);
			val criteria = session.createCriteria(classOf[ShirtSize])
					.add(Restrictions.eq(ShirtSize.SHIRT_SIZE_ID, id))
					.setCacheable(true);
			val shirtSize = criteria.uniqueResult().asInstanceOf[ShirtSize];
			hibernateService.closeSessionIfNecessary(session);
			Option(shirtSize);
	}
	def getShirtSize(name: String): Option[ShirtSize] = {
			val session = hibernateService.getCurrentSession(true);
			val criteria = session.createCriteria(classOf[ShirtSize])
					.add(Restrictions.eq(ShirtSize.NAME, name))
					.setCacheable(true);
			val shirtSize = criteria.uniqueResult().asInstanceOf[ShirtSize];
			hibernateService.closeSessionIfNecessary(session);
			Option(shirtSize);
	}
	def getShirtSizes(): List[ShirtSize] = {
			val session = hibernateService.getCurrentSession(true);
			val criteria = session.createCriteria(classOf[ShirtSize])
					.setCacheable(true);
			val shirtSizes = criteria.list().asInstanceOf[java.util.List[ShirtSize]].toList;
			hibernateService.closeSessionIfNecessary(session);
			shirtSizes;
	}
}