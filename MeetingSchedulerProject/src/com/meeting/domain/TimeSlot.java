package com.meeting.domain;

import java.util.Calendar;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TimeSlot {
	private Calendar startDate; // Holds the day and the hour for the start
	private Calendar endDate; // Holds the day and the hour for the end

	public TimeSlot() {

	}

	public TimeSlot(Calendar classStart, Calendar classEnd) {
		this.setStartDate(classStart);
		this.setEndDate(classEnd);
	}

	public TimeSlot(Calendar classStart, int lengthOfMeetingInMinutes) {
		this.setStartDate(classStart);
		this.setEndDate(classStart);
		this.endDate.add(Calendar.MINUTE, lengthOfMeetingInMinutes);
	}

	public Calendar getStartDate() {
		return startDate;
	}

	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	public Calendar getEndDate() {
		return endDate;
	}

	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}
}