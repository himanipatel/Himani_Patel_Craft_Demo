package com.meeting.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CalendarViewClass {

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

	public enum Status {
		BUSY, FREE
	}

	private Status status;
	private TimeSlot time;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public TimeSlot getTime() {
		return time;
	}

	public void setTime(TimeSlot time) {
		this.time = time;
	}
}