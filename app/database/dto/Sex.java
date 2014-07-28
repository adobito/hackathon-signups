package database.dto;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import database.json.Json;
import database.json.SexJson;

@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE, region = "events") 
@Table(name = "sex")
public class Sex implements Jsonable {

	public static final String ID = "sexId";
	public static final String NAME = "name";
	private Integer id;
	private String name;


	public Sex() {
	}
	@Id
	@Column(name = "sex_id")
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
	@Override
	public Json toJson() {
		return new SexJson(this);
	}


}
