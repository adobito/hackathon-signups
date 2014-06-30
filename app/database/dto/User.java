package database.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String USER_ID = "userId";
	public static final String EMAIL = "email";
	public static final String PASSWORD = "password";
	public static final String IS_ACTIVE = "isActive";
	public static final String USER_INFO = "userInfo";
	public static final String LOGIN_SESSIONS = "loginSessions";
	public static final String RESUMES = "resumes";
	public static final String EVENT_ATTENDANCES = "eventAttendances";
	
	private Integer userId;
	private String email;
	private String password;
	private boolean isActive;
	private UserInfo userInfo;
	private List<LoginSession> loginSessions;
	private List<Resume> resumes;
	private List<EventAttendance> eventAttendances;
	
	public User() {
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
	@Column(name = "email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name = "password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name = "is_active")
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	@OneToOne
	@JoinColumn(name = "user_id")
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
	@OneToMany
	@JoinColumn(name = "user_id")
	public List<LoginSession> getLoginSessions() {
		return loginSessions;
	}
	public void setLoginSessions(List<LoginSession> loginSessions) {
		this.loginSessions = loginSessions;
	}
	
	@OneToMany
	@JoinColumn(name = "resume_id")
	public List<Resume> getResumes() {
		return resumes;
	}
	public void setResumes(List<Resume> resumes) {
		this.resumes = resumes;
	}
	@OneToMany
	@JoinColumn(name = "user_id")
	public List<EventAttendance> getEventAttendances() {
		return eventAttendances;
	}
	public void setEventAttendances(List<EventAttendance> eventAttendances) {
		this.eventAttendances = eventAttendances;
	}

}
