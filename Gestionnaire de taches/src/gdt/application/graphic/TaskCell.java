package gdt.application.graphic;

import gdt.assets.Task;
import javafx.scene.control.ListCell;

/**
 * @author Florian DELCROIX
 * @version 1
 * @see gdt.assets.Task
 */
public class TaskCell extends ListCell<Task> {

    /**
     * @param item  The new task for the cell.
     * @param empty whether or not this cell represents data from the list. If it is empty, then it does not represent any domain data, but is a cell being used to render an "empty" row.
     */
    @Override
    protected void updateItem(Task item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null)
            textProperty().bind(item.titleProperty());
        else {
            if (textProperty().isBound())
                textProperty().unbind();
            textProperty().set("");
        }

    }


}
