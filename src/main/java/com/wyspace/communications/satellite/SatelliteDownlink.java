package com.wyspace.communications.satellite;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Scanner;

public class SatelliteDownlink {
	
	public List<SatellitePassSchedule> getPassSchedule(String passScheduleFilePath) {

		List<SatellitePassSchedule> passSchedule = new ArrayList<>();
		int passID = 1;

		try (Scanner satellitePassSchedulesFile = new Scanner(new File(passScheduleFilePath))) {

			while (satellitePassSchedulesFile.hasNextLine()) {
				String[] schedule = satellitePassSchedulesFile.nextLine().split(",");
				passSchedule.add(new SatellitePassSchedule(passID++, schedule[0], Integer.parseInt(schedule[1]),
						LocalTime.parse(schedule[2]), LocalTime.parse(schedule[3])));
			}

		} catch (FileNotFoundException e) {
			System.err.println("Pass schedule file not found on give path - " + passScheduleFilePath);
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Error occurred while processing satellite pass schedules - " + e.getMessage());
			e.printStackTrace();
		}

		return passSchedule;
	}

	public Map<LocalTime, Integer> getDownlinkDetails(List<SatellitePassSchedule> satellitePassSchedule) {

		LocalTime downlinkPeriodStartTime = LocalTime.of(0, 0);		
		Map<LocalTime, List<SatellitePassSchedule>> satelliteDownlinkPeriodicGroups = new HashMap<>(48);
		Map<LocalTime, Integer> satelliteDownlinkRates = new HashMap<>(48);

		do {

			int downlinkRate = 0;
			
			List<SatellitePassSchedule> schedulesForSpecificPeriod = new ArrayList<>();
			ListIterator<SatellitePassSchedule> scheduleIterator = satellitePassSchedule.listIterator();

			while (scheduleIterator.hasNext()) {

				SatellitePassSchedule passSchedule = scheduleIterator.next();
				LocalTime passStartTime = passSchedule.getPassStartTime();
				LocalTime passEndTime = passSchedule.getPassEndTime();

				if ((passStartTime.isBefore(downlinkPeriodStartTime)
						|| passStartTime.equals(downlinkPeriodStartTime))
						&& passEndTime.isAfter(downlinkPeriodStartTime)) {

					downlinkRate = downlinkRate + passSchedule.getDownlinkRateInUnitsPer30Minutes();
					schedulesForSpecificPeriod.add(passSchedule);
				}
			}

			satelliteDownlinkPeriodicGroups.put(downlinkPeriodStartTime, schedulesForSpecificPeriod);
			satelliteDownlinkRates.put(downlinkPeriodStartTime, downlinkRate);
			
			downlinkPeriodStartTime = downlinkPeriodStartTime.plusMinutes(30);

		} while (downlinkPeriodStartTime.isAfter(LocalTime.MIDNIGHT));
				
		return satelliteDownlinkRates;
	}
}
