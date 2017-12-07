package gdt.user;

import security.PasswordHash;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

/**
 *  @author Tom KAUFFELD
 *  @version 1
 *  @see java.io.Serializable
 */
public class User implements Serializable{

    public static final long GUEST_ID = -1;
    public static final User GUEST_USER = new User( GUEST_ID, "", "guest");

    private long id;
    private String password;
    private String username;

    // /////////// //
    // Constructor //
    // /////////// //

    /**
     * Creates an User
     * @param id        the id of the user
     * @param password  the password of the user
     * @param username  the username of the user
     */
    protected User( long id,String password,String username){
        setId( id);
        setPassword( password);
        setUsername( username);
    }

    // /////// //
    // Methods //
    // /////// //
    /**
     * Checks the password of the user with the one given as parameter
     * @param password the password to check
     * @return  true    if the password is correct
     *          false   if the password is incorrect
     */
    public boolean isPasswordCorrect( String password){
        try {
            return PasswordHash.verify( password, this.password);
        } catch (NoSuchAlgorithmException e) {
            return false;
        }
    }

    // /////// //
    // Getters //
    // /////// //
    /**
     * Getter for the user ID
     * @return the user ID of the user
     */
    public long getId() {
        return id;
    }

    /**
     * Getter for the username
     * @return  the username of the user
     */
    public String getUsername() {
        return username;
    }

    // /////// //
    // Setters //
    // /////// //
    /**
     * sets the user ID
     * @param id the new id of the user
     */
    private void setId( long id){
        this.id = id;
    }

    /**
     * sets the password of the user
     * @param password the new password for the user
     */
    private void setPassword( String password) {
        try {
            this.password = PasswordHash.sha256( password);
        } catch (NoSuchAlgorithmException e) {
            this.password = "ERROR";
        }
    }

    /**
     * sets the username of the user
     * @param username the new username for the user
     */
    private void setUsername( String username) {
        this.username = username;
    }
}
