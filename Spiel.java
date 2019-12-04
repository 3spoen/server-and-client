package AUf1;

public abstract class Spiel {
  private int spieler;
    private int wedth;
    private int langth;
   public abstract boolean spielzuge(Spielfeld spielfeld, int x,int y, Spieler cuSpieler);
   public abstract void durchgang();

    public int getSpieler() {
        return spieler;
    }
    public void setSpieler(int spieler) {
        this.spieler = spieler;
    }
    public int getWedth() {
        return wedth;
    }
    public int getLangth() {
        return langth;
    }
    public void setWedth(int wedth) {
        this.wedth = wedth;
    }
    public void setLangth(int langth) {
        this.langth = langth;
    }
}

