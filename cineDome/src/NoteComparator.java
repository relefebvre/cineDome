import java.util.* ;
	
public class NoteComparator implements Comparator<Film> {
	public int compare(Film f1, Film f2) {
		return f1.note - f2.note ;
	}
}
