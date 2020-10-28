package com.alevel.compsci.advay.studentselector.entity;

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

/**
 * Entity of type Event with various members corresponding to
 * Database columns
 * This is a standard JPA entity
 */
public class Subscription {

    /**
     * eventID is the primary key and automatically generated
     * as specified by annotation
     */
    @Id
    @GeneratedValue
    private int subscriptionID;
    private int userID;
    private int eventID;
    private boolean selected;
    private int weight;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public boolean getSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}
