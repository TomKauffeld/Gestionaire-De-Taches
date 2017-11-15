
package gdt.assets;

import java.time.LocalDate;


public class Tache {
    private String titre;
    private String description;
    private Boolean fini;
    private long id;
    private LocalDate begin;
    private LocalDate end;
    
    protected Tache(String titre,String description,Boolean fini,long id,LocalDate begin,LocalDate end){
        this.titre=titre;
        this.description=description;
        this.fini=fini;
        this.id=id;
        this.begin=begin;
        this.end=end;
    }
    //getter
    protected String getTitre(){ 
        return titre;
    }
    protected String getDescription(){
        return description;
    }
    protected Boolean getFini(){
        return fini;
    }
    protected long getId(){
        return id;
    }
    protected LocalDate getBegin(){
        return begin;
    }
    protected LocalDate getEnd(){
        return end;
    }
    
    //setter
    protected void setTitre(String titre){
        this.titre=titre;
    }
    protected void setDescription(String description){
        this.description=description;
    }
    protected void setFini(Boolean fini){
        this.fini=fini;
    }
    protected void setBegin(LocalDate begin){
        this.begin=begin;
    }
    protected void setEnd(LocalDate end){
        this.end = end;
    }
    
}
