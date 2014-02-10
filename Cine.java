import java.io.*;
import java.util.*;

public class Cine {
	public List<Film> films;
	private Set<String> categories;
	
	public Cine() {
		this.films = new ArrayList<Film>();
		this.categories = new TreeSet<String>();
	}

	public void ajouterFilm(Film film) {
		films.add(film);
	}

	public void supprimerFilm(String titre) {
		
	}

	public List<Film> getFilms() {
		return films;
	}

	public void ajouterCategorie(String cat) throws Exception{
		if (!categories.add(cat)) {
			throw new Exception();
		} 
	}
	
	public void supprimerCategorie(int indice) {
		categories.remove(getCategorie(indice));
	}

	public Set<String> getCategories() {
		return categories;
	}

	public String getCategorie(int indice) {
		String[] tmp = categories.toArray(new String[0]);
		return tmp[indice-1];
	}

	public int nbCategories() {
		return categories.size();
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


