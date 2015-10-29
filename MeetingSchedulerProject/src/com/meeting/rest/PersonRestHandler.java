/**
 * This class handles REST API calls for Person Entity
 */
package com.meeting.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import com.meeting.domain.Person;
import com.meeting.services.PersonService;

/**
 * @author Himani
 * 
 */

@Path("/personservice/")
public class PersonRestHandler {

	PersonService personService = new PersonService();

	/**
	 * GET: This method is used to retrieve person details based on emailid
	 * 
	 * @param emailId
	 * @return Response
	 */
	@GET
	@Path("{emailid}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getPerson(@PathParam("emailid") String emailId) {

		ResponseBuilder respBuilder;

		try {
			Person person = personService.getPersonByEmailId(emailId);
			if (person != null) {
				respBuilder = Response.status(Status.OK);
				respBuilder.entity(person);
			} else {
				respBuilder = Response.status(Status.NOT_FOUND);
			}
		} catch (Exception ex) {
			respBuilder = Response.status(Status.INTERNAL_SERVER_ERROR).entity(
					ex.getMessage());
		}

		return respBuilder.build();
	}
}