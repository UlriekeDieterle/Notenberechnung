package notenberechnung.client.gui;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

public class ProfilAttribute extends BoxPanel{

	public ProfilAttribute(String text) {
		super(text);
		// TODO Auto-generated constructor stub
	}

	
	public void createProfilFormular(){
	TextBox vorname = new TextBox();
	TextBox nachname = new TextBox();
	TextBox matrikelnummer = new TextBox();
	TextBox kuerzel = new TextBox();
	TextBox email = new TextBox();
	TextBox studiengang = new TextBox();
	
	FlexTable profilAttribute = new FlexTable();
	profilAttribute.setText(0, 0, "Matrikelnummer");
	profilAttribute.setText(1, 0, "Vorname");
	profilAttribute.setText(2, 0, "Nachname");
	profilAttribute.setText(3, 0, "Kürzel");
	profilAttribute.setText(4, 0, "Email");
	profilAttribute.setText(5, 0, "Studiengang");
	
	profilAttribute.setWidget(0, 1, matrikelnummer);
	profilAttribute.setWidget(1, 1, vorname);
	profilAttribute.setWidget(2, 1, nachname);
	profilAttribute.setWidget(3, 1, kuerzel);
	profilAttribute.setWidget(4, 1, email);
	profilAttribute.setWidget(5, 1, studiengang);
	
	RootPanel.get("main").clear();
	RootPanel.get("main").add(profilAttribute);
	}

}
