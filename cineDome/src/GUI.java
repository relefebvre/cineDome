import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class GUI implements ActionListener{
	
	private JFrame frame;
	private JMenuBar menuBar;
	private JMenu fichier;
	private JMenuItem openFile;
	private JMenuItem readFile;
	private JPanel boutons;
	private JPanel film;
	private JPanel navigation;
	private JPanel liste;
	private JScrollPane scrollPane;
	private JTable tableFilm;
	private JButton recherche;
	private JButton tri;
	private JButton suivant;
	private JButton precedent;
	private JButton aleatoire;
	private JFileChooser chooser;
	private JList<String> listeFilms; 
	private String[] dataListe;
	private File fileSource;
	private JLabel info;
	
	public GUI(){
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
		openFile = new JMenuItem("Ouvrir fichier");
		readFile = new JMenuItem("Lire le fichier");
		readFile.setEnabled(false);
		
		openFile.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent event)
	        {
	           if(event.getSource() == openFile)
	           {
	   			chooser.showOpenDialog(null);
				fileSource = chooser.getSelectedFile();
				info.setText("Fichier ouvert : "+fileSource.getName());
				readFile.setEnabled(true);
	           }
	        }
		});
		fichier.add(openFile);
		fichier.add(readFile);
		
	}
	
	public void setLayout() {
		frame.setLayout(new BorderLayout());
	}
	
	public void setPanel() {
		boutons = new JPanel();
		film = new JPanel();
		navigation = new JPanel();
		liste = new JPanel();
		scrollPane = new JScrollPane();
		
		frame.add(boutons,BorderLayout.WEST);
		frame.add(film,BorderLayout.CENTER);
		frame.add(navigation,BorderLayout.SOUTH);
		frame.add(liste,BorderLayout.EAST);
		film.add(scrollPane,BorderLayout.SOUTH);
	}
	
	public void addBoutons() {
		recherche = new JButton("Rechercher");
		tri = new JButton("Trier");
		
		boutons.setLayout(new GridLayout(0,1));
		
		boutons.add(recherche);
		boutons.add(tri);
	}
	
	public void addNavigation() {
		suivant = new JButton("Suivant");
		precedent = new JButton("Précédent");
		aleatoire = new JButton("Aléatoire");
		
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
		liste.add(listeFilms);
	}
	
	public void setFilm() {
		
	}
	
	public void actionPerformed(ActionEvent e) {

	}
}
