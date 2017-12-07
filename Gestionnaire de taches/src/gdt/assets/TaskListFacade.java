package gdt.assets;

import gdt.user.User;

import java.time.LocalDate;

public class TaskListFacade {

    private ProjectList projectList = new ProjectList();
    private User connectedUser = User.GUEST_USER;

    public TaskListFacade(){

    }

    public void addNewProject( String title){
        if (isConnected())
            projectList.addProject( new Project( title, connectedUser.getId()));
        else
            projectList.addProject( new Project( title));
    }

    public void addNewProject( String title, boolean visible){
        projectList.addProject( new Project( title, connectedUser.getId(), visible));
    }

    public void addNewTask( Project project, String title, String description, boolean done, LocalDate beginDate, LocalDate endDate){
        if (connectedUser.getId() == project.getUserId() || project.getUserId() == User.GUEST_ID)
            project.addTask( new Task( title, description, done, beginDate, endDate));
    }

    public boolean isConnected( ){
        return (connectedUser.getId() != User.GUEST_ID);
    }

}
