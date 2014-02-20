

public class Main {
	public static void main(String[] argv) {
		Vue vue = new Vue();
		Menu menu = new Menu(vue);
		GUI vueG = new GUI(menu);

		int choix;

		vue.afficherMenu();
		while((choix = vue.getChoix(1 , 9)) != 9) {
			menu.traiterChoix(choix);
			vue.afficherMenu();
		}

		
	}
}
