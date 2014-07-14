package database.json;

import com.google.gson.Gson;

import database.dto.Sex;

public class SexJson implements Json {
	private Integer id;
	private String name;
	
	public SexJson(Sex sex) {
		this.id = sex.getId();
		this.name = sex.getName();
	}
	public SexJson() {
		super();
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
