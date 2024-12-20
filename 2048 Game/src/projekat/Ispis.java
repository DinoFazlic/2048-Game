package projekat;

public class Ispis {

	public static void prikaziPolje(Polje polje) {
		
		if(polje.getVelicina() == 4) {
			for(int i = 0;i < 10;i++) 
				System.out.print(" - ");
			
			System.out.println();
			
			for(int i = 0;i < polje.getVelicina();i++) {
				
				System.out.print("|");
				
				for(int j = 0; j < polje.getVelicina();j++) {
					if(polje.brojNaPolju(i, j) == 0)
						System.out.printf(" %4s |","");
					else
						System.out.printf(" %4s |","" + polje.brojNaPolju(i, j));
				}
				
				System.out.println();
				
				for(int k = 0;k < 10;k++) 
					System.out.print(" - ");
				
				
				System.out.println();
			}
		}
		else {
			for(int i = 0;i < 12;i++) 
				System.out.print(" - ");
			
			System.out.println();
			
			for(int i = 0;i < polje.getVelicina();i++) {
				
				System.out.print("|");
				
				for(int j = 0; j < polje.getVelicina();j++) {
					if(polje.brojNaPolju(i, j) == 0)
						System.out.printf(" %4s |","");
					else
						System.out.printf(" %4s |","" + polje.brojNaPolju(i, j));
				}
				
				System.out.println();
				
				for(int k = 0;k < 12;k++) 
					System.out.print(" - ");
				
				
				System.out.println();
			}
		}
		
	}
}
