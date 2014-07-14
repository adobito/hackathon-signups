package database.json;

import com.google.gson.Gson;

import database.dto.ShirtSize;

public class ShirtSizeJson implements Json {

	private Integer id;
	private String name;
	private String shortName;
	
	public ShirtSizeJson(ShirtSize shirtSize) {
		this.id = shirtSize.getId();
		this.name = shirtSize.getName();
		this.shortName = shirtSize.getShortName();
	}
	public ShirtSizeJson() {
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


	public String getShortName() {
		return shortName;
	}


	public void setShortName(String shortName) {
		this.shortName = shortName;
	}


	@Override
	public String toJsonString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

}
