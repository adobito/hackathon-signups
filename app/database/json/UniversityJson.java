package database.json;

import database.dto.University;

public class UniversityJson {

	private Integer id;
	private String name;
	
	
	public UniversityJson(University university) {
		this.id = university.getUniversityId();
		this.name = university.getName();
	}
	
	public UniversityJson(Integer id, String name) {
		this.id = id;
		this.name = name;
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
	
	
	
}
