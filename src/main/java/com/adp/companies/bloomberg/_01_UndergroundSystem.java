package com.adp.companies.bloomberg;

import java.util.HashMap;
import java.util.Map;

public class _01_UndergroundSystem {

    private final Map<Integer, CheckinDetail> checkInMap = new HashMap<>();
    private final Map<StationPair, TripDetail> travelTimes = new HashMap<>();

    public record StationPair(String source, String destination) {}

    public record CheckinDetail(String station, int time) {}

    // Mutable class because we want to avoid creating a new TripDetail every time
    public static class TripDetail {
        private int totalTime;
        private int tripCount;

        public TripDetail(int totalTime, int tripCount) {
            this.totalTime = totalTime;
            this.tripCount = tripCount;
        }

        public void addTrip(int duration) {
            this.totalTime += duration;
            this.tripCount++;
        }

        public double getAverage() {
            return (double) totalTime / tripCount;
        }
    }

    public void checkIn(int id, String stationName, int t) {
        checkInMap.put(id, new CheckinDetail(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        CheckinDetail detail = checkInMap.remove(id); // Remove to free memory
        StationPair key = new StationPair(detail.station(), stationName);
        travelTimes.computeIfAbsent(key, k -> new TripDetail(0, 0)).addTrip(t - detail.time());
    }

    public double getAverageTime(String startStation, String endStation) {
        TripDetail trip = travelTimes.get(new StationPair(startStation, endStation));
        return trip != null ? trip.getAverage() : 0.0;
    }

    public static void main(String[] args) {
        _01_UndergroundSystem undergroundSystem = new _01_UndergroundSystem();
        undergroundSystem.checkIn(10, "L", 3);
        undergroundSystem.checkOut(10, "P", 8);
        double avgTime = undergroundSystem.getAverageTime("L", "P");
        undergroundSystem.checkIn(5, "L", 10);
        undergroundSystem.checkOut(5, "P", 16);
        double avgTime2 = undergroundSystem.getAverageTime("L", "P");
    }
}
