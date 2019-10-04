package com.rest.app.messenger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.UriInfo;

@Path("/demo")
@Consumes(javax.ws.rs.core.MediaType.TEXT_PLAIN)
@Produces(javax.ws.rs.core.MediaType.TEXT_PLAIN)
public class AnnotationsTestingResources {
	@GET
	@Path("ann")
	public String getUsingAnnotations(
			@MatrixParam("matrix") String matrixParam,
			@HeaderParam("header") String headerParam,
			@CookieParam("cook") String cookie) {
		return "Matrix::: " + matrixParam + " HEADER ::::: " + headerParam
				+ " Cookie::::" + cookie;
	}

	@GET
	@Path("context")
	public String getParamUsingContext(@Context UriInfo uriInfo, @Context HttpHeaders headers) {
		String path= uriInfo.getPath().toString();
		
		return path+"CookieS: "+headers.getCookies().toString();
	}
}
