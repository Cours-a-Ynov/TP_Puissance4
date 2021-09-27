
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

            for (int y = 0; y < L; y++) {
                System.out.print('|');
                for (int x = 0; x < C; x++) {
                    System.out.print(" " + plateau[x][y] + " ");
                }
                System.out.print('|');
                System.out.println();
            }

            for (int loop = 0; loop < C + 2 + 2 * C; loop++)
                System.out.print('-');
            System.out.println();


            // partie des pions X et O
            System.out.println("Tour du joueur " + (i % 2 == 1 ? 'X' : 'O'));
            System.out.println("Entrez le numero de la colonne entre 1 et " + C + " ...");
            boolean placement = false;
            int colonne = -1;
            while (!placement) {
                colonne = -1;
                String ligne = scanner.nextLine();
                // vérification que la ligne est un entier entre 1 et C:
                try {
                    colonne = Integer.valueOf(ligne);

                    if (colonne >= 1 && colonne <= C) {
                        if (plateau[colonne - 1][0] != '.') {
                            System.out.println("Colonne pleine, choisir autre");
                        } else {
                            placement = true;
                        }
                    } else {
                        System.out.println("Nombre incorrect, choisir autre");
                    }

                } catch (Exception e) {
                    System.out.println("Nombre incorrect, choisir autre");
                }

            } 
            // placement des pion X & O:   


          //  System.out.println("Tour du joueur " + (i % 2 == 1 ? 'X' : 'O'));

        }
    
    }
}
