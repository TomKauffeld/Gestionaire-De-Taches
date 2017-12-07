package gdt.assets;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Tom KAUFFELD
 * @version 1
 * @see gdt.assets.Project
 * @see java.io.Serializable
 * @see java.time.LocalDate
 * @see javafx.beans.property.Property
 */
public class Task implements Serializable{

	private static final long serialVersionUID = -3466802709741483618L;
	
	private StringProperty title = new SimpleStringProperty();
    private StringProperty description = new SimpleStringProperty();
    private BooleanProperty done = new SimpleBooleanProperty();
    private ObjectProperty<LocalDate> beginDate = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDate> endDate = new SimpleObjectProperty<>();

    // /////////// //
    // Constructor //
    // /////////// //

    /**
     * Creates an Task
     * @param title         the title of the task
     * @param description   the description of the task
     * @param done          if the task is finished or not
     * @param beginDate     the date the task begins
     * @param endDate       the date the task should end (deadline)
     */
    protected Task( String title, String description, Boolean done,  LocalDate beginDate, LocalDate endDate){
        setTitle( title);
        setDescription( description);
        setDone( done);
        setBeginDate( beginDate);
        setEndDate( endDate);
    }

    // ////////// //
    // Properties //
    // ////////// //

    /**
     * Gets the StringProperty for the title of the task
     * @return the StringProperty (title)
     */
    public StringProperty titleProperty( ) {
        return title;
    }

    /**
     * Gets the StringProperty for the description of the task
     * @return the StringProperty (description)
     */
    public StringProperty descriptionProperty( ) {
        return description;
    }

    /**
     * Gets the BooleanProperty for the done of the task
     * @return the BooleanProperty (done)
     */
    public BooleanProperty doneProperty( ) {
        return done;
    }

    /**
     * Gets the ObjectProperty LocalDate  for the beginDate of the task
     * @return the ObjectProperty (LocalDate) (beginDate)
     */
    public ObjectProperty<LocalDate> beginDateProperty( ) {
        return beginDate;
    }

    /**
     * Gets the ObjectProperty LocalDate  for the endDate of the task
     * @return the ObjectProperty (LocalDate) (endDate)
     */
    public ObjectProperty<LocalDate> endDateProperty( ) {
        return endDate;
    }

    // /////// //
    // Getters //
    // /////// //

    /**
     * Getter for the title
     * @return the title of the task
     */
    protected String getTitle(){
        return title.get();
    }

    /**
     * Getter for the description
     * @return the description of the task
     */
    protected String getDescription(){
        return description.get();
    }

    /**
     * Getter for the beginDate
     * @return the date the task should begin
     */
    protected LocalDate getBeginDate(){
        return beginDate.get();
    }

    /**
     * Getter for the endDate
     * @return the date the task should be finished
     */
    protected LocalDate getEndDate(){
        return endDate.get();
    }

    /**
     * Getter to see whether or not the task is finished
     * @return  true    if the task is finished
     *          false   if the task is not finished
     */
    protected Boolean isDone(){
        return done.get();
    }

    // /////// //
    // Setters //
    // /////// //

    /**
     * Setter for the title
     * @param title the new title of the task
     */
    protected void setTitle( String title){
        this.title.set( title);
    }

    /**
     * Setter for the description
     * @param description   the new description of the task
     */
    protected void setDescription( String description){
        this.description.set( description);
    }

    /**
     * Setter for done
     * @param done whether or not the task is finished/done
     */
    protected void setDone( Boolean done){
        this.done.set( done);
    }

    /**
     * Setter for the beginDate
     * @param beginDate when the task should begin
     */
    protected void setBeginDate( LocalDate beginDate){
        this.beginDate.set( beginDate);
    }

    /**
     * Setter for the endDate
     * @param endDate   when the task should be done
     */
    protected void setEndDate( LocalDate endDate){
        this.endDate.set( endDate);
    }

    // ////////// //
    // Write/Read //
    // ////////// //

    /**
     * Writes a task to an OutputStream
     * @param out the stream to write the task to
     * @throws IOException
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeUTF( getTitle());
        out.writeUTF( getDescription());
        out.writeBoolean( isDone());
        out.writeObject( getBeginDate());
        out.writeObject( getEndDate());
    }

    /**
     * Reads a task from the InputStream
     * @param in the stream to read the task from
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
        title.set( in.readUTF());
        description.set( in.readUTF());
        done.set( in.readBoolean());
        beginDate.set( (LocalDate) in.readObject());
        endDate.set( (LocalDate) in.readObject());
    }
    
}
