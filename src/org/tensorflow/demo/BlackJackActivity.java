package org.tensorflow.demo;

import java.util.ArrayList;
import java.util.List;

public class BlackJackActivity {
    static List<Integer> valeursDetectees = new ArrayList<>();
    static List<Integer> getValeursDetectees() {
        return valeursDetectees;
    }

    public void setValeursDetectees(List<Integer> valeursDetectees) {
        this.valeursDetectees = valeursDetectees;
    }
    static int carteDealer = valeursDetectees.get(0);
    public static int sommeMain(List<Integer> valeursDetectees){
        int Main = 0;
        for (int i=1; i<valeursDetectees.size(); i++){
            Main+=valeursDetectees.get(i);
        }
        return Main;
    }
    public static String ChoixConseil( int S, int C){
        String choix;
        switch(S) {

            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:

                choix="T";

                break;

            case 9:

                if(C==2 || C>6){
                    choix="T";
                }
                else{
                    choix="D";
                }

                break;

            case 10:

                if(C>9){
                    choix="T";
                }
                else{
                    choix="D";
                }

                break;

            case 11:

                choix="D";

                break;

            case 12:

                if(C<4 || C>6){
                    choix="T";
                }
                else{
                    choix="S";
                }

                break;

            case 13:
            case 14:
            case 15:
            case 16:

                if(C>6){
                    choix="T";
                }
                else{
                    choix="S";
                }

                break;

            case 17:
            case 18:
            case 19:
            case 20:

                choix="S";

                break;

            default:

                System.out.println("Erreur : La somme de la main du joueur vaut 0 ou plus de 20 ou bien la carte du croupier n'est pas valide.");
                return "ERROR";

        }
        return choix;
    }


}
