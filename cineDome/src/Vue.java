import java.util.*;

public class Vue {
	public Vue() {
	}

	public void affiche(List<Film> films){
		for (Film f : films) {
			System.out.println(f.titre);
			System.out.println("Sortie le : "+f.dateSortie);
			System.out.println("Catégorie : "+f.categorie);
			System.out.println("Note : "+f.notePresse);
			System.out.println("Horraires SÃ©ances : ");
			for (Date d : f.getSeances()) {
				System.out.println("\t"+d);
			}
		}
	}

	public void afficherCategories(Set<String> cat) {
		int i=1;
		for (String s : cat) {
			System.out.println(i+"\t"+s);
			i++;
		}
	}
}
