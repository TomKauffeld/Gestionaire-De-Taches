package gdt.assets;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.io.Serializable;
import java.time.LocalDate;

public class Task implements Serializable{

	private static final long serialVersionUID = -3466802709741483618L;
	
	private StringProperty title = new SimpleStringProperty();
    private StringProperty description = new SimpleStringProperty();
    private BooleanProperty done = new SimpleBooleanProperty();
    private ObjectProperty<LocalDate> beginDate = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDate> endDate = new SimpleObjectProperty<>();
    
    protected Task( String title, String description, Boolean done,  LocalDate beginDate, LocalDate endDate){
        setTitle( title);
        setDescription( description);
        setDone( done);
        setBeginDate( beginDate);
        setEndDate( endDate);
    }

    //propreties
    public StringProperty titleProperty( ) {
        return title;
    }
    public StringProperty descriptionProperty( ) {
        return description;
    }
    public BooleanProperty doneProperty( ) {
        return done;
    }
    public ObjectProperty<LocalDate> beginDateProperty( ) {
        return beginDate;
    }
    public ObjectProperty<LocalDate> endDateProperty( ) {
        return endDate;
    }

    //getters
    protected String getTitle(){
        return title.get();
    }
    protected String getDescription(){
        return description.get();
    }
    protected Boolean getDone(){
        return done.get();
    }
    protected LocalDate getBegin(){
        return beginDate.get();
    }
    protected LocalDate getEnd(){
        return endDate.get();
    }
    
    //setters
    protected void setTitle( String title){
        this.title.set( title);
    }
    protected void setDescription( String description){
        this.description.set( description);
    }
    protected void setDone( Boolean done){
        this.done.set( done);
    }
    protected void setBeginDate( LocalDate beginDate){
        this.beginDate.set( beginDate);
    }
    protected void setEndDate( LocalDate endDate){
        this.endDate.set( endDate);
    }
    
}
