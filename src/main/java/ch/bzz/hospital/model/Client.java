package ch.bzz.hospital.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;

/**
 * <h1>Client</h1>
 *
 * @author Andras Tarlos
 * @since 2022.05.24
 * @version 0.1
 *
 * Represents a client of a hospital as a class
 */

public class Client implements Comparable<Client> {
    @FormParam("firstname")
    @NotEmpty
    @Size(min = 1, max = 25)
    private String firstname;
    @FormParam("name")
    @NotEmpty
    @Size(min = 1, max = 25)
    private String name;
    @FormParam("sex")
    @NotEmpty
    @Size(min = 3, max = 10)
    private String sex;
    @FormParam("condition")
    @NotEmpty
    @Size(min = 8, max = 80)
    private String condition;
    @FormParam("phoneNumber")
    @NotEmpty
    //@Pattern(regexp = "0(2[1-246-7]|3[1-4]|4[13-4]|5[25-6]|6[1-2]|7[15-68-9]|8[17]|91)[0-9]{7}")
    private String phoneNumber;
    @FormParam("bill")
    @DecimalMin(value = "0")
    private Double bill;
    @FormParam("checkin")
    @NotEmpty
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd")
    private LocalDate checkin;
    @FormParam("avhNumber")
    @NotEmpty
    //@Pattern(regexp = "/[7][5][6][.][\\d]{4}[.][\\d]{4}[.][\\d]{2}$/")
    private String ahvNumber;


    /**
     * Under construction
     */
    public void createClient() {

    }
    /**
     * Under construction
     */
    public void readClient() {

    }
    /**
     * Under construction
     */
    public void listClient() {

    }
    /**
     * Under construction
     */
    public void updateClient() {

    }
    /**
     * Under construction
     */
    public void deleteClient() {

    }
    /**
     * Under construction
     */
    public String getFirstname() {
        return firstname;
    }
    /**
     * sets forename
     *
     * @param forename the value to set
     */
    public void setFirstname(String forename) {
        this.firstname = forename;
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
     * gets sex
     *
     * @return value of sex
     */
    public String getSex() {
        return sex;
    }
    /**
     * sets sex
     *
     * @param sex the value to set
     */
    public void setSex(String sex) {
        this.sex = sex;
    }
    /**
     * gets condition
     *
     * @return value of condition
     */
    public String getCondition() {
        return condition;
    }
    /**
     * sets condition
     *
     * @param condition the value to set
     */
    public void setCondition(String condition) {
        this.condition = condition;
    }
    /**
     * gets phoneNumber
     *
     * @return value of phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    /**
     * sets phoneNumber
     *
     * @param phoneNumber the value to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    /**
     * gets bill
     *
     * @return value of bill
     */
    public Double getBill() {
        return bill;
    }
    /**
     * sets bill
     *
     * @param bill the value to set
     */
    public void setBill(Double bill) {
        this.bill = bill;
    }
    /**
     * gets checkin
     *
     * @return value of checkin
     */
    public LocalDate getCheckin() {
        return checkin;
    }
    /**
     * sets checkin
     *
     * @param checkin the value to set
     */
    public void setCheckin(LocalDate checkin) {
        this.checkin = checkin;
    }
    /**
     * gets checkin
     *
     * @return value of checkin
     */
    public String getAhvNumber() {
        return ahvNumber;
    }
    /**
     * sets ahvNumber
     *
     * @param ahvNumber the value to set
     */
    public void setAhvNumber(String ahvNumber) {
        this.ahvNumber = ahvNumber;
    }

    /**
     * Compares two clients for sorting
     * @param client
     * @return sorting values represented as ints (-1, 0, 1)
     */
    @Override
    public int compareTo(Client client) {
        return getName().compareTo(client.getName());
    }
}