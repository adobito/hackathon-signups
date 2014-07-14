package database.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import database.json.Json;
import database.json.UserInfoJson;

@Entity
@Table(name = "user_info")
public class UserInfo implements Serializable, Jsonable{

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
	public static final String USER_ID = "user.userId";

	private User user;
	private String name;
	private String github;
	private String linkedin;
	private Resume resume;
	private University university;
	private Sex sex;
	private ShirtSize shirtSize;
	private List<LoginSession> loginSessions;
	private List<Resume> resumes;
	private List<EventAttendance> eventAttendances;
	private List<Skill> skills;


	public UserInfo() {
	}

	@Id
	@OneToOne(fetch = FetchType.LAZY)
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
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "resume_id")
	public Resume getResume() {
		return resume;
	}

	public void setResume(Resume resume) {
		this.resume = resume;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "university_id")
	public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="sex_id")
	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
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

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public List<LoginSession> getLoginSessions() {
		return loginSessions;
	}
	public void setLoginSessions(List<LoginSession> loginSessions) {
		this.loginSessions = loginSessions;
	}

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "resume_id")
	public List<Resume> getResumes() {
		return resumes;
	}
	public void setResumes(List<Resume> resumes) {
		this.resumes = resumes;
	}
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public List<EventAttendance> getEventAttendances() {
		return eventAttendances;
	}
	public void setEventAttendances(List<EventAttendance> eventAttendances) {
		this.eventAttendances = eventAttendances;
	}
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "user_skill", joinColumns = { 
			@JoinColumn(name = "user_id", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "skill_id", 
			nullable = false, updatable = false) })
	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	@Override
	public Json toJson() {
		return new UserInfoJson(this);
	}




}
