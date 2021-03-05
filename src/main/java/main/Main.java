package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.dialect.Oracle10gDialect;

import DAO.TaiKhoanDAO;
import entities.CTMonHoc;
import entities.Lop;
import entities.MonHoc;
import entities.TaiKhoan;
import util.HibernateUtil;
import view.chucNang.PnDangNhap;
import view.nguoiDung.PnGiaoVu;
import view.nguoiDung.PnSinhVien;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JPasswordField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Font;

public class Main extends JFrame {

	private JPanel contentPane;
	private CardLayout card;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public Main() throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 824, 647);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		card = new CardLayout(0, 0);
		contentPane.setLayout(card);
		
		PnDangNhap pnDangNhap = new PnDangNhap(card, contentPane);
		contentPane.add(pnDangNhap, "pnDangNhap");
		
		PnGiaoVu pnGiaoVu = new PnGiaoVu(card, contentPane);
		contentPane.add(pnGiaoVu, "pnGiaoVu");
		
	}
}
