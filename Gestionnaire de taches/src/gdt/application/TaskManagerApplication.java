package gdt.application;

/**
 * @author Tom KAUFFELD
 * @version 1
 */
public interface TaskManagerApplication {

    /**
     * Launches the Application
     *
     * @param args the command line arguments passed to the application. An application may get these parameters using the getParameters() method.
     */
    void begin(String... args);
}
