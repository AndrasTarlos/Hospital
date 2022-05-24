package ch.bzz.hospital.service;

import ch.bzz.hospital.data.DataHandler;
import ch.bzz.hospital.model.Equipment;
import ch.bzz.hospital.model.Hospital;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("hospital")
public class HospitalService {
    @Path("list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listHospital() {
        List<Hospital> hospitalList = DataHandler.getInstance().readAllHospitals();
        Response response = Response
                .status(200)
                .entity(hospitalList)
                .build();
        return response;
    }

    @Path("read")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response readEquipment(@QueryParam("name") String name) {
        Equipment equipment = DataHandler.getInstance().readEquipmentByName(name);
        Response response = Response
                .status(200)
                .entity(equipment)
                .build();
        return response;
    }
}
