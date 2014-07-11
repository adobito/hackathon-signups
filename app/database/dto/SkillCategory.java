package database.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="skill_category")
public class SkillCategory {

	public static final String ID = "id";
	public static final String NAME = "name";
	private Integer id;
	private String name;


	public SkillCategory() {
	}
	@Id
	@Column(name = "skill_category_id")
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

}
