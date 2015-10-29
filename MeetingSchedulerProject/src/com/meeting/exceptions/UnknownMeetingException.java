package com.meeting.exceptions;

public class UnknownMeetingException extends RuntimeException {

	private String msg;

	public UnknownMeetingException() {
		this("Meeting is not found.");
	}

	public UnknownMeetingException(String msg) {
		super(msg);
		this.msg = msg;
	}

}
