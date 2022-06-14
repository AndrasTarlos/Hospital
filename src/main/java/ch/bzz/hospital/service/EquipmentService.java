package ch.bzz.hospital.service;

import ch.bzz.hospital.data.DataHandler;
import ch.bzz.hospital.model.Equipment;

import javax.validation.Valid;
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
    public Response readEquipment(
            @QueryParam("name") String name
    ) {
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
    public Response deleteEquipment(@QueryParam("name") String name) {
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
     * updates a new equipment
     * @param equipment
     * @return Response
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateEquipment(
            @Valid @BeanParam Equipment equipment
    ) {

        int httpStatus = 200;
        Equipment oldEquipment = DataHandler.getInstance().readEquipmentByName(equipment.getName()).get(0);

        if (oldEquipment != null) {
            oldEquipment.setName(equipment.getName());
            oldEquipment.setAmount(equipment.getAmount());
            oldEquipment.setStorageRoom(equipment.getStorageRoom());
            oldEquipment.setDescription(equipment.getDescription());


            DataHandler.updateEquipment();
        } else {
            httpStatus = 410;
        }

        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * create a new Equipment
     * 
     * @return response
     */

    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createEquipment(
            @Valid @BeanParam Equipment equipment
    ) {

        DataHandler.getInstance().insertEquipment(equipment);
        return Response
                .status(200)
                .entity("")
                .build();
    }
}