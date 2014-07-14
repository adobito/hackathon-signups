package database.json;

import com.google.gson.Gson;

import database.dto.SkillCategory;

public class SkillCategoryJson implements Json {

	private Integer id;
	private String name;

	public SkillCategoryJson(SkillCategory skillCategory) {
		this.id = skillCategory.getId();
		this.name = skillCategory.getName();
	}
	public SkillCategoryJson() {
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
		Gson gson  = new Gson();
		return gson.toJson(this);
	}

}
