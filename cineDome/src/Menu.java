import java.util.Scanner;
import java.io.IOException;

public class Menu {
	private Cine cineDome;
	private Vue vueTexte;
	private Scanner scan;
	private Lecteur lect;

	public Menu(Vue vue) {
		vueTexte = vue;
		cineDome = new Cine();
		scan = new Scanner(System.in);
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

	public void traiterChoix(int choix) {
		switch(choix) {
			case 1 : 
				creerFilm();
				break;
			case 2 :
				vueTexte.affiche(cineDome.films);
				break;
			case 3 :
				vueTexte.afficheRand(cineDome.films);
			case 4 :
				cineDome.alphaSort() ;
				break ;
			case 5 :
				cineDome.noteSort() ;
				break ;
			case 6 : 
				cineDome.dateSort() ;
				break ;
			default : System.out.println("Choix incorrect");
		}
	}
	
	public Film lireFilm(String[] src) {
		Film f;
		
		f = new Film(src[0],src[2],src[3],src[4],src[5],src[6],src[7]);
		
		try {
			f.setDateDeSortie(src[1]);
		} catch (Exception e) {
			System.out.println("Format de date incorrect");
		}
		
		String[] seances = src[8].split(",");
		
		for (String s : seances) {
			try {
				f.ajouterSeance(s);
			} catch (Exception e) {
				System.out.println("Format de séance incorrect");
			}
		}
		
		return f;
	}

	public void creerFilm() {
		String src;
		String[] res = new String[9];
		String raw;
		
		scan.nextLine();
		System.out.print("Fichier source : ");
		src = scan.nextLine();
		
		lect = new Lecteur(src);
		try {
			lect.open();
			while ((raw = lect.read()) != null) {
				try {
					Film f;
					res = lect.parse(raw);
					if (res[0].length() == 0) {
						continue;
					}
					f = lireFilm(res);
					cineDome.ajouterFilm(f);
				} catch (Exception e) {
					System.out.println("Problème de syntaxe du fichier source");
				}
			}
		} catch (IOException e1) {
			System.out.println("Problème d'ouverture du fichier source");
		}
	}
}
