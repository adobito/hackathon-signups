package database.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import database.json.EventJson;
import database.json.Json;

@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE, region = "events") 
@Table(name = "event")
public class Event implements Serializable, Jsonable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String START_TIME = "startTime";
	public static final String END_TIME = "endTime";
	public static final String ATTENDANCE = "attendance";
	public static final String EVENT_OWNER = "eventOwner";
	
	private Integer eventId;
	private String name;
	private Date startTime;
	private Date endTime;
	private User eventOwner;
	private List<EventAttendance> attendance;
	
	public Event() {
		// TODO Auto-generated constructor stub
	}
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "start_time")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Column(name = "end_time")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "event_owner")
	public User getEventOwner() {
		return eventOwner;
	}
	
	public void setEventOwner(User eventOwner) {
		this.eventOwner = eventOwner;
	}
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "event_id")
	public List<EventAttendance> getAttendance() {
		return attendance;
	}

	public void setAttendance(List<EventAttendance> attendance) {
		this.attendance = attendance;
	}


	@Override
	public Json toJson() {
		// TODO Auto-generated method stub
		return new EventJson(this);
	}

	@Override
	public String toString() {
		return "Event{" +
				"id=" + eventId +
				", name='" + name + '\'' +
				", startTime=" + startTime +
				", endTime=" + endTime +
				", eventOwner=" + eventOwner +
				", attendance=" + attendance +
				'}';
	}
}
