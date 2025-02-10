package view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import controller.mainMVC;
import model.LIVRE;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;

public class View_catalogue {

	private JFrame frame;
	private JTable table;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application. là on enlève une partie car jsp pk
	 */
	
	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public View_catalogue() throws SQLException {
		mainMVC.getM().getAll();
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton accueil_revenirenarriere = new JButton("Accueil");
		accueil_revenirenarriere.setBounds(313, 214, 89, 23);
		frame.getContentPane().add(accueil_revenirenarriere);
		
		
		// Définir les colonnes du tableau
		String[] columnNames = {"Titre"}; // Remplace par tes colonnes réelles
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);
		table = new JTable(model);
		table.setBounds(44, 46, 350, 151);
		frame.getContentPane().add(table);
		
		lblNewLabel = new JLabel("CATALOGUE DES LIVRES");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(133, 21, 167, 14);
		frame.getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Imane Ouamar\\Downloads\\library_image.jpeg"));
		lblNewLabel_1.setBounds(0, 0, 436, 263);
		frame.getContentPane().add(lblNewLabel_1);

		// Récupérer la liste des livres
		ArrayList<LIVRE> livres = mainMVC.getM().getListLivre();

		if (livres == null) {
		    System.out.println("Erreur : la liste de livres est null !");
		} else if (livres.isEmpty()) {
		    System.out.println("Erreur : la liste de livres est vide !");
		} else {
		    for (LIVRE livre : livres) {
		        if (livre != null) {
		            model.addRow(new Object[]{livre.ligne()});
		        } else {
		            System.out.println("Erreur ");
		           
		        }
		    }
		}

		// Forcer la mise à jour de l'affichage
		table.revalidate();
		table.repaint();

		
		    System.out.println("test");
		    accueil_revenirenarriere.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.setVisible(false);
				}
			});
	}
}
