package ch.bzz.hospital.model;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;
import java.util.List;

/**
 * <h1>Hospital</h1>
 *
 * @author Andras Tarlos
 * @since 2022.05.24
 * @version 0.1
 *
 * Represents a hospital as a Java class
 */

public class Hospital {
    private List<Client> clients;
    private List<Equipment> equipments;
    @FormParam("name")
    @NotEmpty
    @Size(min = 4, max = 80)
    private String name;
    @FormParam("address")
    @NotEmpty
    @Size(min = 8, max = 80)
    private String address;
    @FormParam("owner")
    @NotEmpty
    @Size(min = 2, max = 80)
    private String owner;
    @FormParam("numberOfEmployees")
    @DecimalMin(value = "0")
    @DecimalMax(value = "200")
    private Integer numberOfEmployees;

    /**
     * gets clientList
     *
     * @return value of clientList
     */
    public List<Client> getClients() {
        return clients;
    }
    /**
     * sets clients
     *
     * @param clients the value to set
     */
    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
    /**
     * gets equipments
     *
     * @return value of equipments
     */
    public List<Equipment> getEquipments() {
        return equipments;
    }
    /**
     * sets equipments
     *
     * @param equipments the value to set
     */
    public void setEquipments(List<Equipment> equipments) {
        this.equipments = equipments;
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
     * gets address
     *
     * @return value of address
     */
    public String getAddress() {
        return address;
    }
    /**
     * sets address
     *
     * @param address the value to set
     */
    public void setAddress(String address) {
        this.address = address;
    }
    /**
     * gets owner
     *
     * @return value of owner
     */
    public String getOwner() {
        return owner;
    }
    /**
     * sets owner
     *
     * @param owner the value to set
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }
    /**
     * gets numberOfEmployees
     *
     * @return value of numberOfEmployees
     */
    public Integer getNumberOfEmployees() {
        return numberOfEmployees;
    }
    /**
     * sets numberOfEmployees
     *
     * @param numberOfEmployees the value to set
     */
    public void setNumberOfEmployees(Integer numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }
}