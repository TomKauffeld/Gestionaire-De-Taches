package gdt.save;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveState implements Runnable {

    private Object o;
    private String path;
    private Thread thread;
    private Thread parent = null;

    public SaveState( Object o, String path){
        this.o = o;
        this.path = path;
    }

    @Override
    public void run() {
        if (parent == null)
            return;
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
        parent = Thread.currentThread();
        thread = new Thread( this);
        thread.start();
    }

    public synchronized void stop( ){
        try {
            parent.join();
            parent = null;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
