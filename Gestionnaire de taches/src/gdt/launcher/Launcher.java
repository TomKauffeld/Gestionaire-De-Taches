package gdt.launcher;

import gdt.application.TaskManagerApplication;
import gdt.application.graphic.GraphicApplication;

public class Launcher {

    public static void main( String... args){
        TaskManagerApplication app = new GraphicApplication();

        app.begin();

    }

}
