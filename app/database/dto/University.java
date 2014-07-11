package database.dto;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "university")
public class University implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String UNIVERSITY_ID = "universityId";
	public static final String NAME = "name";
	
	private Integer universityId;
	private String name;

	public University() {
		// TODO Auto-generated constructozr stub
	}

	@Id
	@Column(name = "university_id")
	public Integer getUniversityId() {
		return universityId;
	}

	public void setUniversityId(Integer universityId) {
		this.universityId = universityId;
	}
	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
