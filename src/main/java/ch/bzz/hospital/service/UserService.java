package ch.bzz.hospital.service;

import ch.bzz.hospital.data.UserData;
import ch.bzz.hospital.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("user")
public class UserService {
    @POST
    @Path("login")
    @Produces(MediaType.TEXT_PLAIN)
    public Response login(
            @FormParam("username") String username,
            @FormParam("password") String password
            )
    {
        int httpStatus;
        User user = UserData.findUser(username, password);
        if (user.getRole().equals("guest") || user.getRole() == null) {
            httpStatus = 404;
        } else {
            httpStatus = 200;
        }
        Response response = Response
                .status(httpStatus)
                .entity("")
                .build();
        return response;
    }

    /**
     * logout current user
     * @return Response object with guest-Cookie
     */
    @DELETE
    @Path("logout")
    @Produces(MediaType.TEXT_PLAIN)
    public Response logout() {
        Response response = Response
                .status(200)
                .entity("")
                .build();
        return response;
    }
}
