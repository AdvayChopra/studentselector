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
 * Entity of type AppUser with various members corresponding to
 * Database columns
 * This is a standard JPA entity
 */
public class AppUser {
    /**
     * userID is the primary key and automatically generated
     * as specified by annotation
     */
    @Id
    @GeneratedValue
    private int userID;
    private String username;
    private String email;
    private String password;
    private boolean subscriber;
    private boolean organiser;
    private boolean admin;

    public boolean getAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getOrganiser() {
        return organiser;
    }

    public void setOrganiser(boolean organiser) {
        this.organiser = organiser;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
