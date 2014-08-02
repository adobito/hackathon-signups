package database.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import database.json.Json;
import database.json.UserJson;

@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE, region = "users")
@Table(name = "user")
public class User implements Serializable, Jsonable{



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String ID = "userId";
	public static final String NAME = "name";
	public static final String EMAIL = "email";
	public static final String GITHUB = "github";
	public static final String LINKEDIN = "linkedin";
	public static final String TWITTER = "twitter";
	public static final String RESUME = "resume";
	public static final String SEX = "sex";
	public static final String SHIRT_SIZE = "shirtSize";
	public static final String LOGIN_SESSIONS = "loginSessions";
	public static final String RESUMES = "resumes";
	public static final String EVENT_ATTENDANCES = "eventAttendances";

	private Integer userId;
	private String name;
	private String email;
	private String github;
	private String linkedin;
	private String twitter;
	private String website;
	private Resume resume;
	private University university;
	private Sex sex;
	private ShirtSize shirtSize;
	private Credentials credentials;
	
	private List<LoginSession> loginSessions;
	private List<Resume> resumes;
	private List<EventAttendance> eventAttendances;
	private List<Skill> skills;
	private List<PermissionsGroup> permissionsGroups;




	public User() {
		this.userId = null;
		this.name = "";
		this.email = "";
		this.github = "";
		this.linkedin = "";
		this.twitter = "";
		this.resume = null;
		this.university = null;
		this.sex = null;
		this.shirtSize = null;
		this.credentials = null;
		this.loginSessions = null;
		this.resumes = null;
		this.eventAttendances = null;
		this.skills = null;
	}
	


	@Id
	@GeneratedValue
	@Column(name = "user_id")
	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	@Column(name = "name")
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
	
	@Column(name = "twitter")
	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}
	@Column(name = "website")
	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
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

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "permission_groups_to_users_mappings", joinColumns = { 
			@JoinColumn(name = "user_id", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "permissions_groups_id", 
			nullable = false, updatable = false) })
	public List<PermissionsGroup> getPermissionsGroups() {
		return permissionsGroups;
	}

	public void setPermissionsGroups(List<PermissionsGroup> permissionsGroups) {
		this.permissionsGroups = permissionsGroups;
	}

	@Override
	public Json toJson() {
		return new UserJson(this);
	}




}
