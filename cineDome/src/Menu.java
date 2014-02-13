import java.util.Scanner;
import java.io.IOException;
import java.text.SimpleDateFormat;

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
	
	public Film lireFilm(String[] src) {
		Film f;
		
		f = new Film(src[0],src[2],src[3],src[4],src[5],src[6],src[7]);
		
		try {
			f.setDateDeSortie(src[1]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String[] seances = src[8].split(",");
		
		for (String s : seances) {
			try {
				f.ajouterSeance(s);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return f;
	}

	public void creerFilm() {
		String titre;
		String date;
		int cat=0;
		int note;
		int nbSeances;
		int i;
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
					f = lireFilm(res);
					cineDome.ajouterFilm(f);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		/*System.out.print("Titre : ");
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

		cineDome.ajouterFilm(f);*/
		//scan.nextLine();
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
