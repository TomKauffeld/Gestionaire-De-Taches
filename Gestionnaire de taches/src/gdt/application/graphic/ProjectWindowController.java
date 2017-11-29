/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdt.application.graphic;

import gdt.assets.Project;
import static javafx.application.ConditionalFeature.FXML;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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
    private ProjectsView projectsView;
    
    private final ObjectProperty<Project> myProject = new SimpleObjectProperty<>(new Project("theProject",21516,false)); 
        public final Project getProject() { return myProject.get(); }
        public final void setMyProject(Project value) { myProject.set(value);}
        public ObjectProperty<Project> myProjectProperty() { return myProject; }
    /* 
    @FXML
    private void handleButtonAction(ActionEvent event){
        
        
    }
    */
    public void initialize(){
        labelUserName.textProperty().bind(IdConnectionField.textProperty());
        
    }
    
}
