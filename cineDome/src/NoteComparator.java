import java.util.* ;
	
public class NoteComparator implements Comparator<Film> {
	public int compare(Film f1, Film f2) {
		
		if (f1.notePresse < f2.notePresse) {
			return -1;
		} else if(f1.notePresse > f2.notePresse){
			return 1;
		}
		return 0 ;
	}
}
