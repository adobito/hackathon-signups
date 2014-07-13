package database.json;

import java.util.Date;

import database.dto.Event;

public class EventJson {

	private Integer id;
	private String name;
	private Date startTime;
	private Date endTime;
	
	public EventJson(Event event) {
		this.id = event.getEventId();
		this.name = event.getName();
		this.startTime = event.getStartTime();
		this.endTime = event.getEndTime();
	}
	public EventJson() {
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	
}
