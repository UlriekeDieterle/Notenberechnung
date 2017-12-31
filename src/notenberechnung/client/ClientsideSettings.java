package notenberechnung.client;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;

import notenberechnung.shared.CommonSettings;
import notenberechnung.shared.NotenberechnungAdministration;
import notenberechnung.shared.NotenberechnungAdministrationAsync;
import notenberechnung.shared.bo.Student;

public class ClientsideSettings extends CommonSettings{

	private static NotenberechnungAdministrationAsync notenVerwaltung = null;
	
	private static Student currentUser = null;
	
	private static final String LOGGER_NAME = "Notenberechnung Web Client";
	
	private static final Logger log = Logger.getLogger(LOGGER_NAME);
	
	 public static Logger getLogger() {
		    return log;
	}
	 
	 
	 public static NotenberechnungAdministrationAsync getNotenberechnungVerwaltung() {
		  
		  /*
		   * Wenn es bisher noch keine partnerboerseVerwaltungs-Instanz gab, wird hiermit nun
		   * eine erzeugt.
		   */
	    if (notenVerwaltung == null) {
	      	/*
	    	 * In diesem Schritt wird die PartnerboerseAdministration instantiiert.
	    	 */
	      notenVerwaltung = GWT.create(NotenberechnungAdministration.class);
	    }

	    /*
	     * Die PartnerboerseVerwaltung wird zurückgegeben.
	     */
	    return notenVerwaltung;
	  }
	 
	 public static Student getCurrentUser() {
		    return currentUser;
		  }

	public static void setCurrentUser(Student currentUser) {
		ClientsideSettings.currentUser = currentUser;		
	}

}
