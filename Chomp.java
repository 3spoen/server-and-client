package AUf1;

import java.util.Scanner;

public class Chomp extends Spiel implements Protokolierbar {
    private static Spieler cuSpieler;
    private static Spieler spieler1;
    private static Spieler spieler2;
    Scanner uInput = new Scanner(System.in);

    @Override
    public void durchgang() {

        String player1Name, player2Name;
// Spielfeldkoordinaten
        int x ,y;
        System.out.println("bitte Geben Sie die SpielfeldKoordinaten ein.");
        x = uInput.nextInt();
        y = uInput.nextInt();


        Spielfeld board = new Spielfeld(x,y);

        board.speilfeldFuelle(board.getFeld(), '*');
        board.spielefeldDarstellung(board.getFeld());
// Eingabe der Name und Arten von Spielern
        System.out.println("Was ist die Name des ersten Spielers?");
        player1Name = uInput.next();
        System.out.println("Danke");
        spieler1 = new Spieler(player1Name, false);

        System.out.println("was ist die Name des zweiten Spielers?");
        player2Name = uInput.next();
        System.out.println("Danke");
        spieler2 = new Spieler(player2Name, false);
        cuSpieler = spieler1;

        while (true) {



            System.out.println("Spieler  "+ cuSpieler.getName() + " ist dran");
            System.out.println("bitte geben Sie ein Zahl fuer Y zwischen 1 and " + board.getY());
            if (!uInput.hasNextInt()) {
                System.out.println(  cuSpieler.getName() + " Bitte geben Sie einen gultigen Nummer zwischen 1 und  "+board.getY());

                uInput.next();
                continue;
            }else{
                int ro = uInput.nextInt() ;
                System.out.println("bitte geben Sie ein Zahl fuer X zwischen 1 and " + board.getX());

                int co = uInput.nextInt() ;
                if (co >= 0 && co <= board.getY() && ro>=0 && ro<=board.getX() ) {

                    spielzuge(board,ro,co,cuSpieler);
                    board.spielefeldDarstellung(board.getFeld());
                     System.out.println("um die Zuge zu enfernen bitte geben Sie (x) ein.");
                     if(uInput.next().charAt(0)=='x') {
                         entfernen(board,ro, co, cuSpieler);
                         board.spielefeldDarstellung(board.getFeld());
                         continue;
                     }
                } else {
                    System.out.println("Falsche Aingabe ؟?  ");
                    continue;
                }
            }
            spielerWechseln();


        }
    }
    @Override
    public boolean spielzuge(Spielfeld spielfeld, int x, int y, Spieler cuSpieler) {
       hinfugen(spielfeld,x,y,cuSpieler);
        for (int i = 0; i < spielfeld.getY(); i++) {
            if (spielfeld.getFeld()[0][i] == '*')
                return false;
        }
        checkGewinner(spielfeld);
        System.exit(0);
        return true;
    }
    @Override
    public boolean hinfugen(Spielfeld spielfeld, int x, int y,Spieler spieler) {
            int ro;
            int co;
        for (ro = spielfeld.getX()-x; ro <spielfeld.getX(); ro++){
            for (co=spielfeld.getY()-y; co < spielfeld.getY(); co++){

               if (spielfeld.getFeld()[ro][co]=='*')
                   spielfeld.getFeld()[ro][co] = ' ';
               else break;
            }
        }
        return true;
    }
    @Override
    public void entfernen(Spielfeld spielfeld, int x, int y, Spieler cuSpieler) {
        for (int ro = spielfeld.getX()-x; ro <x; ro++){
            for (int co=spielfeld.getY()-y; co < y; co++){
                if (spielfeld.getFeld()[ro][co]==' ')
                    spielfeld.getFeld()[ro][co] = '*';
            }
        }
    }

    private  void spielerWechseln() {
        if (cuSpieler == spieler1) cuSpieler = spieler2;
        else { cuSpieler = spieler1; }

    }
    private  boolean checkGewinner(Spielfeld spielfeld){
        if (spielfeld.getFeld()[0][0] == ' ')
        {
            System.out.println(cuSpieler.getName() + " hat verloren :-( !");
            return true;
        }return false;
    }


}
