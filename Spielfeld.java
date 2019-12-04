package AUf1;


public class Spielfeld {
    private int x;
    private int y;
    private char[][] feld;

    public Spielfeld(int x, int y) {
        this.feld = new char[x][y];
        this.x=x;
        this.y=y;

    }
    public Spielfeld() {

    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public char[][] getFeld() {
        return this.feld;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setFeld(char[][] feld) {
        this.feld = feld;
    }
// fuelle die Reihung mit Groundzeichnung k.
    void speilfeldFuelle(char[][] feld, char k) {

        for (int row = 0; row < feld.length; row++) {
            java.util.Arrays.fill(feld[row], 0, feld[row].length, k);
        }
    }////// Ausgabe das Spilefeldes.
    void spielefeldDarstellung(char[][] feld) {
        int i; int j;
        System.out.println("");
        for( i = 0; i < feld.length; i++) {
            System.out.print("|_");

            for( j = 0; j < feld[i].length; j++) {
                System.out.print(feld[i][j]+"_|_");
            }

            System.out.println();
        }

    }
}
