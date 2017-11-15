
package gdt.user;

import java.io.Serializable;


public class User implements Serializable{
    private long id;
    private String motDePasse;
    private String pseudo;
    
    protected User(long id,String motDePasse,String pseudo){
        this.id=id;
        setMotDePasse( motDePasse);
        this.pseudo=pseudo;
    }

    public boolean passwordIsCorrect( String password){
        return (motDePasse.equals( password));
    }
    
    
   //getter 
    public long getId() {
        return id;
    }
    public String getPseudo() {
        return pseudo;
    }
    
    //setter
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
    
    
    
    
    
}
