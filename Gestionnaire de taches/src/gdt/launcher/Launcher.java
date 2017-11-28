package gdt.launcher;

import gdt.application.TaskManagerApplication;
import gdt.application.graphic.GraphicApplication;

/**
 * @author Tom KAUFFELD
 * @version 1
 * @see gdt.application.TaskManagerApplication
 * @see gdt.assets.Project
 */
public class Launcher {

    /**
     * The main launcher of the application
     * @param args  arguments to pass to the application (at this moment they are ignored)
     */
    public static void main( String... args){
        TaskManagerApplication app = new GraphicApplication();
        app.begin();

    }

}
