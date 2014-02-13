import java.util.*;
import java.text.SimpleDateFormat;

public class Vue {
	public Vue() {
	}

	public void affiche(List<Film> films){
		for (Film f : films) {
			afficheFilm(f);
		}
	}
	
	public void afficheFilm(Film f) {
		SimpleDateFormat sdfD = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfS = new SimpleDateFormat("hh:mm");
		System.out.println(f.titre);
		System.out.println("Sortie le : "+sdfD.format(f.dateSortie));
		System.out.print("Durée (en secondes) : ");
		if (f.duree != -1) {
			System.out.println(f.duree);
		} else {
			System.out.println("NR");
		}
		System.out.println("Catégorie : "+f.categorie);
		System.out.print("Note Presse : ");
		if (f.notePresse != -1) {
			System.out.println(f.notePresse);
		} else {
			System.out.println("NR");
		}
		System.out.print("Note Spectateurs : ");
		if (f.noteSpect != -1) {
			System.out.println(f.noteSpect);
		} else {
			System.out.println("NR");
		}
		System.out.println("Acteurs : ");
		for (String acteur : f.acteurs) {
			System.out.println("\t"+acteur);
		}
		System.out.println("Horraires Séances : ");
		for (Date d : f.getSeances()) {
			System.out.println("\t"+sdfS.format(d));
		}
	}
	
	public void afficheRand(List<Film> films) {
		Random r = new Random();
		int pos = r.nextInt(films.size());
		afficheFilm(films.get(pos));
	}
}
