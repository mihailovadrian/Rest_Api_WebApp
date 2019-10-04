package com.rest.app.messenger.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.rest.app.messenger.models.ErrorMessage;

@Provider
public class DataNotFoundExceptionMapper implements
		ExceptionMapper<DataNotFoundException> {

	@Override
	public Response toResponse(DataNotFoundException error) {
		// TODO Auto-generated method stub
		ErrorMessage errorMessage = new ErrorMessage(error.getMessage(),
				"www.google.com", 404);
		return Response.status(Status.NOT_FOUND).entity(errorMessage).build();
	}

}
