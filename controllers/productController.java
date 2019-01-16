package controllers;

import interfaceGraphic.FenetrePrincipale;

public class productController {
	
	public productController() {
	}

	public boolean addProduit(String nom, double prixHT, int quantite) {
		return FenetrePrincipale.catalogue.addProduit(nom, prixHT, quantite);		
	}
	
	public boolean deleteProduit(String nom) {
		return FenetrePrincipale.catalogue.removeProduit(nom);
	}
	
	public String[] getProducts() {
		return FenetrePrincipale.catalogue.getNomProduits();
	}
}
