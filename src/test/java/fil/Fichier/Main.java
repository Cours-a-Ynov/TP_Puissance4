package fil.Fichier;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Applications extends ImageView{
	
	private static Image BMW = new Image("BMW.jpg");
	private static Image Merco = new Image("Merco.jpg");
	
	
	//vide = 0 ; BMW = 1 ; Merco = 2;
	private int statut;
	
	public Case(){
		this.statut = 0;
	}
	
	public void set(int j){
		this.setImage(j == 1 ? BMW : Merco);
		this.statut = j;
	}
	
	
	public int getStatut(){
		return statut;
	}
	
}