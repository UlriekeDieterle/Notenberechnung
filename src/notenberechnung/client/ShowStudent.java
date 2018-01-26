package notenberechnung.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Logger;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.i18n.client.DateTimeFormat;


import notenberechnung.client.gui.BoxPanel;
import notenberechnung.client.gui.GeburtstagsListbox;
import notenberechnung.client.gui.Startseite;
import notenberechnung.shared.NotenberechnungAdministrationAsync;
import notenberechnung.shared.bo.Student;



public class ShowStudent extends BasicFrame{

	NotenberechnungAdministrationAsync notenVerwaltung = ClientsideSettings.getNotenberechnungVerwaltung();
	
	private VerticalPanel verticalpanel = new VerticalPanel();
	private HorizontalPanel buttonpanel = new HorizontalPanel();
	private HorizontalPanel horizontalpanel = new HorizontalPanel();
	
	private FlexTable editProfileTable = new FlexTable();
	private TextBox vorname = new TextBox();
	private TextBox nachname = new TextBox();
	private TextBox studiengang = new TextBox();
	private TextBox kuerzel = new TextBox();
	private DateTimeFormat geburtsdatumFormat = DateTimeFormat.getFormat("dd.MM.yyyy");
	private DateBox geburtstag = new DateBox();
	private IntegerBox matrikelnummer = new IntegerBox();
	private TextBox email = new TextBox();
	private Label geburtstagLabel = new Label();
	
	private Button saveBtn = new Button("Speichern");
	private Button editBtn = new Button("Bearbeiten");
	private Button cancelBtn = new Button ("Abbrechen");
	
	//BoxPanel clb = null;
	//FlowPanel buttonpanel = new FlowPanel();
	/*GeburtstagsListbox gebtag = null;
	
	FlowPanel alignpanel = new FlowPanel();
	FlowPanel contentpanel = new FlowPanel();*/
	
		
	Student student = ClientsideSettings.getCurrentUser();
	Logger logger = ClientsideSettings.getLogger();
	
	/*@Override
	public String getHeadlineText(){
		return ("Willkommen " + student.getFirstName() + "!");
	}
	
	@Override
	public String getSubHeadlineText(){
		return ("Hier kannst du deine Profileinstellungen verwalten.");
	}*/
	
	
	public ShowStudent() {
		this.add(verticalpanel);
		load();
	}
	
		
	public void load(){
		editProfileTable.setText(0, 0, "Matrikelnummer: ");
		editProfileTable.setText(1, 0, "Vorname: ");
		editProfileTable.setText(2, 0, "Nachname: ");
		editProfileTable.setText(3, 0, "Geburtstag: ");
		editProfileTable.setText(4, 0, "Email: ");
		editProfileTable.setText(5, 0, "Kürzel: ");
		editProfileTable.setText(6, 0, "Studiengang: ");

		editProfileTable.addStyleName("flextable");
		
		editProfileTable.setWidget(0, 2, matrikelnummer);
		editProfileTable.setWidget(1, 2, vorname);
		editProfileTable.setWidget(2, 2, nachname);
		
		editProfileTable.setWidget(4, 2, email);
		editProfileTable.setWidget(5, 2, kuerzel);
		editProfileTable.setWidget(6, 2, studiengang);
		
		
		geburtstag.setFormat(new DateBox.DefaultFormat(geburtsdatumFormat));
		geburtstag.getDatePicker().setYearAndMonthDropdownVisible(true);
		geburtstag.getDatePicker().setVisibleYearCount(20);
		
		geburtstag.setValue(new Date());

		geburtstag.addValueChangeHandler(new ValueChangeHandler<Date>() {
			public void onValueChange(ValueChangeEvent<Date> event) {
				Date geburtsdatum = event.getValue();
				String geburtsdatumString = DateTimeFormat.getFormat("dd.MM.yyyy").format(geburtsdatum);
				geburtstagLabel.setText(geburtsdatumString);

				if (event.getValue().after(today())) {
					geburtstag.setValue(today(), false);
				}
			}
		});
		editProfileTable.setWidget(3, 2, geburtstag);
		
		profilDatenauslesen();
		
		editBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				aktualisiereProfil();
				
				RootPanel.get("Profil").clear();
				RootPanel.get("NutzerForm").clear();
				Startseite ladeStartseite = new Startseite();
				ladeStartseite.ladeStartseite();
			}
			
		});
		
		cancelBtn.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get("Profil").clear();
				RootPanel.get("NutzerForm").clear();
				Startseite ladeStartseite = new Startseite();
				ladeStartseite.ladeStartseite();
			}
		});
		
		buttonpanel.add(saveBtn);
		buttonpanel.add(editBtn);
		buttonpanel.add(cancelBtn);
		
		verticalpanel.add(editProfileTable);
		verticalpanel.add(buttonpanel);
		horizontalpanel.add(verticalpanel);
		RootPanel.get("Profil").add(horizontalpanel);
		
				
		/* alignpanel.setStyleName("pure-form pure-form-aligned");
		    contentpanel.setStyleName("content");
		    buttonpanel.setStyleName("pure-controls-group");

		  //  editBtn.setIcon("fa fa-pencil");
		    editBtn.addClickHandler(new EditButtonClickhandler());

		  //  saveBtn.setIcon("fa fa-floppy-o");
		    saveBtn.addClickHandler(new SaveButtonClickhandler());
		    saveBtn.setEnabled(false);


		 //   deleteBtn.setIcon("fa fa-trash");
		    deleteBtn.addClickHandler(new DeleteButtonClickHandler());

		    buttonpanel.add(deleteBtn);
		    buttonpanel.add(editBtn);
		    buttonpanel.add(saveBtn);

		    alignpanel.add(buttonpanel);
		    
		    gebtag = new GeburtstagsListbox();
		    gebtag.createGebtagListbox("Geburtstag");
		    gebtag.setGebtag(student.getBirthday());
		    
		    notenVerwaltung.getAlleProfileAttribute(student.getId(), new GetAlleProfileAttributeCallback(null, student));
		    
		    contentpanel.add(alignpanel);
		    RootPanel.get("main").add(contentpanel);*/
	}
	
	public void profilDatenauslesen(){
		notenVerwaltung.getStudentById(student.getId(), new AsyncCallback<Student>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Es trat ein Fehler beim Auslesen auf.");
			}
			
			@Override
			public void onSuccess(Student result) {
				vorname.setText(result.getFirstName());
				nachname.setText(result.getLastName());
				Date geburtsdatum = result.getBirthday(); 
				String geburtsdatumString = DateTimeFormat.getFormat("dd.MM.yyyy").format(geburtsdatum);
				
				geburtsdatum.setValue(geburtsdatum);
				
				geburtstagLabel.setText(geburtsdatumString);
				matrikelnummer.setText(String.valueOf(result.getId()));
				studiengang.setText(result.getStudies());
				kuerzel.setText(result.getKuerzel());
				email.setText(result.getEmail());
			}
		});
		
		
	}
	
	public void aktualisiereProfil() {
			notenVerwaltung.bearbeiteProfil(student.getId(), vorname.getText(), nachname.getText(), getGeburtsdatum(),
					studiengang.getText(), kuerzel.getText(), email.getText());
		}
	
	Date getGeburtsdatum() {
		Date geburtsdatum = geburtsdatumFormat.parse(geburtstagLabel.getText());
		java.sql.Date sqlDate = new java.sql.Date(geburtsdatum.getTime());
		return sqlDate;
	}
	
		
	/*class GetAlleProfileAttributeCallback implements AsyncCallback<Vector<Student>> {
		private BasicFrame basicframe = null;
		private Student student = null;
		
		public GetAlleProfileAttributeCallback (BasicFrame bf, Student s) {
			this.basicframe = bf;
			this.student = s;
		}
		
		@Override
		public void onSuccess(Vector<Student> student) {
			if(student != null) {
				if(student.size() != 0) {
					StringBuffer attribute = new StringBuffer();
					
					for (Student s : student) {
						attribute.append("Matrikelnummer " + s.getId());
						attribute.append("Vorname " + s.getFirstName());
						attribute.append("Nachname " + s.getLastName());
						attribute.append("Email " + s.getEmail());
						attribute.append("Geburtstag " + s.getBirthday());
						attribute.append("Kürzel " + s.getKuerzel());
						attribute.append("Studiengang " + s.getStudies());						
					}
					
					this.basicframe.append("Dies ist dein Profil: " + attribute);
				} 
				
				else {this.basicframe.append("Du hast kein Profil hinterlegt.");}
			}
		}

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}
		
		
	}
	
	private class EditButtonClickhandler implements ClickHandler {
		
		@Override
		public void onClick(ClickEvent event) {
			saveBtn.setEnabled(true);
		      editBtn.setEnabled(false);
		      deleteBtn.setEnabled(false);
		      
		      //schauen, ob hier noch was gemacht werden muss oder ob das so passt!
		      // Wird der Button überhaupt gebraucht?
		}

		
	}
	
	
	
	
	private class DeleteButtonClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			
		}
		
	}*/
	
	private static Date today() {
		return zeroTime(new Date());
	}
	
	private static Date zeroTime(Date date) {
		return DateTimeFormat.getFormat("yyyyMMdd").parse(DateTimeFormat.getFormat("yyyyMMdd").format(date));
	}
	
}
