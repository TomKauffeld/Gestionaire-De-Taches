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
    
    public void addUser( String password, String username){
        
        
    }
    
    
}
