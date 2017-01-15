package com.ranga.webservices.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ranga.webservices.DataService;
import com.ranga.webservices.resources.data.Profile;

@Path("/profile")
public class ProfileResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Profile> getProfiles() {
		List<Profile> pList = DataService.getInstance().getProfiles();
		return pList;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{profileId}")
	public Profile getProfile(@PathParam("profileId")String profileId)
	{
		return DataService.getInstance().getProfileById(profileId);
	}
	
}
