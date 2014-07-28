package database.dao

import com.google.common.cache.CacheBuilder
import com.google.common.cache.CacheLoader
import database.dto.Event
import database.Database
import database.HibernateService
import org.hibernate.criterion.Restrictions
import com.google.common.cache.LoadingCache
import java.util.Date
import database.dto.EventAttendance
import database.dto.User
import scala.collection.JavaConversions._

object EventDao {
  private val hibernateService = Database.HibernateService;
  
	def getEvent(eventId: Int): Option[Event] = {
			val session = hibernateService.getCurrentSession(true);
			val criteria = session.createCriteria(classOf[Event])
					.add(Restrictions.eq(Event.EVENT_ID, eventId))
					.setCacheable(true);
			val event = criteria.uniqueResult().asInstanceOf[Event];
			hibernateService.closeSessionIfNecessary(session);
			Option(event)
	}
		def getEventAttendees(eventId: Int): List[EventAttendance] = {
			val session = hibernateService.getCurrentSession(true);
			val criteria = session.createCriteria(classOf[EventAttendance],"attendance")
					.createAlias("attendance.event", "event")
					.add(Restrictions.eq("event.eventId", eventId));
			val eventAttendanceList = criteria.list().asInstanceOf[java.util.List[EventAttendance]].toList;
			hibernateService.closeSessionIfNecessary(session);
			eventAttendanceList;
	}
	def getEventAttendee(eventId: Int, userId: Int): Option[EventAttendance] = {
			val session = hibernateService.getCurrentSession(true);
			val criteria = session.createCriteria(classOf[EventAttendance])
					.add(Restrictions.eq(EventAttendance.EVENT_ID,eventId))
					.add(Restrictions.eq(EventAttendance.USER_ID, userId))
					.setCacheable(true);
			val eventAttendee = criteria.uniqueResult().asInstanceOf[EventAttendance];
			hibernateService.closeSessionIfNecessary(session);
			Option(eventAttendee);
	}
	def getEventAttendee(event: Event, user: User): Option[EventAttendance] = {
			getEventAttendee(event.getEventId(), user.getUserId());
	}
	def addEvent(name: String, startTime: Date, endTime: Date): Option[Event] = {
			val event = new Event();
			event.setName(name);
			event.setStartTime(startTime);
			event.setEndTime(endTime);
			addEvent(event);
	}
	def addEvent(event: Event): Option[Event] = {
			None;
	}
	def addEventAttendee(eventId: Int, userId: Int): Option[EventAttendance] = {
	  val event = getEvent(eventId).getOrElse(return None);
	  val user = UserDao.getUser(userId).getOrElse(return None);
	  addEventAttendee(event, user);

	}
	def addEventAttendee(event: Event, user: User): Option[EventAttendance] = {
			None;
	}
	def updateEvent(id: Int,name: Option[String], startTime: Option[Date], endTime: Option[Date]): Option[Event] = {
			val event = getEvent(id).getOrElse(return None);
			event.setName(name.getOrElse(event.getName()));
			event.setStartTime(startTime.getOrElse(event.getStartTime()));
			event.setEndTime(endTime.getOrElse(event.getEndTime()));
			updateEvent(event);
	}
	def updateEvent(event: Event): Option[Event] = {
			val session = hibernateService.getCurrentSession(true);
			val transaction = session.beginTransaction();
			session.update(event);
			transaction.commit();
			session.close();
			Option(event); //will never be None
	}
}