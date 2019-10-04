package com.rest.app.messenger.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import beans.MessageFilterBean;

import com.rest.app.messenger.exceptions.DataNotFoundException;
import com.rest.app.messenger.models.Message;
import com.rest.app.messenger.services.MessageService;
import com.rest.app.messenger.subresources.CommentResource;

@Path("/messages")
public class MessageResource {
	MessageService messageService = new MessageService();

	@GET
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public List<Message> getMessages(
			@BeanParam MessageFilterBean messageFilterBean) {
		if (messageFilterBean.getYear() > 0)
			return messageService.getAllMessagesForYear(messageFilterBean
					.getYear());
		if (messageFilterBean.getStart() > 0 && messageFilterBean.getSize() > 0)
			return messageService.getAllMessagesPaginated(
					messageFilterBean.getStart(), messageFilterBean.getSize());

		return messageService.getAllMessages();
	}

	@GET
	@Path("/{messageId}")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Message getMessage(@PathParam("messageId") Long id) {
		Message message = messageService.getMessage(id);
		if (message != null)
			return message;
		else
			throw new DataNotFoundException("Message not found");
	}

	@POST
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response addMessage(Message message, @Context UriInfo info) {
		Message newMessage = messageService.addMessage(message);
		String idMessage = String.valueOf(newMessage.getId());
		URI uri = info.getAbsolutePathBuilder().path(idMessage).build();

		return Response.created(uri).entity(newMessage).build();
	}

	@PUT
	@Path("/{messageId}")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Message updateMessage(@PathParam("messageId") Long id,
			Message message) {
		message.setId(id);
		return messageService.updateMessage(message);
	}

	@DELETE
	@Path("/{messageId}")
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Message deleteMessage(@PathParam("messageId") Long id) {
		return messageService.deleteMessage(id);
	}

	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
	}
}
