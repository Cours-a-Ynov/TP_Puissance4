package fil.Fichier;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.When;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Main extends Application {
	
	private static int precedent;
	private static int nbTour;
	
	private Application[][] application;
	
	public void start(Stage primaryStage) {
		try {
			//Le plateau vertical de jeu:
			//Alignement requis :
			int N = 4;
			//Colonnes et lignes :
			int C = 7;
			int L = 6;

			//Couleur de fond:
			Color couleurFond = Color.BLACK;
			
			Group root = new Group();
			Scene scene = new Scene(root,700,600 + 100);
			scene.setFill(couleurFond);
			
			//Utile layouts:
			When w = Bindings.when((scene.widthProperty().divide(scene.heightProperty())).greaterThan(7.0/6.0));
			
			//Grille:
			Rectangle r = new Rectangle(0, 0, 700, 600);
			LinearGradient lg = new LinearGradient(0,0,1,1, true, CycleMethod.NO_CYCLE, new Stop(0, Color.BLUE), new Stop(0.5, Color.rgb(0, 0, 100)), new Stop(1, Color.BLUE));
			r.setFill(lg);
			root.getChildren().addAll(r);
			
			r.heightProperty().bind(w.then(scene.heightProperty().subtract(100)).otherwise(r.widthProperty().multiply(6.0/7.0)));
			r.widthProperty().bind(w.then(r.heightProperty().multiply(7.0/6.0)).otherwise(scene.widthProperty()));
			
			for(int i = 0 ; i < L ; i++){
				for (int j = 0 ; j < C ; j++){
					Circle c = new Circle(5 +45 + 100*j, 5+45 + 100*i, 45);
					c.setFill(couleurFond);
					c.radiusProperty().bind(r.heightProperty().divide(12).subtract(5));
					c.centerXProperty().bind(r.widthProperty().divide(7).multiply(j+0.5));
					c.centerYProperty().bind(r.heightProperty().divide(6).multiply(i+0.5));
					root.getChildren().add(c);
				}
			}
			
			//Création Application:
			application = new Application[7][6];
			
			for(int i = 0 ; i < L ; i++){
				for (int j = 0 ; j < C ; j++){
					application[j][i] = new Application();
					application[j][i].layoutXProperty().bind(r.widthProperty().divide(7).multiply(j));
					application[j][i].layoutYProperty().bind(r.heightProperty().divide(6).multiply(i));
					application[j][i].fitHeightProperty().bind(r.heightProperty().divide(6));
					application[j][i].fitWidthProperty().bind(r.widthProperty().divide(7));
					
					
					root.getChildren().add(application[j][i]);
				}
			}
			
			//Textes:
			Label tour = new Label("Tour 1");
			tour.setTextFill(Color.DARKGREEN);
			tour.setFont(Font.font("Comic Sans MS", scene.getHeight() / 20));
			tour.setLayoutX(100);
			tour.layoutYProperty().bind(r.heightProperty());
			
			
			Label joueur = new Label("A BMW de jouer");
			joueur.setTextFill(Color.DARKGREEN);
			joueur.setFont(Font.font("Comic Sans MS", scene.getHeight() / 20));
			joueur.setLayoutX(300);
			joueur.layoutYProperty().bind(r.heightProperty());
			
			Label victoire = new Label("");
			victoire.setTextFill(Color.MAGENTA);
			victoire.setFont(Font.font("Comic Sans MS", scene.getHeight() / 20));
			victoire.setLayoutX(300);
			victoire.layoutYProperty().bind(r.heightProperty());
			victoire.setVisible(false);
			
			scene.heightProperty().addListener(e->{
				tour.setFont(Font.font("Comic Sans MS", scene.getHeight() / 20));
				joueur.setFont(Font.font("Comic Sans MS", scene.getHeight() / 20));
				victoire.setFont(Font.font("Comic Sans MS", scene.getHeight() / 20));
			});
			
			root.getChildren().addAll(tour, joueur, victoire);
			
			//Cadres de sélections:
			Rectangle[] rects = new Rectangle[C];
			for(int i = 0 ; i < C ; i++){
				rects[i] = new Rectangle(0, 0, 10, 10);
				rects[i].layoutXProperty().bind(r.widthProperty().divide(C).multiply(i));
				rects[i].heightProperty().bind(r.heightProperty());
				rects[i].widthProperty().bind(r.widthProperty().divide(C));
				rects[i].setFill(Color.TRANSPARENT);
				rects[i].setStroke(Color.GREENYELLOW);
				rects[i].setStrokeType(StrokeType.INSIDE);
				rects[i].setStrokeWidth(12);
				rects[i].setVisible(false);
				
				root.getChildren().addAll(rects[i]);
			}
			
			//Selections:
			Rectangle r2 = new Rectangle(0,0,10,10);
			r2.heightProperty().bind(r.heightProperty());
			r2.widthProperty().bind(r.widthProperty());
			r2.setFill(Color.TRANSPARENT);
			root.getChildren().addAll(r2);
			
			precedent = -1;
			r2.setOnMouseMoved(e -> {
				
					int val = (int)(e.getX() / (r.getWidth() / C));
					if(val != precedent){
						rects[val].setVisible(true);
						if(precedent > -1)
							rects[precedent].setVisible(false);
					}
					precedent= val;
				
			});
			
			nbTour = 1;
			
			//Clique:
			r2.setOnMouseClicked(e -> {
				
				
				int colonne = (int)(e.getX() / (r.getWidth() / C));
				
				//Placement du jeton:
				if(application[colonne][0].getStatut() == 0 && !victoire.isVisible()){
					
					int rang = L-1;
					while(application[colonne][rang].getStatut() != 0){
						rang--;
					}
					application[colonne][rang].set(nbTour%2==1 ? 1 : 2);
					
					
					//Condiiton de victoire:
					//Couleur en cours:
					int couleur = (nbTour%2==1 ? 1 : 2);
					//nombre alignés maximal: 
					int max = 0;
					int x; int y;
					int somme;
					
					//Diagonale HG-BD
					x = colonne; y = rang; somme=-1;
					while(y >= 0 && x >= 0 && application[x][y].getStatut() == couleur){ y--; x--; somme++;}
					x = colonne; y = rang;
					while(y < L && x < C && application[x][y].getStatut() == couleur){ y++; x++; somme++;}
					if(somme > max) max= somme;
					
					//Diagonale HD-BG
					x = colonne; y = rang; somme=-1;
					while(y >= 0 && x < C && application[x][y].getStatut() == couleur){ y--; x++; somme++;}
					x = colonne; y = rang;
					while(y < L && x >= 0 && application[x][y].getStatut() == couleur){ y++; x--; somme++;}
					if(somme > max) max= somme;
					
					//Verticale:
					x = colonne; y = rang; somme=-1;
					while(y >= 0 && application[x][y].getStatut() == couleur){ y--; somme++;}
					y = rang;
					while(y < L && application[x][y].getStatut() == couleur){ y++; somme++;}
					if(somme > max) max= somme;
					
					//Horizontale:
					x = colonne; y = rang; somme=-1;
					while(x >= 0 && application[x][y].getStatut() == couleur){ x--; somme++;}
					x = colonne;
					while(x < C && application[x][y].getStatut() == couleur){ x++; somme++;}
					if(somme > max) max= somme;
					
					
					if(max >= N){
						joueur.setVisible(false);
						victoire.setVisible(true);
						victoire.setTextFill(couleur == 1 ? Color.RED : Color.YELLOW);
						victoire.setText("VICTOIRE DE " + (couleur == 1 ? "BMW" : "Merco"));
						nbTour--;
					}
					
					nbTour++;
					
					if(nbTour > C*L && max < N){
						joueur.setVisible(false);
						victoire.setVisible(true);
						victoire.setText("EGALITE !");
						nbTour--;
					}
					tour.setText("Tour " + nbTour);
					joueur.setText("A " + (nbTour%2 == 1 ? "BMW" : "Merco") + " de jouer");
					
				}
			});
			primaryStage.setTitle("Puissance 4 by ROMAINPC");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
}