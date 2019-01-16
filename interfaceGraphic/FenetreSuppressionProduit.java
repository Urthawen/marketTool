package interfaceGraphic;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controllers.productController;

public class FenetreSuppressionProduit extends JFrame implements ActionListener {

	private JButton btSupprimer;
	private JComboBox<String> combo;

	private productController pc;
	
	public FenetreSuppressionProduit() {

		pc = new productController();
		setTitle("Suppression produit");
		setBounds(500, 500, 200, 105);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btSupprimer = new JButton("Supprimer");

		combo = new JComboBox<String>(pc.getProducts());
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Produit"));
		contentPane.add(combo);
		contentPane.add(btSupprimer);

		btSupprimer.addActionListener(this);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		String name = (String) combo.getSelectedItem();
		System.out.println(name);
		if(pc.deleteProduit(name)) {
			this.dispose();		
		}

	}

}
