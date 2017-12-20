package gdt.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author Florian DELCROIX
 * @author Tom KAUFFELD
 * @version 1
 * @see gdt.user.User
 */
public class UserBase implements Serializable {

    private transient Random random = new Random(System.nanoTime());

    private ArrayList<User> users = new ArrayList<>();

    /**
     * Adds a new user
     *
     * @param password the password of the new user
     * @param username the username of the new user
     * @return true if the User could be added to the database, false if not
     */
    public boolean addUser(String password, String username) {
        if (usernameExists(username))
            return false;
        if (password.length() < 5)
            return false;
        if (username.length() < 3)
            return false;
        if (username.equals(password))
            return false;


        long id;
        do {
            id = random.nextLong();
        } while (idExists(id));

        users.add(new User(id, password, username));
        return true;
    }

    /**
     * Returns the User if the username is inside the database, and if the password is correct
     *
     * @param username the username of the user
     * @param password the password of the user
     * @return The user if he could 'login' correctly, null if not
     */
    public User connectUser(String username, String password) {
        User user = getUser(username);
        if (user != null && user.isPasswordCorrect(password))
            return user;
        return null;
    }

    /**
     * Checks if the id is already in use inside the database
     *
     * @param id the id to check
     * @return true if it already exists, false if not
     */
    private boolean idExists(long id) {
        for (User user : users)
            if (user.getId() == id)
                return true;
        return false;
    }

    /**
     * Checks if the username is already in use inside the database
     *
     * @param username the username to check
     * @return true if it already exists, false if not
     */
    private boolean usernameExists(String username) {
        return (getUser(username) != null);
    }

    /**
     * Returns the user with the username specified
     *
     * @param username the username of the user to find
     * @return the user if he's found, null otherwise
     */
    private User getUser(String username) {
        for (User user : users)
            if (user.getUsername().equals(username))
                return user;
        return null;
    }


}
