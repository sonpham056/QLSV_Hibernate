package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name="sinhvien")
public class SinhVien implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String maSinhVien;
	
	private String hoVaTen;
	
	private String gioiTinh;
	
	private String cmnd;
	
	@ManyToOne
	@JoinColumn(name="maLop_SinhVien")
	private Lop lop;
	
	@OneToMany(mappedBy = "sinhVien")
	private List<BangDiem> bangDiems;
	
	@OneToOne(mappedBy = "sinhVien")
	private TaiKhoan taiKhoan;
	
	public SinhVien() {}

	public String getMaSinhVien() {
		return maSinhVien;
	}

	public void setMaSinhVien(String maSinhVien) {
		this.maSinhVien = maSinhVien;
	}

	public String getHoVaTen() {
		return hoVaTen;
	}

	public void setHoVaTen(String hoVaTen) {
		this.hoVaTen = hoVaTen;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getCmnd() {
		return cmnd;
	}

	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}

	public Lop getLop() {
		return lop;
	}

	public void setLop(Lop lop) {
		this.lop = lop;
	}


	public List<BangDiem> getBangDiems() {
		return bangDiems;
	}

	public void setBangDiems(List<BangDiem> bangDiems) {
		this.bangDiems = bangDiems;
	}

	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
