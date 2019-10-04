package com.rest.app.messenger.subresources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.rest.app.messenger.models.Comment;
import com.rest.app.messenger.services.CommentService;

@Path("/")
@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
public class CommentResource {
	private CommentService commentService = new CommentService();

	@GET
	public List<Comment> getAllComments(@PathParam("messageId") Long messageId) {
		return commentService.getAllComments(messageId);
	}

	@GET
	@Path("{commentId}")
	public Comment getCommentById(@PathParam("commentId") Long commentId,
			@PathParam("messageId") Long messageId) {
		return commentService.getComment(messageId, commentId);
	}

	@POST
	public Comment addComment(Comment comment,
			@PathParam("messageId") Long messageId) {

		return commentService.addComment(messageId, comment);
	}

	@PUT
	@Path("/commentId")
	public Comment updateComment(@PathParam("messageId") Long messageId,
			@PathParam("commentId") Long commentId, Comment comment) {
		comment.setId(commentId);
		return commentService.updateComment(messageId, comment);
	}
	
	@DELETE
	@Path("/commentId")
	public Comment deleteComment(@PathParam("messageId") Long messageId,@PathParam("commentId") Long commentId)
	{return commentService.removeComment(messageId, commentId);}
	
}
