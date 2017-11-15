package gdt.assets;

import java.util.ArrayList;
import java.util.List;

public class ListeDeTaches {
	
	private String titre;
	private List<Tache> taches;
	private long userId;
	private boolean isprivate;
	
	public ListeDeTaches( String titre, long userId, boolean isprivate) {
		taches = new ArrayList<>();
		this.titre = titre;
		this.userId = userId;
		this.isprivate = isprivate;
	}
	
	public ListeDeTaches( String titre, long userId) {
		this( titre, userId, false);
	}
	
	public ListeDeTaches( String titre) {
		this( titre, -1);
	}
	
	protected List<Tache> getTaches(){
		return taches;
	}
	
	protected Tache getTache( int index) {
		if (index < 0 || index >= taches.size())
			return null;
		return taches.get( index);
	}
	
	protected Tache searchTache( long id) {
		for (Tache tache : taches)
			if (tache.getId() == id)
				return tache;
		return null;
	}
	
	protected String getTitre() {
		return titre;
	}
	
	protected long getUserId( ) {
		return userId;
	}
	
	protected boolean isPrivate( ) {
		return isprivate;
	}
	
	protected void setTitre( String titre) {
		this.titre = titre;
	}
	
	protected void setPrivate( boolean isprivate) {
		if (userId != -1)
			this.isprivate = isprivate;
	}
	
	protected void addTache( Tache tache) {
		if (searchTache( tache.getId()) != null)
			return;
		taches.add( tache);
	}

}
