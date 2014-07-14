package database.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	public static final String EVENT_ID = EVENT + "." + Event.EVENT_ID;
	public static final String NAME = "name";
	public static final String GITHUB = "github";
	public static final String LINKEDIN = "linkedin";
	public static final String RESUME = "resume";
	public static final String SEX = "sex";
	public static final String SHIRT_SIZE = "shirtSize";
	
	private User user;
	private Event event;
	private String name;
	private String github;
	private String linekdIn;
	private Resume resume;
	private University university;
	private Sex sex;
	private ShirtSize shirtSize;
	private Timestamp signup_timestamp;
	private Timestamp modified_timestamp;
	
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

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "github")
	public String getGithub() {
		return github;
	}

	public void setGithub(String github) {
		this.github = github;
	}
	@Column(name = "linkedin")
	public String getLinekdIn() {
		return linekdIn;
	}

	public void setLinekdIn(String linekdIn) {
		this.linekdIn = linekdIn;
	}
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "resume_id")
	public Resume getResume() {
		return resume;
	}

	public void setResume(Resume resume) {
		this.resume = resume;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "university_id")
	public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sex_id")
	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "shirt_size_id")
	public ShirtSize getShirtSize() {
		return shirtSize;
	}

	public void setShirtSize(ShirtSize shirtSize) {
		this.shirtSize = shirtSize;
	}

	@Column(name = "signup_timestamp")
	public Timestamp getSignup_timestamp() {
		return signup_timestamp;
	}

	public void setSignup_timestamp(Timestamp signup_timestamp) {
		this.signup_timestamp = signup_timestamp;
	}

	@Column(name = "modified_timestamp")
	public Timestamp getModified_timestamp() {
		return modified_timestamp;
	}

	public void setModified_timestamp(Timestamp modified_timestamp) {
		this.modified_timestamp = modified_timestamp;
	}
	

}
