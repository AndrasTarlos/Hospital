package ch.bzz.hospital.service;

import ch.bzz.hospital.data.DataHandler;
import ch.bzz.hospital.model.Client;

import javax.validation.Valid;
import javax.ws.rs.*;
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
     * @param firstname of the searched client
     * @param name of the searched client
     * @return the found Client by the given params
     */
    @Path("read")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response readClient(
            @QueryParam("firstname") String firstname,
            @QueryParam("name") String name
    ) {
        List<Client> clients = DataHandler.getInstance().readClientByName(firstname, name);
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
    public Response readClient() {
        List<Client> clients = DataHandler.getInstance().readSortedClient();
        Response response = Response
                .status(200)
                .entity(clients)
                .build();
        return response;
    }

    /**
     * deletes a Client identified by his/hers forename and name
     * @param firstname and name the key
     * @return Response
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteClient(
            @QueryParam("firstname") String firstname,
            @QueryParam("name") String name) {
        int httpStatus = 200;
        if (!DataHandler.getInstance().deleteClient(firstname, name)) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * updates a new client
     * @param client
     * @return
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateClient(
            @Valid @BeanParam Client client
    ) {
        try {
            int httpStatus = 200;

            Client oldClient = DataHandler.getInstance().readClientByName(client.getFirstname(), client.getName()).get(0);
            if (oldClient != null) {
                if (client.getFirstname() != null)
                    oldClient.setFirstname(client.getFirstname());
                if (client.getName() != null)
                    oldClient.setName(client.getName());
                if (client.getSex() != null)
                    oldClient.setSex(client.getSex());
                if (client.getPhoneNumber() != null)
                    oldClient.setPhoneNumber(client.getPhoneNumber());
                if (client.getBill() != null)
                    oldClient.setBill(client.getBill());
                if (client.getCheckin() != null)
                    oldClient.setCheckin(client.getCheckin());
                if (client.getAhvNumber() != null)
                    oldClient.setAhvNumber(client.getAhvNumber());

                DataHandler.updateClient();
            } else {
                httpStatus = 410;
            }

            return Response
                    .status(httpStatus)
                    .entity("")
                    .build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Response
                .status(400)
                .entity("")
                .build();
    }

    /**
     * insert a new client
     * @return
     */

    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createClient(
            @Valid @BeanParam Client client
    ) {

        DataHandler.getInstance().insertClient(client);
        return Response
                .status(200)
                .entity("")
                .build();
    }
}