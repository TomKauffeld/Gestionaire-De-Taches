/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdt.application.graphic;

import gdt.assets.Project;
import gdt.assets.ProjectList;
import static javafx.application.ConditionalFeature.FXML;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
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
    
    private ProjectList projectL = new ProjectList();
    
    
    public void initialize(){
        projectList.itemsProperty().bind(projectL.projectsProperty());
        projectList.setCellFactory((ListView<Project> List) -> new ProjectCell());
    }
    
}
