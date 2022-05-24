package ch.bzz.hospital.model;

import java.util.List;

public class Hospital {
    private List<Client> clients;
    private List<Equipment> equipments;
    private String name;
    private String address;
    private String owner;
    private Integer numberOfEmployees;

    public void createHospital() {

    }

    public void readHospital() {

    }

    public void listHospital() {

    }

    public void updateHospital() {

    }

    public void deleteHospital() {

    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Equipment> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<Equipment> equipments) {
        this.equipments = equipments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Integer getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void setNumberOfEmployees(Integer numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }
}
