package gdt.application.graphic;

import gdt.application.TaskManagerApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Tom KAUFFELD
 * @version 1
 */
public class GraphicApplication extends Application implements TaskManagerApplication {

    private Locale locale = Locale.getDefault();

    /**
     * The main entry point for all JavaFX applications. The start method is called after the init method has returned, and after the system is ready for the application to begin running.
     * NOTE: This method is called on the JavaFX Application Thread.
     *
     * @param primaryStage the primary stage for this application, onto which the application scene can be set. The primary stage will be embedded in the browser if the application was launched as an applet. Applications may create other stages, if needed, but they will not be primary stages and will not be embedded in the browser.
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gdt/application/graphic/ProjectWindow.fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("gdt.res.lang", locale));
        primaryStage.setScene(new Scene(fxmlLoader.load()));
        primaryStage.show();
    }


    /**
     * Starts the Graphic application
     *
     * @param args the command line arguments passed to the application. An application may get these parameters using the getParameters() method.
     */
    @Override
    public void begin(String... args) {
        launch(args);
    }


}
