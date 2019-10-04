package com.rest.app.messenger.filters;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

@Provider
public class SecurityFilter implements ContainerRequestFilter {
	private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
	private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";
	private static final String CHECK_AUTH_PREFIX = "secured";

	@Override
	public void filter(ContainerRequestContext requestContext)
			throws IOException {
		if (requestContext.getUriInfo().getPath().contains(CHECK_AUTH_PREFIX)) {

			List<String> authHeader = requestContext.getHeaders().get(
					AUTHORIZATION_HEADER_KEY);
			
			if (authHeader!=null && authHeader.size() > 0) {
				String authToken = authHeader.get(0);
				authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX,
						"");
				String decodedString = org.glassfish.jersey.internal.util.Base64
						.decodeAsString(authToken);

				StringTokenizer tokenizer = new StringTokenizer(decodedString,
						":");
				String userName = tokenizer.nextToken();
				String password = tokenizer.nextToken();

				// testing auth
				if ("admin".equals(userName) && "admin".equals(password))
					return;
			}
			Response unauthStatus = Response.status(Status.UNAUTHORIZED)
					.entity("You can`t acces this").build();
			requestContext.abortWith(unauthStatus);
		}
	}
}
