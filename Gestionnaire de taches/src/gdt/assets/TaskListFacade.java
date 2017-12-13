
package gdt.assets;

import gdt.user.User;
import gdt.user.UserBase;
import javafx.beans.property.ListProperty;

import java.io.*;
import java.time.LocalDate;

public class TaskListFacade {

    private ProjectList projectList = new ProjectList();
    private User connectedUser = User.GUEST_USER;
    private UserBase userBase = new UserBase();

    public TaskListFacade(){

    }

    public void saveProjects( String path){
        try{
            FileOutputStream fo = new FileOutputStream( path);
            ObjectOutputStream out = new ObjectOutputStream( fo);
            out.writeObject( projectList);
            out.close();
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadProjects( String path){
        try{
            FileInputStream fi = new FileInputStream( path);
            ObjectInputStream in = new ObjectInputStream( fi);
            projectList = (ProjectList) in.readObject();
            in.close();
            fi.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addNewProject( String title){
        if (isConnected())
            projectList.addProject( new Project( title, connectedUser.getId()));
        else
            projectList.addProject( new Project( title));
    }

    public void addNewProject( String title, boolean visible){
        if (!isConnected())
            visible = true;
        projectList.addProject( new Project( title, connectedUser.getId(), visible));
    }

    public void addNewTask( Project project, String title, String description, boolean done, LocalDate beginDate, LocalDate endDate){
        if (connectedUser.getId() == project.getUserId() || project.getUserId() == User.GUEST_ID)
            project.addTask( new Task( title, description, done, beginDate, endDate));
    }

    public boolean isConnected( ){
        return (connectedUser.getId() != User.GUEST_ID);
    }

    public ListProperty<Project> projectsProperty(){
        return projectList.projectsProperty();
    }
    
    public boolean addNewUser(String password, String username){
        return userBase.addUser(password, username);
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
