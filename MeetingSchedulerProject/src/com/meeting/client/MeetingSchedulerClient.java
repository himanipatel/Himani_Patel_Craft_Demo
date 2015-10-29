/**
 * This class is a Jersey Client class to call REST API
 */
package com.meeting.client;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ws.rs.core.MediaType;

import com.meeting.domain.Person;
import com.meeting.domain.Room;
import com.meeting.domain.ScheduledMeeting;
import com.meeting.domain.TimeSlot;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * 
 * @author Himani
 * 
 */
public class MeetingSchedulerClient {

	public static void main(String[] args) {

		Room scheduledRoom = getRoomByJerseyClient("Pluto");
		System.out.println(scheduledRoom);
		System.out.println();
		Person attendee1 = getPersonByJerseyClient("robert@abc.com");
		System.out.println(attendee1);

		ScheduledMeeting newMeeting = getScheduledMeetingObject();
		newMeeting = scheduleMeetingByJerseyClient(newMeeting);
		System.out.println(newMeeting.getMeetingId());
	}

	/**
	 * Jersey Client method to call REST API for accessing room details
	 * 
	 * @param name
	 * @return room object return by REST
	 */
	private static Room getRoomByJerseyClient(String name) {
		Room room = null;

		try {

			Client client = Client.create();
			WebResource webResource2 = client
					.resource("http://localhost:8080/MeetingSchedulerProject/meeting/roomservice/"
							+ name);

			ClientResponse response2 = webResource2.accept(
					MediaType.APPLICATION_JSON).get(ClientResponse.class);
			if (response2.getStatus() != 200) {
				System.out.println(response2.getStatusInfo());
			} else {
				room = response2.getEntity(Room.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return room;
	}

	/**
	 * Jersey Client method to call REST API for accessing person details
	 * 
	 * @param name
	 * @return person object return by REST
	 */
	private static Person getPersonByJerseyClient(String emailId) {
		Person person = null;

		try {

			Client client = Client.create();
			WebResource webResource2 = client
					.resource("http://localhost:8080/MeetingSchedulerProject/meeting/personservice/"
							+ emailId);

			ClientResponse response2 = webResource2.accept(
					MediaType.APPLICATION_JSON).get(ClientResponse.class);
			if (response2.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response2.getStatus());
			}

			person = response2.getEntity(Person.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return person;
	}

	/**
	 * Jersey Client method to call REST API for scheduling meeting
	 * 
	 * @param name
	 * @return schedule meeting object return by REST
	 */
	private static ScheduledMeeting scheduleMeetingByJerseyClient(
			ScheduledMeeting newMeeting) {
		// Person person = null;

		try {

			Client client = Client.create();
			WebResource webResource2 = client
					.resource("http://localhost:8080/MeetingSchedulerProject/meeting/calendarservice/schedulemeeting/");

			ClientResponse response2 = webResource2.accept(
					MediaType.APPLICATION_JSON).post(ClientResponse.class,
					newMeeting);

			if (response2.getStatus() != 201) {
				System.out.println(response2.getStatus());
			} else {
				newMeeting = response2.getEntity(ScheduledMeeting.class);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return newMeeting;

	}

	/**
	 * This method is used to build schedule meeting object (Later: instead of
	 * hard coded data, this data can be populated from user interface)
	 * 
	 * @return schedule meeting object
	 */
	private static ScheduledMeeting getScheduledMeetingObject() {
		ScheduledMeeting objScheduledMeeting = new ScheduledMeeting();

		String description = "prepare with your timesheet";
		objScheduledMeeting.setDescription(description);

		String subject = "Task1 Followup";
		objScheduledMeeting.setSubject(subject);

		Room selectedRoom = getRoomByJerseyClient("Pluto");
		objScheduledMeeting.setRoom(selectedRoom);

		Person host = getPersonByJerseyClient("himani@abc.com");
		objScheduledMeeting.setHost(host);

		TimeSlot selectedTimeSlot = getTimeSlot();
		objScheduledMeeting.setScheduledTime(selectedTimeSlot);

		// add attendee
		List<Person> attendeeList = new ArrayList<Person>();
		Person attendee1 = getPersonByJerseyClient("robert@abc.com");
		Person attendee2 = getPersonByJerseyClient("andrew@abc.com");
		attendeeList.add(attendee1);
		attendeeList.add(attendee2);
		objScheduledMeeting.setAttendees(attendeeList);

		return objScheduledMeeting;
	}

	/**
	 * This method is used to build time slot object (Later: instead of hard
	 * coded data, this data can be populated from user interface)
	 * 
	 * @return time slot object
	 */
	private static TimeSlot getTimeSlot() {

		Calendar classStart = new GregorianCalendar();
		classStart.set(Calendar.YEAR, 2015);
		classStart.set(Calendar.MONTH, 9); // Jan = 0, not 1
		classStart.set(Calendar.DAY_OF_MONTH, 25);
		classStart.set(Calendar.HOUR, 10);
		classStart.set(Calendar.MINUTE, 30);
		classStart.set(Calendar.AM_PM, Calendar.AM);

		// duration for 60 mins
		TimeSlot timeSlot = new TimeSlot(classStart, 60);

		return timeSlot;
	}
}