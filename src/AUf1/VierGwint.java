package AUf1;

import java.util.Scanner;

public class VierGwint extends Spiel implements Protokolierbar {
    private Spieler cuSpieler;
    private Spieler spieler1;
    private Spieler spieler2;
    private Scanner uInput = new Scanner(System.in);


    @Override          //Eingabe im Feld hinfuegen
    public boolean hinfugen(Spielfeld spielfeld, int x,int y,Spieler cuSpieler) {

        for(int row1 = spielfeld.getX() - 1; row1 >= 0; row1 --) {

            if(spielfeld.getFeld()[row1][y] == '*') {
                spielfeld.getFeld()[row1][y] = cuSpieler.getFarbe();
                return true;
            }
        }
        return true;
  }
    @Override  // die umgekehrte Funktion von hifugen.
    public void entfernen(Spielfeld spielfeld, int x, int y, Spieler cuSpieler) {
        for(int row1 = spielfeld.getX() - 1; row1 >= 0; row1 --) {

            if (spielfeld.getFeld()[row1][y]==cuSpieler.getFarbe()) {
                spielfeld.getFeld()[row1][y] = '*';
            }
        }
    }
    @Override     //hier wird gepruft ob die gewuenschte Punkt gultig oder schon gewaehlt ist.
    public boolean spielzuge(Spielfeld spielfeld, int x,int y, Spieler cuSpieler) {

        if (spielfeld.getFeld()[0][y] != '*' ) {
            System.out.println("schon gewaehlt!!!!");
            spielerWechseln();
        }
        if ( y >= spielfeld.getY()) {
            System.out.println("Falsche Aingabe ؟?  ");
            spielerWechseln();
        }
        hinfugen(spielfeld,x,y,cuSpieler);
        return true;
    }
        @Override
        public void durchgang()  {
            String player1Name, player2Name;
// Spielfeldkoordinaten
            int x ,y;
            System.out.println("bitte Geben Sie die SpielfeldKoordinaten ein.");
            x = uInput.nextInt();
            y = uInput.nextInt();
            Spielfeld feld = new Spielfeld(x,y);

            feld.speilfeldFuelle(feld.getFeld(), '*');
            feld.spielefeldDarstellung(feld.getFeld());
// das SpielFeld ist nun bereit
// Eingabe der Name und Arten von Spielern
            System.out.println("Was ist die Name des ersten Spielers?");
            player1Name = uInput.next();
            spieler1 = new Spieler(player1Name, false);
            System.out.println("Danke");

//            System.out.println("Moechten Sie gegen Cumputer spielen? o.ja  1.Nein");
//            int cump=uInput.nextInt();

//            if (cump==0)spieler2= new Spieler("Cumputer",true);


            System.out.println("was ist die Name des zweiten Spielers?");
            player2Name = uInput.next();
            spieler2 = new Spieler(player2Name, false);
            System.out.println("Danke");

            char farbe_1, farbe_2;
            System.out.println("Welche Farbe moechte "+spieler1.getName()+"  (Rot r oder Blau b)?....Anders haben wir leider nicht :-)");
            farbe_1 = uInput.next().charAt(0);

            if (farbe_1 == 'r') farbe_2='b';
              else farbe_2='r';

                spieler1.setFarbe(farbe_1);
                spieler2.setFarbe(farbe_2);
                cuSpieler = spieler1;

//Spiel verlauf
            while (true) {
                System.out.println( cuSpieler.getName() + " ist dran");
                System.out.println("bitte geben Sie ein Zahl zwischen 1 and " + feld.getY());

                if (!uInput.hasNextInt()) {
                    System.out.println(  cuSpieler.getName() + " Bitte geben Sie einen gultigen Nummer zwischen 1 und  "+feld.getY());
                    uInput.next();
                    continue;
                }else{
                    int number = uInput.nextInt();
                        spielzuge(feld, 0,number - 1, cuSpieler);
                        feld.spielefeldDarstellung(feld.getFeld());

                    System.out.println("um die Zuge zu enfernen bitte geben Sie (x) ein.");
                    if(uInput.next().charAt(0)=='x'){
                        entfernen(feld,0,number-1,cuSpieler);
                        feld.spielefeldDarstellung(feld.getFeld());
                        continue;
                    }
                }
                if(checkGewinner(feld)) {
                    System.out.println(cuSpieler.getName() + " hat Gewonnen :-)");
                    break;
                }


                  checkObUnentschieden(feld);
                  spielerWechseln();
            }

        }

    private  void spielerWechseln() {
        if (cuSpieler == spieler1) cuSpieler = spieler2;
        else { cuSpieler = spieler1; }

    }
    private  boolean checkGewinner(Spielfeld spielfeld) {

        //horizontal
        for (int row = 0; row < spielfeld.getFeld().length; row++){

            for (int col = 0; col < spielfeld.getFeld()[row].length - 3; col++){

                if (spielfeld.getFeld()[row][col] != '*' && spielfeld.getFeld()[row][col] == spielfeld.getFeld()[row][col+1]
                        && spielfeld.getFeld()[row][col] == spielfeld.getFeld()[row][col+2]
                        && spielfeld.getFeld()[row][col] == spielfeld.getFeld()[row][col+3])
                    return true;
            }
        }

        //vertical
        for (int col = 0; col < spielfeld.getFeld()[0].length; col++){

            for (int row = 0; row < spielfeld.getFeld().length - 3; row++){

                if (spielfeld.getFeld()[row][col] != '*' && spielfeld.getFeld()[row][col] == spielfeld.getFeld()[row+1][col]
                        && spielfeld.getFeld()[row][col] == spielfeld.getFeld()[row+2][col]
                        && spielfeld.getFeld()[row][col] == spielfeld.getFeld()[row+3][col])
                    return true;
            }
        }

        //diagonal oben links
        for (int row = 0; row < spielfeld.getFeld().length - 3; row++){

            for (int col = 0; col < spielfeld.getFeld()[row].length - 3; col++){

                if (spielfeld.getFeld()[row][col] != '*' && spielfeld.getFeld()[row][col] == spielfeld.getFeld()[row+1][col+1]
                        && spielfeld.getFeld()[row][col] == spielfeld.getFeld()[row+2][col+2]
                        && spielfeld.getFeld()[row][col] == spielfeld.getFeld()[row+3][col+3])
                    return true;
            }
        }

        //diagonal oben rechts
        for (int row = 0; row < spielfeld.getFeld().length - 3; row++){

            for (int col = 3; col < spielfeld.getFeld()[row].length; col++){

                if (spielfeld.getFeld()[row][col] != '*' && spielfeld.getFeld()[row][col] == spielfeld.getFeld()[row+1][col-1]
                        && spielfeld.getFeld()[row][col] == spielfeld.getFeld()[row+2][col-2]
                        && spielfeld.getFeld()[row][col] == spielfeld.getFeld()[row+3][col-3])
                    return true;
            }
        }
        return false;
    }
    private void checkObUnentschieden(Spielfeld board) {

        for (int i = 0; i < board.getY(); i++) {

            if (board.getFeld()[0][i] == '*')
                return;
        }
        System.out.println("keiner hat gewonnen:-(");
        System.exit(0);
    }
}
