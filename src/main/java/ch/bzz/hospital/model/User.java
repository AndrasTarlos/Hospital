package ch.bzz.hospital.model;

import javax.print.attribute.HashAttributeSet;
import java.util.UUID;
/**
 * <h1>User</h1>
 *
 * @author Andras Tarlos
 * @since 2022.05.24
 * @version 0.1
 *
 * A class for saving User accessing the webservice
 */
public class User {
    private UUID userUUID;
    private String userName;
    private HashAttributeSet password;
    private String userRole;
    /**
     * Under construction
     */
    public void logon() {

    }
    /**
     * Under construction
     */
    public void logoff() {

    }
}