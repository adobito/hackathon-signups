package database.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "event_attendees")
public class EventAttendance implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String USER = "user";
	public static final String USER_ID = USER + "." + User.ID;
	public static final String EVENT = "event";
	public static final String EVENT_ID = EVENT + "." + Event.ID;
	
	private User user;
	private Event event;
	private Timestamp signupTimestamp;
	
	public EventAttendance() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Id
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "event_id")
	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
	@Column(name = "signup_timestamp")
	public Timestamp getSignupTimestamp() {
		return signupTimestamp;
	}

	public void setSignupTimestamp(Timestamp signupTimestamp) {
		this.signupTimestamp = signupTimestamp;
	}
	

}
