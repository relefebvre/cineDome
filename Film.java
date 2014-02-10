import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class Film {
	public String titre;
	public Date dateSortie;
	public int note;
	public String categorie;
	private SimpleDateFormat sdf;
	public List<Date> seances;

	public Film(String titre, int note, String categorie) {
		this.titre = titre;
		this.note = note;
		this.categorie = categorie;
		seances = new ArrayList<Date>();
	}

	public void setDateDeSortie(String date) throws Exception {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
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
