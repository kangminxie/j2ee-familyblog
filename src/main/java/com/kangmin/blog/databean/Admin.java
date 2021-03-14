package com.kangmin.blog.databean;

import java.sql.Date;

import org.genericdao.PrimaryKey;

@PrimaryKey("adminName")
public class Admin {

    private String adminName;  // unique email address
    private String password;
    private String firstName;
    private String lastName;
    private Date enrollDate;

    public String getPassword() {
        return password;
    }

    public String getAdminName() {
        return adminName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getEnrollDate() {
        return enrollDate;
    }

    public void setPassword(final String s) {
        password = s;
    }

    public void setAdminName(final String s) {
        adminName = s;
    }

    public void setFirstName(final String s) {
        firstName = s;
    }

    public void setLastName(final String s) {
        lastName = s;
    }

    public void setEnrollDate(final Date d) {
        enrollDate = d;
    }
}
