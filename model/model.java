package model;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

public class model {
	private ArrayList<LIVRE> ListLivre;
	private ArrayList<AUTEUR> ListAuteur;
	private ArrayList<ADHERENT> ListAdherent;
	private Connection conn;
	public ArrayList<LIVRE> getListLivre() {
		return ListLivre;
	}
	public void setListLivre(ArrayList<LIVRE> listLivre) {
		ListLivre = listLivre;
	}
	public ArrayList<AUTEUR> getListAuteur() {
		return ListAuteur;
	}
	public void setListAuteur(ArrayList<AUTEUR> listAuteur) {
		ListAuteur = listAuteur;
	}
	public ArrayList<ADHERENT> getListAdherent() {
		return ListAdherent;
	}
	public void setListAdherent(ArrayList<ADHERENT> listAdherent) {
		ListAdherent = listAdherent;
	}

	public model() throws ClassNotFoundException, SQLException 
	{
		ListLivre = new ArrayList<LIVRE>();
		ListAuteur= new ArrayList<AUTEUR>();
		ListAdherent = new ArrayList<ADHERENT>();
		String BDD = "ap4_imane";
		String url = "jdbc:mysql://localhost:3306/"+BDD;
		String user = "root";
		String passwd="";
		Class.forName("com.mysql.jdbc.Driver");

		conn =  (Connection) DriverManager.getConnection(url,user,passwd);
		System.out.println("connexion OK");
	}

	public void getAll() throws SQLException
	{
		ListAdherent.clear();
		ListAuteur.clear();
		ListLivre.clear();
		ResultSet resultats;
		String requete;
		Statement stmt = conn.createStatement();

		//****************************************
		//Création des AUTEURS
		//****************************************
		requete = "select * from ap2_biblio_auteur";
		resultats = stmt.executeQuery(requete);
		while(resultats.next())
		{
			AUTEUR a = new AUTEUR(
					resultats.getString("num"), 
					resultats.getString("nom"), 
					resultats.getString("prenom"), 
					resultats.getString("date_naissance"), 
					resultats.getString("description"));
			ListAuteur.add(a);

		}

		//****************************************
		//Création des LIVRES
		//****************************************
		requete = "select * from ap2_biblio_livre";
		resultats = stmt.executeQuery(requete);
		while(resultats.next())
		{
			//(String iSBN, String titre, int prix, AUTEUR auteur, ADHERENT emprunteur)
			LIVRE l = new LIVRE(
					resultats.getString("ISBN"), 
					resultats.getString("titre"), 
					resultats.getInt("prix"), 
					null, 
					null);
			ListLivre.add(l); // je créee un livre et je l'ajoute à la liste

		}
		
		//****************************************
		//Création des ADHERENTS
		//****************************************
		requete = "select * from ap2_biblio_adherent";
		resultats = stmt.executeQuery(requete);
		while(resultats.next())
		{
			//String num, String nom, String prenom, String email, ArrayList<LIVRE> listLivre) {
			ADHERENT a = new ADHERENT(
					resultats.getString("num"), 
					resultats.getString("nom"), 
					resultats.getString("prenom"), 
					resultats.getString("email"), 
					new ArrayList<LIVRE>());
			ListAdherent.add(a); // je crée un adherent et je l'ajoute à la liste adherent

		}
		
		
	
		//****************************************
		//lien des LIVRES
		//****************************************
		requete = "select * from ap2_biblio_livre";
		resultats = stmt.executeQuery(requete);
		while(resultats.next())
		{
			if (resultats.getString("auteur") != null)
			{
				AUTEUR a = findAuteur(resultats.getString("auteur"));
				LIVRE l = findLivre(resultats.getString("ISBN"));
				l.setAuteur(a);
			}
			if(resultats.getString("adherent") != null)
			{
				LIVRE l = findLivre(resultats.getString("ISBN"));
				ADHERENT ad = findAdherent(resultats.getString("adherent"));
				l.setEmprunteur(ad);
				ad.getListLivre().add(l);
			}

		}


	}
	public LIVRE findLivre(String num) {
		// TODO Auto-generated method stub
		for (int i=0 ; i<ListLivre.size();i++)
		{
			if(ListLivre.get(i).getISBN().equals(num))
			{
				return ListLivre.get(i);
			}
		}
		return null;
	}
	private AUTEUR findAuteur(String num) {
		// TODO Auto-generated method stub
		for (int i=0 ; i<ListAuteur.size();i++)
		{
			if(ListAuteur.get(i).getNum().equals(num))
			{
				return ListAuteur.get(i);
			}
		}
		return null;
	}
	
	public void updateadherent(String num, String nom, String prenom, String email) throws SQLException {
	    String $sql = "UPDATE `ap2_biblio_adherent` SET `nom` = '" + nom + "', `prenom` = '" + prenom + "', " +
	                 "`email` = '" + email + "' WHERE `ap2_biblio_adherent`.`num` = '" + num + "';";
	    
	    Statement stmt = conn.createStatement();
	    int maj = stmt.executeUpdate($sql);
	}
	
	public void updateempruntlivre(String adherent, String ISBN) throws SQLException {
		String $sql = "UPDATE ap2_biblio_livre SET adherent = '"+ adherent+"' WHERE ISBN = '"+ISBN+"'";
		System.out.println($sql);
	    Statement stmt = conn.createStatement();
	    int maj = stmt.executeUpdate($sql);
	}
	public void updateretourlivre(String ISBN) throws SQLException {
		String $sql = "UPDATE ap2_biblio_livre SET adherent = NULL WHERE ISBN = '" + ISBN + "';";

		System.out.println($sql);
	    Statement stmt = conn.createStatement();
	    int maj = stmt.executeUpdate($sql);
	}
	
	
	
	
	public ArrayList<LIVRE> getliste_emprunte() throws SQLException {
		String requete;
		Statement stmt = conn.createStatement();
		
		requete = "select * from ap2_biblio_livre where adherent IS NULL";
		ResultSet resultats = stmt.executeQuery(requete);
		while(resultats.next())
		{
			LIVRE a = new LIVRE(
					resultats.getString("ISBN"), 
					resultats.getString("titre"), 
					resultats.getInt("prix"), 
					null, 
					null
					);
			ListLivre.add(a);

		}
		return ListLivre;
	}

	
	
	public ADHERENT findAdherent(String num) {
		// TODO Auto-generated method stub
		
		for (int i=0 ; i<ListAdherent.size();i++)
		{
		
			if(ListAdherent.get(i).getNum().equals(num))
			{
				;
				return ListAdherent.get(i);
			}
		}
		return null;
	}
}
