

public class Main {
	public static void main(String[] argv) {
		Vue vue = new Vue();
		GUI vueG = new GUI();
		Menu menu = new Menu(vue);

		int choix;

		vue.afficherMenu();
		while((choix = vue.getChoix(1 , 9)) != 9) {
			menu.traiterChoix(choix);
			vue.afficherMenu();
		}

		
	}
}
