package com.rest.app.messenger.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.rest.app.messenger.db.Stupid_database;
import com.rest.app.messenger.models.Message;

public class MessageService {
	private Map<Long, Message> messages = Stupid_database.getMessages();

	public MessageService() {
		messages.put(1L, new Message(1L, "test1", "author1"));
		messages.put(2L, new Message(2L, "test2", "autho2"));
	}

	public List<Message> getAllMessages() {
		return new ArrayList<Message>(messages.values());
	};

	public List<Message> getAllMessagesForYear(int year) {
		List<Message> result = new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
		for (Message mess : messages.values()) {
			calendar.setTime(mess.getDate());
			if (calendar.get(Calendar.YEAR) == year)
				result.add(mess);
		}
		return result;
	}

	public List<Message> getAllMessagesPaginated(int start, int size) {
		List<Message> result = new ArrayList<>(messages.values());
		return result.subList(start, start + size);
	}

	public Message getMessage(Long id) {
		return messages.get(id);
	}

	public Message addMessage(Message newMessage) {
		newMessage.setId(Long.valueOf(messages.size() + 1));
		messages.put(newMessage.getId(), newMessage);
		return newMessage;

	}

	public Message updateMessage(Message message) {
		if (message.getId() <= 0)
			return null;
		messages.put(message.getId(), message);
		return message;
	}

	public Message deleteMessage(Long messageId) {
		return messages.remove(messageId);
	}
}
