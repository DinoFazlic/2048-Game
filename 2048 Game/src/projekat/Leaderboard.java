package projekat;

import java.io.*;
import java.util.*;

public class Leaderboard {
    private static final String FILE_NAME = "leaderboard.txt";
    private List<Rezultat> rezultati;

    public Leaderboard() {
        rezultati = new ArrayList<>();
        ucitajRezultateIzFajla();
    }

   
    public void dodajIgraca(String ime, int bodovi) {
    	
        Rezultat rezultat = new Rezultat(ime, bodovi);
        rezultati.add(rezultat);
        rezultati.sort(Comparator.comparingInt(Rezultat::getBodovi).reversed()); 
        sacuvajRezultateUFajl();
    }

    
    public void prikaziLeaderboard() {
        System.out.println("==== Leaderboard ====");
        for (int i = 0; i < rezultati.size(); i++) {
            Rezultat rezultat = rezultati.get(i);
            System.out.printf("%d. %s - %d bodova%n", i + 1, rezultat.getIme(), rezultat.getBodovi());
        }
    }

    // Čuvanje rezultata u fajl
    private void sacuvajRezultateUFajl() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Rezultat rezultat : rezultati) {
                writer.write(rezultat.getIme() + "," + rezultat.getBodovi());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Greška pri čuvanju rezultata: " + e.getMessage());
        }
    }

    
    private void ucitajRezultateIzFajla() {
        rezultati.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String ime = parts[0];
                    int bodovi = Integer.parseInt(parts[1]);
                    rezultati.add(new Rezultat(ime, bodovi));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fajl za leaderboard ne postoji. Kreira se novi.");
        } catch (IOException e) {
            System.out.println("Greška pri učitavanju rezultata: " + e.getMessage());
        }
    }
    
    private static class Rezultat {
        private final String ime;
        private final int bodovi;

        public Rezultat(String ime, int bodovi) {
            this.ime = ime;
            this.bodovi = bodovi;
        }

        public String getIme() {
            return ime;
        }

        public int getBodovi() {
            return bodovi;
        }
    }
}
