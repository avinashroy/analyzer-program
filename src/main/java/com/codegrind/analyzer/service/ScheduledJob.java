package com.codegrind.analyzer.service;

import com.codegrind.analyzer.model.Alerts;
import com.codegrind.analyzer.model.GpsInfo;
import com.codegrind.analyzer.repository.AlertsRepo;
import com.codegrind.analyzer.repository.EmpInfoRepo;
import com.codegrind.analyzer.repository.GpsInfoRepo;
import com.codegrind.analyzer.repository.TravelInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Component
public class ScheduledJob {

    @Autowired
    private EmpInfoRepo empInfoRepo;
    @Autowired
    private GpsInfoRepo gpsInfoRepo;
    @Autowired
    private TravelInfoRepo travelInfoRepo;
    @Autowired
    private AlertsRepo alertsRepo;
    private Iterable<GpsInfo> lastTenGpsInfo;
    private GpsInfo firstGpsInfo;
    private GpsInfo lastGpsInfo;
    private double previousLatitude;
    private double previousLongitude;
    private double currentLatitude;
    private double currentLongitude;
    @Autowired
    private Alerts alert;
    Iterator activeTravelIterator;
    Iterator lastTenGpsInfoIterator;

    @Scheduled(fixedDelay = 5000)
    public void gpsInfoAnalyser() {

        System.out.println("Hi, Hello!");

        System.out.println("List: " + travelInfoRepo.getTravelIdForActiveTravels("COMPLETE"));
        activeTravelIterator = travelInfoRepo.getTravelIdForActiveTravels("COMPLETE").iterator();

        while(activeTravelIterator.hasNext()) {

            if(gpsInfoRepo.count() >= 10) {

                lastTenGpsInfo = gpsInfoRepo.findLastTenRecords(activeTravelIterator.next().toString());
                System.out.println("\n\nLast ten records: " + lastTenGpsInfo + "\n\n");
            } else
                lastTenGpsInfo = gpsInfoRepo.findAll();

            lastTenGpsInfoIterator = lastTenGpsInfo.iterator();

            lastGpsInfo = (GpsInfo) lastTenGpsInfoIterator.next();
            currentLatitude = Double.parseDouble(lastGpsInfo.getLatitude());
            currentLongitude = Double.parseDouble(lastGpsInfo.getLongitude());

            System.out.println("Last gps info details:\n" +
                    "Info ID: " + lastGpsInfo.getInfoId() + "\n" +
                    "Latitude: " + currentLatitude + "\n" +
                    "Longitude: " + currentLongitude);

            while(lastTenGpsInfoIterator.hasNext())
                firstGpsInfo = (GpsInfo) lastTenGpsInfoIterator.next();

            previousLatitude = Double.parseDouble(firstGpsInfo.getLatitude());
            previousLongitude = Double.parseDouble(firstGpsInfo.getLongitude());

            System.out.println("First gps info details:\n" +
                    "Info ID: " + firstGpsInfo.getInfoId() + "\n" +
                    "Latitude: " + previousLatitude + "\n" +
                    "Longitude: " + previousLongitude);

            double theta = currentLongitude - previousLongitude;
            double distance = Math.sin(Math.toRadians(currentLatitude)) * Math.sin(Math.toRadians(previousLatitude)) +
                    Math.cos(Math.toRadians(currentLatitude)) * Math.cos(Math.toRadians(previousLatitude)) * Math.cos(Math.toRadians(theta));
            distance = Math.acos(distance);
            distance = Math.toDegrees(distance);
            distance = distance * 60 * 1.1515;
            distance = distance * 1.609344;

            if(distance < 1) {

                System.out.println("\n\nDistance: " + distance + "\n\n");

                if(!alertsRepo.findStatusByTravelId(lastGpsInfo.getTravelId(), "INSERT").isPresent()) {

                    alert.setTravelId(lastGpsInfo.getTravelId());
                    alert.setEmpId(lastGpsInfo.getEmpId());
                    alert.setAlertText("Distress to " + empInfoRepo.findById(alert.getEmpId()).get().getEmpName());
                    alert.setAlertStatus("INSERT");

                    System.out.println(alertsRepo.save(alert).getAlertId());

                    System.out.println("ALERT ADDED!");
                } else
                    System.out.println("ALERT already exists");
            }
        }
    }
}
