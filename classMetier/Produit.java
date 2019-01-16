package classMetier;

public class Produit implements I_Produit{
	
	private String _nom;
	private double _prixHT;
	private int _quantite;
	private double _tauxTVA = 1.2;
	
	public Produit(String nom, double prixHT, int quantite) {
		this._nom = nom;
		this._prixHT = prixHT;
		this._quantite = quantite;
	}

	@Override
	public boolean ajouter(int qteAchetee) {
		this._quantite += qteAchetee;
		return true;
	}

	@Override
	public boolean enlever(int qteVendue) {
		if(qteVendue <= this._quantite) {
			this._quantite -= qteVendue;
			return true;
		}else {
			return false;
		}		
	}

	@Override
	public String getNom() {
		return this._nom;
	}

	@Override
	public int getQuantite() {		
		return this._quantite;
	}

	@Override
	public double getPrixUnitaireHT() {
		return this._prixHT;
	}

	@Override
	public double getPrixUnitaireTTC() {
		return this._prixHT * this._tauxTVA;
	}

	@Override
	public double getPrixStockTTC() {
		double prixUnitaireTTC = getPrixUnitaireTTC();
		double prixStock = prixUnitaireTTC * getQuantite();
		return prixStock;
	}

	@Override
	public void setNom(String correctName) {
		this._nom = correctName;		
	}

}
