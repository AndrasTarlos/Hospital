package ch.bzz.hospital.model;

import java.util.Comparator;

public class Equipment implements Comparable<Equipment>{
    private String name;
    private String description;
    private Integer amount;
    private String storageRoom;

    public void createEquipment() {

    }

    public void readEquipment() {

    }

    public void listEquipment() {

    }

    public void updateEquipment() {

    }

    public void deleteEquipment() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getStorageRoom() {
        return storageRoom;
    }

    public void setStorageRoom(String storageRoom) {
        this.storageRoom = storageRoom;
    }

    @Override
    public int compareTo(Equipment equipment) {
        return getAmount().compareTo(equipment.getAmount());
    }
}
