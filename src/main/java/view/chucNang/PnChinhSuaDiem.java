package view.chucNang;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;

import java.util.List;
import java.util.Vector;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.BangDiemDAO;
import DAO.MonHocDAO;
import DAO.SinhVienDAO;
import entities.BangDiem;
import entities.MonHoc;
import entities.SinhVien;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PnChinhSuaDiem extends JPanel {
	private JTable table;
	private JComboBox cmbMonHoc;
	private JTextField txtMSSV;
	private JTextField txtTen;
	private JTextField txtGK;
	private JTextField txtCK;
	private JTextField txtKhac;
	private JTextField txtTong;

	/**
	 * Create the panel.
	 */
	public PnChinhSuaDiem() {
		setBounds(0, 0, 585, 605);
		
		setBackground(SystemColor.activeCaption);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Môn học");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(69, 29, 112, 30);
		add(lblNewLabel);
		
		Vector<MonHoc> monHocs = getMonHocs();
		cmbMonHoc = new JComboBox((monHocs));
		cmbMonHoc.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cmbMonHoc.setBounds(191, 26, 235, 33);
		add(cmbMonHoc);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 168, 565, 247);
		add(scrollPane);
		
		table = new JTable(new DefaultTableModel(new Object[] {"MSSV", "Tên SV", "GK", "CK", "Khác", "Tổng", "Tình trạng"}, 0));
		scrollPane.setViewportView(table);
		
		JButton btnXem = new JButton("Xem");
		btnXem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnXemClicked();
			}
		});
		btnXem.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnXem.setBounds(448, 125, 127, 33);
		add(btnXem);
		
		JButton btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCapNhatClicked();
			}
		});
		btnCapNhat.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCapNhat.setBounds(448, 562, 127, 33);
		add(btnCapNhat);
		
		JLabel lblMssv = new JLabel("MSSV");
		lblMssv.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMssv.setBounds(10, 425, 83, 30);
		add(lblMssv);
		
		txtMSSV = new JTextField();
		txtMSSV.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (SinhVienDAO.layThongTinSinhVien(txtMSSV.getText()) != null)
					txtTen.setText(SinhVienDAO.layThongTinSinhVien(txtMSSV.getText()).getHoVaTen());
				else {
					txtTen.setText("");
				}
			}
		});
		txtMSSV.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtMSSV.setBounds(103, 425, 121, 33);
		add(txtMSSV);
		txtMSSV.setColumns(10);
		
		txtTen = new JTextField();
		txtTen.setEnabled(false);
		txtTen.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtTen.setColumns(10);
		txtTen.setBounds(234, 425, 214, 33);
		add(txtTen);
		
		JLabel lblGk = new JLabel("GK");
		lblGk.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblGk.setBounds(10, 465, 83, 30);
		add(lblGk);
		
		txtGK = new JTextField();
		txtGK.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtGK.setColumns(10);
		txtGK.setBounds(103, 465, 121, 33);
		add(txtGK);
		
		JLabel lblCk = new JLabel("CK");
		lblCk.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCk.setBounds(10, 505, 83, 30);
		add(lblCk);
		
		txtCK = new JTextField();
		txtCK.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtCK.setColumns(10);
		txtCK.setBounds(103, 505, 121, 33);
		add(txtCK);
		
		JLabel lblKhc = new JLabel("Khác");
		lblKhc.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblKhc.setBounds(10, 545, 83, 30);
		add(lblKhc);
		
		txtKhac = new JTextField();
		txtKhac.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtKhac.setColumns(10);
		txtKhac.setBounds(103, 545, 121, 33);
		add(txtKhac);
		
		JLabel lblTng = new JLabel("Tổng");
		lblTng.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTng.setBounds(234, 468, 83, 30);
		add(lblTng);
		
		txtTong = new JTextField();
		txtTong.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtTong.setColumns(10);
		txtTong.setBounds(327, 468, 121, 33);
		add(txtTong);

	}
	
	private Vector<MonHoc> getMonHocs(){
		try {
			Vector<MonHoc> monHocs = new Vector<MonHoc>();
			List<MonHoc> list = MonHocDAO.layDanhSachMonHoc();
			for (int i = 0; i < list.size(); i++) {
				monHocs.add(new MonHoc());
				monHocs.get(i).setBangDiems(list.get(i).getBangDiems());
				monHocs.get(i).setcTMonHocs(list.get(i).getcTMonHocs());
				monHocs.get(i).setMaMonHoc(list.get(i).getMaMonHoc());
				monHocs.get(i).setTenMonHoc(list.get(i).getTenMonHoc());
			}
			return monHocs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private void btnXemClicked() {
		try {
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setRowCount(0);
			
			MonHoc monHoc = (MonHoc) cmbMonHoc.getSelectedItem();
			List<BangDiem> list = BangDiemDAO.layDanhSachSinhVienTheoMon(monHoc);
			for (int i = 0; i < list.size(); i++) {
				String tinhTrang = (list.get(i).getTong() >= 5) ? "Đậu" : "Rớt";
				model.addRow(new Object[] {list.get(i).getSinhVien().getMaSinhVien(),
						list.get(i).getSinhVien().getHoVaTen(),
						list.get(i).getgK(),
						list.get(i).getcK(),
						list.get(i).getKhac(),
						list.get(i).getTong(),
						tinhTrang});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void btnCapNhatClicked() {
		try {
			if (txtTen.getText().compareTo("") == 0)
				throw new Exception("Không tìm thấy mã sinh viên!");
			if (txtCK.getText().compareTo("") == 0|| txtGK.getText().compareTo("")  == 0|| txtKhac.getText().compareTo("") == 0 || txtTong.getText().compareTo("") == 0)
				throw new Exception("Vui lòng điền đầy đủ thông tin");
			if (isNumeric(txtCK.getText()) == false || isNumeric(txtGK.getText()) == false || isNumeric(txtKhac.getText()) == false|| isNumeric(txtTong.getText()) == false)
				throw new Exception("Sai định dạng");
			
			SinhVien sinhVien = SinhVienDAO.layThongTinSinhVien(txtMSSV.getText());
			MonHoc monHoc = (MonHoc) cmbMonHoc.getSelectedItem();
			BangDiem bangDiem = BangDiemDAO.layThongTinBangDiem(sinhVien, monHoc);
			if (bangDiem == null)
				JOptionPane.showMessageDialog(this, "Sinh viên " + txtTen.getText() + " không học môn " + monHoc.getTenMonHoc());
			else {
				bangDiem.setgK(Float.parseFloat(txtGK.getText()));
				bangDiem.setcK(Float.parseFloat(txtCK.getText()));
				bangDiem.setKhac(Float.parseFloat(txtKhac.getText()));
				bangDiem.setTong(Float.parseFloat(txtTong.getText()));
				
				BangDiemDAO.capNhatMotBangDiem(bangDiem);
				
				btnXemClicked();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static boolean isNumeric(String str) { 
		  try {  
		    Double.parseDouble(str);  
		    return true;
		  } catch(NumberFormatException e){  
		    return false;  
		  }  
		}
}
