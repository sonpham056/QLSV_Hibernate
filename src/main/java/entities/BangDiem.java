package entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="bangdiem")
public class BangDiem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*@Id
	private String maSinhVien_CT;
	@Id
	private String maMonHoc_CT;*/
	
	private float gK;
	
	private float cK;
	
	private float khac;
	
	private float tong;
	
	@Id
	@ManyToOne
	@JoinColumn(name="maSinhVien_CT")
	private SinhVien sinhVien;
	
	@Id
	@ManyToOne
	@JoinColumn(name="maMonHoc_CT")
	private MonHoc monHoc;
	
	public BangDiem() {}
	
	public BangDiem(SinhVien s, MonHoc m) {
		this.sinhVien = s;
		this.monHoc = m;
	}
	
	/*public String getMaSinhVien_CT() {
		return maSinhVien_CT;
	}

	public void setMaSinhVien_CT(String maSinhVien_CT) {
		this.maSinhVien_CT = maSinhVien_CT;
	}

	public String getMaMonHoc_CT() {
		return maMonHoc_CT;
	}

	public void setMaMonHoc_CT(String maMonHoc_CT) {
		this.maMonHoc_CT = maMonHoc_CT;
	}*/

	public float getgK() {
		return gK;
	}

	public void setgK(float gK) {
		this.gK = gK;
	}

	public float getcK() {
		return cK;
	}

	public void setcK(float cK) {
		this.cK = cK;
	}

	public float getKhac() {
		return khac;
	}

	public void setKhac(float khac) {
		this.khac = khac;
	}

	public float getTong() {
		return tong;
	}

	public void setTong(float tong) {
		this.tong = tong;
	}

	public SinhVien getSinhVien() {
		return sinhVien;
	}

	public void setSinhVien(SinhVien sinhVien) {
		this.sinhVien = sinhVien;
	}

	public MonHoc getMonHoc() {
		return monHoc;
	}

	public void setMonHoc(MonHoc monHoc) {
		this.monHoc = monHoc;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
