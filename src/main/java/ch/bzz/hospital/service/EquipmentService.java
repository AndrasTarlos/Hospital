package ch.bzz.hospital.service;

import ch.bzz.hospital.annotation.UniqueEquipmentName;
import ch.bzz.hospital.data.DataHandler;
import ch.bzz.hospital.model.Client;
import ch.bzz.hospital.model.Equipment;

import javax.validation.Valid;
import javax.validation.constraints.Size;
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
    public Response listEquipment(
            @CookieParam("userRole") String userRole
    ) {
        Response response;
        int httpStatus;
        List<Equipment> equipmentList = DataHandler.getInstance().readAllEquipment();
        if (userRole == null || userRole.equals("guest") || !userRole.equals("user") && !userRole.equals("admin")) {
            httpStatus = 403;
            response = Response
                    .status(httpStatus)
                    .entity("")
                    .build();
        } else {
            httpStatus = 200;
            response = Response
                    .status(httpStatus)
                    .entity(equipmentList)
                    .build();
        }
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
            @QueryParam("name") String name,
            @CookieParam("userRole") String userRole
    ) {
        List<Equipment> equipment = DataHandler.getInstance().readEquipmentByName(name);
        Response response;
        if (userRole == null || userRole.equals("guest") || !userRole.equals("user") && !userRole.equals("admin")) {
            response = Response
                    .status(403)
                    .build();
        } else if (equipment == null || equipment.size() == 0) {
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
    public Response sortEquipment(
            @CookieParam("userRole") String userRole
    ) {
        Response response;
        List<Equipment> equipment = DataHandler.getInstance().readSortedEquipment();
        if (userRole == null || userRole.equals("guest") || !userRole.equals("user") && !userRole.equals("admin")) {
            response = Response
                    .status(403)
                    .entity("")
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
     * deletes a Equipment identified by its name
     * @param name the key
     * @return Response
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteEquipment(
            @QueryParam("name") String name,
            @CookieParam("userRole") String userRole
    ) {
        int httpStatus = 0;
        if (userRole.equals("admin")) {
            httpStatus = 200;
            if (!DataHandler.getInstance().deleteEquipment(name)) {
                httpStatus = 410;
            }
        } else {
            httpStatus = 403;
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
            @Valid @BeanParam Equipment equipment,
            @CookieParam("userRole") String userRole
    ) {

        int httpStatus = 0;
        if (userRole.equals("admin")) {
            httpStatus = 200;
            Equipment oldEquipment = DataHandler.getInstance().readEquipmentByName(equipment.getName()).get(0);

            if (oldEquipment != null) {
                if (equipment.getName() != null)
                    oldEquipment.setName(equipment.getName());
                if (equipment.getAmount() != null)
                    oldEquipment.setAmount(equipment.getAmount());
                if (equipment.getStorageRoom() != null)
                    oldEquipment.setStorageRoom(equipment.getStorageRoom());
                if (equipment.getDescription() != null)
                    oldEquipment.setDescription(equipment.getDescription());


                DataHandler.updateEquipment();
            } else {
                httpStatus = 410;
            }
        } else {
            httpStatus = 403;
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
            @Valid @BeanParam Equipment equipment,
            @UniqueEquipmentName()
            @Size(max = 80)
            @FormParam("name") String name,
            @CookieParam("userRole") String userRole

    ) {
        int httpStatus = 0;
        if (userRole.equals("admin")) {
            httpStatus = 200;
            DataHandler.getInstance().insertEquipment(equipment);
        } else {
            httpStatus = 403;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }
}