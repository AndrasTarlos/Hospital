package ch.bzz.hospital.service;

import ch.bzz.hospital.data.DataHandler;
import ch.bzz.hospital.model.Client;
import ch.bzz.hospital.model.Equipment;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("client")
public class ClientService {
    @Path("list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listClients() {
        List<Client> clientList = DataHandler.getInstance().readAllClients();
        Response response = Response
                .status(200)
                .entity(clientList)
                .build();
        return response;
    }

    @Path("read")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response readBook(@QueryParam("forename") String forename, @QueryParam("name") String name) {
        Client client = DataHandler.getInstance().readClientByName(forename, name);
        Response response = Response
                .status(200)
                .entity(client)
                .build();
        return response;
    }
}