package com.emerzonic.SpringApp.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name="user")
public class User {
	
	@Id
	@NotNull
	@NotEmpty
	@Column(name="username", unique = true)
	private String username;


	@NotNull
	@NotEmpty
    @Size(min = 4)
	@Column(name="password")
	private String password;

//
//	@ManyToMany(cascade=CascadeType.ALL)
//	@JoinColumn(name="user_role")
//	private List<Role> roles;


	public User() {}

	public User(@NotNull @NotEmpty String username, @NotNull @NotEmpty String password) {
		this.username = username;
		this.password = password;
	}

//	public int getId() {
//		return id;
//	}

//	public void setId(int id) {
//		this.id = id;
//	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

//	public List<Role> getRoles() {
//		return roles;
//	}
//
//	public void setRoles(List<Role> roles) {
//		this.roles = roles;
//	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}	
}

