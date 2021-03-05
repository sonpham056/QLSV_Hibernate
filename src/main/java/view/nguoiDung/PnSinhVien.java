package view.nguoiDung;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.hibernate.boot.model.naming.ImplicitNameSource;

import DAO.BangDiemDAO;
import DAO.MonHocDAO;
import entities.BangDiem;
import entities.MonHoc;
import entities.SinhVien;
import view.chucNang.FrameDoiMatKhau;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.util.List;
import java.util.Vector;

import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PnSinhVien extends JPanel {

	/**
	 * Create the panel.
	 */
	
	private CardLayout card;
	private JPanel contentPane;
	private JTable table;
	private SinhVien sinhVien;
	
	public PnSinhVien(CardLayout card, JPanel contentPane, SinhVien sinhVien) {
		this.card = card;
		this.contentPane = contentPane;
		this.sinhVien = sinhVien;
		
		setBackground(SystemColor.activeCaption);
		setBounds(0, 0, 800, 610);
		this.card = card;
		this.contentPane = contentPane;
		setLayout(null);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 800, 28);
		add(toolBar);
		
		JButton btnDangXuat = new JButton("Đăng xuất");
		btnDangXuat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDangXuatClicked();
			}
		});
		toolBar.add(btnDangXuat);
		
		JButton btnDoiMatKhau = new JButton("Đổi mật khẩu");
		btnDoiMatKhau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDoiMatKhauClicked();
			}
		});
		toolBar.add(btnDoiMatKhau);
		
		JLabel lbTenSV = new JLabel("           Chào: " + sinhVien.getHoVaTen());
		lbTenSV.setHorizontalAlignment(SwingConstants.CENTER);
		toolBar.add(lbTenSV);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 38, 780, 557);
		add(scrollPane);
		
		table = new JTable(new DefaultTableModel(new Object[] {"Tên SV", "Môn học", "GK", "CK", "Khác", "Tổng", "Tình trạng"}, 0));
		scrollPane.setViewportView(table);
		loadDiem();
	}
	
	private void loadDiem() {
		List<BangDiem> list = BangDiemDAO.layDanhSachBangDiemTheoSinhVien(sinhVien);
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		
		for (int i = 0; i < list.size(); i++) {
			String tinhTrang = (list.get(i).getTong() >= 5) ? "Đậu" : "Rớt";
			model.addRow(new Object[] {list.get(i).getSinhVien().getHoVaTen(),
					list.get(i).getMonHoc().getTenMonHoc(),
					list.get(i).getgK(),
					list.get(i).getcK(),
					list.get(i).getKhac(),
					list.get(i).getTong(),
					tinhTrang});
		}
	}
	
	private void btnDangXuatClicked() {
		if (JOptionPane.showConfirmDialog(this, "Bạn có muốn đăng xuất?") == JOptionPane.YES_OPTION) {
			card.show(contentPane, "pnDangNhap");
		}
	}
	
	private void btnDoiMatKhauClicked() {
		JFrame doiMatKhau = new FrameDoiMatKhau();
		doiMatKhau.setVisible(true);
	}
}
