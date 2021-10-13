package fil.Fichier;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class App extends ImageView{
	
	private static Image Rouge = new Image("Rouge.png");
	private static Image Jaune = new Image("Jaune.png");
	
	
	//vide = 0 ; Rouge = 1 ; Jaune = 2;
	private int statut;
	
	public App(){
		this.statut = 0;
	}
	
	public void set(int j){
		this.setImage(j == 1 ? Rouge : Jaune);
		this.statut = j;
	}
	
	
	public int getStatut(){
		return statut;
	}
	
}