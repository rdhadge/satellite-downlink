package com.wyspace.communications.satellite;

import java.time.LocalTime;

/*
 * Satellite pass object
 */

public class SatellitePassSchedule {

	int id;
	String satelliteName;
	int downlinkRateInUnitsPer30Minutes;
	LocalTime passStartTime;
	LocalTime passEndTime;

	public SatellitePassSchedule(int id, String satelliteName, int downlinkRateInUnitsPer30Minutes,
			LocalTime passStartTime, LocalTime passEndTime) {
		this.id = id;
		this.satelliteName = satelliteName;
		this.downlinkRateInUnitsPer30Minutes = downlinkRateInUnitsPer30Minutes;
		this.passStartTime = passStartTime;
		this.passEndTime = passEndTime;
	}

	public int getId() {
		return id;
	}

	public String getSatelliteName() {
		return satelliteName;
	}

	public int getDownlinkRateInUnitsPer30Minutes() {
		return downlinkRateInUnitsPer30Minutes;
	}

	public LocalTime getPassStartTime() {
		return passStartTime;
	}

	public LocalTime getPassEndTime() {
		return passEndTime;
	}

	@Override
	public String toString() {
		return id + " " + satelliteName + " " + downlinkRateInUnitsPer30Minutes + " " + passStartTime.toString() + " "
				+ passEndTime.toString();
	}
}