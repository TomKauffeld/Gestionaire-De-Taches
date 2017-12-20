
package gdt.assets;

import gdt.save.SaveState;
import gdt.user.User;
import gdt.user.UserBase;
import javafx.beans.property.ListProperty;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDate;

public class TaskListFacade {


    private static final String PROJECTS_PATH = System.getProperty( "user.dir").replace( "\\", "/") + "/projects.bin";

    private static final String USERS_PATH = System.getProperty( "user.dir").replace( "\\", "/") + "/users.bin";

    private ProjectList projectList = new ProjectList();
    private User connectedUser;
    private UserBase userBase = new UserBase();

    public TaskListFacade(){
        loadProjects();
        loadUsers();
        setConnectedUser( User.GUEST_USER);
    }
    
    public void setConnectedUser( User user){
        connectedUser = user;
    }
    
    public User getConnectedUser( ){
        return connectedUser;
    }

    public void saveProjects( ){
        saveProjects( PROJECTS_PATH);
    }

    public void saveProjects( String path){
        new SaveState( projectList, path).start();
    }

    public void saveUsers( ){
        saveUsers( USERS_PATH);
    }

    public void saveUsers( String path){
        new SaveState( userBase, path).start();
    }

    public void loadProjects( ){
        loadProjects( PROJECTS_PATH);
    }

    public void loadProjects( String path){
        try{
            FileInputStream fi = new FileInputStream( path);
            ObjectInputStream in = new ObjectInputStream( fi);
            projectList = (ProjectList) in.readObject();
            in.close();
            fi.close();
        } catch (FileNotFoundException e) {
            System.err.println( e.getMessage() + ", creating new projectList");
            projectList = new ProjectList();
        } catch (IOException e) {
            System.err.println( e.getMessage() + ", creating new projectList");
            projectList = new ProjectList();
        } catch (ClassNotFoundException e) {
            System.err.println( e.getMessage() + ", creating new projectList");
            e.printStackTrace();
            projectList = new ProjectList();
        }
    }

    public void loadUsers( ){
        loadUsers( USERS_PATH);
    }

    public void loadUsers( String path){
        try{
            FileInputStream fi = new FileInputStream( path);
            ObjectInputStream in = new ObjectInputStream( fi);
            userBase = (UserBase) in.readObject();
            in.close();
            fi.close();
        } catch (FileNotFoundException e) {
            System.err.println( e.getMessage() + ", creating new userBase");
            userBase = new UserBase();
        } catch (IOException e) {
            System.err.println( e.getMessage() + ", creating new userBase");
            userBase = new UserBase();
        } catch (ClassNotFoundException e) {
            System.err.println( e.getMessage() + ", creating new userBase");
            e.printStackTrace();
            userBase = new UserBase();
        }
    }

    public void addNewProject( String title){
        if (isConnected())
            projectList.addProject( new Project( title, getConnectedUser( ).getId()));
        else
            projectList.addProject( new Project( title));
        saveProjects( );
    }

    public void addNewProject( String title, boolean visible){
        if (!isConnected())
            visible = true;
        projectList.addProject( new Project( title, getConnectedUser( ).getId(), visible));
        saveProjects( );
    }

    public void addNewTask( Project project, String title, String description, boolean done, LocalDate beginDate, LocalDate endDate){
        if (getConnectedUser( ).getId() == project.getUserId() || project.getUserId() == User.GUEST_ID)
            project.addTask( new Task( title, description, done, beginDate, endDate));
        saveProjects( );
    }

    public boolean isConnected( ){
        return (getConnectedUser( ).getId() != User.GUEST_ID);
    }

    public ListProperty<Project> projectsProperty(){
        return projectList.projectsProperty();
    }
    
    public boolean addNewUser(String password, String username){
        boolean ret = userBase.addUser(password, username);
        if (ret)
            saveUsers();
        return ret;
    }
    
    public boolean connection(String password, String username){
        setConnectedUser( userBase.connectUser(username, password));
        if (getConnectedUser( ) == null){
            setConnectedUser( User.GUEST_USER);
            return false;
        }
        return true;
    }

}
