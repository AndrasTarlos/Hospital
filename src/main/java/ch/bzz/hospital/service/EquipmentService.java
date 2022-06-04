package ch.bzz.hospital.service;

import ch.bzz.hospital.data.DataHandler;
import ch.bzz.hospital.model.Client;
import ch.bzz.hospital.model.Equipment;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * <h1>EquipmentService</h1>
 *
 * @author Andras Tarlos
 * @since 2022.05.24
 * @version 0.1
 *
 * Webservice for the equipment information
 */

@Path("equipment")
public class EquipmentService {
    /**
     * returns all the known equipments
     * @return all equipments
     */
    @Path("list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listEquipment() {
        List<Equipment> equipmentList = DataHandler.getInstance().readAllEquipment();
        Response response = Response
                .status(200)
                .entity(equipmentList)
                .build();
        return response;
    }

    /**
     * searches for equipment with the searched name
     * @param name of the equipment
     * @return the found equipment by the given param
     */
    @Path("read")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response readEquipment(@QueryParam("name") String name) {
        List<Equipment> equipment = DataHandler.getInstance().readEquipmentByName(name);
        Response response;
        if (equipment == null || equipment.size() == 0) {
            response = Response
                    .status(404)
                    .build();
        } else {
            response = Response
                    .status(200)
                    .entity(equipment)
                    .build();
        }
        return response;
    }

    /**
     * returns the sorted list of equipment
     * @return sorted list
     */
    @Path("sortByAmount")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response sortEquipment() {
        List<Equipment> equipment = DataHandler.getInstance().readSortedEquipment();
        Response response = Response
                .status(200)
                .entity(equipment)
                .build();
        return response;
    }

    /**
     * deletes a Equipment identified by its name
     * @param name the key
     * @return Response
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteBook(@QueryParam("name") String name) {
        int httpStatus = 200;
        if (!DataHandler.getInstance().deleteEquipment(name)) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * create a new Equipment
     * @param name
     * @param description
     * @param amount
     * @param storageRoom
     * @return response
     */

    @PUT
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createBook(
            @FormParam("name") String name,
            @FormParam("description") String description,
            @FormParam("amount") Integer amount,
            @FormParam("storageRoom") String storageRoom
    ) {
        Equipment equipment = new Equipment();
        equipment.setName(name);
        equipment.setDescription(description);
        equipment.setAmount(amount);
        equipment.setStorageRoom(storageRoom);

        DataHandler.getInstance().insertEquipment(equipment);
        return Response
                .status(200)
                .entity("")
                .build();
    }
}
