package ch.bzz.hospital.data;

import ch.bzz.hospital.model.Equipment;
import ch.bzz.hospital.model.User;
import ch.bzz.hospital.service.Config;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class UserData {
    private static final UserData instance = new UserData();

    /**
     * finds a user by username / password
     * @param username
     * @param password
     * @return User object / null = not found
     */
    public static User findUser(String username, String password) {
        User user = new User();
        List<User> userList = readJson();
        // New encrypted password
        password = DigestUtils.sha256Hex(password);
        for (User entry: userList) {
            if (entry.getName().equals(username) &&
        entry.getPassword().equals(password)) {
                user = entry;
            }
        }
        return user;
    }

    /**
     * reads the json file as a List
     * @return userList
     */
    private static List<User> readJson() {
        List<User> userList = new ArrayList<>();
        try {
            String path = Config.getProperty("userJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            User[] user = objectMapper.readValue(jsonData, User[].class);
            for (User e : user) {
                userList.add(e);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return userList;
    }
}
