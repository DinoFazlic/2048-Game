package projekat;

import java.util.Scanner;

public class Unos {
	
	private static final Scanner unos = new Scanner(System.in);
	
	private static int citanjePoteza(String s) {
		System.out.println(s);
		int potez = unos.nextInt();
		
		while(potez != 1 && potez != 2 && potez != 3 && potez != 5 && potez != 0) {
			System.out.println("Potez " + potez + " nije validan, unesite novi !");
			System.out.println("");
			System.out.println(s);
			
			potez = unos.nextInt();
		}
		
		return potez;
	}
	
	public static int igracPotez() {
		int potez = citanjePoteza("Unesi potez ( 5-gore 2-dole 1-lijevo 3-desno 0-izlaz ): ");
		
		while( !(potez == desktop2048.potez_desno || potez == desktop2048.potez_lijevo || potez == desktop2048.potez_gore || potez == desktop2048.potez_dole || potez == desktop2048.izlaz) ) {
			
			System.out.println("Potez nije validan !");
			System.out.println("");
			
			potez = citanjePoteza("Unesi potez ( 5-gore 2-dole 1-lijevo 3-desno 0-izlaz ): ");
		}
		
		return potez;
	}
}
