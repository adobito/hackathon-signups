package database.dto;

import database.dao.UserDao;
import database.json.Json;
import database.json.UserJson;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "user")
public class User implements Serializable, Jsonable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String EMAIL = "email";
	public static final String GITHUB = "github";
	public static final String LINKEDIN = "linkedin";
	public static final String TWITTER = "twitter";
	public static final String LOGIN_SESSIONS = "loginSessions";
	public static final String EVENT_ATTENDANCES = "eventAttendances";

	private Integer id;
	private String name;
	private String email;
	private String github;
	private String linkedin;
	private String twitter;
	private String website;
	private String passwordHash;
	private List<LoginSession> loginSessions;




	public User() {
		this.id = null;
		this.name = "";
		this.email = "";
		this.github = "";
		this.linkedin = "";
		this.twitter = "";
		this.website = "";
		this.loginSessions = null;
		this.passwordHash = null;
	}



	@Id
	@GeneratedValue
	@Column(name = "id")
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
	@Column(name = "password_hash")
	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public List<LoginSession> getLoginSessions() {
		return loginSessions;
	}
	public void setLoginSessions(List<LoginSession> loginSessions) {
		this.loginSessions = loginSessions;
	}

//	@OneToMany(fetch = FetchType.LAZY)
//	@JoinColumn(name = "user_id")
//	public List<EventAttendance> getEventAttendances() {
//		return eventAttendances;
//	}
//	public void setEventAttendances(List<EventAttendance> eventAttendances) {
//		this.eventAttendances = eventAttendances;
//	}
//	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JoinTable(name = "user_skill", joinColumns = {
//			@JoinColumn(name = "user_id", nullable = false, updatable = false) },
//			inverseJoinColumns = { @JoinColumn(name = "skill_id",
//			nullable = false, updatable = false) })
//	public List<Skill> getSkills() {
//		return skills;
//	}
//
//	public void setSkills(List<Skill> skills) {
//		this.skills = skills;
//	}

//	@JoinTable(name = "permission_groups_to_users_mappings", joinColumns = {
//			@JoinColumn(name = "user_id", nullable = false, updatable = false) },
//			inverseJoinColumns = { @JoinColumn(name = "permissions_groups_id",
//			nullable = false, updatable = false) })
//	public List<PermissionsGroup> getPermissionsGroups() {
//		return permissionsGroups;
//	}
//
//	public void setPermissionsGroups(List<PermissionsGroup> permissionsGroups) {
//		this.permissionsGroups = permissionsGroups;
//	}
	public LoginSession createNewLoginSession() {
		//TODO: make this work intuitively if no userId is assigned yet.
		if(id == null) {
			return null;
		}
		return UserDao.addSessionTokenToUser(this.id).get();
	}
	@Override
	public Json toJson() {
		return new UserJson(this);
	}




}
