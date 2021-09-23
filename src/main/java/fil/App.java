
package fil;

import java.util.Scanner;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);
        
      //  System.out.println( "Hello World!" );
      //gagne lorsque 4 couleur sont alignés
        int N = 4;
        // creation de la grille 8 sur 6
        int C = 8;
        int L = 6;
        char[][] plateau = new char[C][L];

        for (int x = 0; x < C; x++)
            for (int y = 0; y < L; y++)
                plateau[x][y] = '.';

        int gagnant = 0;

        // faire un for pour arrêter le jeu pour victoir de J1 ou J2
        for(int i = 1 ; i <= C*L ; i++) {
            // afficher la grille :
            System.out.println("Tour " + i + ", affichage du plateau :");

            for (int loop = 0; loop < C + 2 + 2 * C; loop++)
                System.out.print('-');
            System.out.println();
        }
    
    }
}
