package com.rest.app.messenger.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.rest.app.messenger.db.Stupid_database;
import com.rest.app.messenger.models.Profile;

public class ProfileService {
	private Map<String, Profile> profiles = Stupid_database.getProfiles();

	public ProfileService() {
		profiles.put("Mihailov",new Profile(1l,"adi","adi","Mihailov"));
		// profiles.put(2L,new Profile(2L,"test2","autho2"));
	}

	public List<Profile> allProfiles() {
		return new ArrayList<Profile>(profiles.values());
	};

	public Profile getProfile(String profileName) {
		return profiles.get(profileName);
	}

	public Profile addProfile(Profile newProfile) {
		newProfile.setId(Long.valueOf(profiles.size() + 1));
		profiles.put(newProfile.getProfileName(), newProfile);
		return newProfile;

	}

	public Profile updateProfile(Profile profile)
	{	if(profile.getProfileName().isEmpty())
			return null;
		profiles.put(profile.getProfileName(),profile);
		return profile;
	}

	public Profile deleteProfile(String profileName) {
		return profiles.remove(profileName);
	}
}
