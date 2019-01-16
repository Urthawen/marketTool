package interfaceGraphic;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controllers.manageProductController;

public class FenetreAchat extends JFrame implements ActionListener {

	private JButton btAchat;
	private JTextField txtQuantite;
	private JComboBox<String> combo;
	private manageProductController mpc;
	public FenetreAchat() {

		mpc = new manageProductController();
		setTitle("Achat");
		setBounds(500, 500, 200, 125);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btAchat = new JButton("Achat");
		txtQuantite = new JTextField(5);
		txtQuantite.setText("0");

		combo = new JComboBox<String>(mpc.getProducts());
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Produit"));
		contentPane.add(combo);
		contentPane.add(new JLabel("Quantité achetée"));
		contentPane.add(txtQuantite);
		contentPane.add(btAchat);

		btAchat.addActionListener(this);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		String nom = (String) combo.getSelectedItem();
		int qte = Integer.parseInt(txtQuantite.getText());
		if(mpc.buyStock(nom, qte)) {
			this.dispose();			
		}
	}

}
