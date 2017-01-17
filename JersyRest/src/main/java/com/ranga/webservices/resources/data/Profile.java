package com.ranga.webservices.resources.data;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Profile {
	int id = -1;
	String username;
	String fullName;
	List<Link> links = new ArrayList<Link>();

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

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
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
				+ fullName + ", links=" + links + "]";
	}

}
