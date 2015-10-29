/**
 * This is a jUnit test class which contains test cases to test Room REST web service
 */
package com.meeting.test;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.MediaType;

import org.junit.Test;

import com.meeting.domain.Room;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * @author Himani
 * 
 */
public class RoomRestHandlerTest {

	/**
	 * This test case is used to test REST api with valid room name which return
	 * room object
	 */
	@Test
	public void testRoomGETMethod() {

		Room room = null;
		String roomName = "Mercury";
		Client client = Client.create();
		WebResource webResource2 = client
				.resource("http://localhost:8080/MeetingSchedulerProject/meeting/roomservice/"
						+ roomName);
		ClientResponse response2 = webResource2.accept(
				MediaType.APPLICATION_JSON).get(ClientResponse.class);
		room = response2.getEntity(Room.class);
		assertEquals(room.getRoomName(), "Mercury");
	}

	/**
	 * This test case is used to test REST api for invalid room name, so it will
	 * return status code 404 - not found
	 */
	@Test
	public void testInvalidRoom() {
		String roomName = "InvalidRoomName";
		Client client = Client.create();
		WebResource webResource2 = client
				.resource("http://localhost:8080/MeetingSchedulerProject/meeting/roomservice/"
						+ roomName);
		ClientResponse response2 = webResource2.accept(
				MediaType.APPLICATION_JSON).get(ClientResponse.class);

		// not found status code
		assertEquals(response2.getStatus(), 404);
	}
}
