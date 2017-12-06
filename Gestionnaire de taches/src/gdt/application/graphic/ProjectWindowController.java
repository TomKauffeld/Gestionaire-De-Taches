/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdt.application.graphic;

import gdt.assets.Project;
import gdt.assets.ProjectList;
import gdt.assets.Task;
import static javafx.application.ConditionalFeature.FXML;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import static javafx.scene.input.KeyCode.T;
import javafx.util.Callback;

/**
 *
 * @author Florian DELCROIX
 */
public class ProjectWindowController {
    
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
    
    @FXML
    private ListView<Project> projectList;
    
    @FXML
    private ListView<Task> taskList;
    
    private ProjectList projectL = new ProjectList();
    
    
    public void initialize(){
        projectList.itemsProperty().bind(projectL.projectsProperty());
        projectList.setCellFactory((ListView<Project> List) -> new ProjectCell());
        taskList.setCellFactory((ListView<Task> List) -> new TaskCell());
        //taskList.itemsProperty().bind( projectList.getSelectionModel().selectedItemProperty().getValue().tasksListProprety());
    }
    
    public void projectSelected( ){
        if (taskList.itemsProperty() != null && taskList.itemsProperty().isBound())
            taskList.itemsProperty().unbind();
        if (projectList.getSelectionModel() != null && 
                projectList.getSelectionModel().selectedItemProperty() != null &&
                projectList.getSelectionModel().selectedItemProperty().get() != null)
            taskList.itemsProperty().bind( projectList.getSelectionModel().selectedItemProperty().getValue().tasksListProprety());
    
    }
    
}
