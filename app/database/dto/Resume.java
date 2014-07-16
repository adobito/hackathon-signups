package database.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name = "resume")
public class Resume implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String RESUME_ID = "resumeId";
	public static final String PATH = "path";
	public static final String USER = "user"; 
	public static final String CREATED_TIMESTAMP = "createdTimestamp";
	
	private Integer resumeId;
	private String path;
	private Credentials user;
	private Timestamp createdTimestamp;
	
	public Resume() {
		// TODO Auto-generated constructor stub
	}
	
	@Id
	@Column(name = "resume_id")
	public Integer getResumeId() {
		return resumeId;
	}

	public void setResumeId(Integer resumeId) {
		this.resumeId = resumeId;
	}
	@Column(name = "path")
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public Credentials getUser() {
		return user;
	}

	public void setUser(Credentials user) {
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
