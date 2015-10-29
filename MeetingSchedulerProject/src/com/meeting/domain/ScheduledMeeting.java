package com.meeting.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ScheduledMeeting {

	private int meetingId;
	private Room room;
	private Person host;
	private String description;
	private String subject;
	private TimeSlot scheduledTime;
	private List<Person> attendees;

	public int getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(int meetingId) {
		this.meetingId = meetingId;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Person getHost() {
		return host;
	}

	public void setHost(Person host) {
		this.host = host;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TimeSlot getScheduledTime() {
		return scheduledTime;
	}

	public void setScheduledTime(TimeSlot scheduledTime) {
		this.scheduledTime = scheduledTime;
	}

	public List<Person> getAttendees() {
		return attendees;
	}

	public void setAttendees(List<Person> attendees) {
		this.attendees = attendees;
	}

}
