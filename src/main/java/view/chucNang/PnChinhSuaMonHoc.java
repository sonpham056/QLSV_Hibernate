package view.chucNang;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.SystemColor;
import java.util.List;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.hibernate.type.YesNoType;

import DAO.BangDiemDAO;
import DAO.MonHocDAO;
import DAO.SinhVienDAO;
import entities.BangDiem;
import entities.MonHoc;
import entities.SinhVien;
import javassist.expr.NewArray;

import java.awt.FlowLayout;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PnChinhSuaMonHoc extends JPanel {
	private JTable table;
	private JComboBox cmbMonHoc;
	private JTextField txtMSSV;
	private JTextField txtTen;

	/**
	 * Create the panel.
	 */
	public PnChinhSuaMonHoc() {
		setBounds(0, 0, 585, 605);
		
		setBackground(SystemColor.activeCaption);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 94, 565, 417);
		add(scrollPane);
		
		table = new JTable(new DefaultTableModel(new Object[]{"Mã SV", "Tên SV", "Lớp", "Môn học"}, 0));
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		
		JButton btnXem = new JButton("Xem");
		btnXem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnXemClicked();
			}
		});
		btnXem.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnXem.setBounds(453, 48, 122, 36);
		add(btnXem);
		
		Vector<MonHoc> monHocString = getCmbMonHoc();
		cmbMonHoc = new JComboBox(monHocString);
		cmbMonHoc.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cmbMonHoc.setBounds(132, 10, 349, 28);
		add(cmbMonHoc);
		
		JLabel lblNewLabel = new JLabel("Môn học");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 10, 112, 28);
		add(lblNewLabel);
		
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
		txtMSSV.setBounds(84, 521, 122, 28);
		add(txtMSSV);
		txtMSSV.setColumns(10);
		
		JLabel lblMssv = new JLabel("MSSV");
		lblMssv.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMssv.setBounds(10, 521, 64, 28);
		add(lblMssv);
		
		JLabel lblTnSv = new JLabel("Tên SV");
		lblTnSv.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTnSv.setBounds(216, 521, 71, 28);
		add(lblTnSv);
		
		txtTen = new JTextField();
		txtTen.setEnabled(false);
		txtTen.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtTen.setColumns(10);
		txtTen.setBounds(286, 521, 289, 28);
		add(txtTen);
		
		JButton btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnThemClicked();
			}
		});
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnThem.setBounds(323, 559, 122, 36);
		add(btnThem);
		
		JButton btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnXoaClicked();
			}
		});
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnXoa.setBounds(453, 559, 122, 36);
		add(btnXoa);
		
		/*DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.addRow(new Object[]{"Column 1", "Column 2", "Column 3"});*/
		
	}
	
	private Vector<MonHoc> getCmbMonHoc() {
		List<MonHoc> list = MonHocDAO.layDanhSachMonHoc();
		Vector<MonHoc> monHocs = new Vector<MonHoc>();
		for (int i = 0; i < list.size(); i++)
		{
			monHocs.add(new MonHoc());
			monHocs.get(i).setBangDiems(list.get(i).getBangDiems());
			monHocs.get(i).setcTMonHocs(list.get(i).getcTMonHocs());
			monHocs.get(i).setMaMonHoc(list.get(i).getMaMonHoc());
			monHocs.get(i).setTenMonHoc(list.get(i).getTenMonHoc());
		}
		return monHocs;
	}
	
	private void btnXemClicked() {
		try {
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setRowCount(0);
			
			MonHoc monHoc = (MonHoc) cmbMonHoc.getSelectedItem();
			List<BangDiem> list = BangDiemDAO.layDanhSachSinhVienTheoMon(monHoc);
			
			for (int i = 0; i < list.size(); i++) {
				model.addRow(new Object[] {list.get(i).getSinhVien().getMaSinhVien(),
						list.get(i).getSinhVien().getHoVaTen(),
						list.get(i).getSinhVien().getLop().getMaLop(),
						monHoc.getTenMonHoc()});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void btnThemClicked() {
		try {
			if (txtTen.getText().compareTo("") == 0)
				throw new Exception("Không tìm thấy sinh viên, vui lòng nhập lại mã sinh viên");
			SinhVien sinhVien = SinhVienDAO.layThongTinSinhVien(txtMSSV.getText());
			MonHoc monHoc = (MonHoc) cmbMonHoc.getSelectedItem();
			BangDiem bangDiem = BangDiemDAO.layThongTinBangDiem(sinhVien, monHoc);
			if (bangDiem != null)
				JOptionPane.showMessageDialog(this, "Sinh viên này đã học môn này rồi");
			else {
				if (JOptionPane.showConfirmDialog(this, "Thêm sinh viên " + sinhVien.getHoVaTen() + " vào học phần này?") == JOptionPane.YES_OPTION) {
					bangDiem = new BangDiem(sinhVien, monHoc);
					BangDiemDAO.themMotBangDiem(bangDiem);
				}		
			}
			
			btnXemClicked();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void btnXoaClicked() {
		try {
			if (txtTen.getText().compareTo("") == 0)
				throw new Exception("Không tìm thấy sinh viên, vui lòng nhập lại mã sinh viên");
			
			SinhVien sinhVien = SinhVienDAO.layThongTinSinhVien(txtMSSV.getText());
			MonHoc monHoc = (MonHoc) cmbMonHoc.getSelectedItem();
			BangDiem bangDiem = BangDiemDAO.layThongTinBangDiem(sinhVien, monHoc);
			if (bangDiem == null)
				JOptionPane.showMessageDialog(this, "Sinh viên này không có trong danh sách môn này");
			else {
				if (JOptionPane.showConfirmDialog(this, "Bạn muốn xóa sinh viên " + sinhVien.getHoVaTen() + " khỏi học phần này?") == JOptionPane.YES_OPTION)
					BangDiemDAO.xoaMotBangDiem(bangDiem);
			}
			
			btnXemClicked();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			e.printStackTrace();
		}
	}
}
