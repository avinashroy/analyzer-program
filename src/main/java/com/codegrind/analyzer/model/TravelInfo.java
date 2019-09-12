package com.codegrind.analyzer.model;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "travel_info")
public class TravelInfo {

    @Id
    private String travelId;
    private String travelDescription;
    private String locStart;
    private String locEnd;
    private String travelMedium;
    private String travelStartDateTime;
    private String travelFinishDateTime;
    private String travelStatus;
    private String empId;

    public String getTravelId() {
        return travelId;
    }

    public void setTravelId(String travelId) {
        this.travelId = travelId;
    }

    public String getTravelDescription() {
        return travelDescription;
    }

    public void setTravelDescription(String travelDescription) {
        this.travelDescription = travelDescription;
    }

    public String getLocStart() {
        return locStart;
    }

    public void setLocStart(String locStart) {
        this.locStart = locStart;
    }

    public String getLocEnd() {
        return locEnd;
    }

    public void setLocEnd(String locEnd) {
        this.locEnd = locEnd;
    }

    public String getTravelMedium() {
        return travelMedium;
    }

    public void setTravelMedium(String travelMedium) {
        this.travelMedium = travelMedium;
    }

    public String getTravelStartDateTime() {
        return travelStartDateTime;
    }

    public void setTravelStartDateTime(String travelStartDateTime) {
        this.travelStartDateTime = travelStartDateTime;
    }

    public String getTravelFinishDateTime() {
        return travelFinishDateTime;
    }

    public void setTravelFinishDateTime(String travelFinishDateTime) {
        this.travelFinishDateTime = travelFinishDateTime;
    }

    public String getTravelStatus() {
        return travelStatus;
    }

    public void setTravelStatus(String travelStatus) {
        this.travelStatus = travelStatus;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }
}
