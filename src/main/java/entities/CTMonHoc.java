package entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="ctmonhoc")
public class CTMonHoc implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	private String maMonHoc_CT;
	@Id
	private String maLop_CT;
	
	private String phongHoc;
	
	@ManyToOne
	@JoinColumn(name="maLop_CT", updatable = false, insertable = false)
	private Lop lop;
	
	@ManyToOne
	@JoinColumn(name="maMonHoc_CT", updatable = false, insertable = false)
	private MonHoc monHoc;
	
	

	public CTMonHoc(String maMonHoc_CT, String maLop_CT, String phongHoc) {
		super();
		this.maMonHoc_CT = maMonHoc_CT;
		this.maLop_CT = maLop_CT;
		this.phongHoc = phongHoc;
	}
	
	public CTMonHoc() {}

	public String getMaMonHoc_CT() {
		return maMonHoc_CT;
	}

	public void setMaMonHoc_CT(String maMonHoc_CT) {
		this.maMonHoc_CT = maMonHoc_CT;
	}

	public String getMaLop_CT() {
		return maLop_CT;
	}

	public void setMaLop_CT(String maLop_CT) {
		this.maLop_CT = maLop_CT;
	}

	public String getPhongHoc() {
		return phongHoc;
	}

	public void setPhongHoc(String phongHoc) {
		this.phongHoc = phongHoc;
	}

	public Lop getLop() {
		return lop;
	}

	public void setLop(Lop lop) {
		this.lop = lop;
	}

	public MonHoc getMonHoc() {
		return monHoc;
	}

	public void setMonHoc(MonHoc monHoc) {
		this.monHoc = monHoc;
	}

}
