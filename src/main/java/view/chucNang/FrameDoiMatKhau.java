package view.chucNang;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.TaiKhoanDAO;
import entities.TaiKhoan;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrameDoiMatKhau extends JFrame {

	private JPanel contentPane;
	private JTextField txtTaiKhoan;
	private JPasswordField txtMatKhau;
	private JPasswordField txtXacNhan;
	private JPasswordField txtMatKhauMoi;

	public FrameDoiMatKhau() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 314);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tài khoản");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 5, 93, 33);
		contentPane.add(lblNewLabel);
		
		JLabel lblMtKhu = new JLabel("Mật khẩu");
		lblMtKhu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMtKhu.setBounds(10, 53, 93, 33);
		contentPane.add(lblMtKhu);
		
		JLabel lblXcNhn = new JLabel("Xác nhận");
		lblXcNhn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblXcNhn.setBounds(10, 149, 93, 33);
		contentPane.add(lblXcNhn);
		
		txtTaiKhoan = new JTextField();
		txtTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtTaiKhoan.setBounds(141, 10, 285, 29);
		contentPane.add(txtTaiKhoan);
		txtTaiKhoan.setColumns(10);
		
		JButton btnDoiMatKhau = new JButton("Đổi");
		btnDoiMatKhau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDoiMatKhauClicked();
			}
		});
		btnDoiMatKhau.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDoiMatKhau.setBounds(295, 202, 131, 48);
		contentPane.add(btnDoiMatKhau);
		
		txtMatKhau = new JPasswordField();
		txtMatKhau.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtMatKhau.setBounds(141, 58, 285, 29);
		contentPane.add(txtMatKhau);
		
		txtXacNhan = new JPasswordField();
		txtXacNhan.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtXacNhan.setBounds(141, 154, 285, 29);
		contentPane.add(txtXacNhan);
		
		txtMatKhauMoi = new JPasswordField();
		txtMatKhauMoi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtMatKhauMoi.setBounds(141, 106, 285, 29);
		contentPane.add(txtMatKhauMoi);
		
		JLabel lblMtKhuMi = new JLabel("Mật khẩu mới");
		lblMtKhuMi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMtKhuMi.setBounds(10, 101, 121, 33);
		contentPane.add(lblMtKhuMi);
	}
	
	private void btnDoiMatKhauClicked() {
		try {
			String pass = new String(txtMatKhau.getPassword());
			String pass2 = new String(txtMatKhauMoi.getPassword());
			String pass3 = new String(txtXacNhan.getPassword());
			if (txtTaiKhoan.getText().compareTo("") == 0 || pass.compareTo("") == 0 || pass2.compareTo("") == 0 || pass3.compareTo("") == 0)
				throw new Exception("Vui lòng nhập đầy đủ thông tin");
			
			TaiKhoan taiKhoan = TaiKhoanDAO.layThongTinTaiKhoan(txtTaiKhoan.getText());
			if (taiKhoan == null)
				throw new Exception("Tài khoản không tồn tại");
			if (taiKhoan.getMatKhau().compareTo(pass) != 0)
				throw new Exception("Sai mật khẩu cũ!");
			if (pass2.compareTo(pass3) != 0)
				throw new Exception("Mật khẩu không trùng khớp!");
			taiKhoan.setMatKhau(pass2);
			TaiKhoanDAO.capNhatTaiKhoan(taiKhoan);
			
			JOptionPane.showMessageDialog(this, "Thành công");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			e.printStackTrace();
		}
	}
}
