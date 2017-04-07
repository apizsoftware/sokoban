package frontend;

import backend.PuzzleGenerator;
import backend.SPattern;
import java.io.IOException;

/*
 * SpeedyRoadie est le nom que l'on a donné à notre Sokoban
 * Je vous souhaite un bon jeu!
 */

/**
 * Classe principale du jeu. 
 * C'est ici que la partie interface graphique et la partie algorithmique vont s'assembler pour donner un programme fonctionnel
 * @author Louis Dhanis
 */
public class Main {

    /**
     * @param args the command line arguments
     */
        public static void printTab(char[][] b){
        for(int i=0;i<b.length;i++){
            for(int j=0;j<b[0].length;j++){
                System.out.print(b[i][j]);
            }
            System.out.println("");
        }        
    }
    
    public static void main(String[] args) throws IOException {
       // Gui test = new Gui();
       
        char[][] newmap= PuzzleGenerator.generateEmptyRoom(3,4,3);
        System.out.println("-----------------");
        printTab(newmap);

    }   
    

}
