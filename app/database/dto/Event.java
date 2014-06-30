package database.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "event")
public class Event implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String EVENT_ID = "eventId";
	public static final String NAME = "name";
	public static final String START_TIME = "startTime";
	public static final String END_TIME = "endTime";
	public static final String ATTENDANCE = "attendance";
	
	private Integer eventId;
	private String name;
	private Date startTime;
	private Date endTime;
	private List<EventAttendance> attendance;
	
	public Event() {
		// TODO Auto-generated constructor stub
	}
	
	@Id
	@Column(name = "event_id")
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

	@OneToMany
	@JoinColumn(name = "event_id")
	public List<EventAttendance> getAttendance() {
		return attendance;
	}

	public void setAttendance(List<EventAttendance> attendance) {
		this.attendance = attendance;
	}


	
	
	
	

}
