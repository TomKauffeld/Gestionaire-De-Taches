package gdt.assets;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

/**
 *  @author Tom KAUFFELD
 *  @version 1
 *  @see javafx.beans.property.Property
 *  @see gdt.assets.Project
 */
public class ProjectList implements Serializable{

    private transient ListProperty<Project> projects;

    // /////////// //
    // Constructor //
    // /////////// //

    /**
     * Creates an ProjectList
     */
    public ProjectList(){
        projects = new SimpleListProperty<>(FXCollections.observableArrayList());
    }

    // //////// //
    // Property //
    // //////// //

    /**
     * Gets the ListProperty of the project of the list
     * @return the ListProperty of Project(s) (projects)
     */
    public ListProperty<Project> projectsProperty() {
        return projects;
    }

    // /////// //
    // Getters //
    // /////// //

    /**
     * Gets all the projects from the list
     * @return a List of projects
     */
    public List<Project> getProjects( ){
        return projects.get();
    }

    /**
     * Returns the project at the index specified
     * @param index the index to take the project from
     * @return
     */
    public Project getProject( int index){
        return projects.get( index);
    }

    // ////// //
    // Setter //
    // ////// //

    /**
     * Adds an project to the list
     * @param project the project to add to the list
     */
    public void addProject( Project project){
        projects.add(project);
    }

    // ////// //
    // Method //
    // ////// //

    /**
     * Removes an Project located at the index specified
     * @param index the index of the project to remove
     */
    public void removeProject( int index){
        projects.remove( index);
    }

    // ////////// //
    // Read/Write //
    // ////////// //

    /**
     * writes the ProjectList to the ObjectOutputStream
     * @param out the ObjectOutputStream to write the ProjectList to
     * @throws IOException
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        if(projects==null || projects.getValue()==null) {
            out.writeInt(0);
            return;
        }
        out.writeInt( projects.size());
        for(Project project : projects)
            out.writeObject(project);
    }

    /**
     * reads an ProjectList from an ObjectInputStream
     * @param in the ObjectInputStream to read the ProjectList from
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
        projects = new SimpleListProperty<>(FXCollections.observableArrayList());
        int nbList = in.readInt();
        for (int i = 0; i < nbList; i++)
            projects.add( (Project) in.readObject());
    }
}
