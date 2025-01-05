package vn.edu.hcmuaf.fit.doancuoiki.model;

import java.time.LocalDate;

public class UserInfo {
    private int id;
    private String fullName;
    private String phoneNumber;
    private LocalDate birthday;
    private String address;

    public UserInfo() {
    }

    public UserInfo(int id, String fullName, String phoneNumber, String address, LocalDate birthday) {
        this.id = id;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
