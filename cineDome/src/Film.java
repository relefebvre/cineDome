import java.util.*;
import java.text.SimpleDateFormat;

public class Film {
	public String titre;
	public Date dateSortie;
	public int duree;
	public float notePresse, noteSpect;
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
			this.duree = -1;
		}
		this.affiche = affiche;
		this.categorie = categorie;
		try {
			this.notePresse = Float.parseFloat(notePresse);
		}
		catch (Exception e) {
			System.out.println("Problème parse notePresse");
			this.notePresse = -1;
		}
		try {
			this.noteSpect = Float.parseFloat(noteSpect);
		}
		catch (Exception e) {
			System.out.println("Problème parse noteSpect");
			this.noteSpect = -1;
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
