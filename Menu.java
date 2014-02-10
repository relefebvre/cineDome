import java.io.*;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class Menu {
	private Cine cineDome;
	private Vue vueTexte;

	public Menu(Vue vue) {
		vueTexte = vue;
		cineDome = new Cine();
	}

	public void afficherMenu() {
		System.out.println("1\tAjouter Film");
		System.out.println("2\tSupprimer Film");
		System.out.println("3\tAfficher Films");
		System.out.println("4\tAjouter Catégorie");
		System.out.println("5\tSupprimer Catégorie");
		System.out.println("6\tTrier les films(alphabetique)");
		System.out.println("7\tTrier les films(note)");
		System.out.println("8\tTrier les films(date)");
		System.out.println("9\tQuitter");
		System.out.print("Choix : ");
	}

	public int getChoix(int min, int max) {
		Scanner scan;
		scan = new Scanner(System.in);
		int choix;
		try {
			choix =  scan.nextInt();
			if (choix < min || choix > max)
				throw new Exception();
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
			case 2 : System.out.println("Suppression Film");
				break;
			case 3 : 
				vueTexte.affiche(cineDome.films);
				break;
			case 4 : 
				creerCategorie();
				break;
			case 5 :
				supprimerCategorie();
				break;
			case 6 :
				cineDome.alphaSort() ;
				break ;
			case 7 :
				cineDome.noteSort() ;
				break ;
			case 8 : 
				cineDome.dateSort() ;
				break ;
			default : System.out.println("Choix incorrect");
		}
	}

	public void creerFilm() {
		Scanner scan;
		scan = new Scanner(System.in);
		String titre;
		String date;
		int cat=0;
		int note;
		char validation='n';
		boolean ok=false;
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
	
		while ((validation != 'O')&&(validation != 'o')) {
			cat = choisirCategorie();
			validation = scan.nextLine().charAt(0);
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


	public void supprimerFilm() {
		
	}

	public int choisirCategorie() {
		int cat;
		vueTexte.afficherCategories(cineDome.getCategories());
        System.out.print("Catégorie : ");
		cat = getChoix(1 , cineDome.nbCategories());
        while (cat == -1) {
			System.out.println("Choix incorrect");
	        System.out.print("Catégorie : ");
	        cat = getChoix(1 , cineDome.nbCategories());
		}
        System.out.println("Catégorie : " + cineDome.getCategorie(cat));
        System.out.print("Valider Catégorie (O/N) : ");
		return cat;
	}

	public void creerCategorie() {
		Scanner scan;
		scan = new Scanner(System.in);
		boolean ok=false;

		while (!ok) {
			System.out.println("Catégories existantes : ");
	                vueTexte.afficherCategories(cineDome.getCategories());
        	        System.out.print("Nom de la nouvelle Catégorie : ");

			try {
				cineDome.ajouterCategorie(scan.nextLine());
			}
			catch (Exception e) {
				System.out.println("Catégorie déjà existante");
				continue;
			}
			ok = true;
		}
	}

	public void supprimerCategorie() {
		Scanner scan;
		scan = new Scanner(System.in);
		char validation = 'n';
		int cat=0;

		if (cineDome.nbCategories() == 0) {
			System.out.println("Il n'y a aucune Catégorie");
			return;
		}

               	while (validation != 'O') {
                        cat = choisirCategorie();
                        validation = scan.nextLine().charAt(0);
                }
		
		cineDome.supprimerCategorie(cat);
	}
}
