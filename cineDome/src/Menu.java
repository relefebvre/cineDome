import java.util.Scanner;
import java.text.SimpleDateFormat;

public class Menu {
	private Cine cineDome;
	private Vue vueTexte;
	private Scanner scan;

	public Menu(Vue vue) {
		vueTexte = vue;
		cineDome = new Cine();
		scan = new Scanner(System.in);
	}

	public void afficherMenu() {
		System.out.println("1\tAjouter Films");
		System.out.println("2\tAfficher Films");
		System.out.println("3\tTrier les films(alphabetique)");
		System.out.println("4\tTrier les films(note)");
		System.out.println("5\tTrier les films(date)");
		System.out.println("9\tQuitter");
		System.out.print("Choix : ");
	}

	public int getChoix(int min, int max) {
		int choix;
		try {
			choix =  scan.nextInt();
			if (choix < min || choix > max) {
				scan.close();
				throw new Exception();
			}
		}
		catch (Exception e) {
			return -1;
		}
		scan.close();
		return choix;
	}

	public void traiterChoix(int choix) {
		switch(choix) {
			case 1 : 
				creerFilm();
				break;
			case 2 :
				System.out.println("TEsts");
				//vueTexte.affiche(cineDome.films);
				break;
			case 3 :
				cineDome.alphaSort() ;
				break ;
			case 4 :
				cineDome.noteSort() ;
				break ;
			case 5 : 
				cineDome.dateSort() ;
				break ;
			default : System.out.println("Choix incorrect");
		}
	}

	public void creerFilm() {
		/*Scanner scan;
		scan = new Scanner(System.in);*/
		String titre;
		String date;
		int cat=0;
		int note;
		int nbSeances;
		int i;
		Film f;

		System.out.print("Titre : ");
		titre = scan.nextLine();
		
		System.out.print("Date de sortie (dd/mm/aaaa) : ");
		date = scan.nextLine();
		while (!traiterDate(date)) {
			System.out.print("Format de date incorrect, veuillez retapez : ");
			date = scan.nextLine();
		}

		System.out.print("Note (/10) des utilisateurs : ");
		note = getChoix(0 ,10);
		while (note == -1) {
			System.out.print("Note incorrecte, veuillez retaper la note : ");
			note = getChoix(0, 10);
		}
	
		String categorie = cineDome.getCategorie(cat);	
		f = new Film(titre,note,categorie);

		try {
			f.setDateDeSortie(date);
		} catch(Exception e) {
		}

		System.out.print("Nombre de  séances : ");
		nbSeances = scan.nextInt();
		while (nbSeances < 0) {
			System.out.print("Nombre de séances inférieur à 0, veuillez retaper : ");
			nbSeances = scan.nextInt();
		}
		for (i=0 ; i<nbSeances ; i++) {
			String heure;
			System.out.print("Heure de la séance n°"+(i+1)+" (HH:mm) : ");
			heure = scan.nextLine();
			while (!traiterHeure(heure)) {
				System.out.print("Format d'horaire incorect, veuillez retaper : ");
				heure = scan.nextLine();
			}
			try {
				f.ajouterSeance(heure);
			} catch(Exception e) {
			}
		}

		cineDome.ajouterFilm(f);
		scan.close();
	}

	public boolean traiterDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			sdf.parse(date);
		}
		catch (Exception e) {
			return false;
		}
		return true;
	}

        public boolean traiterHeure(String heure) {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                try {
                        sdf.parse(heure);
                }
                catch (Exception e) {
                        return false;
                }
                return true;
        }

}
