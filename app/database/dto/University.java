package database.dto;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import database.json.Json;
import database.json.UniversityJson;


@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE, region = "events") 
@Table(name = "university")
public class University implements Serializable, Jsonable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String UNIVERSITY_ID = "universityId";
	public static final String NAME = "name";
	
	private Integer universityId;
	private String name;

	public University() {
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

	@Override
	public Json toJson() {
		return new UniversityJson(this);
	}
	
	
}
