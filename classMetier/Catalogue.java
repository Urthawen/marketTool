package classMetier;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Catalogue implements I_Catalogue{
	
	private List<I_Produit> lesProduits;
	
	public Catalogue() {
		this.lesProduits = new ArrayList<>();
	}

	private boolean existProduit(I_Produit produit) {
		
		boolean find = false;
		
		//On cherche s'il existe déjà le produit
		for(I_Produit produitCatalogue:lesProduits) {
			if(produitCatalogue.getNom().equals(produit.getNom())) {
				find = true;
			}
		}
		
		//On regarde s'il l'a trouvé
		if(find) {
			return false;
		}else {
			return true;
		}
	}
	
	private boolean verificationParametreProduit(I_Produit produit) {
		String nom = produit.getNom();
		nom = nom.replaceAll("\\t", " ");
		nom = nom.trim();
		produit.setNom(nom);
		if(produit.getPrixUnitaireHT()>0 && produit.getQuantite() >= 0) {			
			return true;	
		}
		else
			return false;
	}
	
	private boolean verificationProduit(I_Produit produit) {	
		
		if(produit!=null) {
			if(verificationParametreProduit(produit)) {
				if(existProduit(produit))
					return true;
			}
		}
		
		return false;
	}
	
	private double reformatNumber(double number) {		
		return ((double)Math.round(number*100)/100);
	}
	
	@Override
	public boolean addProduit(I_Produit produit) {
		if(verificationProduit(produit)) {
			this.lesProduits.add(produit);
			return true;
		}else {
			return false;
		}		
	}

	@Override
	public boolean addProduit(String nom, double prix, int qte) {		
		
		I_Produit produit = new Produit(nom,prix,qte);
		
		if(verificationProduit(produit)) {
			this.lesProduits.add(produit);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public int addProduits(List<I_Produit> l) {
		int nbProduitAdd = 0;
		if(l!=null) {
			for(I_Produit produitList:l) {
				if(verificationProduit(produitList)) {
					this.lesProduits.add(produitList);
					nbProduitAdd++;
				}
			}
		}
		return nbProduitAdd;
	}

	@Override
	public boolean removeProduit(String nom) {
		
		boolean deleted = false;
		int idx = 0;

		while (idx < this.lesProduits.size() && !deleted){
		   if(this.lesProduits.get(idx).getNom().equals(nom)){
			 this.lesProduits.remove(idx);
			 deleted = true;
		  }
		  idx++;
		}
		
		return deleted;
	}

	@Override
	public boolean acheterStock(String nomProduit, int qteAchetee) {
		
		boolean find = false;
		
		if(nomProduit!=null && qteAchetee > 0) {
			for(I_Produit produitCatalogue:lesProduits) {
				if(produitCatalogue.getNom() == nomProduit) {
					produitCatalogue.ajouter(qteAchetee);
					find = true;
				}
			}
		}
		
		return find;
	}

	@Override
	public boolean vendreStock(String nomProduit, int qteVendue) {
		
		boolean sell = false;
		
		if(nomProduit!=null && qteVendue>0) {
			for(I_Produit produitCatalogue:lesProduits) {
				if(produitCatalogue.getNom().equals(nomProduit) && produitCatalogue.getQuantite() >= qteVendue) {
					produitCatalogue.enlever(qteVendue);
					sell = true;
				}
			}
		}
		
		return sell;
	}

	@Override
	public String[] getNomProduits() {
		
		String[] response = new String[this.lesProduits.size()];
		int i=0;
		
		while(i<this.lesProduits.size()) {
			response[i] = this.lesProduits.get(i).getNom();
			i++;
		}		
		
		//Tri par ordre alphabétique
		Arrays.sort(response);
		
		return response;
	}

	@Override
	public double getMontantTotalTTC() {
		double montantTotalTTC = 0.00;
		
		for(I_Produit produitCatalogue:lesProduits){
			montantTotalTTC += produitCatalogue.getPrixStockTTC();
		}
		montantTotalTTC = reformatNumber(montantTotalTTC);
		
		return montantTotalTTC;
	}

	@Override
	public void clear() {
		this.lesProduits.clear();		
	}

	@Override
	public int nbProduits() {
		return this.lesProduits.size();
	}

	@Override
	public String toString() {
		
		String response = "";
		NumberFormat nf= new DecimalFormat("0.##");
		nf.setMinimumFractionDigits(2);
		
		if(nbProduits()<=0) {
			response+="\nMontant total TTC du stock : 0,00 €";
		}else {
			
			for(I_Produit produit:this.lesProduits) {
				
				String prixUnitaireHT = nf.format(produit.getPrixUnitaireHT());
				String prixUnitaireTTC = nf.format(produit.getPrixUnitaireTTC());
				
				response+=produit.getNom()+" - prix HT : "+ 
						  prixUnitaireHT +" € - prix TTC : "+ 
						  prixUnitaireTTC +" € - quantité en stock : "+
						  produit.getQuantite()+"\n";
			}
			
			String montantTotalTTC = nf.format(getMontantTotalTTC());
			response+="\nMontant total TTC du stock : "+montantTotalTTC+" €";
		}
		return response;
	}
	
	
	
}
