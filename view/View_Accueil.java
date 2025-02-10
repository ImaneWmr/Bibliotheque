package view;

import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.mainMVC;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Label;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import java.awt.TextArea;
import java.awt.TextField;
import javax.swing.JTextField;

public class View_Accueil {

	private JFrame frame;


	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public View_Accueil() throws SQLException {
		mainMVC.getM().getAll();
		initialize();
		frame.setVisible(true);
	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Imane Ouamar\\Downloads\\library_image.jpeg"));
		frame.setBounds(100, 100, 521, 326);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton_catalogue = new JButton("Catalogue");
		btnNewButton_catalogue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					View_catalogue va = new View_catalogue();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			
		});
		btnNewButton_catalogue.setBounds(91, 57, 116, 80);
		frame.getContentPane().add(btnNewButton_catalogue);
		
		JButton btnNewButtonEmpruntsRetours = new JButton("emprunts");
		btnNewButtonEmpruntsRetours.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					View_empruntRetour va = new View_empruntRetour();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButtonEmpruntsRetours.setBounds(91, 185, 118, 80);
		frame.getContentPane().add(btnNewButtonEmpruntsRetours);
		
		JButton btnNewButton_1profil = new JButton("Profil");
		btnNewButton_1profil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					View_profil va = new View_profil();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1profil.setBounds(286, 57, 116, 80);
		frame.getContentPane().add(btnNewButton_1profil);
		
		JButton btnNewButton = new JButton("Retours");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					View_retour va = new View_retour();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(288, 185, 114, 80);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Imane Ouamar\\Downloads\\library_image.jpeg"));
		lblNewLabel.setBounds(0, 0, 507, 289);
		frame.getContentPane().add(lblNewLabel);
	}
}
