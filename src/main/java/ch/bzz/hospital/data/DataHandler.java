package ch.bzz.hospital.data;

import ch.bzz.hospital.model.Client;
import ch.bzz.hospital.model.Equipment;
import ch.bzz.hospital.model.Hospital;
import ch.bzz.hospital.service.Config;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <h1>DataHandler</h1>
 *
 * @author Andras Tarlos
 * @since 2022.05.24
 * @version 0.1
 *
 * reads and writes the data in the JSON-files
 */
public class DataHandler {
    private static DataHandler instance = null;
    private List<Equipment> equipmentList;
    private List<Client> clientList;
    private List<Hospital> hospitalList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {
        setClientList(new ArrayList<>());
        readClientJSON();
        setEquipmentList(new ArrayList<>());
        readEquipmentJSON();
        setHospitalList(new ArrayList<>());
        readHospitalJSON();
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
     * reads all Equipment
     * @return list of Equipment
     */
    public List<Equipment> readAllEquipment() {
        return getEquipmentList();
    }

    /**
     * reads an Equipment by its name
     * @param equipmentName
     * @return the Equipment (null=not found)
     */
    public Equipment readEquipmentByName(String equipmentName) {
        String hi = "hello";
        if (hi.equals(equipmentName)) {

        }
        Equipment equipment = null;
        if (equipmentName == null) {
            return null;
        }
        for (Equipment entry : getEquipmentList()) {
            if (entry.getName().equals(equipmentName)) {
                equipment = entry;
            }
        }
        return equipment;
    }

    /**
     * reads a Hospital by its name
     * @param hospitalName
     * @return the Hospital (null=not found)
     */
    public Hospital readHospitalByName(String hospitalName) {
        Hospital hospital = null;
        if (hospitalName == null) {
            return null;
        }
        for (Hospital entry : getHospitalList()) {
            if (entry.getName().equals(hospitalName)) {
                hospital = entry;
            }
        }
        return hospital;
    }

    /**
     * sorts the Equipment by its amount
     * @return the sorted Equipment list
     */
    public List<Equipment> readSortedEquipment() {
        List<Equipment> eL = getEquipmentList();
        Collections.sort(eL);
        return eL;
    }
    /**
     * sorts the Clients by their names
     * @return the sorted list of Clients
     */
    public List<Client> readSortedClient() {
        List<Client> cL = getClientList();
        Collections.sort(cL);
        return cL;
    }

    /**
     * reads all Client
     * @return list of Clients
     */
    public List<Client> readAllClients() {
        return getClientList();
    }

    /**
     * reads all Hospitals
     * @return list of Hospitals
     */
    public List<Hospital> readAllHospitals() {
        return getHospitalList();
    }

    /**
     * reads a Client by his/hers name
     * @param forename, name
     * @return the Client (null=not found)
     */
    public List<Client> readClientByName(String forename, String name) {
        int type = 0;
        if (forename != null && name != null) {
            type = 0;
        } else if (forename == null && name != null) {
            type = 1;
        } else if (forename != null && name == null) {
            type = 2;
        } else {
            return null;
        }
        List<Client> clients = new ArrayList<>();

        for (Client entry : getClientList()) {
            switch (type) {
                case 0:
                    if (entry.getForename().equals(forename) && entry.getName().equals(name)) {
                        clients.add(entry);
                    }
                    break;
                case 1:
                    if (entry.getName().equals(name))
                        clients.add(entry);
                    break;
                case 2:
                    if (entry.getForename().equals(forename))
                        clients.add(entry);
                    break;
            }
        }
        return clients;
    }

    /**
     * reads the Equipment from the JSON-file
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
     * reads the Clients from the JSON-file
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
     * reads the Hospitals from the JSON-file
     */
    private void readHospitalJSON() {
        try {
            String path = Config.getProperty("hospitalJSON");
            byte[] jsonData = Files.readAllBytes(Paths.get(path));
            ObjectMapper objectMapper = new ObjectMapper();
            Hospital[] hospitals = objectMapper.readValue(jsonData,Hospital[].class);
            for (Hospital h : hospitals) {
                getHospitalList().add(h);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * gets equipmentList
     *
     * @return value of equipment
     */
    private List<Equipment> getEquipmentList() {
        return equipmentList;
    }
    /**
     * gets hospitalList
     *
     * @return value of Hospital
     */
    private List<Hospital> getHospitalList() {
        return hospitalList;
    }
    /**
     * gets clientList
     *
     * @return value of clientList
     */
    private List<Client> getClientList() {
        return clientList;
    }
    /**
     * sets equipmentList
     *
     * @param equipmentList the value to set
     */
    private void setEquipmentList(List<Equipment> equipmentList) {
        this.equipmentList = equipmentList;
    }
    /**
     * sets hospitalList
     *
     * @param hospitalList the value to set
     */
    private void setHospitalList(List<Hospital> hospitalList) {
        this.hospitalList = hospitalList;
    }
    /**
     * sets clientList
     *
     * @param clientList the value to set
     */
    private void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }
}