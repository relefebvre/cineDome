import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;

public class GUI implements ActionListener{
	
	private JFrame frame;
	private JMenuBar menuBar;
	private JMenu fichier;
	private JMenuItem selectFile;
	private JPanel boutons;
	private JPanel film;
	private JPanel navigation;
	private JPanel liste;
	private JButton recherche;
	private JButton tri;
	private JButton suivant;
	private JButton precedent;
	private JButton aleatoire;
	private JButton alpha;
	private JFileChooser chooser;
	private JList<String> listeFilms; 
	private File fileSource;
	private JLabel info;
	private JLabel titreFilm;
	private JLabel dateSortie;
	private JLabel duree;
	private JLabel notePresse, noteSpectateur;
	private JLabel categorie;
	private JList<String> listeSceances; 
	private JScrollPane scrollSceances;
	private JList<String> listeActeurs;
	private JScrollPane scrollActeurs;
	private JLabel affiche ;
	public Menu control;

	
	int indexNav = 0 ;
	
	public GUI(Menu menu){
		this.control = menu;
		
		frame = new JFrame("Cine Dome");
		frame.setMinimumSize(new Dimension(720,640));
		
		chooser = new JFileChooser();
		
		setMenu();
		setLayout();
		setPanel();
		addBoutons();
		addNavigation();
		addLabel();
		setListe();
		setFilm();
	
		frame.setVisible(true);
	}
	
	public void setMenu() {	
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		fichier = new JMenu("Fichier");
		menuBar.add(fichier);
		selectFile = new JMenuItem("Selectionner un fichier");
		
		selectFile.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent event) {
	           if(event.getSource() == selectFile) {
	   			chooser.showOpenDialog(null);
				fileSource = chooser.getSelectedFile();
				info.setText("Fichier selectionn� : "+fileSource.getName());
				try {
					control.openFile(fileSource.getPath());
					try {
						control.creerFilm();
					} catch (Exception e) {
						info.setText("Probl�me de syntaxe dans le fichier : "+fileSource.getPath());
					}
				} catch(IOException e1) {
					info.setText("Probl�me d'ouverture du fichier : "+fileSource.getPath()+" :: "+e1.getMessage());
					info.setForeground(Color.red);
				}
	           }
	           afficherTitreFilms();
	           updateFilm(control.cineDome.films.get(indexNav));
	        }
		});
		
		fichier.add(selectFile);
		
	}
	
	public void setLayout() {
		frame.setLayout(new BorderLayout());
	}
	
	public void setPanel() {
		boutons = new JPanel();
		film = new JPanel();
		navigation = new JPanel();
		liste = new JPanel();
		
		frame.add(boutons,BorderLayout.WEST);
		frame.add(film,BorderLayout.CENTER);
		frame.add(navigation,BorderLayout.SOUTH);
		frame.add(liste,BorderLayout.EAST);
	
		
	}
	
	
	public void addBoutons() {
		recherche = new JButton("Rechercher");
		tri = new JButton("Trier");
		alpha = new JButton("Alpha");
		
		alpha.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == alpha){
					afficherTitreFilms();
				}
			}
		});
		
		
		boutons.setLayout(new GridLayout(5,0));
		
		boutons.add(recherche);
		boutons.add(tri);
		boutons.add(alpha);
		
	}
	
	public void addNavigation() {
		suivant = new JButton("Suivant");
		precedent = new JButton("Précédent");
		aleatoire = new JButton("Aléatoire");
		
		suivant.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == suivant){
					indexNav++;
					if(indexNav <= control.cineDome.films.size()){
						updateFilm(control.cineDome.films.get(indexNav));
					} else {
						indexNav--;
					}
				}
			}
		});
		
		precedent.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == precedent){
					indexNav--;
					if(indexNav > 0){
						updateFilm(control.cineDome.films.get(indexNav));
					} else {
						indexNav++; 
					}
				}
			}
		});
		
		aleatoire.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==aleatoire){
					Random r = new Random();
					indexNav = r.nextInt(control.cineDome.films.size());
					try{
					updateFilm(control.cineDome.films.get(indexNav));
					}
					catch(Exception ex) {
						info.setText("Aucun film chargé.");
					}
				}
			}
		});
		
		navigation.setLayout(new GridLayout(1,0));
		
		navigation.add(precedent);
		navigation.add(aleatoire);
		navigation.add(suivant);
	}
	
	public void addLabel() {
		info = new JLabel();
		frame.add(info,BorderLayout.NORTH);
	}
	
	public void setListe() {
		listeFilms = new JList<String>();
	}
	
	public void setFilm() {
		titreFilm = new JLabel();
		dateSortie= new JLabel();
		duree= new JLabel();
		notePresse= new JLabel();
		noteSpectateur= new JLabel();
		categorie= new JLabel();
		listeSceances = new JList<String>(); 
		listeActeurs = new JList<String>();
		affiche = new JLabel();
		scrollActeurs = new JScrollPane();
		scrollSceances = new JScrollPane() ;
		
		film.setLayout(new GridLayout(0, 1));
		
		film.add(affiche);
		film.add(titreFilm);
		film.add(dateSortie);
		film.add(duree);
		film.add(notePresse);
		film.add(noteSpectateur);
		film.add(categorie);
		film.add(scrollActeurs);
		film.add(scrollSceances);
		
		
	}
	
	public void updateFilm(Film f){
		/**
		 * Affichage de l'affiche non fonctionnel
		 * 
		 * 
		 * 
		 */
		
		URL url;
		try {
			url = new URL(f.affiche);
			
			try {
				BufferedImage image = ImageIO.read(url);
				
				affiche.setIcon(new ImageIcon(image));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SimpleDateFormat sdfD = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfS = new SimpleDateFormat("hh:mm");
		
		int heure, min ;
		
		heure = f.duree/3600;
		min = (f.duree%3600)/60;
		
		titreFilm.setText("Titre : "+f.titre);
		dateSortie.setText("Sortie : "+sdfD.format(f.dateSortie));
		duree.setText("Durée : "+heure+"h "+min+"min");
		notePresse.setText("Note Presse : "+f.notePresse+"");
		noteSpectateur.setText("Note Spectateurs : "+f.noteSpect+"");
		categorie.setText("Catégorie : "+f.categorie);
		
		listeActeurs = new JList<String>(f.acteurs);
		listeActeurs.setVisibleRowCount(4);
		scrollActeurs.setViewportView(listeActeurs);

		
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		
		for(Date d : f.getSeances()){
			listModel.addElement(sdfS.format(d));
		}
		listeSceances = new JList<String>(listModel);
		listeSceances.setVisibleRowCount(4);
		scrollSceances.setViewportView(listeSceances);
		


		
		
	}
	
	public void afficherTitreFilms() {
		DefaultListModel<String> listModel = new DefaultListModel<String>();

		try {
			for (String f : control.cineDome.data.getListeFilms('a')) {
				listModel.addElement(f);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		listeFilms = new JList<String>(listModel);
		listeFilms.setVisibleRowCount(20);
		JScrollPane listScrollPane2 = new JScrollPane(listeFilms);
		
		liste.add(listScrollPane2);

		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
}
