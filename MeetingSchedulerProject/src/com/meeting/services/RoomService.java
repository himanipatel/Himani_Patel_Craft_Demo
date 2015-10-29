/**
 * This class contains Business Logic for Room Entity
 */
package com.meeting.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.meeting.domain.CalendarViewClass;
import com.meeting.domain.Room;
import com.meeting.domain.TimeSlot;

/**
 * @author Himani
 * 
 */
public class RoomService {

	/**
	 * DAO class object
	 */
	// private RoomDAO roomDao;

	// temporary storage for room data
	// later it is not require because data will be stored in database table
	HashMap<String, Room> roomMap;

	public RoomService() {
		roomMap = new HashMap<String, Room>();
		getRoomData();
	}

	/**
	 * Later: this method can fetch data from database
	 * 
	 * @return
	 */
	public HashMap<String, Room> getRoomData() {

		Room rObj = new Room();
		rObj.setRoomName("Pluto");
		rObj.setLocation("3rd floor");
		roomMap.put(rObj.getRoomName(), rObj);

		rObj = new Room();
		rObj.setRoomName("Mercury");
		rObj.setLocation("2rd floor");
		roomMap.put(rObj.getRoomName(), rObj);

		rObj = new Room();
		rObj.setRoomName("Venus");
		rObj.setLocation("2rd floor");
		roomMap.put(rObj.getRoomName(), rObj);

		rObj = new Room();
		rObj.setRoomName("Jupiter");
		rObj.setLocation("3rd floor");
		roomMap.put(rObj.getRoomName(), rObj);

		return roomMap;
	}

	/**
	 * This method is used to fetch room details from database based on room
	 * name
	 * 
	 * @param name
	 * @return person object
	 */
	public Room getRoomByName(String name) {
		return roomMap.get(name);
	}

	/**
	 * This method is used to see room status (busy or free) for given time
	 * interval. It will return list of time slots and status for each time slot
	 * 
	 * @param roomname
	 * @param interval
	 * @return
	 */
	public List<CalendarViewClass> viewRoomCalendar(String name, Date date,
			boolean isOnlyWorkHours) {

		List<CalendarViewClass> roomCalendar = new ArrayList<CalendarViewClass>();

		/*
		 * For given start and end date/time generate calendar (time table) for
		 * each mins time duration and availability
		 */

		/**
		 * Example data : list of time interval (30 mins duration) and status
		 * 
		 * Start: Mon Oct 26 01:00:00 PDT 2015 End: Mon Oct 26 01:30:00 PDT 2015
		 * Status: Busy
		 * 
		 * Start: Mon Oct 26 01:30:00 PDT 2015 End: Mon Oct 26 02:00:00 PDT 2015
		 * Status: Free
		 * 
		 * Start: Mon Oct 26 02:00:00 PDT 2015 End: Mon Oct 26 02:30:00 PDT 2015
		 * Status: Free
		 * 
		 */

		CalendarViewClass objCalendar;

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		if (isOnlyWorkHours) {
			cal.set(Calendar.HOUR_OF_DAY, 8);
		} else {
			cal.set(Calendar.HOUR_OF_DAY, 0);
		}

		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		int start = cal.get(Calendar.DATE);

		while (cal.get(Calendar.DATE) == start) {

			if (isOnlyWorkHours && cal.get(Calendar.HOUR_OF_DAY) > 18) {
				break;
			}

			// create calendar view object
			objCalendar = new CalendarViewClass();
			TimeSlot timeSlot = new TimeSlot(cal, 30);
			objCalendar.setTime(timeSlot);

			// check room status for 30 mins of time interval
			objCalendar.setStatus(checkRoomStatus(name, timeSlot));

			// add to list
			roomCalendar.add(objCalendar);

			// increment time duration for next time slot
			cal.add(Calendar.MINUTE, 30);
		}

		return roomCalendar;
	}

	/**
	 * This method is used to check the room availability for given time
	 * interval
	 * 
	 * @param name
	 * @param interval
	 * @return status (busy/free)
	 */
	public CalendarViewClass.Status checkRoomStatus(String name,
			TimeSlot interval) {

		/***
		 * Step 1: Call DAO class method to check entry in database
		 ***/

		/***
		 * Step 2: If entry found in database for given room and time interval
		 * then room is busy else room is free
		 ***/

		/***
		 * Step 3: return room status based on value returned by DAO method
		 ***/
		return CalendarViewClass.Status.FREE;

	}
}