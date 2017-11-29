package gdt.assets;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;

import java.io.Serializable;
import java.util.List;
import javafx.collections.FXCollections;

public class ProjectList implements Serializable{

    private ListProperty<Project> projects;

    public ProjectList(){
        projects = new SimpleListProperty<>(FXCollections.observableArrayList());
        addProject( new Project( "Salut"));
    }

    public List<Project> getProjects( ){
        return projects.get();
    }

    public Project getProject( int index){
        return projects.get( index);
    }

    public ListProperty<Project> projectsProperty() {
        return projects;
    }

    public void removeProject( int index){
        projects.remove( index);
    }

    public void addProject( Project project){
        projects.add(project);
    }
}
