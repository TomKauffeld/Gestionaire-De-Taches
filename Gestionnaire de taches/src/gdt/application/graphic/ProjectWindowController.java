package gdt.application.graphic;

import gdt.assets.Project;
import gdt.assets.Task;
import gdt.assets.TaskListFacade;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 *
 * @author Florian DELCROIX
 * @version 1
 * 
 */
public class ProjectWindowController {
    
    //connection
    @FXML
    private GridPane gridConnection;
    @FXML
    private PasswordField passwordConnectionField;
    
    @FXML
    private TextField IdConnectionField;
    
    
    //add project
    @FXML
    private TextField titleProject;
    
    @FXML
    private ListView<Project> projectList;
    
    @FXML
    private Button menuButton;
    
    @FXML
    private CheckBox privateProject;
    
    
    //add task
    @FXML
    private TextField titleTextField;
    
    @FXML
    private TextField descriptionTextField;
    
    @FXML
    private Button addTask;
    
    @FXML
    private Label taskDescription;
    
    
    //Zones
    
    @FXML 
    private Label projectTitleLabel;
    
    @FXML
    private Label taskTitleLabel;

    
    @FXML
    private BorderPane borderPane;
    
    @FXML
    private Pane addProjectZone;
    
    @FXML
    private Pane addTaskZone;
    
    @FXML
    private ListView<Task> taskList;
    
    //bundle
    
    @FXML 
    private ResourceBundle resources;
    
    private TaskListFacade facade = new TaskListFacade();
    
    
    public void initialize(){
        menuButton.prefWidthProperty().bind( borderPane.widthProperty());
        projectList.itemsProperty().bind(facade.projectsProperty());
        projectList.setCellFactory((ListView<Project> List) -> new ProjectCell());
        taskList.setCellFactory((ListView<Task> List) -> new TaskCell());
        switchMenu();
    }
    
    private boolean pro = false;
    public void switchMenu(){
        pro = !pro;
            projectList.setVisible( pro);
            addProjectZone.setVisible( pro);
            taskList.setVisible( !pro);
            addTaskZone.setVisible( !pro);
        if (pro){
            
            menuButton.textProperty().set( resources.getString("Projects"));
            
            taskList.setMaxHeight(0);
            addTaskZone.setMaxHeight( 0);
            
            projectList.setMaxHeight(9999);
            addProjectZone.setMaxHeight( 9999);
        }
        else{
            menuButton.textProperty().set( resources.getString("Tasks"));
            
            projectList.setMaxHeight(0);
            addProjectZone.setMaxHeight( 0);
            
            taskList.setMaxHeight(9999);
            addTaskZone.setMaxHeight( 9999);
            
        }
        
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
        if (projectTitleLabel.textProperty().isBound())
            projectTitleLabel.textProperty().unbind();
        if (!projectTitleLabel.textProperty().get().equals(""))
            projectTitleLabel.setText( "");
        if ((projectList.getSelectionModel() != null && 
                projectList.getSelectionModel().selectedItemProperty() != null &&
                projectList.getSelectionModel().selectedItemProperty().get() != null) &&
                (projectList.getSelectionModel().getSelectedItem().visible().get() ||
                projectList.getSelectionModel().getSelectedItem().userIdProperty().get() == facade.getConnectedUser().getId())){
            projectTitleLabel.textProperty().bind( projectList.getSelectionModel().selectedItemProperty().get().titleProperty());
            taskList.itemsProperty().bind( projectList.getSelectionModel().selectedItemProperty().getValue().tasksListProprety());
        }
        taskSelected();
        switchMenu();
    }
    
    public void taskSelected( ){
        if (taskDescription.textProperty().isBound())
            taskDescription.textProperty().unbind();
        if (!"".equals(taskDescription.textProperty().get()))
            taskDescription.textProperty().set( "");
        if (taskTitleLabel.textProperty().isBound())
            taskTitleLabel.textProperty().unbind();
        if (!"".equals(taskTitleLabel.textProperty().get()))
            taskTitleLabel.textProperty().set( "");
        
        
        if (projectList.getSelectionModel() == null || projectList.getSelectionModel().getSelectedItem() == null)
            return;
        if (!projectList.getSelectionModel().getSelectedItem().visible().get() && projectList.getSelectionModel().getSelectedItem().userIdProperty().get() != facade.getConnectedUser().getId()){
            taskDescription.textProperty().set( resources.getString( "Private"));
            return;
        }
        if (taskList.selectionModelProperty() != null && 
                taskList.selectionModelProperty().getValue().selectedItemProperty() != null &&
                taskList.selectionModelProperty().getValue().selectedItemProperty().get() != null){
            taskDescription.textProperty().bind(taskList.selectionModelProperty().getValue().getSelectedItem().descriptionProperty());
            taskTitleLabel.textProperty().bind( taskList.getSelectionModel().getSelectedItem().titleProperty());
        }
    
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
        if (facade.connection(passwordConnectionField.getText(), IdConnectionField.getText())){
            gridConnection.setVisible(false);
            IdConnectionField.setText( "");
        }
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
        if (projectList.getSelectionModel() != null && 
                projectList.getSelectionModel().selectedItemProperty() != null &&
                projectList.getSelectionModel().selectedItemProperty().get() != null)
            facade.addNewTask(projectList.getSelectionModel().selectedItemProperty().getValue(), title, description, true, LocalDate.MIN, LocalDate.MAX);
                
        
    }
    
}
