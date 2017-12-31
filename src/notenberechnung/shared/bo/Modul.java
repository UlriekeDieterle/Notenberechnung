package notenberechnung.shared.bo;

public class Modul extends BusinessObject {

	private static final long serialVersionUID = 1L;

	private double ects = 0;
	private int id = 0;
	private String titel = "";
	private String verantwortl = "";
	private String zeitp = "";
	private String beschreibung = "";
	

	public void setId(int id) {
		this.id = id;
	}

	public void setECTS(double d) {
		this.ects = d;
	}

	public void setModulTitel(String m) {
		this.titel = m;
	}

	public void setVerantwortlicher(String v) {
		this.verantwortl = v;
	}

	public void setZeitpunkt(String z) {
		this.zeitp = z;
	}

	public void setBeschreibung(String b) {
		this.beschreibung = b;
	}

	//----------------------------------------------------------------------------------------------------------------------------
	public int getId() {
		return this.id;
	}

	public int getECTS() {
		return this.ects;
	}

	public String getModulTitel() {
		return this.titel;
	}

	public String getVerantwortlicher() {
		return this.verantwortl;
	}

	public String getZeitpunkt() {
		return this.zeitp;
	}

	public String getBeschreibung() {
		return this.beschreibung;
	}

		
	@Override
	public String toString() {
	    return super.toString() + " " + this.id + " " + this.ects + " " + this.titel + " " + this.verantwortl + " " + this.zeitp + " " + this.beschreibung;
	  }
}
