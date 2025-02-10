package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


import javax.swing.JFrame;

import controller.mainMVC;
import model.ADHERENT;
import model.AUTEUR;
import model.LIVRE;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import java.awt.List;
import java.awt.Color;
import javax.swing.ImageIcon;

public class View_profil {

	private JFrame frame;
	private JTextField textField_num;
	private JTextField textField_nom;
	private JTextField textField_prenom;
	private JTextField textField_email;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public View_profil() throws SQLException {
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
		
		

		JLabel lblNewLabel = new JLabel("Profil :");
		lblNewLabel.setBounds(63, 11, 49, 35);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_num = new JLabel("num");
		lblNewLabel_num.setBounds(30, 44, 49, 14);
		frame.getContentPane().add(lblNewLabel_num);

		textField_num = new JTextField();
		textField_num.setBounds(91, 41, 96, 20);
		frame.getContentPane().add(textField_num);
		textField_num.setColumns(10);


		JButton btnNewButton_ok = new JButton("ok");
		btnNewButton_ok.setBounds(201, 40, 55, 23);
		frame.getContentPane().add(btnNewButton_ok);
		String userInput;

		JPanel panel_profil = new JPanel();
		panel_profil.setBounds(10, 69, 406, 149);
		frame.getContentPane().add(panel_profil);
		panel_profil.setLayout(null);

		JLabel lblNewLabel_2_panelgeneral = new JLabel("infos perso :");
		lblNewLabel_2_panelgeneral.setBounds(30, 5, 79, 14);
		panel_profil.add(lblNewLabel_2_panelgeneral);
		
		JLabel lblNewLabel_2_nom = new JLabel("nom");
		lblNewLabel_2_nom.setBounds(10, 30, 49, 14);
		panel_profil.add(lblNewLabel_2_nom);
		
		JLabel lblNewLabel_2_prenom = new JLabel("prenom");
		lblNewLabel_2_prenom.setBounds(10, 61, 49, 14);
		panel_profil.add(lblNewLabel_2_prenom);
		
		JLabel lblNewLabel_2_email = new JLabel("email");
		lblNewLabel_2_email.setBounds(10, 86, 49, 14);
		panel_profil.add(lblNewLabel_2_email);
		JLabel lblNewLabel_1 = new JLabel("liste de livres empruntés :");
		lblNewLabel_1.setBounds(229, 5, 157, 14);
		panel_profil.add(lblNewLabel_1);
		
		textField_nom = new JTextField();
		textField_nom.setBounds(40, 30, 96, 20);
		panel_profil.add(textField_nom);
		textField_nom.setColumns(10);
		
		textField_prenom = new JTextField();
		textField_prenom.setBounds(50, 55, 96, 20);
		panel_profil.add(textField_prenom);
		textField_prenom.setColumns(10);
		
		textField_email = new JTextField();
		textField_email.setBounds(60, 83, 96, 20);
		panel_profil.add(textField_email);
		textField_email.setColumns(10);
		
		List list = new List();
		list.setBounds(214, 30, 172, 77);
		panel_profil.add(list);
		
		JButton btnNewButton_modifier = new JButton("Mettre à jour");
		btnNewButton_modifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					mainMVC.getM().updateadherent(textField_num.getText(), textField_nom.getText(), textField_prenom.getText(), textField_email.getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnNewButton_modifier.setBounds(55, 112, 128, 23);
		panel_profil.add(btnNewButton_modifier);
		
		JLabel lblUtilisateurTrouv = new JLabel("");
		lblUtilisateurTrouv.setForeground(Color.GREEN);
		lblUtilisateurTrouv.setBounds(262, 73, 174, 14);
		frame.getContentPane().add(lblUtilisateurTrouv);
		
		JButton boutonRetour = new JButton("Accueil");
		boutonRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		boutonRetour.setBounds(312, 229, 89, 23);
		frame.getContentPane().add(boutonRetour);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\Imane Ouamar\\Downloads\\library_image.jpeg"));
		lblNewLabel_2.setBounds(0, 0, 436, 263);
		frame.getContentPane().add(lblNewLabel_2);
		
		
		
				
		panel_profil.setVisible(false);
		btnNewButton_ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userInput = textField_num.getText();
				System.out.println(" test " + userInput);
				ADHERENT adherent = mainMVC.getM().findAdherent(userInput);
				LIVRE livre = mainMVC.getM().findLivre(userInput);
				
				if (adherent==null)
				{
					System.out.println("erreur");
					lblUtilisateurTrouv.setText("utilisateur pas trouvé");
					lblUtilisateurTrouv.setForeground(Color.red);
				}
				else
				{
					
					textField_nom.setText(adherent.getNom());
					textField_prenom.setText(adherent.getPrenom());
					textField_email.setText(adherent.getEmail());
		            
		            for (int i=0; i!=adherent.getListLivre().size(); i++) {
		    			list.add(adherent.getListLivre().get(i).ligne());
		    		}
					panel_profil.setVisible(true);
					
					
				}
			}


		});





	}
}
