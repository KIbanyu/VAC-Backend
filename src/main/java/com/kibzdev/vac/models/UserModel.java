package com.kibzdev.vac.models;

public class UserModel {
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String password;
    private long yearOfBirth;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(long yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
