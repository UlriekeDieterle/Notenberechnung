package notenberechnung.client.gui;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

import notenberechnung.client.BasicFrame;
import notenberechnung.client.ClientsideSettings;
import notenberechnung.shared.NotenberechnungAdministrationAsync;
import notenberechnung.shared.bo.Student;

public class Startseite extends HorizontalPanel {
	NotenberechnungAdministrationAsync notenVerwaltung = ClientsideSettings.getNotenberechnungVerwaltung();
	Student student = ClientsideSettings.getCurrentUser();
	
	private Label heading = new Label ("Willkommmen");
	private VerticalPanel verticalpanel = new VerticalPanel();
	private HorizontalPanel horizontalpanel = new HorizontalPanel();
	
	private FlexTable startseiteFlexTable = new FlexTable();
	private FlexTable zeigeProfilAn = new FlexTable();
	private Label infoLabel = new Label();
	
	public void ladeStartseite () {
		this.add(horizontalpanel);
		horizontalpanel.add(verticalpanel);
		
		//BasicFrame navbar = new BasicFrame();
		//navbar.run();
		
		startseiteFlexTable.setText(0, 0, "Matrikelnummer: ");
		startseiteFlexTable.setText(1, 0, "Vorname: ");
		startseiteFlexTable.setText(2, 0, "Nachname: ");
		startseiteFlexTable.setText(3, 0, "Geburtstag: ");
		startseiteFlexTable.setText(4, 0, "Email: ");
		startseiteFlexTable.setText(5, 0, "Kürzel: ");
		startseiteFlexTable.setText(6, 0, "Studiengang: ");
		
		startseiteFlexTable.setStyleName("flextable");
		zeigeProfilAn.setStyleName("flextable");
		
		ClientsideSettings.getNotenberechnungVerwaltung().getStudentByID(student.getId(), new AsyncCallback<Student>(){
			
			@Override
			public void onFailure (Throwable caught) {
				infoLabel.setText("Es trat ein Fehler auf");
			}
			
			@Override
			public void onSuccess (Student student) {
				startseiteFlexTable.setText(0, 1, String.valueOf(student.getId()));
				startseiteFlexTable.setText(1, 1, student.getFirstName());
				startseiteFlexTable.setText(2, 1, student.getLastName());
				startseiteFlexTable.setText(3, 1, String.valueOf(student.getBirthday()));
				startseiteFlexTable.setText(4, 1, student.getEmail());
				startseiteFlexTable.setText(5, 1, student.getKuerzel());
				startseiteFlexTable.setText(6, 1, student.getStudies());		
			}
			
		});
		
		verticalpanel.add(startseiteFlexTable);
		verticalpanel.add(infoLabel);
		horizontalpanel.add(verticalpanel);
		RootPanel.get("Profil").add(horizontalpanel);
		RootPanel.get("Container").add(heading);
		
		
		
		
	}
	
	
	
}
