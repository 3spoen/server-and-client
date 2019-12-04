package AUf1;


public class Spieler {
    private char farbe;
    private boolean art;
    private String name;

    public Spieler(String name, boolean art) {
        this.name = name;
        this.art = art;
    }

    public boolean isArt() {
        return this.art;
    }

    public char getFarbe() {
        return farbe;
    }

    public void setFarbe(char farbe) {
        this.farbe = farbe;
    }

    public String getName() {
        return this.name;
    }

    public void setArt(boolean art) {
        this.art = art;
    }

    public void setName(String name) {
        this.name = name;
    }
}
