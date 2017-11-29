package gdt.assets;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;

import java.io.Serializable;
import java.util.List;

public class ProjectList implements Serializable{

    private ListProperty<Project> projects;

    public ProjectList(){
        projects = new SimpleListProperty<>();
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

    public void setProject( int index, Project project){
        projects.set( index, project);
    }

    public void removeProject( int index){
        projects.remove( index);
    }

    public void addProject( Project project){
        setProject( projects.size(), project);
    }
}
