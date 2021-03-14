package com.kangmin.blog.databean;

import java.sql.Date;

import org.genericdao.PrimaryKey;

@PrimaryKey("userName")
public class User {

    private String userName;  // unique email address
    private String password;
    private String firstName;
    private String lastName;
    ////////////////////////////////////////////////////////////
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String country;
    private String zipcode;
    private String phoneNumber;
    private Date enrollDate;

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(final String s) {
        userName = s;
    }

    public void setFirstName(final String s) {
        firstName = s;
    }

    public void setLastName(final String s) {
        lastName = s;
    }

    public void setPassword(final String s) {
        password = s;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Date getEnrollDate() {
        return enrollDate;
    }

    public void setAddressLine1(final String s) {
        addressLine1 = s;
    }

    public void setAddressLine2(final String s) {
        addressLine2 = s;
    }

    public void setCity(final String s) {
        city = s;
    }

    public void setState(final String s) {
        state = s;
    }

    public void setCountry(final String s) {
        country = s;
    }

    public void setZipcode(final String s) {
        zipcode = s;
    }

    public void setPhoneNumber(final String s) {
        phoneNumber = s;
    }

    public void setEnrollDate(final Date d) {
        enrollDate = d;
    }
}
