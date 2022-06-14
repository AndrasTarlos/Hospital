package ch.bzz.hospital.data;

import ch.bzz.hospital.model.Client;
import ch.bzz.hospital.model.Equipment;
import ch.bzz.hospital.model.Hospital;
import ch.bzz.hospital.service.Config;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.*;
import java.nio.charset.StandardCharsets;
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
    private static List<Equipment> equipmentList;
    private static List<Client> clientList;
    private static List<Hospital> hospitalList;

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
     * initialize the lists with the data
     */
    public static void initLists() {
        DataHandler.setClientList(new ArrayList<>());
        DataHandler.setEquipmentList(new ArrayList<>());
        DataHandler.setHospitalList(new ArrayList<>());
        DataHandler.readEquipmentJSON();
        DataHandler.readClientJSON();
        DataHandler.readHospitalJSON();
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
    public static List<Equipment> readAllEquipment() {
        return getEquipmentList();
    }

    /**
     * reads an Equipment by its name
     * @param equipmentName
     * @return the Equipment (null=not found)
     */
    public static List<Equipment> readEquipmentByName(String equipmentName) {
        List<Equipment> equipment = new ArrayList<>();
        if (equipmentName == null) {
            return new ArrayList<>();
        }
        for (Equipment entry : getEquipmentList()) {
            if (entry.getName().equals(equipmentName)) {
                equipment.add(entry);
            }
        }
        return equipment;
    }

    /**
     * reads a Hospital by its name
     * @param hospitalName
     * @return the Hospital (null=not found)
     */
    public static List<Hospital> readHospitalByName(String hospitalName) {
        List<Hospital> hospital = new ArrayList<>();
        if (hospitalName == null) {
            return new ArrayList<>();
        }
        for (Hospital entry : getHospitalList()) {
            if (entry.getName().equals(hospitalName)) {
                hospital.add(entry);
            }
        }
        return hospital;
    }

    /**
     * sorts the Equipment by its amount
     * @return the sorted Equipment list
     */
    public static List<Equipment> readSortedEquipment() {
        List<Equipment> eL = getEquipmentList();
        Collections.sort(eL);
        return eL;
    }
    /**
     * sorts the Clients by their names
     * @return the sorted list of Clients
     */
    public static List<Client> readSortedClient() {
        List<Client> cL = getClientList();
        Collections.sort(cL);
        return cL;
    }

    /**
     * reads all Client
     * @return list of Clients
     */
    public static List<Client> readAllClients() {
        return getClientList();
    }

    /**
     * reads all Hospitals
     * @return list of Hospitals
     */
    public static List<Hospital> readAllHospitals() {
        return getHospitalList();
    }

    /**
     * reads a Client by his/hers name
     * @param forename, name
     * @return the Client (null=not found)
     */
    public static List<Client> readClientByName(String forename, String name) {
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
                    if (entry.getFirstname().equals(forename) && entry.getName().equals(name)) {
                        clients.add(entry);
                    }
                    break;
                case 1:
                    if (entry.getName().equals(name))
                        clients.add(entry);
                    break;
                case 2:
                    if (entry.getFirstname().equals(forename))
                        clients.add(entry);
                    break;
            }
        }
        if (clients.size() == 0)
            return null;
        return clients;
    }

    /**
     *
     * @param forename of Client
     * @param name of Client
     * @return boolean value
     */
    public static boolean deleteClient(String forename, String name) {
        List<Client> clients = readClientByName(forename, name);
        if (clients != null) {
            for (Client c : clients) {
                getClientList().remove(c);
            }
            writeClientJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * deletes equipments
     * @param name of equipment
     * @return a boolean value
     */
    public static boolean deleteEquipment(String name) {
        List<Equipment> equipment = readEquipmentByName(name);
        if (equipment.size() != 0) {
            for (Equipment e : equipment) {
                getClientList().remove(e);
            }
            writeEquipmentJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * deletes a hospital
     * @param name of a hospital
     * @return a boolean value
     */
    public static boolean deleteHospital(String name) {
        List<Hospital> hospitals = readHospitalByName(name);
        if (hospitals.size() != 0) {
            for (Hospital h : hospitals) {
                getClientList().remove(h);
            }
            writeHospitalJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * inserts a new client into the clientList
     *
     * @param client the book to be saved
     */
    public static void insertClient(Client client) {
        getClientList().add(client);
        writeClientJSON();
    }

    /**
     * updates the client list
     */
    public static void updateClient() {
        writeClientJSON();
    }

    /**
     * inserts a new client into the clientList
     *
     * @param equipment the equipment to be saved
     */
    public static void insertEquipment(Equipment equipment) {
        getEquipmentList().add(equipment);
        writeEquipmentJSON();
    }

    /**
     * updates the equipment list
     */
    public static void updateEquipment() {
        writeEquipmentJSON();
    }


    /**
     * inserts a new hospital into the hospitalList
     *
     * @param hospital the book to be saved
     */
    public static void insertHospital(Hospital hospital) {
        getHospitalList().add(hospital);
        writeHospitalJSON();
    }

    /**
     * updates the hospital list
     */
    public static void updateHospital() {
        writeHospitalJSON();
    }

    /**
     * reads the Equipment from the JSON-file
     */
    private static void readEquipmentJSON() {
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
    private static void readClientJSON() {
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
    private static void readHospitalJSON() {
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
     * writes the clientList to the JSON-file
     */
    private static void writeClientJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String bookPath = Config.getProperty("clientJSON");
        try {
            fileOutputStream = new FileOutputStream(bookPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getClientList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * writes the equipmentList to the JSON-file
     */
    private static void writeEquipmentJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String bookPath = Config.getProperty("equipmentJSON");
        try {
            fileOutputStream = new FileOutputStream(bookPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getEquipmentList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * writes the hospitalList to the JSON-file
     */
    private static void writeHospitalJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String bookPath = Config.getProperty("hospitalJSON");
        try {
            fileOutputStream = new FileOutputStream(bookPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getHospitalList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * gets equipmentList
     *
     * @return value of equipment
     */
    private static List<Equipment> getEquipmentList() {
        return equipmentList;
    }
    /**
     * gets hospitalList
     *
     * @return value of Hospital
     */
    private static List<Hospital> getHospitalList() {
        return hospitalList;
    }
    /**
     * gets clientList
     *
     * @return value of clientList
     */
    private static List<Client> getClientList() {
        return clientList;
    }
    /**
     * sets equipmentList
     *
     * @param equipmentList the value to set
     */
    private static void setEquipmentList(List<Equipment> equipmentList) {
        DataHandler.equipmentList = equipmentList;
    }
    /**
     * sets hospitalList
     *
     * @param hospitalList the value to set
     */
    private static void setHospitalList(List<Hospital> hospitalList) {
        DataHandler.hospitalList = hospitalList;
    }
    /**
     * sets clientList
     *
     * @param clientList the value to set
     */
    private static void setClientList(List<Client> clientList) {
        DataHandler.clientList = clientList;
    }
}