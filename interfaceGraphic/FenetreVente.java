package interfaceGraphic;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controllers.manageProductController;

public class FenetreVente extends JFrame implements ActionListener {

	private JButton btVente;
	private JTextField txtQuantite;
	private JComboBox<String> combo;
	private manageProductController mpc;
	
	public FenetreVente() {
		
		mpc = new manageProductController();
		
		setTitle("Vente");
		setBounds(500, 500, 200, 125);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btVente = new JButton("Vente");
		txtQuantite = new JTextField(5);
		txtQuantite.setText("0");

		combo = new JComboBox<String>(mpc.getProducts());
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Produit"));
		contentPane.add(combo);
		contentPane.add(new JLabel("Quantité vendue"));
		contentPane.add(txtQuantite);
		contentPane.add(btVente);

		btVente.addActionListener(this);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		String nom = (String) combo.getSelectedItem();
		int qte = Integer.parseInt(txtQuantite.getText());
		if(mpc.sellStock(nom, qte)) {
			this.dispose();
		}
	}

}
