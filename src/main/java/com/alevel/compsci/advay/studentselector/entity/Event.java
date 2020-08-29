package com.alevel.compsci.advay.studentselector.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table

public class Event {
    @Id
    @GeneratedValue
    private int eventID;
    private int organiserID;
    private int selectionNum;
    private String eventTitle;
    private String eventDescription;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    //private Date eventDate;
    private boolean eventStatus;

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public int getOrganiserID() {
        return organiserID;
    }

    public void setOrganiserID(int organiserID) {
        this.organiserID = organiserID;
    }

    public int getSelectionNum() {
        return selectionNum;
    }

    public void setSelectionNum(int selectionNum) {
        this.selectionNum = selectionNum;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public boolean getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(boolean eventStatus) {
        this.eventStatus = eventStatus;
    }
}
