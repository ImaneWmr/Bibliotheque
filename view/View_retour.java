package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;

import controller.mainMVC;
import model.LIVRE;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class View_retour {

	private JFrame frame;
	private JTextField textField_ISBN;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public View_retour() throws SQLException {
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
		
		JLabel lblNewLabel = new JLabel("Retourner son livre : ");
		lblNewLabel.setBounds(160, 34, 148, 14);
		frame.getContentPane().add(lblNewLabel);
		
		textField_ISBN = new JTextField();
		textField_ISBN.setBounds(160, 74, 96, 20);
		frame.getContentPane().add(textField_ISBN);
		textField_ISBN.setColumns(10);
		
		
		JLabel messageRetourConfirme = new JLabel("Retour effectué !");
		messageRetourConfirme.setForeground(Color.GREEN);
		messageRetourConfirme.setBounds(172, 187, 161, 14);
		frame.getContentPane().add(messageRetourConfirme);
		
		
		messageRetourConfirme.setVisible(false);
		
		JButton confirmation_retour = new JButton("confirmer");
		confirmation_retour.setBounds(160, 124, 89, 23);
		frame.getContentPane().add(confirmation_retour);
		
		JButton Retouraccueil = new JButton("Accueil");
		Retouraccueil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		Retouraccueil.setBounds(317, 212, 89, 23);
		frame.getContentPane().add(Retouraccueil);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Imane Ouamar\\Downloads\\library_image.jpeg"));
		lblNewLabel_1.setBounds(0, 0, 436, 263);
		frame.getContentPane().add(lblNewLabel_1);
		
		confirmation_retour.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		      
		    	String isbn = textField_ISBN.getText();
				System.out.println(" ISBN A RETOURNER " + isbn);
				LIVRE l = mainMVC.getM().findLivre(isbn);
				try {
					if (l.getEmprunteur()!=null) {
					mainMVC.getM().updateretourlivre(textField_ISBN.getText());
					System.out.println(" UPDATED RETOUR" );
					messageRetourConfirme.setVisible(true);
					mainMVC.getM().getAll();
					}else {
						messageRetourConfirme.setText("Mauvais ISBN, le livre est déjà retourné");
						messageRetourConfirme.setVisible(true);
						messageRetourConfirme.setForeground(Color.red);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					
				}		    }
		});
		
	}
}
