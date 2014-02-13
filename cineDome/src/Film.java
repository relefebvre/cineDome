import java.util.*;
import java.text.SimpleDateFormat;

public class Film {
	public String titre;
	public Date dateSortie;
	public int duree, notePresse, noteSpect;
	public String categorie;
	public String affiche;
	public String[] acteurs;
	public List<Date> seances;

	public Film(String titre, String duree, String affiche, String categorie, String notePresse, String noteSpect, String acteurs) {
		this.titre = titre;
		try {
			this.duree = Integer.parseInt(duree);
		}
		catch (Exception e) {
			System.out.println("Problème parse Durée");
		}
		this.affiche = affiche;
		this.categorie = categorie;
		try {
			this.notePresse = Integer.parseInt(notePresse);
		}
		catch (Exception e) {
			System.out.println("Problème parse notePresse");
		}
		try {
			this.noteSpect = Integer.parseInt(noteSpect);
		}
		catch (Exception e) {
			System.out.println("Problème parse noteSpect");
		}
		this.acteurs = acteurs.split(", ");
		seances = new ArrayList<Date>();
	}

	public void setDateDeSortie(String date) throws Exception {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                this.dateSortie = sdf.parse(date);
	}

	public void ajouterSeance(String seance) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		seances.add(sdf.parse(seance));
	}

	public List<Date> getSeances() {
		return seances;
	}
}
