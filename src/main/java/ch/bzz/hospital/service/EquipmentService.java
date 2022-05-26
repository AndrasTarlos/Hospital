package ch.bzz.hospital.service;

import ch.bzz.hospital.data.DataHandler;
import ch.bzz.hospital.model.Equipment;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
        Equipment equipment = DataHandler.getInstance().readEquipmentByName(name);
        Response response;
        if (equipment == null) {
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
}
