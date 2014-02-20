import java.io.IOException;

public class Menu {
	public Cine cineDome;
	private Vue vueTexte;
	public Lecteur lect;

	public Menu(Vue vue) {
		vueTexte = vue;
		cineDome = new Cine();
	}

	public void traiterChoix(int choix) {
		switch(choix) {
			case 1 : 
				//creerFilm(vueTexte.saisirPath());
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
				System.out.println("Format de s�ance incorrect");
			}
		}
		
		return f;
	}

	public void creerFilm() throws Exception {
		String[] res = new String[9];
		String raw;
		
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
					//System.out.println("Probl�me de syntaxe du fichier source");
					throw e;
				}
			}

	}
	
	public void openFile(String path) throws IOException {
		lect = new Lecteur(path);
		try {
			lect.open();
		}catch (IOException e1) {
			throw e1;
		}
	}
}
