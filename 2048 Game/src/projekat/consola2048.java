package projekat;

public class consola2048 {

	private Polje polje;
	
	public static final int maks = 2048;
	public static final int prazno_polje = 0;
	private int bodovi = 0;
	
	public int velicina = 4;
	public static final int potez_gore = 5;
	public static final int potez_dole = 2;
	public static final int potez_lijevo = 1;
	public static final int potez_desno = 3;
	public static final int izlaz = 0;
	private boolean kraj = false;
	
	public consola2048(int velicina) {
		polje = new Polje(velicina);
		this.velicina = velicina;
	}
	
	public Polje getPolje() {
		return polje;
	}
	
	
	public boolean igraPobjeda() {
		for(int i = 0;i < polje.getVelicina();i++) {
			for(int j = 0;j < polje.getVelicina();j++) {
				if( polje.brojNaPolju(i,j) == maks)
					return true;
			}
		}
		
		return false;
	}
	
	public boolean imaPotez() {
		
		for(int i = 0;i < polje.getVelicina() - 1;i++) {
			for(int j = 0;j < polje.getVelicina() - 1;j++) {
				if(polje.brojNaPolju(i, j) ==polje.brojNaPolju(i, j+1) || polje.brojNaPolju(i, j) == polje.brojNaPolju(i + 1, j) ) {
					return true;
				}
			}
		}
		
		for(int i = 0;i < polje.getVelicina() - 1;i++)
			if(polje.brojNaPolju(polje.getVelicina() - 1, i) == polje.brojNaPolju(polje.getVelicina() - 1, i + 1))
				return true;
		
		for(int i = 0;i < polje.getVelicina() - 1;i++)
			if(polje.brojNaPolju(i,polje.getVelicina() - 1) == polje.brojNaPolju(i + 1,polje.getVelicina() - 1))
				return true;
		
		return false;
	}
	
	public boolean igraGotova() {
		if( igraPobjeda() )
			return true;
		
		if(polje.postojiBroj(prazno_polje))
			return false;
		
		
		
		return !imaPotez();
	}
	
	
	public int[] uradiPotezLijevo(int red[]) {
		int noviRed[] = new int[polje.getVelicina()];
		int j = 0;
		
		for(int i = 0;i < polje.getVelicina();i++) {
			if( red[i] != 0)
				noviRed[j++] = red[i];
		}
		
		for(int i = 0;i < polje.getVelicina() - 1;i++) {
			
			if(noviRed[i] != 0 && noviRed[i] == noviRed[i+1]) {
				
				noviRed[i] = 2 * noviRed[i];
				bodovi += noviRed[i];
				
				for(j = i + 1;j < polje.getVelicina() - 1;j++) {
					noviRed[j] = noviRed[j + 1];
				}
				
				noviRed[polje.getVelicina() - 1] = 0;
			}
		}
		
		return noviRed;
	}
	
	

	
	
	public int[] obrniRed(int niz[]) {
		int[] obrnuti = new int[niz.length];
		
		for(int i = niz.length-1;i >= 0;i--) {
			obrnuti[i] = niz[niz.length - i - 1];
		}
		
		return obrnuti;
	}
	
	
	public int[] uradiPotezDesno(int red[]) {
		int noviRed[] = new int[polje.getVelicina()];
		int j = 0;
		
		for(int i = 0;i < polje.getVelicina();i++) {
			if( red[i] != 0)
				noviRed[j++] = red[i];
		}
		
		noviRed = obrniRed(noviRed);
		
		noviRed = uradiPotezLijevo(noviRed);
		
		return obrniRed(noviRed);
	}
	
	

	
	private boolean potezNapravljen(int[][] polje1, int[][] polje2) {
		for(int i = 0;i < polje.getVelicina() ;i++) {
			for(int j = 0;j < polje.getVelicina();j++) {
				if( polje1[i][j] != polje2[i][j]) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public boolean uradiPotez(int potez) {
		int[][] polje = this.polje.getPolje();
		
		switch(potez) {
			case potez_gore:
			{
				for(int i = 0;i < velicina;i++) {
					int red[] = new int[velicina];
					
					for(int j = 0;j < velicina;j++) {
						red[j] = polje[j][i];
					}
					
					int noviRed[] = uradiPotezLijevo(red);
					
					for(int j = 0;j < velicina;j++) {
						polje[j][i] = noviRed[j];
					}
				}
			}
			break;
			case potez_dole:
			{
				for(int i = 0;i < velicina;i++) {
					
					int red[] = new int[velicina];
					
					for(int j = 0;j < velicina;j++) {
						red[j] = polje[j][i];
					}
					
					int noviRed[] = uradiPotezDesno(red);
					
					for(int j = 0;j < velicina;j++) {
						polje[j][i]= noviRed[j];
					}
				}
			}
			break;
			case potez_lijevo:
			{
				for(int i = 0;i < velicina;i++) {
					int noviRed[] = uradiPotezLijevo(polje[i]);
					
					for(int j = 0;j < velicina; j++) {
						polje[i][j] = noviRed[j];
					}
				}
			}
			break;
			case potez_desno:
			{
				for(int i = 0;i < velicina;i++) {
					int noviRed[] = uradiPotezDesno(polje[i]);
					
					for(int j = 0;j < velicina; j++) {
						polje[i][j] = noviRed[j];
					}
				}
			}
			break;
			case izlaz:
			{
				kraj = true;
				return false;
			}
		}
		
		boolean potezNapravljen = potezNapravljen(this.polje.getPolje(),polje);
		
		this.polje.setPolje(polje);
		
		return potezNapravljen;
	}
	
	public int getBodovi() {
		return bodovi;
	}
	
	public boolean getKraj() {
		return kraj;
	}
}
