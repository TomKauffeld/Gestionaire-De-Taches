package gdt.assets;

import gdt.user.User;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

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
	private ListProperty<Task> tasks = new SimpleListProperty<>();
	private LongProperty userId = new SimpleLongProperty();
	private BooleanProperty isprivate = new SimpleBooleanProperty();

	// Constuctors
	public Project( String title, long userId, boolean isprivate) {
		setTitle( title);
		setUserId( userId);
		setPrivate( isprivate);
	}
	
	public Project( String title, long userId) {
		this( title, userId, false);
	}
	
	public Project( String title) {
		this( title, User.GUEST_ID);
	}

	// Propreties
    public StringProperty titleProperty() {
        return title;
    }
    public ListProperty<Task> tasksListProprety(){
	    return tasks;
    }
    public LongProperty userId(){
        return userId;
    }
    public BooleanProperty isprivateProprety(){
        return isprivate;
    }

    // Getters
	protected List<Task> getTasks(){
		return tasks.get();
	}
	protected Task getTask( int index) {
		if (index < 0 || index >= tasks.size())
			return null;
		return tasks.get( index);
	}
	protected String getTitle() {
		return title.get();
	}
	protected long getUserId( ) {
		return userId.get();
	}
	protected boolean isPrivate( ) {
		return isprivate.get();
	}

	// Setters
	protected void setTitle( String title) {
		this.title.set( title);
	}
	private void setUserId( long userId){
		this.userId.set( userId);
    }
	protected void setPrivate( boolean isprivate) {
		if (getUserId() != User.GUEST_ID)
			this.isprivate.set( isprivate);
	}
	protected void addTask( Task task) {
		tasks.add( task);
	}

}
