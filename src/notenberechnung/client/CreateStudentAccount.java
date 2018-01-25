package notenberechnung.client;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import de.superteam2000.gwt.client.CreateProfil.GetAllAuswahlProfilAttributeCallBack;
import de.superteam2000.gwt.client.CreateProfil.GetAllBeschreibungProfilAttributeCallBack;
import de.superteam2000.gwt.client.gui.BoxPanel;
import de.superteam2000.gwt.shared.bo.Auswahl;
import notenberechnung.client.gui.GeburtstagsListbox;
import notenberechnung.shared.NotenberechnungAdministrationAsync;
import notenberechnung.shared.bo.Student;

public class CreateStudentAccount extends BasicFrame {

	NotenberechnungAdministrationAsync notenVerwaltung = ClientsideSettings.getNotenberechnungVerwaltung();
	Student user = ClientsideSettings.getCurrentUser();
	Logger logger = ClientsideSettings.getLogger();
	
	FlowPanel contentpanel = new FlowPanel();
	FlowPanel alignpanel = new FlowPanel();
	
	GeburtstagsListbox gebTag = null;
	
	Button confirmBtn = null;
	
	@Override
	protected String getHeadlineText() {
		return "Studentenkonto anlegen";
	}

	@Override
	protected String getSubHeadlineText() {
		return ("Fülle die Felder aus und lege dein Konto gleich an!");
	}
	
	// Hier fehlt noch was... getCurrentUser ist leer, hier sollte ein Studenten-Objekt übergeben werden0
	@Override
	protected void run() {
		contentpanel.setStyleName("content");
		alignpanel.setStyleName("pure-form pure-form-aligned");
		
		gebTag = new GeburtstagsListbox();
	    gebTag.createGebtagListbox("Was ist dein Geburtstag?");
	    gebTag.setEnable(true);
		
	    confirmBtn = new Button("Weiter");
		confirmBtn.setStyleName("pure-button pure-button-primary");
				
		confirmBtn.addClickHandler(new ConfirmClickHandler());
		
		 notenVerwaltung.getAllAuswahlProfilAttribute(new GetAllAuswahlProfilAttributeCallBack());
		
		contentpanel.add(alignpanel);
		
		RootPanel.get("main").add(contentpanel);
	}
	
	 private class GetAllAuswahlProfilAttributeCallBack implements AsyncCallback<ArrayList<Auswahl>> {

		    @Override
		    public void onSuccess(ArrayList<Auswahl> result) {
		      for (Auswahl a : result) {
		        BoxPanel clb = new BoxPanel(a, false);
		        alignpanel.add(clb);
		      }

		      alignpanel.add(gebTag);
		      alignpanel.add(confirmBtn);
		    }

		    /**
		     * Um Fehler abzufangen.
		     */
		    @Override
		    public void onFailure(Throwable caught) {}
		  }
	
	 private class ConfirmClickHandler implements ClickHandler {

		    @Override
		    public void onClick(ClickEvent event) {
		      createStudent();

		    }
		  }
	
	class CreateStudentAccountCallback implements AsyncCallback<Student> {
		
		@Override
		public void onFailure(Throwable caught){
			logger.severe("Fehler beim erstellen des Kontos " + caught.getMessage());
		}
		
		@Override
		public void onSuccess (Student student) {
			//if(student != null) {
			for(Student s : result) {
				 BoxPanel clb = new BoxPanel(b, false);
			        alignpanel.add(clb);
			}
		}
	}
	
	
	
	class CreateStudentCallback implements AsyncCallback <Student> {
		@Override
	    public void onFailure(Throwable caught) {
	      logger.severe("Studentenkonto konnte nicht angelegt werden.");
	    }
		
		@Override
		public void onSuccess(Student s){
			s.setLogoutUrl(user.getLogoutUrl());
		    s.setLoggedIn(true);
		    
		    ClientsideSettings.setCurrentUser(s);
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
		String email = user.getEmail();
		int geburtsdatum = 0;
		String kuerzel = "";
		String studiengang = "";
		
		 for (Widget box : alignpanel) {
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
			 }
		 }
		
	}
	
	
}