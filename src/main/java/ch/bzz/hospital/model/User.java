package ch.bzz.hospital.model;

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
    private String uuid;
    private String name;
    private String password;
    private String role;

    /**
     * Basic constructor
     */
    public User() {
        setRole("guest");
    }

    /**
     * Get the userUUID
     * @return value of userUUID
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * Sets the userUUID
     * @param uuid
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * Gets the username
     * @return value of username
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the username
     * @param name the value to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the password
     * @return value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password
     * @param password the value to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the userRole
     * @return value of userRole
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the userRole
     * @param role the value to set
     */
    public void setRole(String role) {
        this.role = role;
    }
}