
package gdt.assets;

import gdt.save.SaveState;
import gdt.user.User;
import gdt.user.UserBase;
import javafx.beans.property.ListProperty;

import java.io.*;
import java.time.LocalDate;

public class TaskListFacade {


    private static final String PROJECTS_PATH = System.getProperty( "user.dir").replace( "\\", "/") + "/projects.bin";

    private static final String USERS_PATH = System.getProperty( "user.dir").replace( "\\", "/") + "/users.bin";

    private ProjectList projectList = new ProjectList();
    private User connectedUser = User.GUEST_USER;
    private UserBase userBase = new UserBase();

    public TaskListFacade(){
        loadProjects();
        loadUsers();
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
            projectList.addProject( new Project( title, connectedUser.getId()));
        else
            projectList.addProject( new Project( title));
        saveProjects( );
    }

    public void addNewProject( String title, boolean visible){
        if (!isConnected())
            visible = true;
        projectList.addProject( new Project( title, connectedUser.getId(), visible));
        saveProjects( );
    }

    public void addNewTask( Project project, String title, String description, boolean done, LocalDate beginDate, LocalDate endDate){
        if (connectedUser.getId() == project.getUserId() || project.getUserId() == User.GUEST_ID)
            project.addTask( new Task( title, description, done, beginDate, endDate));
        saveProjects( );
    }

    public boolean isConnected( ){
        return (connectedUser.getId() != User.GUEST_ID);
    }

    public ListProperty<Project> projectsProperty(){
        return projectList.projectsProperty();
    }
    
    public boolean addNewUser(String password, String username){
        System.out.println( "new");
        boolean ret = userBase.addUser(password, username);
        System.out.println( "te");
        if (ret)
            saveUsers();
        return ret;
    }
    
    public boolean connection(String password, String username){
        connectedUser = userBase.connectUser(username, password);
        if (connectedUser == null){
            connectedUser = User.GUEST_USER;
            return false;
        }
        return true;
    }

}
