package controllers;

import interfaceGraphic.FenetrePrincipale;

public class manageProductController {

	public manageProductController() {
		
	}
	
	public boolean buyStock(String nom, int qte) {
		return FenetrePrincipale.catalogue.acheterStock(nom, qte);
	}
	
	public boolean sellStock(String nom, int qte) {
		return FenetrePrincipale.catalogue.vendreStock(nom, qte);
	}
	
	public String[] getProducts() {
		return FenetrePrincipale.catalogue.getNomProduits();
	}
}
