package gdt.assets;

import gdt.user.User;

import javafx.beans.property.*;

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
	
	private StringProperty title = new SimpleStringProperty();
	private ListProperty<ObjectProperty<Task>> tasks = new SimpleListProperty<>();
	private LongProperty userId = new SimpleLongProperty();
	private BooleanProperty visible = new SimpleBooleanProperty();

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
    public ListProperty<ObjectProperty<Task>> tasksListProprety(){
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
	protected List<ObjectProperty<Task>> getTasks(){
		return tasks.get();
	}

    /**
     * Getter for one task in the List
     * @param index the index of the task
     * @return the task at the index "index" in the taskList of the project
     */
	protected ObjectProperty<Task> getTask( int index) {
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
	}

    /**
     * Adds an task to the TaskList
     * @param task  the task to add to the task List of the project
     */
	protected void addTask( Task task) {
		ObjectProperty<Task> taskP = new SimpleObjectProperty<>();
		taskP.set( task);
		tasks.add( taskP);
	}

}
