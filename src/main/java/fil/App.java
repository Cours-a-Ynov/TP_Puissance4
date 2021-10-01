
package fil;

import java.util.Scanner;
/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);
        
      //  System.out.println( "Hello World!" );
      //gagne lorsque 4 couleur sont alignés
        int N = 4;
      // creation de la grille 8 par 6
        int C = 8;
        int L = 6;
        char[][] plateau = new char[C][L];

        for (int x = 0; x < C; x++)
            for (int y = 0; y < L; y++)
                plateau[x][y] = '.';

        int gagnant = 0;

        //  un for pour arrêter le jeu pour victoir de J1 ou J2
        for(int i = 1 ; i <= C*L ; i++) {
            // afficher la grille :
            System.out.println("Tour " + i + ", affichage du plateau :");

            for (int loop = 0; loop < C + 2 + 2 * C; loop++)
                System.out.print('#');
            System.out.println();

            for (int y = 0; y < L; y++) {
                System.out.print('#');
                for (int x = 0; x < C; x++) {
                    System.out.print(" " + plateau[x][y] + " ");
                }
                System.out.print('#');
                System.out.println();
            }

            for (int loop = 0; loop < C + 2 + 2 * C; loop++)
                System.out.print('#');
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
            int rang = L - 1;
            // placement des pion X & O:   
            while (plateau[colonne - 1][rang] != '.') {
                rang--;
            }
            plateau[colonne - 1][rang] = (i % 2 == 1 ? 'X' : 'O');

            // Détection de victoire:
            			
			//symbole en cours:
			char symbole = (i%2==1 ? 'X' : 'O');

			//nombre alignés maximal:
			int max = 0;
			int x; int y;
            int somme;


            // --> diagonale HG-BD
            x = colonne - 1;
            y = rang;
            somme = -1;
            while (y >= 0 && x >= 0 && plateau[x][y] == symbole) {
                y--;
                x--;
                somme++;
            }
            x = colonne - 1;
            y = rang;
            while (y < L && x < C && plateau[x][y] == symbole) {
                y++;
                x++;
                somme++;
            }
            if (somme > max)
                max = somme;

            // --> diagonale HD-BG
            x = colonne - 1;
            y = rang;
            somme = -1;
            while (y >= 0 && x < C && plateau[x][y] == symbole) {
                y--;
                x++;
                somme++;
            }
            x = colonne - 1;
            y = rang;
            while (y < L && x >= 0 && plateau[x][y] == symbole) {
                y++;
                x--;
                somme++;
            }
            if (somme > max)
                max = somme;

            // --> verticale:
            x = colonne - 1;
            y = rang;
            somme = -1;
            while (y >= 0 && plateau[x][y] == symbole) {
                y--;
                somme++;
            }
            y = rang;
            while (y < L && plateau[x][y] == symbole) {
                y++;
                somme++;
            }
            if (somme > max)
                max = somme;

            // --> horizontale:
            x = colonne - 1;
            y = rang;
            somme = -1;
            while (x >= 0 && plateau[x][y] == symbole) {
                x--;
                somme++;
            }
            x = colonne - 1;
            while (x < C && plateau[x][y] == symbole) {
                x++;
                somme++;
            }
            if (somme > max)
                max = somme;

            if (max >= N) {
                gagnant = (i % 2 == 1 ? 1 : 2);
                i = C * L + 1;
            }

            System.out.println("*************************");


            
        }

        // affichage des résultats:
        // si gagnant == 0 c'est que tout le plateau s'est remplis sans gagnant, il y a
        // donc égalité J1=J2
        System.out.println();

        System.out.println("_____________");

        System.out.println("****FIN DE PARTIE****");
        
        System.out.println("*********************");
        if (gagnant == 0)
            System.out.println("*******EGALITE*******");
        if (gagnant == 1)
            System.out.println("****VICTOIRE DE Joueur X****");
        if (gagnant == 2)
            System.out.println("****VICTOIRE DE Joueur O****");
        System.out.println("_____________");

        for (int loop = 0; loop < C + 2 + 2 * C; loop++)
            System.out.print('#');
        System.out.println();

        for (int y = 0; y < L; y++) {
            System.out.print('#');
            for (int x = 0; x < C; x++) {
                System.out.print(" " + plateau[x][y] + " ");
            }
            System.out.print('#');
            System.out.println();
        }

        for (int loop = 0; loop < C + 2 + 2 * C; loop++)
            System.out.print('#');
        System.out.println();   
	}

}


        
    
    


    

