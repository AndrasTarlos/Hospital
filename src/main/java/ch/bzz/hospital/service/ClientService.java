package ch.bzz.hospital.service;

import ch.bzz.hospital.data.DataHandler;
import ch.bzz.hospital.model.Client;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * <h1>ClientService</h1>
 *
 * @author Andras Tarlos
 * @since 2022.05.24
 * @version 0.1
 *
 * Webservice for the clients information
 */

@Path("client")
public class ClientService {
    /**
     * list all known clients
     * @return all clients
     */
    @Path("listAll")
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

    /**
     * search for a client
     * @param forename of the searched client
     * @param name of the searched client
     * @return the found Client by the given params
     */
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

    /**
     * sort the clients by their names
     * @return the sorted list
     */
    @Path("sortByName")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response readBook() {
        List<Client> clients = DataHandler.getInstance().readSortedClient();
        Response response = Response
                .status(200)
                .entity(clients)
                .build();
        return response;
    }
}