package gdt.assets;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.io.Serializable;
import java.util.List;

public class ProjectList implements Serializable{

    private ListProperty<ObjectProperty<Project>> projects;

    public ProjectList(){
        projects = new SimpleListProperty<>();
    }

    public List<ObjectProperty<Project>> getProjects( ){
        return projects.get();
    }

    public ObjectProperty<Project> getProject( int index){
        return projects.get( index);
    }

    public ListProperty<ObjectProperty<Project>> projectsProperty() {
        return projects;
    }

    public void setProject( int index, Project project){
        ObjectProperty<Project> projectp = new SimpleObjectProperty<>();
        projectp.set( project);
        projects.set( index, projectp);
    }

    public void removeProject( int index){
        projects.remove( index);
    }

    public void addProject( Project project){
        setProject( projects.size(), project);
    }
}
