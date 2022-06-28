package ch.bzz.hospital.service;

import ch.bzz.hospital.data.DataHandler;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * <h1>TestService</h1>
 *
 * @author Andras Tarlos
 * @since 2022.05.24
 * @version 0.1
 *
 * Test services with a restore service
 */
@Path("test")
public class TestService {

    /**
     * confirms the application runs
     * @return  message
     */
    @GET
    @Path("test")
    @Produces(MediaType.TEXT_PLAIN)
    public Response test() {

        return Response
                .status(200)
                .entity("Test erfolgreich")
                .build();
    }

    /**
     * restores the json-files
     * @return Response
     */
    @GET
    @Path("restore")
    @Produces(MediaType.TEXT_PLAIN)
    public Response restore() {
        try {
            java.nio.file.Path path = Paths.get(Config.getProperty("clientJSON"));
            String filename = path.getFileName().toString();
            String folder = path.getParent().toString();

            byte[] clientJSON = Files.readAllBytes(Paths.get(folder, "backup", filename));
            FileOutputStream fileOutputStream = new FileOutputStream(Config.getProperty("clientJSON"));
            fileOutputStream.write(clientJSON);

            path = Paths.get(Config.getProperty("equipmentJSON"));
            filename = path.getFileName().toString();
            folder = path.getParent().toString();

            byte[] equipmentJSON = Files.readAllBytes(Paths.get(folder, "backup", filename));
            fileOutputStream = new FileOutputStream(Config.getProperty("equipmentJSON"));
            fileOutputStream.write(equipmentJSON);

            path = Paths.get(Config.getProperty("hospitalJSON"));
            filename = path.getFileName().toString();
            folder = path.getParent().toString();

            byte[] hospitalJSON = Files.readAllBytes(Paths.get(folder, "backup", filename));
            fileOutputStream = new FileOutputStream(Config.getProperty("hospitalJSON"));
            fileOutputStream.write(hospitalJSON);

            path = Paths.get(Config.getProperty("userJSON"));
            filename = path.getFileName().toString();
            folder = path.getParent().toString();

            byte[] userJSON = Files.readAllBytes(Paths.get(folder, "backup", filename));
            fileOutputStream = new FileOutputStream(Config.getProperty("userJSON"));
            fileOutputStream.write(userJSON);

        } catch (IOException e) {
            e.printStackTrace();
        }

        DataHandler.initLists();
        return Response
                .status(200)
                .entity("Erfolgreich")
                .build();
    }
}