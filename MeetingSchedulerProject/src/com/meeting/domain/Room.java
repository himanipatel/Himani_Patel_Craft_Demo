package com.meeting.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Room {

	// unique for room database
	private String roomName;
	private String location;

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Room Name: " + this.roomName + "\nLocation: " + this.location;
	}
}