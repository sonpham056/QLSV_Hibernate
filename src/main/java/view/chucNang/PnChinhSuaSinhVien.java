package view.chucNang;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.SystemColor;
import java.util.List;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.hibernate.Session;

import DAO.LopDAO;
import DAO.SinhVienDAO;
import entities.Lop;
import entities.SinhVien;

import javax.swing.JComboBox;
import java.awt.Font;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PnChinhSuaSinhVien extends JPanel {
	private JTextField txtMSSV;
	private JTextField txtTen;
	private JTextField txtCmnd;
	private JComboBox cmbGioiTinh;
	private JComboBox cmbLop;

	/**
	 * Create the panel.
	 */
	public PnChinhSuaSinhVien() {
		setBounds(0, 0, 585, 605);

		setBackground(SystemColor.activeCaption);
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Mã số sinh viên");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(66, 108, 143, 30);
		add(lblNewLabel);

		txtMSSV = new JTextField();
		txtMSSV.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtMSSV.setBounds(239, 108, 279, 30);
		add(txtMSSV);
		txtMSSV.setColumns(10);

		JLabel lblTnSinhVin = new JLabel("Tên sinh viên");
		lblTnSinhVin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTnSinhVin.setBounds(66, 179, 143, 30);
		add(lblTnSinhVin);

		txtTen = new JTextField();
		txtTen.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtTen.setColumns(10);
		txtTen.setBounds(239, 179, 279, 30);
		add(txtTen);

		JLabel lblGiiTnh = new JLabel("Giới tính");
		lblGiiTnh.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblGiiTnh.setBounds(66, 250, 143, 30);
		add(lblGiiTnh);

		JLabel lblCmnd = new JLabel("Cmnd");
		lblCmnd.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCmnd.setBounds(66, 321, 143, 30);
		add(lblCmnd);

		txtCmnd = new JTextField();
		txtCmnd.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtCmnd.setColumns(10);
		txtCmnd.setBounds(239, 321, 279, 30);
		add(txtCmnd);

		JLabel lblLp = new JLabel("Lớp");
		lblLp.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLp.setBounds(66, 392, 143, 30);
		add(lblLp);

		String[] comboBoxItems = getComboBoxItems();
		cmbLop = new JComboBox(comboBoxItems);
		cmbLop.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cmbLop.setBounds(239, 392, 279, 30);
		add(cmbLop);

		JButton btnThemSua = new JButton("Thêm/Sửa");
		btnThemSua.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnThemSua.setBounds(152, 463, 156, 37);
		add(btnThemSua);

		JButton btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnXoaClicked();
			}
		});
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnXoa.setBounds(318, 463, 115, 37);
		add(btnXoa);

		JButton btnMoi = new JButton("Mới");
		btnMoi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnMoi.setBounds(443, 463, 115, 37);
		add(btnMoi);
		
		cmbGioiTinh = new JComboBox(new Object[]{"Nam", "Nữ"});
		cmbGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cmbGioiTinh.setBounds(239, 250, 279, 30);
		add(cmbGioiTinh);
		
		//button events
		btnThemSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnThemClicked();
			}
		});

	}

	private String[] getComboBoxItems() {
		try {
			
			List<Lop> list = LopDAO.layDanhSachLop();
			String[] lop = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				lop[i] = list.get(i).getMaLop();
			}
			return lop;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private void btnThemClicked() {
		try {
			if (txtMSSV.getText().compareTo("") == 0 || txtCmnd.getText().compareTo("") == 0 || txtTen.getText().compareTo("") == 0)
				throw new Exception("Vui lòng điền đầy đủ thông tin");
			SinhVien sinhVien = SinhVienDAO.layThongTinSinhVien(txtMSSV.getText());
			if (sinhVien != null) //update
			{
				sinhVien.setHoVaTen(txtTen.getText());
				sinhVien.setGioiTinh((String)cmbGioiTinh.getSelectedItem());
				sinhVien.setLop(LopDAO.layThongTinLop((String)cmbLop.getSelectedItem()));
				
				SinhVienDAO.suaMotSinhVien(sinhVien);
			} else { //insert
				sinhVien = new SinhVien();
				sinhVien.setMaSinhVien(txtMSSV.getText());
				sinhVien.setHoVaTen(txtTen.getText());
				sinhVien.setCmnd(txtCmnd.getText());
				sinhVien.setGioiTinh((String)cmbGioiTinh.getSelectedItem());
				sinhVien.setLop(LopDAO.layThongTinLop((String)cmbLop.getSelectedItem()));

				SinhVienDAO.themMotSinhVien(sinhVien);
			}
			JOptionPane.showMessageDialog(this, "Thêm/sửa sinh viên thành công");
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(this, e2.getMessage());
		}
	}
	
	private void btnXoaClicked() {
		try {
			if (txtMSSV.getText().compareTo("") == 0)
				throw new Exception("Vui lòng điền MSSV cần xóa");
			SinhVien sinhVien = SinhVienDAO.layThongTinSinhVien(txtTen.getText());
			if (sinhVien != null) {
				if (JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa sinh viên " + sinhVien.getHoVaTen() + " ?") == JOptionPane.YES_OPTION) {
					SinhVienDAO.xoaMotSinhVien(sinhVien);
					JOptionPane.showMessageDialog(this, "Xóa sinh viên thành công");
				}
			}
			else {
				JOptionPane.showMessageDialog(this, "MSSV không tồn tại");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
}
