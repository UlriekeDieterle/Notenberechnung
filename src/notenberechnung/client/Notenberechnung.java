package notenberechnung.client;

import com.google.gwt.core.client.*;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

import notenberechnung.server.db.StudentMapper;


	/**
	 * Diese Klasse implementiert den Entry Point. Ueber diesen kann sich der
	 * Benutzer anmelden und gelangt so auf die Partnerboerse.
	 */
public class Notenberechnung implements EntryPoint {

	/**
	 * 
	   * Da diese Klasse die Implementierung des Interface <code>EntryPoint</code>
	   * zusichert, benötigen wir eine Methode
	   * <code>public void onModuleLoad()</code>. Diese ist das GWT-Pendant der
	   * <code>main()</code>-Methode normaler Java-Applikationen.
	   
	 */
	public void onModuleLoad() { 
		createLoginScreen();
	 } 
	
	 private void createLoginScreen() {
	      
	      //CSS Stylename für die entsprechenden Divs setzen und mit Texten füllen
	      FlowPanel splashContaiern = new FlowPanel();
	      splashContaiern.setStyleName("splash-container");
	      
	      FlowPanel splash = new FlowPanel();
	      splash.setStyleName("splash");

	      HTML headingElement = new HTML();
	      headingElement.setHTML("Wenn Sie ein Fahrzeug reservieren möchten, müssen Sie sich anmelden!");
	      headingElement.setStyleName("splash-head");

	      FlowPanel splashSubhead = new FlowPanel(ParagraphElement.TAG);
	      splashSubhead.setStyleName("splash-subhead");
	      HTML splahParagraph = new HTML("Melden Sie sich hier an:");
	      splashSubhead.add(splahParagraph);

	      Anchor loginAnchor = new Anchor("Anmeldung");
	      loginAnchor.setStyleName("pure-button-login pure-button-primary-login");

	      FlexTable ft = new FlexTable();
	      ft.setText(0, 0, "upper-left corner");
	      ft.setText(2, 2, "bottom-right corner");

	      // Let's put a button in the middle...
	      ft.setWidget(1, 0, new Button("Wide Button"));
	      ft.setWidget (0, 1, new Button ("Alle FAhrzeuge anzeigen"));

	      // ...and set it's column span so that it takes up the whole row.
	      ft.getFlexCellFormatter().setColSpan(1, 0, 3);

	      RootPanel.get().add(ft);

	      //Divs und Anchor zum Div hinzufügen
	      splash.add(headingElement);
	      splash.add(splashSubhead);
	      splash.add(loginAnchor);
	      
	      splashContaiern.add(splash);
	       
	    }

}
