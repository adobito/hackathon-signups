package database.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "session")
public class LoginSession implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String TOKEN = "token";
	public static final String USER = "user";
	public static final String CREATED_TIMESTAMP = "createdTimestamp";
	
	private String token;
	private User user;
	private Timestamp createdTimestamp;

	public LoginSession() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@Column(name = "token")
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@ManyToOne
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	@Column(name = "created_timestamp")
	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}



}
