package gdt.assets;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;

import java.io.Serializable;
import java.util.List;

public class ProjectList implements Serializable{

    private ListProperty<ObjectProperty<Project>> projets;

    public ProjectList(){

    }

    public List<ObjectProperty<Project>> getProjets( ){
        return projets.get();
    }

    public ObjectProperty<Project> getProjet( int index){
        return projets.get( index);
    }

}
