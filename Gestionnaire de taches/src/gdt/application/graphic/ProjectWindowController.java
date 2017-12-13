/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdt.application.graphic;

import gdt.assets.Project;
import gdt.assets.ProjectList;
import gdt.assets.Task;
import gdt.assets.TaskListFacade;
import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;

/**
 *
 * @author Florian DELCROIX
 * @version 1
 * 
 */
public class ProjectWindowController {
    
    private static final String PROJECTS_PATH = "";
    private static final String USERS_PATH = "";
    
    //connection
    @FXML
    private PasswordField passwordConnectionField;
    
    @FXML
    private TextField IdConnectionField;
    
    @FXML
    private Button connectionButton;
    
    @FXML
    private Label labelUserName;
    
    @FXML
    private Label labelPassword;
    
    
    //add project
    @FXML
    private TextField titleProject;
    
    @FXML
    private ListView<Project> projectList;
    
    @FXML
    private CheckBox privateProject;
    
    @FXML
    private Button addProjectButton;
    
    
    //add task
    @FXML
    private TextField titleTextField;
    
    @FXML
    private TextField descriptionTextField;
    
    @FXML
    private Button addTask;
    
    @FXML
    private Label taskTitle;
    
    @FXML
    private Label taskDescription;
    
    
    
    
    @FXML 
    private FlowPane connectionZone;
    
    @FXML
    private ListView<Task> taskList;
    
    private TaskListFacade facade = new TaskListFacade();
    
    
    public void initialize(){
        //facade.loadProjects( PROJECTS_PATH);
        //facade.loadUsers( USERS_PATH);
        projectList.itemsProperty().bind(facade.projectsProperty());
        projectList.setCellFactory((ListView<Project> List) -> new ProjectCell());
        taskList.setCellFactory((ListView<Task> List) -> new TaskCell());
        
    }
    
    
    /**
     *
     * 
     * 
    */
    public void projectSelected( ){
        if (taskList.itemsProperty() != null && taskList.itemsProperty().isBound()){
            taskList.itemsProperty().unbind();
            taskList.itemsProperty().set( null);
        }
            
        if (projectList.getSelectionModel() != null && 
                projectList.getSelectionModel().selectedItemProperty() != null &&
                projectList.getSelectionModel().selectedItemProperty().get() != null)
            taskList.itemsProperty().bind( projectList.getSelectionModel().selectedItemProperty().getValue().tasksListProprety());
    
    }
    
    
    /**
     *
     * Add a project
     * 
    */
    public void addProject( ){
        String title = titleProject.getText();
        Boolean isprivate = privateProject.isSelected();
        facade.addNewProject(title, !isprivate);
        
    }
    
    
    /**
     *
     * register a new user
     * 
    */
    public void registerClick(){
        if (facade.addNewUser(passwordConnectionField.getText(), IdConnectionField.getText()))
            IdConnectionField.setText( "");
        passwordConnectionField.setText( "");
        
    }
    
    
    /**
     *
     * connect an user
     * 
    */
    public void connectionClick(){
        if (facade.connection(passwordConnectionField.getText(), IdConnectionField.getText()))
            IdConnectionField.setText( "");
        passwordConnectionField.setText( "");
    }
    
    
    /**
     *
     * add a new task
     * 
    */
    public void addTask(){
        String title = titleTextField.getText();
        String description = descriptionTextField.getText();
        facade.addNewTask(projectList.getSelectionModel().selectedItemProperty().getValue(), title, description, true, LocalDate.MIN, LocalDate.MAX);
                
        
    }
    
}
