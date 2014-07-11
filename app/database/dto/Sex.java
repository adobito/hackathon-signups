package database.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sex")
public class Sex {

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


}
