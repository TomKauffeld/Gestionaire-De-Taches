package gdt.application.graphic;

import gdt.application.TaskManagerApplication;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GraphicApplication extends Application implements TaskManagerApplication{

    private Locale locale = Locale.getDefault();
    
    @Override
    public void start( Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gdt/application/graphic/ProjectWindow.fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("gdt.res.lang", locale));
        primaryStage.setScene( new Scene( fxmlLoader.load()));
        primaryStage.show();
    }
    
    

    @Override
    public void begin( String... args){
        launch( args);
    }
    
    
}
