package gdt.application.graphic;

import gdt.assets.Task;
import javafx.scene.control.ListCell;

/**
 *
 * @author Florian DELCROIX
 * @version 1
 */
public class TaskCell extends ListCell<Task> {
    
    @Override
    protected void updateItem(Task item, boolean empty){
        super.updateItem(item, empty);
        if (item != null)
            textProperty().bind(item.titleProperty());
        else{
            textProperty().unbind();
            textProperty().set("");
        }
            
    }
    
    
}
