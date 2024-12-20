package projekat;

import java.util.Random;

public class Polje {
	
	private int polje[][];
	private Random random;
	public static int velicina;
	
	public Polje(int velicina) {
		this.velicina = velicina;
		polje = new int[velicina][velicina];
		random = new Random();
		
		int[] izbor = {2,4};
        randomBroj(izbor[random.nextInt(izbor.length)]);
        randomBroj(izbor[random.nextInt(izbor.length)]);
	}
	
	public void setPolje(int polje[][]) {
		for(int i = 0;i < velicina;i++) {
			for(int j = 0;j < velicina;j++) {
				this.polje[i][j] = polje[i][j];
			}
		}
	}
	
	public int[][] getPolje(){
		int[][] temp = new int[velicina][velicina];
		
		for(int i = 0;i < velicina;i++) {
			for(int j = 0;j < velicina;j++) {
				temp[i][j] = polje[i][j];
			}
		}
		
		return temp;
	}
	
	public void randomBroj(int broj) {
        
        int i = random.nextInt(velicina);
        int j = random.nextInt(velicina);
        
        while( polje[i][j] != 0) {
             i = random.nextInt(velicina);
             j = random.nextInt(velicina);
        }
        
        polje[i][j] = broj;
	}
	
	public int brojNaPolju(int i, int j) {
		int temp =  polje[i][j];
		return temp;
	}
	
	public boolean postojiBroj(int x) {
		for(int i = 0;i < velicina;i++) {
			for(int j = 0;j < velicina;j++) {
				if( polje[i][j] == x)
					return true;
			}
		}
		
		return false;
	}
	
	public Random getRandom() {
		return random;
	}
	
	public int getVelicina() {
		return velicina;
	}
	
	public void resetujPolje() {
        for (int i = 0; i < velicina; i++) {
            for (int j = 0; j < velicina; j++) {
                polje[i][j] = 0; 
            }
        }
    }
	
}


