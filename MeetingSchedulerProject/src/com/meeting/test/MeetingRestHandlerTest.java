/**
 * This is a jUnit test class which contains test cases to test Meeting REST web service
 */

package com.meeting.test;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Himani
 * 
 */
public class MeetingRestHandlerTest {

	@Before
	public void setup() {
		// setup test object
	}

	@Test
	public void testMeetingPOSTMethod() {
		// status code 201 - created will return
	}

	@Test
	public void testMeetingGETMethod() {
		// status code 200 - ok will return
	}

	@Test
	public void testInvalidMeetingGETMethod() {
		// 404 - not found status will return
	}

	@Test
	public void testRoomNotAvail() {

		// 400 - Bad request status code will return
	}

	@Test
	public void testPersonNotAvail() {

		// 400 - Bad request status code will return
	}
}