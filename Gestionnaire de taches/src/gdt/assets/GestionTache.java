package gdt.assets;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GestionTache  implements Serializable{
	
	private static final long serialVersionUID = 4255355399413740164L;
	
	private static List<Long> ids = new ArrayList<>();

	
	
	protected static Tache createTache( String titre, String description, boolean fini, LocalDate begin, LocalDate end) {
		long id = 0;
		Random r = new Random( titre.hashCode());
		do {
			id = r.nextLong();
		}
		while (ids.contains( id));
		ids.add( id);
		return new Tache( titre, description, fini, id, begin, end);
	}
	
	
}
