package projekat;

public class Igrac {
    private String ime;
    private int bodovi;

    public Igrac(String ime, int bodovi) {
        this.ime = ime;
        this.bodovi = bodovi;
    }

    public String getIme() {
        return ime;
    }

    public int getBodovi() {
        return bodovi;
    }

    public void setBodovi(int bodovi) {
        this.bodovi = bodovi;
    }

    @Override
    public String toString() {
        return ime + ": " + bodovi + " poena";
    }
}
