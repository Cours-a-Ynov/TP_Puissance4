package fil;

public class Menu {
    public static void main(String[] args) {
        int a=0;
        int r;
        System.out.println("Nouvelle Partie !");
        System.out.println("Continuer");
        System.out.println("n");
        System.out.println("Quittez");
        System.out.println("entrer le numero correspondant à votre choix");
        a=Clavier.lireInt();
        r=menu(a);
    }
    static int menu(int x) {
        switch(x){
        case 1: System.out.println("vous avez selectionné‚ le choix "+x);break;
        case 2: System.out.println("vous avez selectionné‚ le choix "+x);break;
        case 3: System.out.println("vous avez selectionné‚ le choix "+x);break;
        case 4: System.out.println("vous avez selectionné‚ le choix "+x);break;
        default:{ System.out.println("vous n'avez selectionné‚ aucun choix ");}
        }

        return x;
    }
}