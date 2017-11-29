/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdt.application.graphic;

import gdt.assets.Project;
import javafx.scene.control.ListCell;

/**
 *
 * @author fldelcroix
 */
public class ProjectCell extends ListCell<Project> {
    
    @Override
    protected void updateItem(Project item, boolean empty){
        super.updateItem(item, empty);
        if (item != null)
            textProperty().bind(item.titleProperty());
        else
            textProperty().unbind();
    }
}
