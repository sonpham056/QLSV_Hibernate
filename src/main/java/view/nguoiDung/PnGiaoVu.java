package view.nguoiDung;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import main.Main;
import view.chucNang.FrameDoiMatKhau;
import view.chucNang.PnChinhSuaDiem;
import view.chucNang.PnChinhSuaMonHoc;
import view.chucNang.PnChinhSuaSinhVien;
import view.chucNang.PnImportLop;
import view.chucNang.PnXemThongTin;

import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PnGiaoVu extends JPanel {

	/**
	 * Create the panel.
	 * 
	 * @throws IOException
	 */
	private CardLayout cardMain;
	private CardLayout card;
	private JPanel contentPane;

	public PnGiaoVu(CardLayout cardMain, JPanel contentPane) throws IOException {
		this.cardMain = cardMain;
		this.contentPane = contentPane;
		setBounds(0, 0, 800, 605);
		BufferedImage myPicture = ImageIO
				.read(new File("D:\\Hoctap\\java_save_2\\LeanHibernate\\src\\test\\resources\\sinhvien.png"));
		setLayout(null);

		JPanel pnDashboard = new JPanel();
		pnDashboard.setBackground(Color.LIGHT_GRAY);
		pnDashboard.setBounds(0, 0, 215, 605);
		add(pnDashboard);
		pnDashboard.setLayout(null);
		
		JPanel pnCardLayout = new JPanel();
		pnCardLayout.setBounds(215, 0, 585, 605);
		add(pnCardLayout);
		card = new CardLayout(0, 0);
		pnCardLayout.setLayout(card);

		JPanel pnSinhVien = new JPanel();
		pnSinhVien.addMouseListener(new PanelButtonMouseAdapter(pnSinhVien, pnCardLayout));
		pnSinhVien.setName("pnSinhVien");
		pnSinhVien.setBackground(Color.LIGHT_GRAY);
		pnSinhVien.setBounds(0, 241, 215, 81);
		pnDashboard.add(pnSinhVien);
		pnSinhVien.setLayout(null);

		JLabel lblSinhVien = new JLabel("Chỉnh sửa sinh viên");
		lblSinhVien.setBackground(Color.LIGHT_GRAY);
		lblSinhVien.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblSinhVien.setHorizontalAlignment(SwingConstants.CENTER);
		lblSinhVien.setBounds(0, 0, 215, 81);
		pnSinhVien.add(lblSinhVien);

		JPanel pnMonHoc = new JPanel();
		pnMonHoc.addMouseListener(new PanelButtonMouseAdapter(pnMonHoc, pnCardLayout));
		pnMonHoc.setName("pnMonHoc");
		pnMonHoc.setBackground(Color.LIGHT_GRAY);
		pnMonHoc.setBounds(0, 332, 215, 81);
		pnDashboard.add(pnMonHoc);
		pnMonHoc.setLayout(null);

		JLabel lblMonHoc = new JLabel("Chỉnh sửa môn học");
		lblMonHoc.setBackground(Color.LIGHT_GRAY);
		lblMonHoc.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblMonHoc.setHorizontalAlignment(SwingConstants.CENTER);
		lblMonHoc.setBounds(0, 0, 215, 81);
		pnMonHoc.add(lblMonHoc);

		JPanel pnDiem = new JPanel();
		pnDiem.addMouseListener(new PanelButtonMouseAdapter(pnDiem, pnCardLayout));
		pnDiem.setName("pnDiem");
		pnDiem.setBackground(Color.LIGHT_GRAY);
		pnDiem.setBounds(0, 423, 215, 81);
		pnDashboard.add(pnDiem);
		pnDiem.setLayout(null);

		JLabel lblDiem = new JLabel("Chỉnh sửa điểm");
		lblDiem.setBackground(Color.LIGHT_GRAY);
		lblDiem.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblDiem.setHorizontalAlignment(SwingConstants.CENTER);
		lblDiem.setBounds(0, 0, 215, 81);
		pnDiem.add(lblDiem);

		JPanel pnXem = new JPanel();
		pnXem.addMouseListener(new PanelButtonMouseAdapter(pnXem, pnCardLayout));
		pnXem.setName("pnXem");
		pnXem.setBackground(Color.LIGHT_GRAY);
		pnXem.setBounds(0, 514, 215, 81);
		pnDashboard.add(pnXem);
		pnXem.setLayout(null);

		JLabel lblXem = new JLabel("Xem");
		lblXem.setBackground(Color.LIGHT_GRAY);
		lblXem.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblXem.setHorizontalAlignment(SwingConstants.CENTER);
		lblXem.setBounds(0, 0, 215, 81);
		pnXem.add(lblXem);

		JPanel pnImport = new JPanel();
		pnImport.addMouseListener(new PanelButtonMouseAdapter(pnImport, pnCardLayout));
		pnImport.setName("pnImport");
		pnImport.setBackground(Color.LIGHT_GRAY);
		pnImport.setBounds(0, 150, 215, 81);
		pnDashboard.add(pnImport);
		pnImport.setLayout(null);

		JLabel lblImportLop = new JLabel("Import lớp");
		lblImportLop.setBackground(Color.LIGHT_GRAY);
		lblImportLop.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblImportLop.setHorizontalAlignment(SwingConstants.CENTER);
		lblImportLop.setBounds(0, 0, 215, 81);
		pnImport.add(lblImportLop);

		JLabel lblPic = new JLabel();
		lblPic.setBounds(34, 40, 145, 100);
		pnDashboard.add(lblPic);

		Image image = myPicture.getScaledInstance(lblPic.getWidth(), lblPic.getHeight(), Image.SCALE_SMOOTH);
		lblPic.setIcon(new ImageIcon(image));
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 215, 30);
		pnDashboard.add(toolBar);
		
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
		
		
		PnImportLop pnImportLop = new PnImportLop();
		pnCardLayout.add(pnImportLop, "pnImportLop");
		
		PnChinhSuaDiem pnChinhSuaDiem = new PnChinhSuaDiem();
		pnCardLayout.add(pnChinhSuaDiem, "pnChinhSuaDiem");
		
		PnChinhSuaMonHoc pnChinhSuaMonHoc = new PnChinhSuaMonHoc();
		pnCardLayout.add(pnChinhSuaMonHoc, "pnChinhSuaMonHoc");
		
		PnChinhSuaSinhVien pnChinhSuaSinhVien = new PnChinhSuaSinhVien();
		pnCardLayout.add(pnChinhSuaSinhVien, "pnChinhSuaSinhVien");
		
		PnXemThongTin pnXemThongTin = new PnXemThongTin();
		pnCardLayout.add(pnXemThongTin, "pnXemThongTin");
		
		

	}

	private class PanelButtonMouseAdapter extends MouseAdapter {
		JPanel panel;
		JPanel cardPanel;

		public PanelButtonMouseAdapter(JPanel panel, JPanel cardPanel) {
			this.panel = panel;
			this.cardPanel = cardPanel;
		}

		@Override
		public void mousePressed(MouseEvent e) {
			panel.setBackground(Color.CYAN);
			if (panel.getName().compareTo("pnImport") == 0) {
				card.show(cardPanel, "pnImportLop");
			}
			if (panel.getName().compareTo("pnDiem") == 0) {
				card.show(cardPanel, "pnChinhSuaDiem");
			}
			if (panel.getName().compareTo("pnMonHoc") == 0) {
				card.show(cardPanel, "pnChinhSuaMonHoc");
			}
			if (panel.getName().compareTo("pnSinhVien") == 0) {
				card.show(cardPanel, "pnChinhSuaSinhVien");
			}
			if (panel.getName().compareTo("pnXem") == 0) {
				card.show(cardPanel, "pnXemThongTin");
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			panel.setBackground(Color.BLUE);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			panel.setBackground(Color.BLUE);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			panel.setBackground(Color.LIGHT_GRAY);
		}
	}
	private void btnDangXuatClicked() {
		if (JOptionPane.showConfirmDialog(this, "Bạn có muốn đăng xuất?") == JOptionPane.YES_OPTION) {
			cardMain.show(contentPane, "pnDangNhap");
		}
	}
	
	private void btnDoiMatKhauClicked() {
		JFrame doiMatKhau = new FrameDoiMatKhau();
		doiMatKhau.setVisible(true);
	}
}
