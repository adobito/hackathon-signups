package database

import java.util.Date

import scala.collection.JavaConverters._
import scala.collection.mutable.ListBuffer

import org.hibernate.criterion.Restrictions

import cryptography.PasswordHash
import database.dto.Event
import database.dto.EventAttendance
import database.dto.LoginSession
import database.dto.Resume
import database.dto.Sex
import database.dto.Skill
import database.dto.SkillCategory
import database.dto.University
import database.dto.User
import database.dto.UserInfo

object Dao {

	private val hibernateService = new HibernateService(Database.SessionFactory);
	private val SessionHashByteSize = 32;
	//Users
	def getUser(userId: Int): Option[User] = {
			val session = hibernateService.getCurrentSession(true);
			val criteria = session.createCriteria(classOf[User]);
			criteria.add(Restrictions.eq(User.ID, userId));
			val user = criteria.uniqueResult().asInstanceOf[User];
			hibernateService.closeSessionIfNecessary(session);
			Option(user)
	}
	def getUser(email: String): Option[User] = {
			val session = hibernateService.getCurrentSession(true);
			val criteria = session.createCriteria(classOf[User]);
			criteria.add(Restrictions.eq(User.EMAIL, email));
			val user = criteria.uniqueResult().asInstanceOf[User];
			hibernateService.closeSessionIfNecessary(session);
			Option(user)
	}
	def getUsers(startingAt: Int, maxAmount: Int): Array[User] = {
			val session = hibernateService.getCurrentSession(true);
			val criteria = session.createCriteria(classOf[User]);
			criteria.setMaxResults(maxAmount).setFirstResult(startingAt);
			val usersList = criteria.list();
			val users = usersList.asInstanceOf[java.util.List[User]].asScala.toArray;
			hibernateService.closeSessionIfNecessary(session);
			users;
	}

	def getUserInfo(userId: Int): Option[UserInfo] = {
			val session = hibernateService.getCurrentSession(true);
			val criteria = session.createCriteria(classOf[UserInfo]);
			criteria.add(Restrictions.eq(UserInfo.USER_ID, userId));
			val userInfo = criteria.uniqueResult().asInstanceOf[UserInfo];
			hibernateService.closeSessionIfNecessary(session);
			Option(userInfo);
	}
	def getUserInfo(startingAt: Int, maxAmount: Int): Array[UserInfo] = {
			val session = hibernateService.getCurrentSession(true);
			val criteria = session.createCriteria(classOf[UserInfo]);
			criteria.setMaxResults(maxAmount).setFirstResult(startingAt);
			val usersInfoList = criteria.list();
			val usersInfo = usersInfoList.asInstanceOf[java.util.List[UserInfo]].asScala.toArray;
			hibernateService.closeSessionIfNecessary(session);
			usersInfo;
	}

	//Events
	def getEvent(eventId: Int): Option[Event] = {
			val session = hibernateService.getCurrentSession(true);
			val criteria = session.createCriteria(classOf[Event]);
			criteria.add(Restrictions.eq(Event.EVENT_ID, eventId));
			val event = criteria.uniqueResult().asInstanceOf[Event];
			hibernateService.closeSessionIfNecessary(session);
			Option(event)
	}
	def getEventsInRange(fromStartTime: Date, untilEndTime: Date, startingAt: Int, maxAmount: Int): Array[Event] = {
			val session = hibernateService.getCurrentSession(true);
			val criteria = session.createCriteria(classOf[Event])
					.setMaxResults(maxAmount)
					.setFirstResult(startingAt)
					.add(Restrictions.ge(Event.START_TIME, fromStartTime))
					.add(Restrictions.le(Event.END_TIME, untilEndTime));
			val events = criteria.list().asInstanceOf[java.util.List[Event]].asScala.toArray;
			hibernateService.closeSessionIfNecessary(session);
			events;
	}
	def getEventsAfter(fromStartTime: Date, startingAt: Int, maxAmount: Int): Array[Event] = {
			val session = hibernateService.getCurrentSession(true);
			val criteria = session.createCriteria(classOf[Event])
					.setMaxResults(maxAmount)
					.setFirstResult(startingAt)
					.add(Restrictions.ge(Event.START_TIME, fromStartTime));
			val events = criteria.list().asInstanceOf[java.util.List[Event]].asScala.toArray;
			hibernateService.closeSessionIfNecessary(session);
			events;
	}
	//Event Attendance
	def getEventAttendance(eventId: Int): Array[EventAttendance] = {
			val session = hibernateService.getCurrentSession(true);
			val criteria = session.createCriteria(classOf[EventAttendance],"attendance")
					.createAlias("attendance.event", "event")
					.add(Restrictions.eq("event.eventId", eventId));
			val eventAttendanceList = criteria.list().asInstanceOf[java.util.List[EventAttendance]].asScala.toArray;
			hibernateService.closeSessionIfNecessary(session);
			eventAttendanceList;
	}
	//Resumes
	def getResume(resumeId: Int): Option[Resume] = {
			val session = hibernateService.getCurrentSession(true);
			val criteria = session.createCriteria(classOf[Event]);
			criteria.add(Restrictions.eq(Resume.RESUME_ID, resumeId));
			val resume = criteria.uniqueResult().asInstanceOf[Resume];
			hibernateService.closeSessionIfNecessary(session);
			Option(resume);
	}
	//LoginSessions
	def getLoginSessions(userId: Int): Array[LoginSession] = {
			val session = hibernateService.getCurrentSession(true);
			val criteria = session.createCriteria(classOf[LoginSession])
					.add(Restrictions.eq(LoginSession.USER + ".userId", userId));
			val eventAttendanceList = criteria.list().asInstanceOf[java.util.List[LoginSession]].asScala.toArray;
			hibernateService.closeSessionIfNecessary(session);
			eventAttendanceList;
	}
	def getLoginSessions(email: String): List[LoginSession] = {
			val listBuffer = new ListBuffer[LoginSession]();

			listBuffer.toList;
	}
	def getLoginSession(token: String):Option[LoginSession] = {
			val session = hibernateService.getCurrentSession(true);
			val criteria = session.createCriteria(classOf[LoginSession]);
			criteria.add(Restrictions.eq(LoginSession.TOKEN, token));
			val loginSession = criteria.uniqueResult().asInstanceOf[LoginSession];
			hibernateService.closeSessionIfNecessary(session);
			Option(loginSession);
	}
	//University
	def getUniversities(): Array[University] = {
			val session = hibernateService.getCurrentSession(true);
			val criteria = session.createCriteria(classOf[University]);
			val universities = criteria.list().asInstanceOf[java.util.List[University]].asScala.toArray;
			hibernateService.closeSessionIfNecessary(session);
			universities;
	}
	def getUniversity(id: Int): Option[University] = {
			val session = hibernateService.getCurrentSession(true);
			val criteria = session.createCriteria(classOf[University])
					.add(Restrictions.eq(University.UNIVERSITY_ID, id));
			val university = criteria.uniqueResult().asInstanceOf[University];
			hibernateService.closeSessionIfNecessary(session);
			Option(university);
	}
	//Sex
	def getSexes(): Array[Sex] = {
			val session = hibernateService.getCurrentSession(true);
			val criteria = session.createCriteria(classOf[University]);
			val sexes = criteria.list().asInstanceOf[java.util.List[Sex]].asScala.toArray;
			hibernateService.closeSessionIfNecessary(session);
			sexes;
	}
	def getSex(id: Int): Option[Sex] = {
			val session = hibernateService.getCurrentSession(true);
			val criteria = session.createCriteria(classOf[University])
					.add(Restrictions.eq(Sex.ID,id));
			val sex = criteria.uniqueResult().asInstanceOf[Sex];
			hibernateService.closeSessionIfNecessary(session);
			Option(sex);
	}
	def getSkills(): Array[Skill] = {
			val session = hibernateService.getCurrentSession(true);
			val criteria = session.createCriteria(classOf[Skill]);
			val skills = criteria.list().asInstanceOf[java.util.List[Skill]].asScala.toArray;
			hibernateService.closeSessionIfNecessary(session);
			skills;
	}
	def getSkillCategories(): Array[SkillCategory] = {
			val session = hibernateService.getCurrentSession(true);
			val criteria = session.createCriteria(classOf[SkillCategory]);
			val skillCategories = criteria.list().asInstanceOf[java.util.List[SkillCategory]].asScala.toArray;
			hibernateService.closeSessionIfNecessary(session);
			skillCategories;
	}
	def getSkillCategory(id: Int): Option[SkillCategory] = {
			val session = hibernateService.getCurrentSession(true);
			val criteria = session.createCriteria(classOf[SkillCategory])
					.add(Restrictions.eq(SkillCategory.ID, id));
			val skillCategory = criteria.uniqueResult().asInstanceOf[SkillCategory];
			hibernateService.closeSessionIfNecessary(session);
			Option(skillCategory);
	}
	def getSkillsByCategory(categoryId: Int): Array[Skill] = {
			val session = hibernateService.getCurrentSession(true);
			val criteria = session.createCriteria(classOf[Skill])
					.add(Restrictions.eq(Skill.SKILL_CATEGORY_ID, categoryId));
			val skills = criteria.list().asInstanceOf[java.util.List[Skill]].asScala.toArray;
			hibernateService.closeSessionIfNecessary(session);
			skills;
	}
	def getSkillsByCategory(category: SkillCategory): Array[Skill] = {
				val session = hibernateService.getCurrentSession(true);
			val criteria = session.createCriteria(classOf[Skill])
					.add(Restrictions.eq(Skill.SKILL_CATEGORY_ID, category.getId()));
			val skills = criteria.list().asInstanceOf[java.util.List[Skill]].asScala.toArray;
			hibernateService.closeSessionIfNecessary(session);
			skills;
	}
	def login(email: String, password: String): Boolean = {
			val user = Dao.getUser(email).getOrElse(return false);
			val passwordsMatch = PasswordHash.validatePassword(password, user.getPassword());
			passwordsMatch;

	}
	def register(email: String, password: String): Option[User] = {
			val user = new User();
			user.setEmail(email);
			val hashedPassword = PasswordHash.createHash(password);
			user.setPassword(hashedPassword);
			val session = hibernateService.getCurrentSession(true);
			val transaction = session.beginTransaction();
			val id = session.save(user).toString.toInt;
			transaction.commit();
			val newUserOpt = getUser(id);
			hibernateService.closeSessionIfNecessary(session);
			//some activate user logic
			newUserOpt;

	}
	def addSessionTokenToUser(userId: Int): Option[LoginSession] = {
			val user = getUser(userId).getOrElse(return None);
			val loginSession = new LoginSession();
			loginSession.setUser(user);
			val sessionToken = PasswordHash.createRandomString(SessionHashByteSize);
			loginSession.setToken(sessionToken);
			val session = hibernateService.getCurrentSession(true);
			val transaction = session.beginTransaction();
			session.save(loginSession);
			transaction.commit();
			session.close();
			getLoginSession(sessionToken);
	}
	def deleteSessionToken(token: String): Boolean = {
			val loginSession = getLoginSession(token).getOrElse(return false);
			val session = hibernateService.getCurrentSession(true);
			val transaction = session.beginTransaction();
			session.delete(loginSession);
			transaction.commit();
			session.close();
			true;
			;	}


}	