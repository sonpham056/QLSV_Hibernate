package view.chucNang;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PnXemThongTin extends JPanel {

	/**
	 * Create the panel.
	 */
	
	private CardLayout card;
	private JPanel panel;
	
	public PnXemThongTin() {
		setBounds(0, 0, 585, 605);
		setBackground(SystemColor.activeCaption);
		setLayout(null);
		
		JButton btnXemLop = new JButton("Xem lớp");
		btnXemLop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnXemLopClicked();
			}
		});
		btnXemLop.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnXemLop.setBounds(10, 10, 118, 40);
		add(btnXemLop);
		
		JButton btnXemTKB = new JButton("Xem TKB");
		btnXemTKB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnXemTKBClicked();
			}
		});
		btnXemTKB.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnXemTKB.setBounds(138, 10, 118, 40);
		add(btnXemTKB);
		
		panel = new JPanel();
		panel.setBounds(10, 60, 565, 535);
		add(panel);
		card = new CardLayout(0, 0);
		panel.setLayout(card);
		
		panel.add(new PnXemLop(), "PnXemLop");
		panel.add(new PnXemTKB(), "PnXemTKB");

	}
	
	private void btnXemLopClicked() {
		card.show(panel, "PnXemLop");
	}
	
	private void btnXemTKBClicked() {
		card.show(panel, "PnXemTKB");
	}
}
