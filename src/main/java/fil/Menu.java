package Puissance4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

public class Menu {

	public Menu(final JFrame fenetre) {
		
		JMenuBar barreDeMenu = new JMenuBar();

		JMenu menu = new JMenu("Menu");

		JMenuItem menuItemAPropos = new JMenuItem("A propos");

		menuItemAPropos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame fenAPropos = new JFrame();
				fenAPropos.setTitle("A propos");
				fenAPropos.setSize(400, 80);
				fenAPropos.setVisible(true);
				JTextArea label = new JTextArea(
						"Bienvenue sur notre puissance 4");
				fenAPropos.add(label);

			}
		});
		
		JMenuItem menuItemFermer = new JMenuItem("Fermer");
		menuItemFermer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fenetre.dispose();
			}
		});

		menu.add(menuItemFermer);

		barreDeMenu.add(menu);

		fenetre.setJMenuBar(barreDeMenu);

		menu.add(menuItemAPropos);

	}

}