import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Lecteur {
	private Path path;
	private String nomFic ;
	private InputStream in ;
	private InputStreamReader inr ;
	private BufferedReader buf ;
	
	public Lecteur(String nomFic) {
		this.nomFic=nomFic ; 
		
	}
	
	public void open() throws IOException {
		path = Paths.get(nomFic);
		in = Files.newInputStream(path);
		inr = new InputStreamReader(in) ;
		buf = new BufferedReader(inr) ;
		
		
		
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
