package ch.bzz.hospital.data;

import ch.bzz.hospital.model.Client;
import ch.bzz.hospital.model.Equipment;
import ch.bzz.hospital.service.Config;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * reads and writes the data in the JSON-files
 */
public class DataHandler {
    private static DataHandler instance = null;
    private List<Equipment> equipmentList;
    private List<Client> clientList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {
        setPublisherList(new ArrayList<>());
        readClientJSON();
        setBookList(new ArrayList<>());
        readEquipmentJSON();
    }

    /**
     * gets the only instance of this class
     * @return
     */
    public static DataHandler getInstance() {
        if (instance == null)
            instance = new DataHandler();
        return instance;
    }


    /**
     * reads all books
     * @return list of books
     */
    public List<Equipment> readAllEquipment() {
        return getEquipmentList();
    }

    /**
     * reads a book by its uuid
     * @param equipmentName
     * @return the Equipment (null=not found)
     */
    public Equipment readEquipmentByName(String equipmentName) {
        Equipment equipment = null;
        for (Equipment entry : getEquipmentList()) {
            if (entry.getName().equals(equipmentName)) {
                equipment = entry;
            }
        }
        return equipment;
    }

    /**
     * reads all Publishers
     * @return list of publishers
     */
    public List<Client> readAllClients() {

        return getClientList();
    }

    /**
     * reads a publisher by its uuid
     * @param forename, name
     * @return the Publisher (null=not found)
     */
    public Client readClientByName(String forename, String name) {
        Client publisher = null;
        for (Client entry : getClientList()) {
            if (entry.getForename().equals(forename) && entry.getName().equals(name)) {
                publisher = entry;
            }
        }
        return publisher;
    }

    /**
     * reads the books from the JSON-file
     */
    private void readEquipmentJSON() {
        try {
            String path = Config.getProperty("equipmentJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Equipment[] equipment = objectMapper.readValue(jsonData, Equipment[].class);
            for (Equipment e : equipment) {
                getEquipmentList().add(e);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads the publishers from the JSON-file
     */
    private void readClientJSON() {
        try {
            String path = Config.getProperty("clientJSON");
            byte[] jsonData = Files.readAllBytes(Paths.get(path));
            ObjectMapper objectMapper = new ObjectMapper();
            Client[] clients = objectMapper.readValue(jsonData,Client[].class);
            for (Client client : clients) {
                getClientList().add(client);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * gets bookList
     *
     * @return value of bookList
     */
    private List<Equipment> getEquipmentList() {
        return equipmentList;
    }

    /**
     * sets bookList
     *
     * @param equipmentList the value to set
     */
    private void setBookList(List<Equipment> equipmentList) {
        this.equipmentList = equipmentList;
    }

    /**
     * gets publisherList
     *
     * @return value of publisherList
     */
    private List<Client> getClientList() {
        return clientList;
    }

    /**
     * sets clientList
     *
     * @param clientList the value to set
     */
    private void setPublisherList(List<Client> clientList) {
        this.clientList = clientList;
    }


}