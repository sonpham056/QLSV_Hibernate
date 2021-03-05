package view.chucNang;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import org.hibernate.Session;
import org.hibernate.Transaction;

import DAO.BangDiemDAO;
import DAO.CTMonHocDAO;
import DAO.LopDAO;
import DAO.MonHocDAO;
import DAO.SinhVienDAO;
import DAO.TaiKhoanDAO;
import entities.BangDiem;
import entities.CTMonHoc;
import entities.Lop;
import entities.MonHoc;
import entities.SinhVien;
import entities.TaiKhoan;
import net.bytebuddy.description.ModifierReviewable.OfAbstraction;
import util.HibernateUtil;

import java.awt.Font;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.List;
import java.awt.event.ActionEvent;

public class PnImportLop extends JPanel {

	/**
	 * Create the panel.
	 */
	public PnImportLop() {
		setBackground(SystemColor.activeCaption);
		setLayout(null);

		setBounds(0, 0, 585, 605);

		JLabel lblNewLabel = new JLabel("Import danh sách lớp");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(46, 119, 252, 25);
		add(lblNewLabel);

		JLabel lblImportDanhSch = new JLabel("Import thời khóa biểu");
		lblImportDanhSch.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblImportDanhSch.setBounds(46, 277, 252, 25);
		add(lblImportDanhSch);

		JLabel lblImportim = new JLabel("Import bảng điểm");
		lblImportim.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblImportim.setBounds(46, 448, 252, 25);
		add(lblImportim);

		JButton btnLop = new JButton("Import");
		btnLop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnImportLopClicked();
			}
		});
		btnLop.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnLop.setBounds(360, 106, 160, 51);
		add(btnLop);

		JButton btnTKB = new JButton("Import");
		btnTKB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnImportTKBClicked();
			}
		});
		btnTKB.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnTKB.setBounds(360, 264, 160, 51);
		add(btnTKB);

		JButton btnBangDiem = new JButton("Import");
		btnBangDiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnBangDiemClicked();
			}
		});
		btnBangDiem.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnBangDiem.setBounds(360, 435, 160, 51);
		add(btnBangDiem);

	}

	private void btnImportLopClicked() {
		FileDialog fileDialog = new FileDialog(new JFrame(), "choose a file", FileDialog.LOAD);
		fileDialog.setDirectory("D:\\Hoctap\\java_save_2\\LeanHibernate\\src\\main\\resources\\");
		fileDialog.setFile("*csv");
		fileDialog.setVisible(true);
		String path = fileDialog.getDirectory() + fileDialog.getFile();
		// JOptionPane.showMessageDialog(this, fileDialog.getDirectory() +
		// fileDialog.getFile());
		if (fileDialog.getFile() != null) {
			String[] str = fileDialog.getFile().split("-");
			int flag = 0;
			for (int i = 0; i < str.length; i++) {
				// JOptionPane.showMessageDialog(this, str[i]);
				if (str[i].compareTo("dssv.csv") == 0)
					flag = 1;
			}
			if (flag == 1) {

				try {
					FileInputStream inputStream = new FileInputStream(new File(path));
					InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
					BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

					// đọc tên lớp
					String[] maLopString = bufferedReader.readLine().split(",");
					String maLop = maLopString[0];
					Lop lop = LopDAO.layThongTinLop(maLop);
					if (lop != null) {
						String string = bufferedReader.readLine();
						string = bufferedReader.readLine();// skip 1 dong
						while (string != null) {
							// JOptionPane.showMessageDialog(this, string);
							String[] arr = string.split(",");

							SinhVien sinhVien = SinhVienDAO.layThongTinSinhVien(arr[1]);
							if (sinhVien == null) {
								sinhVien = new SinhVien();
								sinhVien.setMaSinhVien(arr[1]);
								sinhVien.setHoVaTen(arr[2]);
								sinhVien.setGioiTinh(arr[3]);
								sinhVien.setCmnd(arr[4]);
								sinhVien.setLop(lop);

								TaiKhoan taiKhoan = new TaiKhoan();
								taiKhoan.setTaiKhoan(sinhVien.getMaSinhVien());
								taiKhoan.setMatKhau(sinhVien.getMaSinhVien());

								SinhVienDAO.themMotSinhVien(sinhVien);
								TaiKhoanDAO.themMotTaiKhoan(taiKhoan);
							}

							string = bufferedReader.readLine();
						}
					} else {
						lop = new Lop();
						lop.setMaLop(maLop);

						LopDAO.themMotLop(lop);

						String string = bufferedReader.readLine();
						string = bufferedReader.readLine();// skip 1 dong
						while (string != null) {
							// JOptionPane.showMessageDialog(this, string);
							String[] arr = string.split(",");

							SinhVien sinhVien = SinhVienDAO.layThongTinSinhVien(arr[1]);
							if (sinhVien == null) {
								sinhVien = new SinhVien();
								sinhVien.setMaSinhVien(arr[1]);
								sinhVien.setHoVaTen(arr[2]);
								sinhVien.setGioiTinh(arr[3]);
								sinhVien.setCmnd(arr[4]);
								sinhVien.setLop(lop);

								SinhVienDAO.themMotSinhVien(sinhVien);
							}

							string = bufferedReader.readLine();
						}
					}

					inputStream.close();
					inputStreamReader.close();
					bufferedReader.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn file có từ 'dssv' hoặc đổi tên file");
			}
		}
	}

	private void btnImportTKBClicked() {
		FileDialog fileDialog = new FileDialog(new JFrame(), "choose a file", FileDialog.LOAD);
		fileDialog.setDirectory("D:\\Hoctap\\java_save_2\\LeanHibernate\\src\\main\\resources\\");
		fileDialog.setFile("*csv");
		fileDialog.setVisible(true);
		String path = fileDialog.getDirectory() + fileDialog.getFile();
		if (fileDialog.getFile() != null) {
			String[] str = fileDialog.getFile().split("-");
			int flag = 0;
			for (int i = 0; i < str.length; i++) {
				if (str[i].compareTo("dsmh.csv") == 0)
					flag = 1;
			}
			if (flag == 1) {

				try {
					FileInputStream inputStream = new FileInputStream(new File(path));
					InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
					BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

					// đọc tên lớp
					String[] maLopString = bufferedReader.readLine().split(",");
					String maLop = maLopString[0];
					Lop lop = LopDAO.layThongTinLop(maLop);
					if (lop != null) {
						String string = bufferedReader.readLine();
						string = bufferedReader.readLine();// skip 1 dong
						if (SinhVienDAO.layDanhSachSVThuocLop(lop).size() == 0)
							JOptionPane.showMessageDialog(this, "Lớp " + lop.getMaLop() + " chưa có sinh viên nào");
						else {
							while (string != null) {
								// JOptionPane.showMessageDialog(this, string);
								String[] arr = string.split(",");
								MonHoc monHoc = MonHocDAO.layThongTinMonHoc(arr[1]); // 1 la ma, 2 la ten, 3 la phong
								if (monHoc == null) {
									monHoc = new MonHoc();
									monHoc.setMaMonHoc(arr[1]);
									monHoc.setTenMonHoc(arr[2]);

									MonHocDAO.themMotMonHoc(monHoc);
								}

								CTMonHoc ctMonHoc = CTMonHocDAO.layThongTinCTMH(monHoc.getMaMonHoc(), lop.getMaLop(),
										arr[3]);
								if (ctMonHoc == null) {
									ctMonHoc = new CTMonHoc();
									ctMonHoc.setMaMonHoc_CT(monHoc.getMaMonHoc());
									ctMonHoc.setMaLop_CT(lop.getMaLop());
									ctMonHoc.setPhongHoc(arr[3]);
									ctMonHoc.setLop(lop);
									ctMonHoc.setMonHoc(monHoc);

									CTMonHocDAO.themMotCTMH(ctMonHoc);
								}

								List<SinhVien> listSinhViens = SinhVienDAO.layDanhSachSVThuocLop(lop);
								for (int i = 0; i < listSinhViens.size(); i++) {
									BangDiem bangDiem = BangDiemDAO.layThongTinBangDiem(listSinhViens.get(i), monHoc);
									if (bangDiem == null) {
										bangDiem = new BangDiem(listSinhViens.get(i), monHoc);
										BangDiemDAO.themMotBangDiem(bangDiem);
									}
								}

								string = bufferedReader.readLine();
							}
						}
					} else {
						JOptionPane.showMessageDialog(this, "Không tìm thấy lớp");
					}

					inputStream.close();
					inputStreamReader.close();
					bufferedReader.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn file có từ 'dsmh' hoặc đổi tên file");
			}
		}
	}

	private void btnBangDiemClicked() {
		FileDialog fileDialog = new FileDialog(new JFrame(), "choose a file", FileDialog.LOAD);
		fileDialog.setDirectory("D:\\Hoctap\\java_save_2\\LeanHibernate\\src\\main\\resources\\");
		fileDialog.setFile("*csv");
		fileDialog.setVisible(true);
		String path = fileDialog.getDirectory() + fileDialog.getFile();
		if (fileDialog.getFile() != null) {
			String[] str = fileDialog.getFile().split("-");
	
			str[1] = str[1].split("[.]")[0];
			MonHoc monHoc = MonHocDAO.layThongTinMonHoc(str[1]);
			if (monHoc != null) {
				try {
					FileInputStream inputStream = new FileInputStream(new File(path));
					InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
					BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
					
					String readString = bufferedReader.readLine();
					readString = bufferedReader.readLine(); // pass 2 dòng đầu
					readString = bufferedReader.readLine(); // đọc dòng 3
					while (readString != null) {
						System.out.println(readString);
						String[] strings = readString.split(",");
						SinhVien sinhVien = SinhVienDAO.layThongTinSinhVien(strings[1]);
						if (sinhVien == null) {
							JOptionPane.showMessageDialog(this, "Không tồn tại sinh viên có mã số " + strings[1]);
							break;
						}
						BangDiem bangDiem = BangDiemDAO.layThongTinBangDiem(sinhVien, monHoc);
						if (bangDiem == null) {
							JOptionPane.showMessageDialog(this, "Sinh viên " + sinhVien.getHoVaTen() + " không học môn " + monHoc.getTenMonHoc());
						} else {
							bangDiem.setgK(Float.parseFloat(strings[3]));
							bangDiem.setcK(Float.parseFloat(strings[4]));
							bangDiem.setKhac(Float.parseFloat(strings[5]));
							bangDiem.setTong(Float.parseFloat(strings[6]));
							
							BangDiemDAO.capNhatMotBangDiem(bangDiem);
						}
						
						readString = bufferedReader.readLine();
					}
					
					
					
					inputStream.close();
					inputStreamReader.close();
					bufferedReader.close();					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			else {
				JOptionPane.showMessageDialog(this, "Lỗi file");
			}
		}
	}
}
