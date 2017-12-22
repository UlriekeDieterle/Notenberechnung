package notenberechnung.shared.bo;

public class Modulbelegung extends BusinessObject{

	private static final long serialVersionUID = 1L;
	
	private int belegung = 0;
	private double note = 0;
	private int matrikelnr = 0;
	private int edvnr = 0;
	
	public void setBelegungsnr(int bnr) {
		this.belegung = bnr;
	}

	public void setNote(double n) {
		this.note = n;
	}

	public void setMatrikelnummerFK(int mfk) {
		this.matrikelnr = mfk;
	}

	public void setEDVNr(int efk) {
		this.edvnr = efk;
	}

	public int getBelegungsnr() {
		return this.belegung;
	}

	public double getNote() {
		return this.note;
	}

	public int getMatrikelnrFK() {
		return this.matrikelnr;
	}

	public int getEDVNrFK() {
		return this.edvnr;
	}
	
	@Override
	public String toString() {
	    return super.toString() + " " + this.belegung + " " + this.note + " " + this.matrikelnr + " " + this.edvnr;
	  }

}
