import java.util.*;

public class Cine {
	public List<Film> films;
	public Database data;

	
	public Cine() {
		this.films = new ArrayList<Film>();
		this.data = new Database();
	}

	public void ajouterFilm(Film film) {
		films.add(film);
	}

	public void supprimerFilm(String titre) {
		
	}

	public List<Film> getFilms() {
		return films;
	}
	
	public void alphaSort() {
		Collections.sort(films, new AlphaComparator()) ;
	}
	
	public void noteSort() {
		Collections.sort(films, new NoteComparator()) ;
	}
	
	public void dateSort() {
		Collections.sort(films, new DateComparator()) ;
	}
}


