package com.kibzdev.vac.entities;


import javax.persistence.*;

@Entity
@Table(name = "vac_users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String password;
    private long yearOfBirth;
    private boolean canBook;


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public boolean isCanBook() {
        return canBook;
    }

    public void setCanBook(boolean canBook) {
        this.canBook = canBook;
    }

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
