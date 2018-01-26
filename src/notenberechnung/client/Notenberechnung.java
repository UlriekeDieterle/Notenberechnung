package notenberechnung.client;

import com.google.gwt.core.client.*;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

import notenberechnung.shared.NotenberechnungAdministrationAsync;
import notenberechnung.shared.bo.Student;


	/**
	 * Diese Klasse implementiert den Entry Point. Ueber diesen kann sich der
	 * Benutzer anmelden und gelangt so auf die Notenberechnung.
	 */
public class Notenberechnung implements EntryPoint {

	
	NotenberechnungAdministrationAsync notenVerwaltung = ClientsideSettings.getNotenberechnungVerwaltung();
	
	/**
	 * 
	   * Da diese Klasse die Implementierung des Interface <code>EntryPoint</code>
	   * zusichert, benötigen wir eine Methode
	   * <code>public void onModuleLoad()</code>. Diese ist das GWT-Pendant der
	   * <code>main()</code>-Methode normaler Java-Applikationen.
	   
	 */
	public void onModuleLoad() { 
		notenVerwaltung.login(GWT.getHostPageBaseURL() + "Notenberechnung.html", new LoginCallback());
		
		//createLoginScreen();
	 } 
	
	class LoginCallback implements AsyncCallback<Student> {
		@Override
	    public void onFailure(Throwable caught) {
	      ClientsideSettings.getLogger().severe("Login fehlgeschlagen!");
	    }
		
		@Override
		public void onSuccess(Student result) {
			ClientsideSettings.getCurrentUser();
			if (result.isLoggedIn() && !result.isCreated()) {
		        ClientsideSettings.getLogger().info("Erstelle Profil für " + result.getEmail());
		        profilErstellen();
		}else if (result.isLoggedIn()) {
	        ClientsideSettings.getLogger().info("Lade vorhandenes Profil");
	        loadProfil();

	      } else {
	        createLoginScreen(result);
	      }
			
		}	
	}
	
	public void createLoginScreen(Student result) {
		 FlowPanel splashContainer = new FlowPanel();
	      splashContainer.setStyleName("splash-container");
	      
	      FlowPanel splash = new FlowPanel();
	      splash.setStyleName("splash");
	      
	      HTML headingElement = new HTML();
	      headingElement.setHTML("Willkommen bei der Notenberechnung");
	      headingElement.setStyleName("splash-head");

	      FlowPanel splashSubhead = new FlowPanel(ParagraphElement.TAG);
	      splashSubhead.setStyleName("splash-subhead");
	     // HTML splahParagraph = new HTML("Melde dich an und finde deine bessere Hälfte");
	     // splashSubhead.add(splahParagraph);

	      Anchor loginAnchor = new Anchor("Los!");
	      loginAnchor.setStyleName("pure-button-login pure-button-primary-login");
	      loginAnchor.setHref(result.getLoginUrl());

	      //Divs und Anchor zum Div hinzufügen
	      splash.add(headingElement);
	      splash.add(splashSubhead);
	      splash.add(loginAnchor);
	      
	      splashContainer.add(splash);
	      RootPanel.get("main").add(splashContainer);
	}
	
	private void profilErstellen() {
	      BasicFrame cf = new CreateStudentAccount();
	      RootPanel.get("main").clear();
	      RootPanel.get("main").add(cf);
	    }
	
	private void loadProfil() {
	      Navbar nb = new Navbar();
	      RootPanel.get("menu").clear();
	      RootPanel.get("menu").add(nb);

	      ShowStudent sp = new ShowStudent();
	      RootPanel.get("main").add(sp);
	    }
}
