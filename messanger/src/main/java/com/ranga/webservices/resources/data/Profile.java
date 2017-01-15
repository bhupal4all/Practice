package com.ranga.webservices.resources.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Profile {
	int id;
	String username;
	String fullName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Profile() {
		super();
	}

	public Profile(int id, String username, String fullName) {
		super();
		this.id = id;
		this.username = username;
		this.fullName = fullName;
	}

	@Override
	public String toString() {
		return "Profile [id=" + id + ", username=" + username + ", fullName="
				+ fullName + "]";
	}

}
