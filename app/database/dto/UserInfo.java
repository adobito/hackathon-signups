package database.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "userinfo")
public class UserInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String USER = "user";
	public static final String NAME = "name";
	public static final String GITHUB = "github";
	public static final String LINKEDIN = "linkedin";
	public static final String RESUME = "resume";
	public static final String SEX = "sex";
	public static final String SHIRT_SIZE = "shirtSize";
	
	private User user;
	private String name;
	private String github;
	private String linkedin;
	private Resume resume;
	private String sex;
	private ShirtSize shirtSize;
	
	
	public UserInfo() {
	}

	@Id
	@OneToOne
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "github")
	public String getGithub() {
		return github;
	}


	public void setGithub(String github) {
		this.github = github;
	}

	@Column(name = "linkedin")
	public String getLinkedin() {
		return linkedin;
	}


	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}
	@OneToOne
	@JoinColumn(name = "resume_id")
	public Resume getResume() {
		return resume;
	}
	
	public void setResume(Resume resume) {
		this.resume = resume;
	}


	@Column(name = "sex")
	public String getSex() {
		return sex;
	}



	public void setSex(String sex) {
		this.sex = sex;
	}

	@OneToOne
	@JoinColumn(name = "shirt_size_id")
	public ShirtSize getShirtSize() {
		return shirtSize;
	}


	public void setShirtSize(ShirtSize shirtSize) {
		this.shirtSize = shirtSize;
	}
	
	
	
	

}
