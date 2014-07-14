package database.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_skill")
public class UserSkill implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3911027831511629263L;
	public static final String USER = "user";
	public static final String USER_ID = "user." + User.ID;
	public static final String SKILL = "skill";
	public static final String SKILL_ID = "skill." + Skill.ID;
	private User user;
	private Skill skill;
	
	public UserSkill() {
		// TODO Auto-generated constructor stub
	}
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "skill_id")
	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	
}
