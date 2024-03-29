package com.rest.app.messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.rest.app.messenger.models.Profile;
import com.rest.app.messenger.services.ProfileService;
import com.rest.app.messenger.subresources.CommentResource;

@Path("/profiles")
@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
public class ProfileResource {

	private ProfileService profileService = new ProfileService();

	@GET
	public List<Profile> getProfiles() {

		return profileService.allProfiles();
	}

	@GET
	@Path("/{profileName}")
	public Profile getProfile(@PathParam("profileName") String profileName) {
		return profileService.getProfile(profileName);
	}

	@POST
	public Profile addProfile(Profile profile) {
		return profileService.addProfile(profile);
	}

	@PUT
	@Path("/{profileName}")
	public Profile updateProfile(@PathParam("profileName") String profileName, Profile profile) {
		profile.setProfileName(profileName);;
		return profileService.updateProfile(profile);
	}

	@DELETE
	@Path("/{profileName}")
	public Profile deleteProfile(@PathParam("profileName") String profileName) {
		return profileService.deleteProfile(profileName);
	}
	@GET
	@Path("/{messageId}/comments")
	public CommentResource getComments() {

		return new CommentResource();
	}

}