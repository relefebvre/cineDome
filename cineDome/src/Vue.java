import java.util.*;
import java.text.SimpleDateFormat;

public class Vue {
	
	private Scanner scan;
	
	public Vue() {
		scan = new Scanner(System.in);
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
		System.out.print("Dur�e (en secondes) : ");
		if (f.duree != -1) {
			System.out.println(f.duree);
		} else {
			System.out.println("NR");
		}
		System.out.println("Cat�gorie : "+f.categorie);
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
		System.out.println("Horraires S�ances : ");
		for (Date d : f.getSeances()) {
			System.out.println("\t"+sdfS.format(d));
		}
	}
	
	public void afficheRand(List<Film> films) {
		Random r = new Random();
		int pos = r.nextInt(films.size());
		afficheFilm(films.get(pos));
	}
	
	public void afficherMenu() {
		System.out.println("1\tAjouter Films");
		System.out.println("2\tAfficher Films");
		System.out.println("3\tAfficher un Film au hazard");
		System.out.println("4\tTrier les films(alphabetique)");
		System.out.println("5\tTrier les films(note Presse)");
		System.out.println("6\tTrier les films(date)");
		System.out.println("9\tQuitter");
		System.out.print("Choix : ");
	}
	
	public int getChoix(int min, int max) {
		int choix;
		try {
			choix =  scan.nextInt();
			if (choix < min || choix > max) {
				throw new Exception();
			}
		}
		catch (Exception e) {
			return -1;
		}
		return choix;
	}
	
	public String saisirPath() {
		scan.nextLine();
		System.out.print("Fichier source : ");
		return scan.nextLine();
	}
	
}
