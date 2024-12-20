package projekat;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import okvir.Prikaz2048;

public class Kontroler {
	private desktop2048 igraDesktop;
	private consola2048 igraConsola;
	private Prikaz2048 prikaz;
	
	
	public Kontroler() {}
	
	public void pokreniDesktop(desktop2048 igra) {
		this.igraDesktop = igra;
	}
	
	public void pokreniConsolu(consola2048 igra) {
		this.igraConsola = igra;
	}
	
	public void pokreniPrikaz(Prikaz2048 p) {
		this.prikaz = p;
		prikaz.promijeniPolje(igraDesktop.getPolje(),igraDesktop.getBodovi());
	}
	
	public void uradiPotez(int potez) {
		boolean potezNapravljen = igraDesktop.uradiPotez(potez);
		
		if ( igraDesktop.igraGotova() ) {
			prikaz.promijeniPolje(igraDesktop.getPolje(),igraDesktop.getBodovi());
			
			if( igraDesktop.igraPobjeda()) {
				prikaz.prikaziRezultat("POBJEDILI STE !!");
			}
			else {
				prikaz.prikaziRezultat("IZGUBILI STE !!");
			}
		}
		else {
			if( potezNapravljen ) {
				int[] izbor = {2,4};
				igraDesktop.getPolje().randomBroj(izbor[igraDesktop.getPolje().getRandom().nextInt(izbor.length)]);
				prikaz.promijeniPolje(igraDesktop.getPolje(),igraDesktop.getBodovi());
			}
		}
	}
	
	public void igraj() {
		int[] izbor = {2,4};
		
        while( !igraConsola.igraGotova()) {
        	
        	System.out.println("Trenutni score: " + igraConsola.getBodovi());
        	Ispis.prikaziPolje(igraConsola.getPolje());
        	
        	boolean potezNapravljen = igraConsola.uradiPotez(Unos.igracPotez());
        	
        	if(igraConsola.getKraj() == true)
        		break;
        	
        	if(potezNapravljen)
        		igraConsola.getPolje().randomBroj(izbor[igraConsola.getPolje().getRandom().nextInt(izbor.length)]);
        	
        }
        
        if (igraConsola.igraPobjeda()) {
        	System.out.println("");
        	System.out.println("POBJEDILI STE !!");
        }
        else if(igraConsola.getKraj() == true){
        	System.out.println("Igra je zavrsena. Zavrsni rezultat: " + igraConsola.getBodovi());
        }
        else {
        	System.out.println("");
        	System.out.println("IZGUBILI STE !!");
        }
        
	}
	
	public boolean sacuvajStanje(String fajl) {
	    try (FileWriter writer = new FileWriter(new File(fajl))) {
	    	int[][] polje = igraDesktop.getPolje().getPolje();
	        for (int i = 0; i < polje.length; i++) {
	            for (int j = 0; j < polje[i].length; j++) {
	                writer.write(polje[i][j] + " ");
	            }
	            writer.write("\n");
	        }
	        return true;
	    } catch (IOException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public void novaIgra() {
	    if (igraDesktop == null) {
	        igraDesktop = new desktop2048(igraDesktop.velicina);  // Inicijalizuj novu igru sa odgovarajućom veličinom
	    }

	    // Resetuj polje i bodove
	    igraDesktop.getPolje().resetujPolje();  // Resetuj polje
	    igraDesktop.setBodovi(0);  // Postavi bodove na 0
	    prikaz.promijeniPolje(igraDesktop.getPolje(), igraDesktop.getBodovi());  // Ažuriraj prikaz
	}



	
	

}
