package AUf1;

import java.util.Scanner;

class Main {


    public static void main(String[] args) {
        System.out.println("Willcomen:-) welche Spiel?");
        System.out.println("1.Vier-Gewinnt oder 2.Chomp");

        Scanner x = new Scanner(System.in);
        int sp = x.nextInt();

        if (sp == 1) { VierGwint sp_1 = new VierGwint();
            sp_1.durchgang();
        } else { Chomp sp_2 = new Chomp();
            sp_2.durchgang();
        }



    }
}
