package ch.bzz.hospital.service;

import ch.bzz.hospital.data.DataHandler;
import ch.bzz.hospital.model.Client;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

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
        List<Client> clients = DataHandler.getInstance().readClientByName(forename, name);
        Response response;
        if (clients == null || clients.size() == 0) {
            response = Response
                    .status(404)
                    .build();
        } else {
            response = Response
                    .status(200)
                    .entity(clients)
                    .build();
        }
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

    /**
     * deletes a Client identified by his/hers forename and name
     * @param forename and name the key
     * @return Response
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteBook(
            @QueryParam("forename") String forename,
            @QueryParam("name") String name) {
        int httpStatus = 200;
        if (!DataHandler.getInstance().deleteClient(forename, name)) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * insert a new client
     * @param forename
     * @param name
     * @param sex
     * @param condition
     * @param phonenumber
     * @param bill
     * @return
     */

    @PUT
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createBook(
            @FormParam("forename") String forename,
            @FormParam("name") String name,
            @FormParam("sex") String sex,
            @FormParam("condition") String condition,
            @FormParam("phoneNumber") String phonenumber,
            @FormParam("bill") Double bill
    ) {
        Client client = new Client();
        client.setForename(forename);
        client.setName(name);
        client.setSex(sex);
        client.setCondition(condition);
        client.setPhoneNumber(phonenumber);
        client.setBill(bill);

        DataHandler.getInstance().insertClient(client);
        return Response
                .status(200)
                .entity("")
                .build();
    }
}