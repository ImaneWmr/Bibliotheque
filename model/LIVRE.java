package model;

import java.awt.Component;

public class LIVRE {
	private String ISBN;
	private String titre;
	private int prix ;
	private AUTEUR Auteur;
	private ADHERENT Emprunteur;
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public int getPrix() {
		return prix;
	}
	public void setPrix(int prix) {
		this.prix = prix;
	}
	public AUTEUR getAuteur() {
		return Auteur;
	}
	public void setAuteur(AUTEUR auteur) {
		Auteur = auteur;
	}
	public ADHERENT getEmprunteur() {
		return Emprunteur;
	}
	public void setEmprunteur(ADHERENT emprunteur) {
		this.Emprunteur = emprunteur;
	}
	public LIVRE(String iSBN, String titre, int prix, AUTEUR auteur, ADHERENT emprunteur) {
		super();
		ISBN = iSBN;
		this.titre = titre;
		this.prix = prix;
		Auteur = auteur;
		this.Emprunteur = emprunteur;
	}
	
	public String ligne() {
		String ligne = ISBN + " : " + titre ;
		if (Auteur == null) {
			ligne= ligne + "(non renseigné)";
		}else {
			ligne = ligne + "("+ Auteur.getNom()+")";
		}
		if (Emprunteur == null) {
			ligne = ligne + " - Dispo";
		}else {
			ligne = ligne + " - Emprunté";
		}
		return ligne;
	}
	

}
