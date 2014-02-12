import java.util.* ;
	
public class DateComparator implements Comparator<Film> {
	public int compare(Film f1, Film f2) {
		int value ;
		value = f1.dateSortie.compareTo(f2.dateSortie) ;
		return value ;
	}
}
