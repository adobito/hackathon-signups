package database.json;

import com.google.gson.Gson;

import database.dto.Skill;

public class SkillJson implements Json{
	private Integer skillId;
	private String category;
	private String skillName;
	
	public SkillJson(Skill skill) {
		this.skillId = skill.getId();
		this.category = skill.getSkillCategory().getName();
		this.skillName = skill.getName();
	}
	
	public SkillJson(Integer skillId, String category, String name) {
		this.skillId = skillId;
		this.category = category;
		this.skillName = name;
	}
	public Integer getSkillId() {
		return skillId;
	}
	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	@Override
	public String toJsonString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
	
	
}
