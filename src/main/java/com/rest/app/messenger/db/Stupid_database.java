package com.rest.app.messenger.db;

import java.util.HashMap;

import java.util.Map;

import com.rest.app.messenger.models.Message;
import com.rest.app.messenger.models.Profile;

public class Stupid_database {
	private static Map<Long, Message> messages = new HashMap<>();
	private static Map<String, Profile> profiles = new HashMap<>();

	public static Map<Long, Message> getMessages() {
		return messages;
	}

	public static Map<String, Profile> getProfiles() {
		return profiles;
	}

}
