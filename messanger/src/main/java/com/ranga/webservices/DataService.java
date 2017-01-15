package com.ranga.webservices;

import java.util.ArrayList;
import java.util.List;

import com.ranga.webservices.resources.data.Profile;

public class DataService {
	static List<Profile> pList = new ArrayList<Profile>();
	static DataService INSTANCE = null;

	static {
		pList.add(new Profile(1, "bhupal4all", "Bhupal"));
	}

	public static DataService getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new DataService();
		}

		return INSTANCE;
	}

	public List<Profile> getProfiles() {
		return pList;
	}

	public Profile getProfileById(String id) {
		List<Profile> list = getProfiles();

		for (int i = 0; i < list.size(); i++) {
			Profile p = list.get(i);
			if (p.getId() == Integer.parseInt(id)) {
				return p;
			}
		}

		return null;
	}
	
	public Profile addProfile(Profile p) {
		List<Profile> list = getProfiles();
		if (p.getId() == -1 || p.getId() == 0) {
			int id = list.size() + 1;
			p.setId(id);
		}
		
		list.add(p);
		
		return p;
	}
}
