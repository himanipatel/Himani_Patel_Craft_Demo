package com.meeting.exceptions;

public class RoomNotAvailableException extends RuntimeException {
	private String msg;

	public RoomNotAvailableException() {
		this("Room is not available.");
	}

	public RoomNotAvailableException(String msg) {
		super(msg);
		this.msg = msg;
	}
}
