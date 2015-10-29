/**
 * This is a jUnit test class which contains test cases to test Person REST web service
 */
package com.meeting.test;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.MediaType;

import org.junit.Test;

import com.meeting.domain.Person;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * 
 * @author Himani
 *
 */
public class PersonRestHandlerTest {

	/**
	 * This test case is used to test REST api with valid person email which
	 * return person object
	 */
	@Test
	public void testPersonGETMethod() {

		String emailId = "himani@abc.com";
		Person person = null;
		Client client = Client.create();
		WebResource webResource2 = client
				.resource("http://localhost:8080/MeetingSchedulerProject/meeting/personservice/"
						+ emailId);
		ClientResponse response2 = webResource2.accept(
				MediaType.APPLICATION_JSON).get(ClientResponse.class);
		person = response2.getEntity(Person.class);
		assertEquals(person.getEmailId(), "himani@abc.com");
	}

	/**
	 * This test case is used to test REST api for invalid person email id, so
	 * it will return status code 404 - not found
	 */
	@Test
	public void testInvalidEmailId() {
		String emailId = "InvalidPerson";
		Client client = Client.create();
		WebResource webResource2 = client
				.resource("http://localhost:8080/MeetingSchedulerProject/meeting/personservice/"
						+ emailId);
		ClientResponse response2 = webResource2.accept(
				MediaType.APPLICATION_JSON).get(ClientResponse.class);

		// not found status code
		assertEquals(response2.getStatus(), 404);
	}
}
