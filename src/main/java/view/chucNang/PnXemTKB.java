package view.chucNang;

import javax.swing.JPanel;
import java.awt.SystemColor;
import javax.swing.JScrollPane;

import DAO.CTMonHocDAO;
import DAO.LopDAO;
import entities.CTMonHoc;
import entities.Lop;

import javax.swing.JComboBox;

import java.util.List;
import java.util.Vector;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PnXemTKB extends JPanel {
	
	private JComboBox cmbLop;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public PnXemTKB() {
		setBackground(SystemColor.activeCaption);
		setBounds(0, 0, 565, 535);
		setLayout(null);
		
		Vector<Lop> lops = getLops();
		cmbLop = new JComboBox(lops);
		cmbLop.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cmbLop.setBounds(191, 26, 235, 33);
		add(cmbLop);
		
		JLabel lblNewLabel = new JLabel("Lớp");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(69, 29, 112, 30);
		add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 168, 545, 357);
		add(scrollPane);
		
		table = new JTable(new DefaultTableModel(new Object[] {"Lớp", "Môn học"}, 0));
		scrollPane.setViewportView(table);
		
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
	
	private void btnXemClicked() {
		try {
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setRowCount(0);
			
			Lop lop = (Lop) cmbLop.getSelectedItem();
			List<CTMonHoc> list = CTMonHocDAO.layDanhSachCTMonHocTheoLop(lop);
			for (int i = 0; i < list.size(); i++) {
				model.addRow(new Object[] {list.get(i).getMaLop_CT(),
						list.get(i).getMonHoc().getTenMonHoc()});
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
