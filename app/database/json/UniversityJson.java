package database.json;

import com.google.gson.Gson;

import database.dto.University;

public class UniversityJson implements Json {

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
	@Override
	public String toJsonString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
	
	
	
}
