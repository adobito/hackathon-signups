package database.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "permission_groups")
public class PermissionsGroup {

	private Integer id;
	private String name;
	private List<Permission> permissions;
	public PermissionsGroup() {
		super();
	}
	@Id
	@Column(name = "permissions_groups_id")
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
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "permissions_to_groups_mappings", joinColumns = { 
			@JoinColumn(name = "permission_groups_id", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "permissions_id", 
					nullable = false, updatable = false) })
	public List<Permission> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
	
	
	
	
}
