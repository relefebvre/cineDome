import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStreamReader;


public class Lecteur {
	private String path;
	private BufferedReader buf ;
	
	public Lecteur(String nomFic) {
		this.path=nomFic ; 
	}
	
	public void open() throws IOException {
		buf = new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF-8")) ;	
	}
	
	public String read() throws IOException {
		return buf.readLine();
	}
	
	public String[] parse(String raw) throws Exception{
		String[] split = new String[9] ;
		
		split = raw.split(";",-1);
		
		if(split.length<9){
			throw new Exception("Erreur de syntaxe dans le fichier source\n");
		}
		
		return split;	
	}
}
