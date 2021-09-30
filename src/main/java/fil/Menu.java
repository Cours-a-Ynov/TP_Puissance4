package Puissance4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

public class Menu {


int sousmenu = 0;
        boolean arret = false;
        while (!arret){
	System.out.println("--Mon menu--");
	System.out.println("Sous menu 1 : tapez 1");
	System.out.println("Sous menu 2 : tapez 2");
	System.out.println("Sous menu 3 : tapez 3");
        System.out.println("quitter : tapez 9");
	sousmenu = Lire.i();
	switch(sousmenu)
	{
		case 1 :{
					int choix = 0;
					System.out.println("---Sous menu 1---");
					System.out.println("Sous menu 1-1 : tapez 1");
					System.out.println("Sous menu 1-2 : tapez 2");
                                        System.out.println("quitter : tapez 9");
					choix = Lire.i();
					switch(choix){
						case 1 : System.out.println("Sous menu 1-1"); break;
						case 2 : System.out.println("Sous menu 1-2"); break;
                                                case 9 : arret = true; break;
                                                default : System.out.println("entrez un choix entre 1 et 2"); break;
					}
				} break;
		case 2 : System.out.println("Sous menu 2"); break;
		case 3 : System.out.println("Sous menu 2"); break;
                case 9 : arret = true; break;
		default : System.out.println("entrez un choix entre 1 et 3"); break;
	}
                }