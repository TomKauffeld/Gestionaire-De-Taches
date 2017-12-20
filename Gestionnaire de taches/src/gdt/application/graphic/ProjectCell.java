package gdt.application.graphic;

import gdt.assets.Project;
import javafx.scene.control.ListCell;

/**
 *
 * @author Florian DELCROIX
 * @version 1
 * 
 */
public class ProjectCell extends ListCell<Project> {

    public ProjectCell(){
        
    }
    
    @Override
    protected void updateItem(Project item, boolean empty){
        super.updateItem(item, empty);
        
        if (item != null){
            textProperty().bind(item.titleProperty().concat( item.visible().getValue() ? " (+)" : " (-)"));
        }
        else{
            textProperty().unbind();
            textProperty().set( "");
        }
            
    }
    
    
}
