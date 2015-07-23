package database.dao

import java.util.Date

import scala.collection.JavaConversions.asScalaBuffer

import org.hibernate.criterion.Restrictions

import database.Database
import database.dto.Event
import database.dto.EventAttendance
import database.dto.User

object EventDao {
  private val hibernateService = Database.HibernateService;
  
  def getEventsEndingAfter(date: Date, startingAt: Int, maxResults: Int): List[Event] = {
    	val session = hibernateService.getCurrentSession(true);
			val criteria = session.createCriteria(classOf[Event])
					.add(Restrictions.gt(Event.END_TIME, date))
					.setMaxResults(maxResults)
					.setFirstResult(startingAt)
					.setCacheable(true);
			val events = criteria.list().toList.asInstanceOf[List[Event]];
			hibernateService.closeSessionIfNecessary(session);
			events;
  }
    def getEventsStartingBefore(date: Date): List[Event] = {
    	val session = hibernateService.getCurrentSession(true);
			val criteria = session.createCriteria(classOf[Event])
					.add(Restrictions.lt(Event.START_TIME, date))
					.setCacheable(true);
			val events = criteria.list().toList.asInstanceOf[List[Event]];
			hibernateService.closeSessionIfNecessary(session);
			events;
  }
	def getEvent(eventId: Int): Option[Event] = {
			val session = hibernateService.getCurrentSession(true);
			val criteria = session.createCriteria(classOf[Event])
					.add(Restrictions.eq(Event.ID, eventId))
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
			val eventAttendanceList = criteria.list().toList.asInstanceOf[List[EventAttendance]]
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
			getEventAttendee(event.getEventId(), user.getId());
	}
	def createEvent(name: String, startTime: Date, endTime: Date, user: User): Option[Event] = {
			val event = new Event();
			event.setName(name);
			event.setStartTime(startTime);
			event.setEndTime(endTime);
			event.setEventOwner(user);
			createEvent(event);
	}
	def createEvent(event: Event): Option[Event] = {
		val session = hibernateService.getCurrentSession(true);
		val txn = session.beginTransaction();
		session.save(event);
		txn.commit();
		hibernateService.closeSessionIfNecessary(session);
		Option(event);
	}
	def attendEvent(eventId: Int, userId: Int): Option[EventAttendance] = {
	  val event = getEvent(eventId).getOrElse(return None);
	  val user = UserDao.getUser(userId).getOrElse(return None);
	  attendEvent(event, user);

	}
	def attendEvent(event: Event, user: User): Option[EventAttendance] = {
		val session = hibernateService.getCurrentSession(true);
		val txn = session.beginTransaction();
		val eventAttendance = new EventAttendance();
		eventAttendance.setEvent(event);
		eventAttendance.setUser(user);
		session.save(eventAttendance);
		txn.commit();
		hibernateService.closeSessionIfNecessary(session);
		Option(eventAttendance);
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