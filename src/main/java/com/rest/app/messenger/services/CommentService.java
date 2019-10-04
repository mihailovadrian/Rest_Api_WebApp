package com.rest.app.messenger.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.rest.app.messenger.db.Stupid_database;
import com.rest.app.messenger.models.Comment;
import com.rest.app.messenger.models.ErrorMessage;
import com.rest.app.messenger.models.Message;

public class CommentService {
	private Map<Long, Message> messages = Stupid_database.getMessages();

	public List<Comment> getAllComments(Long messageId) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return new ArrayList<Comment>(comments.values());
	}

	public Comment getComment(Long messageId, Long commentId) {
		Message message = messages.get(messageId);
		ErrorMessage errorMessage = new ErrorMessage("Not found",
				"www.google.com", 404);
		Response response = Response.status(Status.NOT_FOUND)
				.entity(errorMessage).build();

		if (message == null)
			throw new WebApplicationException(response);

		Map<Long, Comment> comments = messages.get(messageId).getComments();

		if (comments.get(commentId) == null)
			throw new WebApplicationException(response);
		return comments.get(commentId);
	}

	public Comment addComment(Long messageId, Comment newComment) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		newComment.setId(Long.valueOf(comments.size() + 1));
		comments.put(newComment.getId(), newComment);
		return newComment;
	}

	public Comment updateComment(Long messageId, Comment comment) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		if (comment.getId() <= 0)
			return null;
		comments.put(comment.getId(), comment);
		return comment;
	}

	public Comment removeComment(Long messageId, Long commentId) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return comments.remove(commentId);
	}
}
