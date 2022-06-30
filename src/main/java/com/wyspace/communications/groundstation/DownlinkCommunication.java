package com.wyspace.communications.groundstation;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;

import com.wyspace.communications.satellite.SatelliteDownlink;
import com.wyspace.communications.satellite.SatellitePassSchedule;

public class DownlinkCommunication {

	public static void main(String[] args) {

		int groundStationBandwidth = 0;
		String satellitePassScheduleFilePath = "";

		if (args.length != 4) {
			System.err.println("Incorrect arguments, please retry with correct arguments");
			System.err.println(
					"Usage: java -jar satellite-downlink-1.0.0.jar --groud-station-bandwidth <groud_station_bandwidth_in_units_per_30_minutes>"
							+ " --satellite-pass-schedule <pass_schedule_file_path>");
		}

		for (int i = 0; i < args.length; i++) {
			if ("--groud-station-bandwidth".equals(args[i])) {
				groundStationBandwidth = Integer.valueOf(args[++i]);
				continue;
			}

			if ("--satellite-pass-schedule".equals(args[i])) {
				satellitePassScheduleFilePath = args[++i];
				continue;
			}
		}

		System.out.println("Ground Station Bandwidth - " + groundStationBandwidth);

		DownlinkCommunication downlinkCommunication = new DownlinkCommunication();
		downlinkCommunication.findDownlinkCommunicationDetails(groundStationBandwidth, satellitePassScheduleFilePath);
	}

	private void findDownlinkCommunicationDetails(int groundStationBandwidth, String satellitePassScheduleFilePath) {

		SatelliteDownlink satelliteDownlink = new SatelliteDownlink();
		List<SatellitePassSchedule> satellitePassSchedule = satelliteDownlink
				.getPassSchedule("C:\\coding\\satellite-downlink-problem\\pass-schedule.txt");

		Map<LocalTime, Integer> downlinkDetails = satelliteDownlink.getDownlinkDetails(satellitePassSchedule);

		Optional<Entry<LocalTime, Integer>> maxDownlink = downlinkDetails.entrySet().stream().max(
				(Entry<LocalTime, Integer> e1, Entry<LocalTime, Integer> e2) -> e1.getValue().compareTo(e2.getValue()));

		if (!maxDownlink.isEmpty()) {
			LocalTime maxDownlinkStartTime = maxDownlink.get().getKey();
			int maxDownlinkRate = maxDownlink.get().getValue();

			System.out.println(
					"Maximum Satellite downlink rate is " + maxDownlinkRate + ", and it is present in the period from '"
							+ maxDownlinkStartTime + "' to '" + maxDownlinkStartTime.plusMinutes(30) + "'");

			if (groundStationBandwidth > maxDownlinkRate) {
				System.out.println("Ground station has the bandwidth i.e. " + groundStationBandwidth
						+ " to support maximum satellite downlink rate i.e. " + maxDownlinkRate);

			} else {
				System.out.println("Ground station does not have the bandwidth i.e. " + groundStationBandwidth
						+ " to support maximum satellite downlink rate i.e. " + maxDownlinkRate);
			}

		} else {
			System.out.println("Maximum Satellite downlink not calculated");
		}
	}
}
