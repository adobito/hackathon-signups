package database.dao

import database.dto.User
import database.dto.LoginSession
import cryptography.PasswordHash
import database.Database
import org.hibernate.criterion.Restrictions
import scala.collection.JavaConversions._

object UserDao {
  private val hibernateService = Database.HibernateService;

	def getUser(userId: Int): Option[User] = {
			val session = hibernateService.getCurrentSession(true);
			val criteria = session.createCriteria(classOf[User])
					.add(Restrictions.eq(User.ID, userId))
					.setCacheable(true);;
					val user = criteria.uniqueResult().asInstanceOf[User];
					hibernateService.closeSessionIfNecessary(session);
					Option(user);
	}
	def getUser(email: String): Option[User] = {
			val session = hibernateService.getCurrentSession(true);
			val criteria = session.createCriteria(classOf[User])
					.add(Restrictions.eq(User.EMAIL, email))
					.setCacheable(true);
			val user = criteria.uniqueResult().asInstanceOf[User];
			hibernateService.closeSessionIfNecessary(session);
			Option(user);
	}
	def getUsers(startingAt: Int, maxAmount: Int): List[User] = {
			val session = hibernateService.getCurrentSession(true);
			val criteria = session.createCriteria(classOf[User]);
			criteria.setMaxResults(maxAmount).setFirstResult(startingAt);
			val usersList = criteria.list();
			val users = usersList.asInstanceOf[java.util.List[User]].toList;
			hibernateService.closeSessionIfNecessary(session);
			users;
	}


	def addUser(email: String, password: String): Option[User] = {
			val user = new User();
			user.setEmail(email);
			user.setPasswordHash(PasswordHash.createHash(password));
			addUser(user);
	}
	def addUser(user: User): Option[User] = {
	  None;
	}

	def updateUser(user: User) {
		val session = hibernateService.getCurrentSession(true);
		val transaction = session.beginTransaction();
		session.update(user);
		transaction.commit();
		hibernateService.closeSessionIfNecessary(session);
	}

	def getLoginSession(token: String):Option[LoginSession] = {
			val session = hibernateService.getCurrentSession(true);
			val criteria = session.createCriteria(classOf[LoginSession])
					.add(Restrictions.eq(LoginSession.TOKEN, token))
					.setCacheable(true);
			val loginSession = criteria.uniqueResult().asInstanceOf[LoginSession];
			hibernateService.closeSessionIfNecessary(session);
			Option(loginSession);
	}

	def getLoginSessions(userId: Int): List[LoginSession] = {
			val user = getUser(userId).getOrElse(return Nil);
			getLoginSessions(user);
	}
	def getLoginSessions(email: String): List[LoginSession] = {
			val user = getUser(email).getOrElse(return Nil);
			getLoginSessions(user);
	}
	def getLoginSessions(user: User): List[LoginSession] = {
			Nil;
	}
	def deleteSessionToken(token: String): Boolean = {
			val loginSession = getLoginSession(token).getOrElse(return false);
			val session = hibernateService.getCurrentSession(true);
			val transaction = session.beginTransaction();
			session.delete(loginSession);
			transaction.commit();
			session.close();
			true;
	}

//	def getSkillsByUser(userId:Int): List[Skill] = {
//		val user = getUser(userId).getOrElse(return List[Skill]());
//		val skills = user.getSkills();
//		skills.asScala.toList;
//	}
//
//	def getPermissionsGroups(userId: Int): List[PermissionsGroup] = {
//			val user = getUser(userId).getOrElse( return Nil);
//			val permissionsGroupsList = user.getPermissionsGroups().asScala.toList;
//			permissionsGroupsList;
//
//	}

	def login(email: String, password: String): Boolean = {
			val user = getUser(email).getOrElse(return false);
			val passwordsMatch = PasswordHash.validatePassword(password, user.getPasswordHash());
			passwordsMatch;

	}

	def register(email: String, password: String): Option[User] = {
			val user = new User();
			user.setEmail(email);
			val hashedPassword = PasswordHash.createHash(password);
			user.setPasswordHash(hashedPassword);
			val session = hibernateService.getCurrentSession(true);
			val transaction = session.beginTransaction();
			session.save(user);
			transaction.commit();
			hibernateService.closeSessionIfNecessary(session);
			Option(user);

	}

	def addSessionTokenToUser(userId: Int): Option[LoginSession] = {
		None;
	}
}
