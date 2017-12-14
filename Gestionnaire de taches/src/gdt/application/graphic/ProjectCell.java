/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdt.application.graphic;

import gdt.assets.Project;
import gdt.user.User;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
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
