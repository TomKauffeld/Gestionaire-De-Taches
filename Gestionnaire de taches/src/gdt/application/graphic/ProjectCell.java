package gdt.application.graphic;

import gdt.assets.Project;
import javafx.scene.control.ListCell;

/**
 * @author Florian DELCROIX
 * @version 1
 * @see gdt.assets.Project
 */
public class ProjectCell extends ListCell<Project> {

    /**
     * @param item  The new Project for the cell.
     * @param empty whether or not this cell represents data from the list. If it is empty, then it does not represent any domain data, but is a cell being used to render an "empty" row.
     */
    @Override
    protected void updateItem(Project item, boolean empty) {
        super.updateItem(item, empty);

        if (item != null) {
            textProperty().bind(item.titleProperty().concat(item.visible().getValue() ? " (+)" : " (-)"));
        } else {
            if (textProperty().isBound())
                textProperty().unbind();
            textProperty().set("");
        }

    }


}
