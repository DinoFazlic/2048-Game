package projekat;

import java.util.Scanner;
import okvir.Prikaz2048;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Leaderboard leaderboard = new Leaderboard();
        String imeIgraca;
        boolean igraPonovo;
        int velicina;

        System.out.println("Dobrodošli u igru 2048!");
        System.out.println("");
        
        System.out.println("Unesite svoje ime: ");
        imeIgraca = scanner.nextLine();
        System.out.println("");

        do {
            
            System.out.println("Unesite koju dimenziju igre zelite (4 ili 5): ");
            velicina = scanner.nextInt();
            System.out.println("");
            
            while(velicina != 4 && velicina != 5) {
            	System.out.println("Molimo unesite korektnu dimenziju 4 ili 5: ");
                velicina = scanner.nextInt();
                System.out.println("");
            }
            
            System.out.println("");
            System.out.println("Izaberite koju verziju želite:");
            System.out.println("1. Console verzija");
            System.out.println("2. Desktop verzija");
            System.out.println("3. Izlaz");

            int izbor = scanner.nextInt();
            scanner.nextLine();

            switch (izbor) {
                case 1:
                	System.out.println("");
                    System.out.println("Pokretanje console verzije...");
                    System.out.println("");
                    consola2048 igraConsola = new consola2048(velicina);
                    Kontroler kontroler = new Kontroler();
                    kontroler.pokreniConsolu(igraConsola);
                    kontroler.igraj();

                    leaderboard.dodajIgraca(imeIgraca, igraConsola.getBodovi());
                    leaderboard.prikaziLeaderboard();
                    break;

                case 2:
                	System.out.println("");
                    System.out.println("Pokretanje desktop verzije...");
                    System.out.println("");
                    desktop2048 igraDesktop = new desktop2048(velicina);
                    Prikaz2048 prikaz = new Prikaz2048();
                    Kontroler k = new Kontroler();
                    k.pokreniDesktop(igraDesktop);
                    k.pokreniPrikaz(prikaz);
                    prikaz.pokreniKontroler(k);
                    
                    while (!igraDesktop.igraGotova()) {}
                    
                    leaderboard.dodajIgraca(imeIgraca, igraDesktop.getBodovi());
                    leaderboard.prikaziLeaderboard();
                    
                    break;

                case 3:
                	System.out.println("");
                    System.out.println("Izlazak iz igre. Doviđenja!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Pogrešan unos, izaberite 1, 2, ili 3.");
            }
            System.out.println("");
            System.out.println("Želite li igrati ponovo? (da/ne)");
            String odgovor = scanner.nextLine();
            igraPonovo = odgovor.equalsIgnoreCase("da");

        } while (igraPonovo);

        System.out.println("");
        System.out.println("Hvala što ste igrali!");
        scanner.close();
    }
}
