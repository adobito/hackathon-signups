package database.dao

import scala.collection.JavaConversions._
import database.Database
import database.dto.Skill
import database.dto.SkillCategory
import org.hibernate.criterion.Restrictions

object SkillsDao {

	def hibernateService = Database.HibernateService;

	def getSkillCategories(): List[SkillCategory] = {
			val session = hibernateService.getCurrentSession(true);
			val criteria = session.createCriteria(classOf[SkillCategory])
					.setCacheable(true);
			val skillCategories = criteria.list().asInstanceOf[java.util.List[SkillCategory]].toList;
			hibernateService.closeSessionIfNecessary(session);
			skillCategories;
	}

	def getSkills(skillCategoryId: Int): List[Skill] = {
			val session = hibernateService.getCurrentSession(true);
			val criteria = session.createCriteria(classOf[Skill])
					.add(Restrictions.eq(Skill.SKILL_CATEGORY_ID, skillCategoryId))
					.setCacheable(true);
			val skills = criteria.list().asInstanceOf[java.util.List[Skill]].toList;
			hibernateService.closeSessionIfNecessary(session);
			skills;
	}

}