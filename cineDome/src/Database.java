import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Database {
	
	private Connection connection ;
	
	public Database(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://hina/phpmyadmin/dbalducros", "alducros", "zboub");
		}
		catch (Exception e){
			e.printStackTrace();
		}
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
		case 'a' : triReq = " ORDER BY `COL 1`";
		break;
		case 'p': triReq = " ORDER BY `COL 6`";
		break;
		case 's' : triReq = " ORDER BY `COL 7`";
		break ;
		case 'd' : triReq = " ORDER BY `COL 2`";
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

}
