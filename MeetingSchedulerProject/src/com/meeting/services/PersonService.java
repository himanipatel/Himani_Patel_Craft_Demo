/**
 * This class contains Business Logic for Person Entity
 */
package com.meeting.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.meeting.domain.CalendarViewClass;
import com.meeting.domain.Person;
import com.meeting.domain.TimeSlot;

/**
 * @author Himani
 * 
 */
public class PersonService {

	/**
	 * DAO class object
	 */
	// private PersonDAO personDao;

	// temporary storage for person data
	// later it is not require because data will be stored in database table
	HashMap<String, Person> personMap;

	public PersonService() {
		personMap = new HashMap<String, Person>();
		loadPersonData();
	}

	/**
	 * Later: this method can fetch data from database
	 * 
	 * @return
	 */
	private HashMap<String, Person> loadPersonData() {

		Person pObj = new Person();
		pObj.setName("Himani");
		pObj.setDesignation("Software Engineer");
		pObj.setEmailId("himani@abc.com");
		pObj.setEmployeeId(111);
		personMap.put(pObj.getEmailId(), pObj);

		pObj = new Person();
		pObj.setName("Robert");
		pObj.setDesignation("Software Engineer");
		pObj.setEmailId("robert@abc.com");
		pObj.setEmployeeId(112);
		personMap.put(pObj.getEmailId(), pObj);

		pObj = new Person();
		pObj.setName("Jeniffer");
		pObj.setDesignation("Software Engineer - 2");
		pObj.setEmailId("jeniffer@abc.com");
		pObj.setEmployeeId(113);
		personMap.put(pObj.getEmailId(), pObj);

		pObj = new Person();
		pObj.setName("Andrew");
		pObj.setDesignation("Senior Software Engineer");
		pObj.setEmailId("andrew@abc.com");
		pObj.setEmployeeId(114);
		personMap.put(pObj.getEmailId(), pObj);

		return personMap;
	}

	/**
	 * This method is used to fetch person details from database based on email
	 * Id
	 * 
	 * @param emailId
	 * @return person object
	 */
	public Person getPersonByEmailId(String emailId) {
		return personMap.get(emailId);
	}

	/**
	 * This method is used to see person availability (busy or free) for given
	 * time interval. It will return list of time slots and status for each time
	 * slot
	 * 
	 * @param emailid
	 * @param interval
	 * @return
	 */
	public List<CalendarViewClass> viewPersonCalendar(String emailId,
			Date date, boolean isOnlyWorkHours) {

		List<CalendarViewClass> personCalendar = new ArrayList<CalendarViewClass>();

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

			// check person availability for 30 mins of time interval
			objCalendar.setStatus(checkPersonStatus(emailId, timeSlot));

			// add to list
			personCalendar.add(objCalendar);

			// increment time duration for next time slot
			cal.add(Calendar.MINUTE, 30);
		}

		return personCalendar;
	}

	/**
	 * This method is used to check the person availability for given time
	 * interval
	 * 
	 * @param name
	 * @param interval
	 * @return status (busy/free)
	 */
	public CalendarViewClass.Status checkPersonStatus(String emailId,
			TimeSlot interval) {

		/***
		 * Step 1: Call DAO class method to check entry in database
		 ***/

		/***
		 * Step 2: If entry found in database for given person email id and time
		 * interval then person is busy else person is available
		 ***/

		/***
		 * Step 3: return person availability based on value returned by DAO
		 * method
		 ***/
		return CalendarViewClass.Status.FREE;

	}
}