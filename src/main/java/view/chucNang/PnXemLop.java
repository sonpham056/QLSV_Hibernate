package view.chucNang;

import javax.swing.JPanel;
import java.awt.SystemColor;
import java.util.List;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DAO.LopDAO;
import DAO.MonHocDAO;
import DAO.SinhVienDAO;
import entities.Lop;
import entities.MonHoc;
import entities.SinhVien;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PnXemLop extends JPanel {
	private JTable table;
	private JComboBox cmbLop;
	private JComboBox cmbMonHoc;

	/**
	 * Create the panel.
	 */
	public PnXemLop() {
		setBackground(SystemColor.activeCaption);
		setBounds(0, 0, 565, 535);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Lớp");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(69, 29, 112, 30);
		add(lblNewLabel);
		
		Vector<Lop> lops = getLops();
		cmbLop = new JComboBox(lops);
		//cmbLop.addItem("-----");
		cmbLop.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cmbLop.setBounds(191, 26, 235, 33);
		add(cmbLop);
		
		JLabel lblNewLabel_1 = new JLabel("Môn học");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(69, 72, 112, 30);
		add(lblNewLabel_1);
		
		Vector<MonHoc> monHocs = getMonHocs();
		cmbMonHoc = new JComboBox(monHocs);
		cmbMonHoc.addItem("-----");
		cmbMonHoc.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cmbMonHoc.setBounds(191, 69, 235, 33);
		add(cmbMonHoc);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 168, 545, 357);
		add(scrollPane);
		
		String[] columns = {"1", "2", "3"};
		table = new JTable(new DefaultTableModel(new Object[]{"MSSV", "Tên", "Lớp", "Môn học"}, 0));
		scrollPane.setViewportView(table);
		
		JButton button = new JButton("New button");
		button.setBounds(470, 137, -44, -35);
		add(button);
		
		JButton btnXem = new JButton("Xem");
		btnXem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnXemClicked();
			}
		});
		btnXem.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnXem.setBounds(428, 125, 127, 33);
		add(btnXem);
	}
	
	private Vector<Lop> getLops(){
		try {
			Vector<Lop> lops = new Vector<Lop>();
			List<Lop> list = LopDAO.layDanhSachLop();
			for (int i = 0; i < list.size(); i++) {
				lops.add(new Lop());
				lops.get(i).setcTMonHocs(list.get(i).getcTMonHocs());
				lops.get(i).setMaLop(list.get(i).getMaLop());
				lops.get(i).setSinhViens(list.get(i).getSinhViens());
			}
			return lops;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
			model.setRowCount(0); // reset table
			
			int index = cmbMonHoc.getSelectedIndex();
			if (index == cmbMonHoc.getItemCount() - 1) { //kiểm tra có đang chọn dấu "-----" không
				Lop lop = (Lop) cmbLop.getSelectedItem();
				List<SinhVien> list = SinhVienDAO.layDanhSachSVThuocLop(lop);
				
				for (int i = 0; i < list.size(); i++) {
					model.addRow(new Object[] {list.get(i).getMaSinhVien(),
							list.get(i).getHoVaTen(),
							list.get(i).getLop().getMaLop()});
				}
			}
			else {
				Lop lop = (Lop) cmbLop.getSelectedItem();
				MonHoc monHoc = (MonHoc) cmbMonHoc.getSelectedItem();
				List<SinhVien> list = SinhVienDAO.layDanhSachSVThuocLopVaMonHoc(lop, monHoc);
				for (int i = 0; i < list.size(); i++) {
					model.addRow(new Object[] {list.get(i).getMaSinhVien(),
							list.get(i).getHoVaTen(),
							list.get(i).getLop().getMaLop(),
							monHoc.getTenMonHoc()});
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
