import java.util.* ;
	
public class AlphaComparator implements Comparator<Film> {
	public int compare(Film f1, Film f2) {
		int value = 0 ;
		
		value = f1.titre.compareTo(f2.titre) ;
		return value ;
	}
}
