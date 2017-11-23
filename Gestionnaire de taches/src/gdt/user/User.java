
package gdt.user;

import java.io.Serializable;


public class User implements Serializable{

    public static final long GUEST_ID = -1;

    private long id;
    private String password;
    private String username;
    
    protected User( long id,String password,String username){
        setId( id);
        setPassword( password);
        setUsername( username);
    }

    public boolean isPasswordCorrect( String password){
        return (getPassword().equals( password));
    }

   // Getters
    public long getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    private String getPassword( ){
        return password;
    }
    
    // Setters
    private void setId( long id){
        this.id = id;
    }
    private void setPassword( String password) {
        this.password = password;
    }
    private void setUsername(String username) {
        this.username = username;
    }
    
    
    
    
    
}
