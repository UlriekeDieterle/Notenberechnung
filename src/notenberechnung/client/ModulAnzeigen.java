package notenberechnung.client;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

import notenberechnung.shared.NotenberechnungAdministrationAsync;
import notenberechnung.shared.bo.*;

public class ModulAnzeigen extends BasicFrame {
	NotenberechnungAdministrationAsync notenVerwaltung = ClientsideSettings.getNotenberechnungVerwaltung();
	Student student = ClientsideSettings.getCurrentUser();
	Modul modul = new Modul();
	
	private VerticalPanel verticalpanel = new VerticalPanel();
	
	public ModulAnzeigen() {
		this.add(verticalpanel);
		final Label modulLabel = new Label();
		final Label infoLabel = new Label();
		final FlexTable flextable = new FlexTable();
		
		flextable.setText(0, 0, "Modul");
		flextable.setText(0, 1, "EDV Nummer");
		flextable.setText(0, 2, "ECTS");
		flextable.setText(0, 3, "Modulname");
		flextable.setText(0, 4, "Modulverantwortlicher");
		flextable.setText(0, 5, "Zeitpunkt der Leistungserbringung");
		flextable.setText(0, 6, "Beschreibung");
		
		flextable.setStyleName("flextable");
		modulLabel.setStyleName("Label-Style");
		
		notenVerwaltung.findModulById(modul.getId(), new AsyncCallback<Vector<Modul>> () {
			@Override
			public void onFailure(Throwable caught) {
				infoLabel.setText("Fehler");
			}
			
			@Override
			public void onSuccess(Vector<Modul> result) {
				int row = flextable.getRowCount();
				for(Modul m : result) {
					row++;
					
					final String modulID = String.valueOf(m.getId());
					flextable.setText(1, 0, String.valueOf(m.getId()));
					flextable.setText(1, 1, String.valueOf(m.getECTS()));
					flextable.setText(1, 2, m.getModulTitel());
					flextable.setText(1, 3, m.getVerantwortlicher());
					flextable.setText(1, 4, m.getZeitpunkt());
					flextable.setText(1, 5, m.getBeschreibung());					
				}
				
				/*TextBox edvNummer = new TextBox();
				TextBox ects = new TextBox();
				TextBox modultitel = new TextBox();
				TextBox verantwortlicher = new TextBox();
				TextBox zeitpunkt = new TextBox();
				TextBox beschreibung = new TextBox();
				
				
				flextable.setWidget(1, flextable.getCellCount(row), widget);
				
				final Button hinzufuegenBtn = new Button("Modul hinzufügen");
				flextable.setWidget(row, 6, hinzufuegenBtn);
				
				hinzufuegenBtn.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						//for(int i = 5; i <= flextable.getRowCount(); i++) {
						//	String test = flextable.getText(i, 0);
							
						//	if (Integer.valueOf(test) == Integer.valueOf(modulID)){
								ClientsideSettings.getNotenberechnungVerwaltung().deleteModulbelegung(student.getId(),
										modul.getId(), new AsyncCallback<Void>() {

											@Override
											public void onFailure(Throwable caught) {
												infoLabel.setText("Fehler beim hinzufügen des Moduls.");
											}

											@Override
											public void onSuccess(Void result) {
												
											}
									
								});
						//	}
						//}
					}
				});*/
			}
		});
		
	}

	@Override
	protected String getHeadlineText() {
		return "Hier kannst du die Module verwalten";
	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
}
