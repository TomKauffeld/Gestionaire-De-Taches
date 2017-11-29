package gdt.application.graphic;

import gdt.application.TaskManagerApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GraphicApplication extends Application implements TaskManagerApplication{

    @Override
    public void start( Stage primaryStage) throws Exception {
        primaryStage.setScene( new Scene( FXMLLoader.load(getClass().getResource("/gdt/application/graphic/ProjectWindow.fxml"))));
        primaryStage.show();
    }

    public void begin( String... args){
        launch( args);
    }
}
