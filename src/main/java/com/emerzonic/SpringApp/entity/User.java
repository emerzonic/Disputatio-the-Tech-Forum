package com.emerzonic.SpringApp.entity;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="user")
public class User {
	
	@Id
	@NotNull
	@NotEmpty
	@Column(name="username", unique = true)
	private String username;


	@NotNull
    @Size(min = 4)
	@Column(name="password")
	private String password;


	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="user_roles",joinColumns = {
			@JoinColumn(name="user_username", referencedColumnName = "username")},
			inverseJoinColumns = {
				@JoinColumn(name="role_name", referencedColumnName = "name")})
	private List<Role> roles;


	public User() {}

	public User(@NotNull @NotEmpty String username, @NotNull @NotEmpty String password) {
		this.username = username;
		this.password = password;
	}


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

    public void addRole(Role newRole) {
        if (roles == null) {
            roles = new ArrayList<>();
        }
        roles.add(newRole);
    }


	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}	
}

