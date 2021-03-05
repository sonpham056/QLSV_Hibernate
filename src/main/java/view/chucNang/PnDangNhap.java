package view.chucNang;

import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import DAO.SinhVienDAO;
import DAO.TaiKhoanDAO;
import entities.SinhVien;
import entities.TaiKhoan;
import view.nguoiDung.PnGiaoVu;
import view.nguoiDung.PnSinhVien;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PnDangNhap extends JPanel {
	private JTextField txtTaiKhoan;
	private JPasswordField txtPassword;
	private CardLayout card;
	private JPanel contentPane;
	/**
	 * Create the panel.
	 */
	public PnDangNhap(CardLayout card, JPanel contentPane) {
		this.card = card;
		this.contentPane = contentPane;
		setBackground(new Color(0, 255, 255));
		setBounds(0, 0, 800, 600);
		setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 134, 780, 45);
		add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 455, 780, 45);
		add(separator_1);
		
		JLabel lblNewLabel = new JLabel("CHƯƠNG TRÌNH QUẢN LÝ SINH VIÊN");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 38, 780, 74);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Tài khoản");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(130, 220, 134, 29);
		add(lblNewLabel_1);
		
		txtTaiKhoan = new JTextField();
		txtTaiKhoan.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnDangNhapClicked();
				}
			}
		});
		txtTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtTaiKhoan.setBounds(308, 220, 367, 29);
		add(txtTaiKhoan);
		txtTaiKhoan.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Mật khẩu");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(130, 339, 134, 29);
		add(lblNewLabel_1_1);
		
		txtPassword = new JPasswordField();
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnDangNhapClicked();
				}
			}
		});
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtPassword.setBounds(308, 339, 367, 29);
		add(txtPassword);
		
		JButton btnDangNhap = new JButton("Đăng nhập");
		btnDangNhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDangNhapClicked();
			}
		});
		btnDangNhap.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDangNhap.setBounds(308, 482, 147, 45);
		add(btnDangNhap);
		
		JButton btnThoat = new JButton("Thoát");
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnThoat.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnThoat.setBounds(528, 482, 147, 45);
		add(btnThoat);
	}
	
	private void btnDangNhapClicked() {
		try {
			String pass = new String(txtPassword.getPassword());
			if (txtTaiKhoan.getText().compareTo("") == 0 || pass.compareTo("") == 0)
				throw new Exception("Vui lòng nhập đầy đủ thông tin");
			TaiKhoan t = TaiKhoanDAO.layThongTinTaiKhoan(txtTaiKhoan.getText());
			if (t == null)
				throw new Exception("Sai tài khoản hoặc mật khẩu");
			if (pass.compareTo(t.getMatKhau()) != 0)
				throw new Exception("Sai tài khoản hoặc mật khẩu");
			JOptionPane.showMessageDialog(this, "Đăng nhập thành công chào: " + t.getSinhVien().getHoVaTen());
			if (t.getSinhVien().getMaSinhVien().compareTo("giaovu") == 0) {
				card.show(contentPane, "pnGiaoVu");
			}
			else {
				SinhVien sinhVien = SinhVienDAO.layThongTinSinhVien(txtTaiKhoan.getText());
				PnSinhVien pnSinhVien = new PnSinhVien(card, contentPane, sinhVien);
				contentPane.add(pnSinhVien, "pnSinhVien");
				card.show(contentPane, "pnSinhVien");
			}
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(this, e2.getMessage());
			e2.printStackTrace();
		}
	}
}
