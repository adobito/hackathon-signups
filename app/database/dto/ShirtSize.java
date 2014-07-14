package database.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import database.json.Json;
import database.json.ShirtSizeJson;

@Entity
@Table(name = "shirt_size")
public class ShirtSize implements Serializable, Jsonable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String SHIRT_SIZE_ID = "id";
	public static final String NAME = "name";
	public static final String SHORT_NAME = "shortName";
	
	private Integer id;
	private String name;
	private String shortName;
	
	public ShirtSize() {
	}
	
	@Id
	@Column(name = "shirt_size_id")
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
	@Column(name = "short_name")
	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Override
	public Json toJson() {
		return new ShirtSizeJson(this);
	}

	
}
