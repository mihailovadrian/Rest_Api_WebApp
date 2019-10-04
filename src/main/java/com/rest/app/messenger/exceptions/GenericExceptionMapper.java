package com.rest.app.messenger.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import com.rest.app.messenger.models.ErrorMessage;
//@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable ex) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), "", 500);

		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.entity(errorMessage).build();
	}

}
