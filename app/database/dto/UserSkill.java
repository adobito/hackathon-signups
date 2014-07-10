package database.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_skill")
public class UserSkill implements Serializable{

	private User user;
	private Skill skill;
	
	public UserSkill() {
		// TODO Auto-generated constructor stub
	}
	@Id
	@ManyToOne
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	@Id
	@ManyToOne
	@JoinColumn(name = "skill_id")
	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	
}