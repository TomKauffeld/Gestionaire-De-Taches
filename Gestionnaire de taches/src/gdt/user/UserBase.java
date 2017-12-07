/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdt.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Florian DELCROIX
 * @version 1
 * @see java.io.Serializable
 */
public class UserBase implements Serializable{
    
    private transient Random random = new Random( System.nanoTime());
    
    private ArrayList<User> users = new ArrayList<>();
    
    public boolean addUser( String password, String username){
        if (usernameExists( username))
            return false;
        if (password.length() < 5)
            return false;
        if (username.length() < 3)
            return false;
        if (username.equals(password))
            return false;
        
        
        long id;
        do{
            id = random.nextLong();
        }while (idExists( id));
        
        users.add( new User( id, password, username));
        return true;
    }
    
    public User connectUser( String username, String password){
        User user = getUser( username);
        if (user != null && user.isPasswordCorrect(password))
            return user;
        return null;
    }
    
    private boolean idExists( long id){
        for (User user : users)
            if (user.getId() == id)
                return true;
        return false;
    }
    
    private boolean usernameExists( String username){
        return (getUser(username) != null);
    }
    
    private User getUser( String username){
        for (User user : users)
            if (user.getUsername().equals(username))
                return user;
        return null;
    }
    
    
}
