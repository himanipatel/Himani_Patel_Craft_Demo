/**
 * This class contains Business Logic for Meeting Entity
 */
package com.meeting.services;

import com.meeting.domain.CalendarViewClass;
import com.meeting.domain.Person;
import com.meeting.domain.Room;
import com.meeting.domain.ScheduledMeeting;
import com.meeting.domain.TimeSlot;
import com.meeting.exceptions.PersonNotAvailableException;
import com.meeting.exceptions.RoomNotAvailableException;
import com.meeting.exceptions.UnknownMeetingException;

/**
 * 
 * @author Himani
 *
 */
public class MeetingService {

	/**
	 * DAO class object
	 */
	// private MeetingDAO meetingDao;

	/**
	 * Default constructor It will initialize DAO class object
	 */
	public MeetingService() {
		/*** Step 1: Create object of DAO class ***/
		// meetingDao = new MeetingDAO();
	}

	/**
	 * This method is to schedule new meeting
	 * 
	 * @param newScheduledMeeting
	 * @return new meeting Id
	 */
	public int scheduleMeeting(ScheduledMeeting newScheduledMeeting) {

		/***
		 * Step 1: Check for room availability
		 ***/
		if (!isRoomAvailable(newScheduledMeeting.getRoom(),
				newScheduledMeeting.getScheduledTime())) {
			throw new RoomNotAvailableException();
		}

		/***
		 * Step 2: Check for host person availability
		 ***/
		if (!isPersonAvailable(newScheduledMeeting.getHost(),
				newScheduledMeeting.getScheduledTime())) {
			throw new PersonNotAvailableException("Host is not available.");
		}

		/***
		 * Step 3: Check for each attendees availability
		 ***/
		for (Person attendee : newScheduledMeeting.getAttendees()) {
			if (!isPersonAvailable(attendee,
					newScheduledMeeting.getScheduledTime())) {
				throw new PersonNotAvailableException(attendee.getEmailId()
						+ " is not available.");
			}
		}

		/***
		 * Step 4: Call DAO class method to create new entry in database
		 * (schedule meeting)
		 ***/

		/*** Step 5: DAO method will return new meeting Id ***/
		int newMeetingId = 1;
		// newMeetingId = daoMethod();

		/*** Step 6: return newly generated meeting Id ***/
		return newMeetingId;
	}

	/**
	 * This method is to fetch scheduled meeting from database based on id
	 * 
	 * @param id
	 * @return ScheduledMeeting meeting object
	 * @throws UnknownMeetingException
	 */
	public ScheduledMeeting getMeetingById(int id) {

		ScheduledMeeting objScheduledMeeting = new ScheduledMeeting();

		/***
		 * Step 1: Call DAO class method to fetch meeting based on given id from
		 * database
		 ***/
		// objScheduledMeeting = daoMethod();

		/*** Step 2: throw error if meeting is not found ***/

		/*
		 * if(objScheduledMeeting == null){ throw new UnknownMeetingException();
		 * }
		 */

		/*** Step 3: return ***/
		return objScheduledMeeting;
	}

	/**
	 * This method is for remove meeting
	 * 
	 * @param id
	 * @return number of records deleted
	 * @throws UnknownMeetingException
	 */
	public int cancelMeeting(int id) {

		/***
		 * Step 1: Call DAO class method to delete meeting based on given id
		 * from database
		 ***/

		/*** Step 2: DAO method will return number of records deleted ***/
		int numberOfRecords = 1;
		// numberOfRecords = daoMethod();

		/***
		 * Step 3: If meeting is not found in database for given id then throw
		 * exception
		 ***/
		if (numberOfRecords <= 0) {
			throw new UnknownMeetingException();
		}

		/*** Step 4: return ***/
		return numberOfRecords;
	}

	public int updateMeeting(int id, ScheduledMeeting objScheduledMeeting) {

		/***
		 * Step 1: Check for room availability
		 ***/
		if (!isRoomAvailable(objScheduledMeeting.getRoom(),
				objScheduledMeeting.getScheduledTime())) {
			throw new RoomNotAvailableException();
		}

		/***
		 * Step 2: Check for each attendees availability
		 ***/
		for (Person attendee : objScheduledMeeting.getAttendees()) {
			if (!isPersonAvailable(attendee,
					objScheduledMeeting.getScheduledTime())) {
				throw new PersonNotAvailableException(attendee.getEmailId()
						+ " is not available.");
			}
		}

		/***
		 * Step 3: Call DAO class method to update entry in database
		 ***/
		/*** Step 4: DAO method will return number of records deleted ***/
		int numberOfRecords = 1;
		// numberOfRecords = daoMethod();

		if (numberOfRecords <= 0) {
			throw new UnknownMeetingException();
		}

		/*** Step 4: return ***/
		return numberOfRecords;
	}

	/**
	 * This is a private method used by this class for checking room
	 * availability
	 * 
	 * @param room
	 * @param scheduledTimeSlot
	 * @return boolean (is room is available or not)
	 */
	private boolean isRoomAvailable(Room room, TimeSlot scheduledTimeSlot) {
		RoomService roomService = new RoomService();
		CalendarViewClass.Status roomStatus = roomService.checkRoomStatus(
				room.getRoomName(), scheduledTimeSlot);

		if (roomStatus.equals(CalendarViewClass.Status.BUSY)) {
			return false;
		}

		return true;
	}

	/**
	 * This is a private method used by this class for checking person
	 * availability
	 * 
	 * @param person
	 * @param scheduledTimeSlot
	 * @return boolean (is person is available or not)
	 */
	private boolean isPersonAvailable(Person person, TimeSlot scheduledTimeSlot) {
		PersonService personService = new PersonService();

		CalendarViewClass.Status hostStatus = personService.checkPersonStatus(
				person.getEmailId(), scheduledTimeSlot);

		if (hostStatus.equals(CalendarViewClass.Status.BUSY)) {
			return false;
		}

		return true;

	}
}