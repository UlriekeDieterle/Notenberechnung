package notenberechnung.client;

import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import notenberechnung.client.gui.GeburtstagsListbox;
import notenberechnung.client.gui.ProfilAttribute;
import notenberechnung.shared.NotenberechnungAdministrationAsync;
import notenberechnung.shared.bo.Student;

public class CreateStudentAccount extends BasicFrame {

	NotenberechnungAdministrationAsync notenVerwaltung = ClientsideSettings.getNotenberechnungVerwaltung();
	Student user = ClientsideSettings.getCurrentUser();
	Logger logger = ClientsideSettings.getLogger();
	
	FlowPanel contentpanel = new FlowPanel();
	FlowPanel alignpanel = new FlowPanel();
	
	//GeburtstagsListbox gebTag = null;
	
	Button confirmBtn = null;
	
	TextBox vorname = new TextBox();
	TextBox nachname = new TextBox();
	TextBox matrikelnummerTB = new TextBox();
	TextBox kuerzelTB = new TextBox();
	TextBox emailTB = new TextBox();
	TextBox studiengangTB = new TextBox();
	
	@Override
	protected String getHeadlineText() {
		return "Studentenkonto anlegen";
	}

	/*@Override
	protected String getSubHeadlineText() {
		return ("Fülle die Felder aus und lege dein Konto gleich an!");
	}*/
	
	// Hier fehlt noch was... getCurrentUser ist leer, hier sollte ein Studenten-Objekt übergeben werden0
	@Override
	protected void run() {
		contentpanel.setStyleName("content");
		alignpanel.setStyleName("pure-form pure-form-aligned");
		
		/*gebTag = new GeburtstagsListbox();
	    gebTag.createGebtagListbox("Was ist dein Geburtstag?");
	    gebTag.setEnable(true);*/
		
	    confirmBtn = new Button("Weiter");
		confirmBtn.setStyleName("pure-button pure-button-primary");
				
		confirmBtn.addClickHandler(new ConfirmClickHandler());
		
		 //notenVerwaltung.getProfilFormular(new GetProfilFormularCallBack());
		
		contentpanel.add(alignpanel);
		
		RootPanel.get("main").add(contentpanel);
	}
	
	/* private class GetProfilFormularCallBack implements AsyncCallback<Vector<Student>> {
		 FlexTable flexTable = new FlexTable();
		    @Override
		    public void onSuccess(Vector<Student> result) {
		     // for (Student s : result) {
		        flexTable = ProfilAttribute.createProfilFormular();
		        alignpanel.add(flexTable);
		     // }

		    //  alignpanel.add(gebTag);
		      alignpanel.add(confirmBtn);
		    }

		    /**
		     * Um Fehler abzufangen.
		     
		    @Override
		    public void onFailure(Throwable caught) {}
		  }*/
	
	 private class ConfirmClickHandler implements ClickHandler {

		    @Override
		    public void onClick(ClickEvent event) {
		      createStudent();

		    }
		  }
	
	class CreateStudentCallback implements AsyncCallback<Student> {
		
		@Override
		public void onFailure(Throwable caught){
			logger.severe("Fehler beim erstellen des Kontos " + caught.getMessage());
		}
		
		@Override
		public void onSuccess (Student student) {
			student.setLogoutUrl(student.getLogoutUrl());
		    student.setLoggedIn(true);
		    
		    ClientsideSettings.setCurrentUser(student);
		    ShowStudent ss = new ShowStudent();
		    Navbar nb = new Navbar();
		    RootPanel.get("main").clear();
		    RootPanel.get("menu").add(nb);
		    RootPanel.get("main").add(ss);
		}
	}
	
	public void createStudent() {
		String firstName = "";
		String lastName = "";
		int matrikelnummer = 0;
		String email = student.getEmail();
		//int geburtsdatum = 0;
		String kuerzel = "";
		String studiengang = "";
		
		matrikelnummer = Integer.valueOf(matrikelnummerTB.getText());
		firstName = vorname.getText();
		lastName = nachname.getText();
		email = emailTB.getText();
		kuerzel = kuerzelTB.getText();
		studiengang = studiengangTB.getText();
		
		if(!firstName.isEmpty() && !lastName.isEmpty()) {
			//pbVerwaltung.createProfil(lastName, firstName, email, gebTagSql, haarfarbe, raucher, religion,
//	          groesse, geschlecht, new CreateCustomerCallback());
			notenVerwaltung.createProfile(matrikelnummer, firstName, lastName, email, kuerzel, studiengang, new CreateStudentCallback());
		} else { System.out.println("Bitte füllen Sie alle wichtigen Felder aus.");}
		
		
		/* for (Widget box : alignpanel) {
			 if(box instanceof TextBox) {
				 TextBox tb = (TextBox) box;
				 logger.severe("Test " + tb.getName());
				 switch (tb.getName()) {
				 case "Vorname":
					 firstName = tb.getText();
					 break;
				 case "Nachname":
					 lastName = tb.getText();
					 break;
				 case "Studiengang":
					 studiengang = tb.getText();
					 break;
				 case "Kürzel":
					 kuerzel = tb.getText();
					 break;
				 }
			 }*/
		 }
	
	/*FlexTable profilAttribute = new FlexTable();
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
	profilAttribute.setWidget(5, 1, studiengang);*/
	
	
	}