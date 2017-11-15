package gdt.assets;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestionListeDeTaches implements Serializable{

	private static final long serialVersionUID = 2489019390718734973L;
	
	private List<ListeDeTaches> listes;
	
	public GestionListeDeTaches( ) {
		listes = new ArrayList<>();
	}
	
	
	public int createNewListe( String titre, long userId, boolean isprivate) {
		listes.add( new ListeDeTaches( titre, userId, isprivate));
		return listes.size()-1;
	}
	
	public int createNewListe( String titre, long userId) {
		listes.add( new ListeDeTaches( titre, userId));
		return listes.size()-1;
	}
	
	public int createNewListe( String titre) {
		listes.add( new ListeDeTaches( titre));
		return listes.size()-1;
	}
	
	public void addNewTache( int listId, String titre, String description, boolean fini, LocalDate begin, LocalDate end) {
		if (listId < 0 || listId >= listes.size())
			return;
		if (!listes.get( listId).isPrivate())
			listes.get( listId).addTache( GestionTache.createTache( titre, description, fini, begin, end));
	}
	
	public void addNewTache( int listId, String titre, String description, boolean fini, LocalDate begin, LocalDate end, long userId) {
		if (listId < 0 || listId >= listes.size())
			return;
		if (listes.get( listId).getUserId() == userId || !listes.get( listId).isPrivate())
			listes.get( listId).addTache( GestionTache.createTache( titre, description, fini, begin, end));
	}

}
