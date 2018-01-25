package notenberechnung.client;

import java.util.Vector;

import com.google.gwt.user.client.ui.CustomButton;
import com.google.gwt.user.client.ui.FlowPanel;

import de.superteam2000.gwt.client.Sperre.EntfernenButtonClickhandler;
import de.superteam2000.gwt.client.Sperre.KontaktsperreForProfilCallback;
import de.superteam2000.gwt.client.Sperre.SelectionChangeHandler;
import notenberechnung.shared.NotenberechnungAdministrationAsync;
import notenberechnung.shared.bo.*;

public class ModulbelegungUndBerechnung extends BasicFrame {

	NotenberechnungAdministrationAsync notenVerwaltung = ClientsideSettings.getNotenberechnungVerwaltung();
	
	Student student = ClientsideSettings.getCurrentUser();
	 CustomButton belegungEntfernen = new CustomButton("Modulbelegung entfernen ");
	 CustomButton modulHinzufuegen = new CustomButton("Modul hinzufügen");
	 CustomButton modulEntfernen = new CustomButton("Modul entfernen");
	 CustomButton belegungHinzufuegen = new CustomButton("Modulbelegung hinzufügen");
	 CustomButton durchschnittBerechnen = new CustomButton("Durchschnitt berechnen");
	 CustomButton ectsAnzeigen = new CustomButton("erreichte ECTS anzeigen");
	 
	 Vector<Modul> module = new Vector<Modul>();
	 Vector<Modulbelegung> modulbelegungen = new Vector<Modulbelegung>();
	 
	 FlowPanel alignpanel = new FlowPanel();
	 FlowPanel contentpanel = new FlowPanel();
	
	 @Override
	  public String getHeadlineText() {
	    return "Module und Modulbelegungen";
	  }
	 
	 @Override
	 public String getSubHeadlineText() {
		 return "Hier kannst du deine Module ansehen, die Belegungen ändern und deinen Durchschnitt berechnen lassen";
	 }
	 
	 
	 @Override
	 public void run() {
		 belegungEntfernen.setEnabled(true);
		 modulHinzufuegen.setEnabled(true);
		 modulEntfernen.setEnabled(true);
		 belegungHinzufuegen.setEnabled(true);
		 durchschnittBerechnen.setEnabled(true);
		 ectsAnzeigen.setEnabled(true);
		 
		 alignpanel.setStyleName("pure-form pure-form-aligned content");
		 alignpanel.add(modulHinzufuegen);
		 alignpanel.add(modulEntfernen);
		 alignpanel.add(belegungHinzufuegen);
		 alignpanel.add(belegungEntfernen);
		 alignpanel.add(durchschnittBerechnen);
		 alignpanel.add(ectsAnzeigen);
		 contentpanel.add(alignpanel);
		 
		 modulHinzufuegen.addClickHandler(new ModulHinzufuegenButtonClickhandler());
		 modulEntfernen.addClickHandler(new ModulEntfernenButtonClickhandler());
		 belegungHinzufuegen.addClickHandler(new BelegungHinzufuegenButtonClickHandler());
		 belegungEntfernen.addClickHandler(new BelegungEntfernenButtonClickhandler());
		 durchschnittBerechnen.addClickHandler(new DurchschnittBerechnenButtonClickHandler());
		 ectsAnzeigen.addClickHandler(new ECTSAnzeigenButtonClickHandler());
		 
		 //Selection Model / electionModel.addSelectionChangeHandler(new SelectionChangeHandler());

		 //pbVerwaltung.getKontaktsperreForProfil(user, new KontaktsperreForProfilCallback());
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
	 }
}
