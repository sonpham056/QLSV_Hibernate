package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name="lop")
public class Lop implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String maLop;
	
	@OneToMany(mappedBy = "lop")
	private List<SinhVien> sinhViens;
	
	@OneToMany(mappedBy = "lop")
	private List<CTMonHoc> cTMonHocs;

	public Lop() {}
	
	public Lop(String maLop) {
		this.maLop = maLop;
	}
	
	public String getMaLop() {
		return maLop;
	}

	public void setMaLop(String maLop) {
		this.maLop = maLop;
	}

	public List<SinhVien> getSinhViens() {
		return sinhViens;
	}

	public void setSinhViens(List<SinhVien> sinhViens) {
		this.sinhViens = sinhViens;
	}

	public List<CTMonHoc> getcTMonHocs() {
		return cTMonHocs;
	}

	public void setcTMonHocs(List<CTMonHoc> cTMonHocs) {
		this.cTMonHocs = cTMonHocs;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return maLop;
	}
	
}
