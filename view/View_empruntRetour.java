package view;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;

import controller.mainMVC;
import model.ADHERENT;
import model.LIVRE;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.List;
import javax.swing.JPanel;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.ImageIcon;

public class View_empruntRetour {

	private JFrame frame;
	private JTextField textField_num;
	private JTextField textField_isbn;
	private JTextField textField_ISBNretour;

	

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public View_empruntRetour() throws SQLException {
		mainMVC.getM().getAll();
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		frame = new JFrame();
		frame.setBounds(100, 100, 744, 405);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_num = new JLabel("numéro adherent");
		lblNewLabel_num.setForeground(Color.BLACK);
		lblNewLabel_num.setBounds(89, 8, 175, 14);
		frame.getContentPane().add(lblNewLabel_num);
		
		textField_num = new JTextField();
		textField_num.setBounds(21, 33, 243, 20);
		frame.getContentPane().add(textField_num);
		textField_num.setColumns(10);
		
		
		
		Panel panel_EMPRUN = new Panel();
		panel_EMPRUN.setBounds(369, 34, 336, 220);
		frame.getContentPane().add(panel_EMPRUN);
		panel_EMPRUN.setLayout(null);
		
		textField_isbn = new JTextField();
		textField_isbn.setBounds(139, 87, 154, 20);
		panel_EMPRUN.add(textField_isbn);
		textField_isbn.setColumns(10);
		
		
		JLabel lblNewLabel = new JLabel("Emprunter un livre ");
		lblNewLabel.setBounds(139, 46, 225, 14);
		panel_EMPRUN.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("entrer l'ISBN");
		lblNewLabel_1.setBounds(23, 90, 96, 14);
		panel_EMPRUN.add(lblNewLabel_1);
		
		
		
		JButton btnNewButton_emprunter = new JButton("confirmer");
		btnNewButton_emprunter.setBounds(146, 152, 147, 23);
		panel_EMPRUN.add(btnNewButton_emprunter);
		
		JLabel messageconfirmationemprunt = new JLabel("EMPRUNTE");
		messageconfirmationemprunt.setForeground(Color.GREEN);
		messageconfirmationemprunt.setBounds(162, 127, 131, 14);
		panel_EMPRUN.add(messageconfirmationemprunt);
		messageconfirmationemprunt.setVisible(false);
		
		panel_EMPRUN.setVisible(false);
		JButton btnNewButton_ok = new JButton("ok");
		btnNewButton_ok.setBounds(50, 65, 138, 23);
		frame.getContentPane().add(btnNewButton_ok);
		
		JButton btnNewButton_retouraccueil = new JButton("Accueil");
		btnNewButton_retouraccueil.setBounds(535, 310, 144, 23);
		frame.getContentPane().add(btnNewButton_retouraccueil);
		
		JLabel userfound = new JLabel("utilisateur innexistant");
		userfound.setForeground(Color.RED);
		userfound.setBounds(230, 69, 144, 14);
		frame.getContentPane().add(userfound);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\Imane Ouamar\\Downloads\\library_image.jpeg"));
		lblNewLabel_2.setBounds(0, 0, 730, 368);
		frame.getContentPane().add(lblNewLabel_2);
		userfound.setVisible(false);
		btnNewButton_ok.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	mainMVC.getM().findAdherent(null);
		    	String userInput = textField_num.getText();
				System.out.println(" test " + userInput);
				ADHERENT adherent = mainMVC.getM().findAdherent(userInput);
				LIVRE livre = mainMVC.getM().findLivre(userInput);
				
				if (adherent==null)
				{
					System.out.println("erreur Adherent inconnu");
					userfound.setVisible(true);
					
				}
				else {
				
		    	String isbn = textField_num.getText();
				System.out.println(" NUM  " + isbn);
		        
		        userfound.setVisible(false);
		        panel_EMPRUN.setVisible(true);
		        btnNewButton_ok.setEnabled(false);
		       
		}}});
		
		btnNewButton_retouraccueil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		
		btnNewButton_emprunter.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		      
		    	String isbn = textField_isbn.getText();
		    	LIVRE l = mainMVC.getM().findLivre(isbn);
				System.out.println(" ISBN  " + isbn);
				if (l==null) {
					messageconfirmationemprunt.setText("ISBN inconnu");
					messageconfirmationemprunt.setVisible(true);
					messageconfirmationemprunt.setForeground(Color.RED);
				}else {
					if(l.getEmprunteur()!=null) {
						messageconfirmationemprunt.setText("Erreur livre déja emprunté");
						messageconfirmationemprunt.setVisible(true);
						messageconfirmationemprunt.setForeground(Color.RED);
					}else {
						try {
							mainMVC.getM().updateempruntlivre(textField_num.getText(), textField_isbn.getText());
							System.out.println(" UPDATED " );
							messageconfirmationemprunt.setText("Livre emprunté avec succès");
							messageconfirmationemprunt.setVisible(true);
							messageconfirmationemprunt.setForeground(Color.green);
							messageconfirmationemprunt.setVisible(true);
							mainMVC.getM().getAll();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
						    }
		});

	}
}
