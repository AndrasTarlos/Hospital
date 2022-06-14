package ch.bzz.hospital.service;

import ch.bzz.hospital.data.DataHandler;
import ch.bzz.hospital.model.Hospital;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * <h1>HospitalService</h1>
 *
 * @author Andras Tarlos
 * @since 2022.05.24
 * @version 0.1
 *
 * Webservice for the hospital information
 */

@Path("hospital")
public class HospitalService {
    /**
     * returns all hospitals
     * @return all known hospitals
     */
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

    /**
     * searches for a hospital with the searched name
     * @param name of the hospital
     * @return matching hospital
     */
    @Path("read")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response readHospital(@QueryParam("name") String name) {
        List<Hospital> hospital = DataHandler.getInstance().readHospitalByName(name);
        Response response;
        if (hospital.size() != 0) {
            response = Response
                    .status(200)
                    .entity(hospital)
                    .build();
        } else {
            response = Response
                    .status(404)
                    .build();
        }
        return response;
    }

    /**
     * deletes a Hospital identified by its name
     * @param name the key
     * @return Response
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteHospital(
            @QueryParam("name") String name
    ) {
        int httpStatus = 200;
        if (!DataHandler.getInstance().deleteHospital(name)) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * updates a new hospital
     * @param hospital
     * @return Response
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateHospital(
            @Valid @BeanParam Hospital hospital
    ) {

        int httpStatus = 200;
        Hospital oldHospital = DataHandler.getInstance().readHospitalByName(hospital.getName()).get(0);

        if (oldHospital != null) {
            if (hospital.getName() != null)
                oldHospital.setName(hospital.getName());
            if (hospital.getNumberOfEmployees() != null)
                oldHospital.setNumberOfEmployees(hospital.getNumberOfEmployees());
            if (hospital.getOwner() != null)
                oldHospital.setOwner(hospital.getOwner());
            if (hospital.getAddress() != null)
                oldHospital.setAddress(hospital.getAddress());

            DataHandler.updateHospital();
        } else {
            httpStatus = 410;
        }

        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * insert a new hospital
     * @return
     */

    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createHospital(
            @Valid @BeanParam Hospital hospital
    ) {

        DataHandler.getInstance().insertHospital(hospital);
        return Response
                .status(200)
                .entity("")
                .build();
    }
}