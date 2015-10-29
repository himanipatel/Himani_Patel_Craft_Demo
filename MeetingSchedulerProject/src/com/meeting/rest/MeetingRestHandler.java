/**
 * This class handles REST API calls for Meeting Entity
 */
package com.meeting.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import com.meeting.domain.ScheduledMeeting;
import com.meeting.exceptions.PersonNotAvailableException;
import com.meeting.exceptions.RoomNotAvailableException;
import com.meeting.exceptions.UnknownMeetingException;
import com.meeting.services.MeetingService;

/**
 * @author Himani
 * 
 */

@Path("/calendarservice")
public class MeetingRestHandler {

	MeetingService meetingService = new MeetingService();

	/**
	 * POST: This is used to create/schedule new meeting
	 * 
	 * @param newMeeting
	 * @return Response
	 */
	@POST
	@Path("/schedulemeeting")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response scheduleMeeting(ScheduledMeeting newMeeting) {
		ResponseBuilder respBuilder;

		try {
			int newMeetingId = meetingService.scheduleMeeting(newMeeting);
			newMeeting.setMeetingId(newMeetingId);
			respBuilder = Response.status(Status.CREATED);
			respBuilder.entity(newMeeting);
		} catch (RoomNotAvailableException ex) {
			// selected room is not available for scheduled time slot
			respBuilder = Response.status(Status.BAD_REQUEST).entity(
					ex.getMessage());
		} catch (PersonNotAvailableException ex) {
			// selected host/attendees are not available for scheduled time slot
			respBuilder = Response.status(Status.BAD_REQUEST).entity(
					ex.getMessage());
		} catch (Exception ex) {
			// database exception or any other exception is business logic
			respBuilder = Response.status(Status.INTERNAL_SERVER_ERROR).entity(
					ex.getMessage());

		}
		return respBuilder.build();
	}

	/**
	 * GET: This method is used to retrieve meeting details
	 * 
	 * @param id
	 * @return Response
	 */
	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getMeeting(@PathParam("id") int id) {

		ResponseBuilder respBuilder;

		try {
			ScheduledMeeting objScheduledMeeting = meetingService
					.getMeetingById(id);
			respBuilder = Response.status(Status.OK);
			respBuilder.entity(objScheduledMeeting);
		} catch (UnknownMeetingException ex) {
			// if given meeting id is invalid
			respBuilder = Response.status(Status.NOT_FOUND).entity(
					ex.getMessage());
		} catch (Exception ex) {
			// database exception or any other exception is business logic
			respBuilder = Response.status(Status.INTERNAL_SERVER_ERROR).entity(
					ex.getMessage());
		}
		return respBuilder.build();
	}

	/**
	 * DELETE: This method will delete existing meeting
	 * 
	 * @param id
	 * @return Response
	 */
	@DELETE
	@Path("{id}")
	public Response cancelMeeting(@PathParam("id") int id) {

		ResponseBuilder respBuilder;
		try {
			meetingService.cancelMeeting(id);
			// success
			respBuilder = Response.ok();
		} catch (UnknownMeetingException ex) {
			// if given meeting id is invalid
			respBuilder = Response.status(Status.NOT_FOUND).entity(
					ex.getMessage());
		} catch (Exception ex) {
			// database exception or any other exception is business logic
			respBuilder = Response.status(Status.INTERNAL_SERVER_ERROR).entity(
					ex.getMessage());
		}
		return respBuilder.build();
	}

	/**
	 * PUT Method: This method will update existing meeting details based on
	 * meeting id. ex: Add/Remove Attendees, Change Time, Change Room, update
	 * subject/description
	 * 
	 * @param meetingid
	 * @param objScheduledMeeting
	 * @return Response
	 */
	@PUT
	@Path("{id}")
	public Response updateMeeting(@PathParam("id") int id,
			ScheduledMeeting objScheduledMeeting) {
		ResponseBuilder respBuilder;
		try {
			meetingService.updateMeeting(id, objScheduledMeeting);
			// success
			respBuilder = Response.ok();
		} catch (UnknownMeetingException ex) {
			// if given meeting id is invalid
			respBuilder = Response.status(Status.NOT_FOUND).entity(
					ex.getMessage());
		} catch (RoomNotAvailableException ex) {
			// selected room is not available for scheduled time slot
			respBuilder = Response.status(Status.BAD_REQUEST).entity(
					ex.getMessage());
		} catch (PersonNotAvailableException ex) {
			// selected host/attendees are not available for scheduled time slot
			respBuilder = Response.status(Status.BAD_REQUEST).entity(
					ex.getMessage());
		} catch (Exception ex) {
			// database exception or any other exception is business logic
			respBuilder = Response.status(Status.INTERNAL_SERVER_ERROR).entity(
					ex.getMessage());
		}
		return respBuilder.build();

	}

}