package notenberechnung.shared.bo;

import java.util.Date;

import com.google.gwt.safehtml.shared.SafeUri;

public class Student extends BusinessObject {

	private static final long serialVersionUID = 1L;
	private int id = 0;
	private Date birthday;
	private String firstName = "";
	private String lastName = "";
	private String email = "";
	private String kuerzel = "";
	private String study = "";
	
	private boolean loggedIn = false;
	private String loginUrl = "";
	private String logoutUrl = "";
	private boolean isCreated = false;

	public void setId(int id1) {
		this.id = id1;
	}

	// bei Set-Methoden immer Überschreibung der Attribute machen, also das was der Methode übergeben wurde, der lokalen Variablen 
	// hier übergeben, damit es hier bekannt ist und von get benutzt werden kann 
	public void setFirstName(String firstname) {
		this.firstName = firstname;
	}

	public void setLastName(String lastname) {
		this.lastName = lastname;
	}

	public void setEmail(String e) {
		this.email = e;
	}

	public void setBirthday(Date birthd) {
		this.birthday = birthd;
	}

	public void setKuerzel(String kuerzl) {
		this.kuerzel = kuerzl;
	}

	public void setStudies(String studies) {
		this.study = studies;
	}
// damit hier int statt standardmässig String steht, muss "return this.id" eingegeben werden und oben die Variable id bekannt gegeben werden
	public int getId() {
		return this.id;
	}

	// Die get-Methoden geben einfach den Wert zurück, der in den set-Methoden an die lokalen Variablen übergeben wurde
	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public String getEmail() {
		return this.email;
	}

	public String getKuerzel() {
		return this.kuerzel;
	}

	public String getStudies() {
		return this.study;
	}

	@Override
	public String toString() {
	    return super.toString() + " " + this.id + " " + this.firstName + " " + this.lastName + " " + this.birthday + " " + this.kuerzel + " " + this.study;
	  }

	public void setLoggedIn(boolean b) {
		this.loggedIn = b;
	}

	public void setLogoutUrl(String logoutURL) {
		this.logoutUrl = logoutURL;
	}

	public void setLoginUrl(String loginURL) {
		this.loginUrl = loginURL;		
	}
	
	public String getLoginUrl() {
		return loginUrl;
	}

	public String getLogoutUrl() {
		return logoutUrl;
	}
	
	@Override
	public boolean equals (Object o) {
		if (o != null && o instanceof Student) {
			Student s = (Student) o;
			try {
				return super.equals(s);
			} catch (IllegalArgumentException e) {
				return false;
			}
		} return false;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public boolean isCreated() {
		return isCreated;
	}

	

}
