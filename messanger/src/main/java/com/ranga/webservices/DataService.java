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
	
	public Profile addProfile(Profile pprofileObj) {
		List<Profile> list = getProfiles();
		if (pprofileObj.getId() == -1 || pprofileObj.getId() == 0) {
			int id = list.size() + 1;
			pprofileObj.setId(id);
		}
		
		list.add(pprofileObj);
		
		return pprofileObj;
	}

	public Profile updateProfile(Profile profileObj) {
		Profile lprofile = getProfileById(String.valueOf(profileObj.getId()));
		
		if (profileObj.getFullName() != null && !profileObj.getFullName().isEmpty()) {
			lprofile.setFullName(profileObj.getFullName());
		}
		
		if (profileObj.getUsername() != null && !profileObj.getUsername().isEmpty()) {
			lprofile.setUsername(profileObj.getUsername());
		}
		
		return profileObj;
	}
	
	public Profile deleteProfileById(String id) {
		List<Profile> list = getProfiles();
		Profile profile = null;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId() == Integer.parseInt(id)) {
				profile = list.remove(i);
				break;
			}
		}

		return profile;
	}

	
}
