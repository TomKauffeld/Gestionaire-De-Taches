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

/**
 * @author Florian DELCROIX
 * @author Tom KAUFFELD
 * @version 1
 * @see gdt.save.SaveState
 * @see gdt.assets.Project
 * @see gdt.assets.ProjectList
 * @see gdt.user.User
 * @see gdt.user.UserBase
 */
public class TaskListFacade {

    private static final String PROJECTS_PATH = System.getProperty("user.dir").replace("\\", "/") + "/projects.bin";

    private static final String USERS_PATH = System.getProperty("user.dir").replace("\\", "/") + "/users.bin";

    private ProjectList projectList = new ProjectList();
    private User connectedUser;
    private UserBase userBase = new UserBase();

    // /////////// //
    // Constructor //
    // /////////// //

    /**
     * Creates an TaskListFacade
     */
    public TaskListFacade() {
        loadProjects();
        loadUsers();
        setConnectedUser(User.GUEST_USER);
    }

    // //////////////////// //
    // User related Methods //
    // //////////////////// //

    /**
     * Gets the connected User
     *
     * @return the user that is connected
     */
    public User getConnectedUser() {
        return connectedUser;
    }

    /**
     * Sets the connected User to the user specified
     *
     * @param user the user to 'connect'
     */
    public void setConnectedUser(User user) {
        connectedUser = user;
    }

    /**
     * Adds a new User to the userBase
     *
     * @param password the password of the new User
     * @param username the username of the new User
     * @return true if the user could be added, false otherwise
     */
    public boolean addNewUser(String password, String username) {
        boolean ret = userBase.addUser(password, username);
        if (ret)
            saveUsers();
        return ret;
    }

    /**
     * Connects an User
     *
     * @param password the password of the user
     * @param username the username of the user
     * @return true if the connection was successful, false otherwise
     */
    public boolean connection(String password, String username) {
        setConnectedUser(userBase.connectUser(username, password));
        if (getConnectedUser() == null) {
            setConnectedUser(User.GUEST_USER);
            return false;
        }
        return true;
    }

    /**
     * Saves the userBase to the default path
     */
    public void saveUsers() {
        saveUsers(USERS_PATH);
    }

    /**
     * Saves the userBase to the path specified
     *
     * @param path the path to save the UserBase to
     */
    public void saveUsers(String path) {
        new SaveState(userBase, path).start();
    }

    /**
     * Loads the userBase from the default path
     */
    public void loadUsers() {
        loadUsers(USERS_PATH);
    }

    /**
     * Loads the userBase from the path specified
     *
     * @param path the path to load the userBase from
     */
    public void loadUsers(String path) {
        try {
            FileInputStream fi = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fi);
            userBase = (UserBase) in.readObject();
            in.close();
            fi.close();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage() + ", creating new userBase");
            userBase = new UserBase();
        } catch (IOException e) {
            System.err.println(e.getMessage() + ", creating new userBase");
            userBase = new UserBase();
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage() + ", creating new userBase");
            e.printStackTrace();
            userBase = new UserBase();
        }
    }

    /**
     * Checks if an user is connected
     *
     * @return true if an user is connected, false if not
     */
    public boolean isConnected() {
        return (getConnectedUser().getId() != User.GUEST_ID);
    }

    // /////////////////////// //
    // Project related Methods //
    // /////////////////////// //

    /**
     * Saves the projects to the default path
     */
    public void saveProjects() {
        saveProjects(PROJECTS_PATH);
    }

    /**
     * Saves the projects to the path specified
     *
     * @param path the path to save the projects to
     */
    public void saveProjects(String path) {
        new SaveState(projectList, path).start();
    }

    /**
     * Loads the projects from the default path
     */
    public void loadProjects() {
        loadProjects(PROJECTS_PATH);
    }

    /**
     * Loads the projects from the path specified
     *
     * @param path the path to load the projects from
     */
    public void loadProjects(String path) {
        try {
            FileInputStream fi = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fi);
            projectList = (ProjectList) in.readObject();
            in.close();
            fi.close();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage() + ", creating new projectList");
            projectList = new ProjectList();
        } catch (IOException e) {
            System.err.println(e.getMessage() + ", creating new projectList");
            projectList = new ProjectList();
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage() + ", creating new projectList");
            e.printStackTrace();
            projectList = new ProjectList();
        }
    }

    /**
     * Adds a new project
     *
     * @param title the title of the project
     */
    public void addNewProject(String title) {
        if (isConnected())
            projectList.addProject(new Project(title, getConnectedUser().getId()));
        else
            projectList.addProject(new Project(title));
        saveProjects();
    }

    /**
     * Adds a new project
     *
     * @param title   the title of the project
     * @param visible if the project is visible or not
     */
    public void addNewProject(String title, boolean visible) {
        if (!isConnected())
            visible = true;
        projectList.addProject(new Project(title, getConnectedUser().getId(), visible));
        saveProjects();
    }

    /**
     * The projectList property
     *
     * @return the list property
     */
    public ListProperty<Project> projectsProperty() {
        return projectList.projectsProperty();
    }

    // /////////////////// //
    // Task related Method //
    // /////////////////// //

    /**
     * Adds a new task to an project
     *
     * @param project     the project to add it to
     * @param title       the title of the Task
     * @param description the description of the Task
     * @param done        if the task is done
     * @param beginDate   the date the task should begin
     * @param endDate     the date the task sould end
     */
    public void addNewTask(Project project, String title, String description, boolean done, LocalDate beginDate, LocalDate endDate) {
        if (getConnectedUser().getId() == project.getUserId() || project.getUserId() == User.GUEST_ID)
            project.addTask(new Task(title, description, done, beginDate, endDate));
        saveProjects();
    }
}
