package gdt.save;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveState implements Runnable {

    private Object o;
    private String path;
    private Thread thread;

    public SaveState(Object o, String path){
        this.o = o;
        this.path = path;
    }

    @Override
    public void run() {
        try {
            FileOutputStream fo = new FileOutputStream( path);
            ObjectOutputStream out = new ObjectOutputStream( fo);
            out.writeObject( o);
            out.close();
            fo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stop();
    }

    public synchronized void start( ){
        thread = new Thread( this);
        thread.start();
    }

    public synchronized void stop( ){
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
