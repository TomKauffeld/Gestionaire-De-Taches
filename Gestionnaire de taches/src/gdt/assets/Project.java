package gdt.assets;

import gdt.user.User;
import javafx.beans.property.*;
import javafx.collections.FXCollections;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

/**
 * @author Tom KAUFFELD
 * @version 1
 * @see gdt.user.User
 * @see gdt.assets.Task
 * @see javafx.beans.property.Property
 * @see java.io.Serializable
 */
public class Project implements Serializable{

	private static final long serialVersionUID = -6024098739190037197L;
	
	private transient StringProperty title = new SimpleStringProperty();
	private transient ListProperty<Task> tasks = new SimpleListProperty<>(FXCollections.observableArrayList());
	private transient LongProperty userId = new SimpleLongProperty();
	private transient BooleanProperty visible = new SimpleBooleanProperty();


	private void writeObject(ObjectOutputStream out) throws IOException{
		out.writeUTF( title.get());
		out.writeLong( userId.get());
		out.writeBoolean( visible.get());
		if(tasks==null || tasks.getValue()==null) {
			out.writeInt(0);
			return;
		}
		out.writeInt( tasks.size());
		for(Task task : tasks)
			out.writeObject(task);
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
            title = new SimpleStringProperty();
            userId = new SimpleLongProperty();
            visible = new SimpleBooleanProperty();
            tasks = new SimpleListProperty<>(FXCollections.observableArrayList());
		setTitle( in.readUTF());
		setUserId( in.readLong());
		setVisible( in.readBoolean());
		int nbList = in.readInt();
		for (int i = 0; i < nbList; i++)
			tasks.add( (Task) in.readObject());
	}
	// /////////// //
	// Constuctors //
	// /////////// //

	/**
	 * Creates a Project
	 * @param title     the title of the project
	 * @param userId    the userId to who the project is attached
	 * @param visible   if the project is public or not
	 */
	public Project( String title, long userId, boolean visible) {
		setTitle( title);
		setUserId( userId);
		setVisible( visible);
	}

    /**
     * Creates a public Project
     * @param title     the title of the project
     * @param userId    the userId to who the project is attached
     */
	public Project( String title, long userId) {
		this( title, userId, true);
	}

    /**
     * Creates a public Project whoes "owner" is the "guest account" (someone who isn't signed in)
     * @param title     the title of the project
     */
	public Project( String title) {
		this( title, User.GUEST_ID);
	}

	// ////////// //
	// Propreties //
    // ////////// //

    /**
     * Gets the StringProperty for the title of the project
     * @return the StringProperty (title)
     */
    public StringProperty titleProperty() {
        return title;
    }

    /**
     * Gets the ListProperty of the tasks of the project
     * @return the ListProperty of Task(s) (tasks)
     */
    public ListProperty<Task> tasksListProprety(){
	    return tasks;
    }

    /**
     * Gets the LongProperty for the userId of the project
     * @return the LongProperty (userId)
     */
    public LongProperty userIdProperty(){
        return userId;
    }

    /**
     * Gets the BooleanProperty for the visible of the project
     * @return the BooleanProperty (visible)
     */
    public BooleanProperty visible(){
        return visible;
    }

    // /////// //
    // Getters //
    // /////// //

    /**
     *  Getter for the tasks
     * @return the task list of the project
     */
	protected List<Task> getTasks(){
		return tasks.get();
	}

    /**
     * Getter for one task in the List
     * @param index the index of the task
     * @return the task at the index "index" in the taskList of the project
     */
	protected Task getTask( int index) {
		if (index < 0 || index >= tasks.size())
			return null;
		return tasks.get( index);
	}

    /**
     * Getter for the title
     * @return the title of the project
     */
	protected String getTitle() {
		return title.get();
	}

    /**
     * Getter for the userId
     * @return the userId of the owner of the project
     */
	protected long getUserId( ) {
		return userId.get();
	}

    /**
     * Getter for visible
     * @return  false   if the project is private
     *          true    if the project is public
     */
	protected boolean isVisible( ) {
		return visible.get();
	}

	// /////// //
	// Setters //
    // /////// //

    /**
     * Setter for the title
     * @param title the title of the project
     */
	protected void setTitle( String title) {
		this.title.set( title);
	}

    /**
     * Setter for the userId
     * @param userId the userId of the owner of the project
     */
	private void setUserId( long userId){
		this.userId.set( userId);
    }

    /**
     * Setter for the visible
     * @param visible   false   if the project must be private
     *                  true    if the project must be public
     */
	protected void setVisible( boolean visible) {
		if (getUserId() != User.GUEST_ID)
			this.visible.set( visible);
                else
                    this.visible.set( true);
	}

    /**
     * Adds an task to the TaskList
     * @param task  the task to add to the task List of the project
     */
	protected void addTask( Task task) {
		tasks.add( task);
	}

}
