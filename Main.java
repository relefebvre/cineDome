import java.io.*;

public class Main {
	public static void main(String[] argv) {
		Vue vue = new Vue();
		Menu menu = new Menu(vue);
		int choix;

		menu.afficherMenu();
		while((choix = menu.getChoix(1 , 9)) != 9) {
			menu.traiterChoix(choix);
			menu.afficherMenu();
		}

		
	}
}
