package ch.bzz.hospital.model;

import java.time.LocalDate;

public class Client {
    private String forename;
    private String name;
    private String sex;
    private String condition;
    private String phoneNumber;
    private Double bill;
    private LocalDate checkin;
    private String ahvNumber;

    public void createClient() {

    }

    public void readClient() {

    }

    public void listClient() {

    }

    public void updateClient() {

    }

    public void deleteClient() {

    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Double getBill() {
        return bill;
    }

    public void setBill(Double bill) {
        this.bill = bill;
    }

    public LocalDate getCheckin() {
        return checkin;
    }

    public void setCheckin(LocalDate checkin) {
        this.checkin = checkin;
    }

    public String getAhvNumber() {
        return ahvNumber;
    }

    public void setAhvNumber(String ahvNumber) {
        this.ahvNumber = ahvNumber;
    }
}
