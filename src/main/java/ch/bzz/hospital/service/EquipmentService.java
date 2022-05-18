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

@Path("equipment")
public class EquipmentService {
    @Path("list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listBooks() {
        List<Equipment> equipmentList = DataHandler.getInstance().readAllEquipment();
        Response response = Response
                .status(200)
                .entity(equipmentList)
                .build();
        return response;
    }

    @Path("read")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response readBook(@QueryParam("name") String name) {
        Equipment book = DataHandler.getInstance().readEquipmentByName(name);
        Response response = Response
                .status(200)
                .entity(book)
                .build();
        return response;
    }
}
