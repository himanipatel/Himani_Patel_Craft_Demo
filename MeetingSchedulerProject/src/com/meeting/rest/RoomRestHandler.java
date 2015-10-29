/**
 * This class handles REST API calls for Room Entity
 */
package com.meeting.rest;

import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import com.meeting.domain.CalendarViewClass;
import com.meeting.domain.Room;
import com.meeting.services.RoomService;

@Path("/roomservice/")
public class RoomRestHandler {

	RoomService roomService = new RoomService();

	/**
	 * GET: This method is used to retrieve room details based on name
	 * 
	 * @param name
	 * @return Response
	 */
	@GET
	@Path("{name}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getRoom(@PathParam("name") String name) {

		ResponseBuilder respBuilder;

		try {
			Room room = roomService.getRoomByName(name);
			if (room != null) {
				respBuilder = Response.status(Status.OK);
				respBuilder.entity(room);
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