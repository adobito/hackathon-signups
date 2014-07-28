package database.dao

import database.dto.Sex
import database.Database
import org.hibernate.criterion.Restrictions
import database.dto.University
import scala.collection.JavaConversions._

object SexDao {
  private val hibernateService = Database.HibernateService; 
  def getSex(shortName: String): Option[Sex] = {
    None;
  };
  	def getSexes(): List[Sex] = {
			val session = hibernateService.getCurrentSession(true);
			val criteria = session.createCriteria(classOf[Sex])
					.setCacheable(true);
			val sexes = criteria.list().asInstanceOf[java.util.List[Sex]].toList.sortBy(x => x.getId());
			hibernateService.closeSessionIfNecessary(session);
			sexes;
	}
	def getSex(id: Int): Option[Sex] = {
			val session = hibernateService.getCurrentSession(true);
			val criteria = session.createCriteria(classOf[University])
					.add(Restrictions.eq(Sex.ID,id))
					.setCacheable(true);
			val sex = criteria.uniqueResult().asInstanceOf[Sex];
			hibernateService.closeSessionIfNecessary(session);
			Option(sex);
	}
}