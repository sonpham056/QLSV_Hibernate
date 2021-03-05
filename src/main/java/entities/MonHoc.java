package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name="monhoc")
public class MonHoc implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String maMonHoc;
	
	private String tenMonHoc;
	
	@OneToMany(mappedBy = "monHoc")
	private List<CTMonHoc> cTMonHocs;
	
	@OneToMany(mappedBy = "monHoc")
	private List<BangDiem> bangDiems;

	public MonHoc() {}
	
	public String getMaMonHoc() {
		return maMonHoc;
	}

	public void setMaMonHoc(String maMonHoc) {
		this.maMonHoc = maMonHoc;
	}

	public String getTenMonHoc() {
		return tenMonHoc;
	}

	public void setTenMonHoc(String tenMonHoc) {
		this.tenMonHoc = tenMonHoc;
	}

	public List<CTMonHoc> getcTMonHocs() {
		return cTMonHocs;
	}

	public void setcTMonHocs(List<CTMonHoc> cTMonHocs) {
		this.cTMonHocs = cTMonHocs;
	}

	public List<BangDiem> getBangDiems() {
		return bangDiems;
	}

	public void setBangDiems(List<BangDiem> bangDiems) {
		this.bangDiems = bangDiems;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return tenMonHoc;
	}
	
}
