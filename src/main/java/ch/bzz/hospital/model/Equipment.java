package ch.bzz.hospital.model;

/**
 * <h1>Equipment</h1>
 *
 * @author Andras Tarlos
 * @since 2022.05.24
 * @version 0.1
 *
 * Represents an equipment type in a hospital
 */

public class Equipment implements Comparable<Equipment>{
    private String name;
    private String description;
    private Integer amount;
    private String storageRoom;

    /**
     * Under construction
     */
    public void createEquipment() {

    }
    /**
     * Under construction
     */
    public void readEquipment() {

    }
    /**
     * Under construction
     */
    public void listEquipment() {

    }
    /**
     * Under construction
     */
    public void updateEquipment() {

    }
    /**
     * Under construction
     */
    public void deleteEquipment() {

    }
    /**
     * gets name
     *
     * @return value of name
     */
    public String getName() {
        return name;
    }
    /**
     * sets name
     *
     * @param name the value to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * gets description
     *
     * @return value of description
     */
    public String getDescription() {
        return description;
    }
    /**
     * sets description
     *
     * @param description the value to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * gets amount
     *
     * @return value of amount
     */
    public Integer getAmount() {
        return amount;
    }
    /**
     * sets amount
     *
     * @param amount the value to set
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getStorageRoom() {
        return storageRoom;
    }
    /**
     * sets storageRoom
     *
     * @param storageRoom the value to set
     */
    public void setStorageRoom(String storageRoom) {
        this.storageRoom = storageRoom;
    }
    /**
     * Compares to equipment for sorting
     * @param equipment
     * @return sorting values represented as ints (-1, 0, 1)
     */
    @Override
    public int compareTo(Equipment equipment) {
        return getAmount().compareTo(equipment.getAmount());
    }
}
