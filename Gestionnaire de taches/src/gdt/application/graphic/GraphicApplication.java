package gdt.application.graphic;

import gdt.application.TaskManagerApplication;
import javafx.application.Application;
import javafx.stage.Stage;

public class GraphicApplication extends Application implements TaskManagerApplication{

    @Override
    public void start( Stage primaryStage) throws Exception {

    }

    public void begin( String... args){
        launch( args);
    }
}
