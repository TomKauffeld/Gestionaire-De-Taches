package gdt.assets;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;
import javafx.collections.FXCollections;

public class ProjectList implements Serializable{

    private ListProperty<Project> projects;

    private void writeObject(ObjectOutputStream out) throws IOException {
        if(projects==null || projects.getValue()==null) {
            out.writeInt(0);
            return;
        }
        out.writeInt( projects.size());
        for(Project project : projects)
            out.writeObject(project);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
        projects = new SimpleListProperty<>(FXCollections.observableArrayList());
        int nbList = in.readInt();
        for (int i = 0; i < nbList; i++)
            projects.add( (Project) in.readObject());
    }

    public ProjectList(){
        projects = new SimpleListProperty<>(FXCollections.observableArrayList());
        addProject( new Project( "Salut"));
        addProject( new Project("bye"));
        getProject(0).addTask(new Task("bonjour", "hello", false, null, null));
        getProject(0).addTask(new Task("tache2", "hello", false, null, null));
        getProject(1).addTask(new Task("aurevoir", "adieu", true, null, null));
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
