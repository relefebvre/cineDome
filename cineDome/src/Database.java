import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;


public class Database {
	
	private JFrame connexionDB;
	private JPanel paneConnexion;
	private JTextField AddrText;
	private JTextField LoginText;
	private JPasswordField mdpText;
	private JButton valider;
	
	private String addrBase = "//hina/phpmyadmin/dbalducros";
	private String login;
	private char[] mdp;
	
	private Connection connection ;
	
	public Database(){
		
		setConnexion();
		
		
		
		connexionDB.setAlwaysOnTop(true);
		connexionDB.setVisible(true);
	}
	
	private ResultSet sendReq(String req){
		ResultSet res = null;
		
		try {
			Statement stm = connection.createStatement();
			res = stm.executeQuery(req);
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return res ;
	}
	
	public List<String> getListeFilms(char tri) throws SQLException{
		String triReq ="" ;
		switch(tri){
		case 'a' : triReq = " ORDER BY `COL 1`";//tri par ordre alphabetique
		break;
		case 'p': triReq = " ORDER BY `COL 6`";//tri par note de presse
		break;
		case 's' : triReq = " ORDER BY `COL 7`";//tri par note des spectateurs
		break ;
		case 'd' : triReq = " ORDER BY `COL 2`";//tri par date de sortie
		break;
		default:
		
		}
		
		String requete = "SELECT `COL 1` FROM `TABLE 1`"+triReq;
		List<String> films = new ArrayList<String>();
		ResultSet res = sendReq(requete);
		
		while(res.next()){
			films.add(res.getString(1));
		}
		
		return films ;
	}
	
	public Film getFilm(String nom) {
		Film f = null ;
		
		String requete = "SELECT * FROM `TABLE 1` WHERE `COL 1` = '"+nom+"';";
		ResultSet res = sendReq(requete);
		
		try {
			while(res.next()){
				//A FAIRE !
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return f;
		
	}
	
	public void setConnexion() {
		connexionDB = new JFrame("Connexion � la Base de Donn�e");
		connexionDB.setMinimumSize(new Dimension(400,120));
		
		connexionDB.setLayout(new BorderLayout());
		connexionDB.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		paneConnexion = new JPanel();
		
		connexionDB.add(paneConnexion);
		
		JLabel addrLabel = new JLabel("Adresse");
		JLabel loginLabel = new JLabel("Login");
		JLabel mdpLabel = new JLabel("Mot de Passe"); 
		LoginText = new JTextField();
		mdpText = new JPasswordField();
		AddrText = new JTextField();
		
		paneConnexion.setLayout(new GridLayout(4,2));
		paneConnexion.add(addrLabel);
		paneConnexion.add(AddrText);
		paneConnexion.add(loginLabel);
		paneConnexion.add(LoginText);
		paneConnexion.add(mdpLabel);
		paneConnexion.add(mdpText);
		
		valider = new JButton("Valider");
		
		valider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == valider) {
					login = LoginText.getText();
					mdp = mdpText.getPassword();
					addrBase = AddrText.getText();
					
					if(login.length() != 0){
						connexionDB.dispose();
						try {
							Class.forName("com.mysql.jdbc.Driver");
							connection = DriverManager.getConnection("jdbc:mysql:"+addrBase, login, new String(mdp));
						}
						catch (Exception exc){
							exc.printStackTrace();
						}
					}
				}
			}
		});
		
		paneConnexion.add(valider);
	}

}
