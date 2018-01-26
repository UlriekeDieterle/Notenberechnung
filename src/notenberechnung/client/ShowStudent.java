package notenberechnung.client;

import java.util.logging.Logger;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

import notenberechnung.client.gui.Startseite;
import notenberechnung.shared.NotenberechnungAdministrationAsync;
import notenberechnung.shared.bo.Student;

public class ShowStudent extends BasicFrame{

	NotenberechnungAdministrationAsync notenVerwaltung = ClientsideSettings.getNotenberechnungVerwaltung();
	
	private VerticalPanel verticalpanel = new VerticalPanel();
//	private HorizontalPanel buttonpanel = new HorizontalPanel();
	private HorizontalPanel horizontalpanel = new HorizontalPanel();
	
	private FlexTable editProfileTable = new FlexTable();
	private TextBox vorname = new TextBox();
	private TextBox nachname = new TextBox();
	private TextBox studiengang = new TextBox();
	private TextBox kuerzel = new TextBox();
	private IntegerBox matrikelnummer = new IntegerBox();
	private TextBox email = new TextBox();
	
	private Button saveBtn = new Button("Speichern");
	private Button editBtn = new Button("Bearbeiten");
	private Button cancelBtn = new Button ("Abbrechen");
	
	//BoxPanel clb = null;
	FlowPanel buttonpanel = new FlowPanel();
//	GeburtstagsListbox gebtag = null;
	
	FlowPanel alignpanel = new FlowPanel();
	FlowPanel contentpanel = new FlowPanel();
		
	Student student = ClientsideSettings.getCurrentUser();
	Logger logger = ClientsideSettings.getLogger();
	
			
	public ShowStudent() {
		this.add(verticalpanel);
		run();
	}
	
	@Override
	public void run(){
		alignpanel.setStyleName("pure-form pure-form-aligned");
	    contentpanel.setStyleName("content");
	    buttonpanel.setStyleName("pure-controls-group");
		
	/*	editProfileTable.setText(0, 0, "Matrikelnummer: ");
		editProfileTable.setText(1, 0, "Vorname: ");
		editProfileTable.setText(2, 0, "Nachname: ");
		editProfileTable.setText(3, 0, "Email: ");
		editProfileTable.setText(4, 0, "Kürzel: ");
		editProfileTable.setText(5, 0, "Studiengang: ");

		editProfileTable.addStyleName("flextable");
		
		editProfileTable.setWidget(0, 2, matrikelnummer);
		editProfileTable.setWidget(1, 2, vorname);
		editProfileTable.setWidget(2, 2, nachname);
		editProfileTable.setWidget(3, 2, email);
		editProfileTable.setWidget(4, 2, kuerzel);
		editProfileTable.setWidget(5, 2, studiengang);*/
		
		buttonpanel.add(saveBtn);
		buttonpanel.add(editBtn);
		buttonpanel.add(cancelBtn);
		
		alignpanel.add(buttonpanel);
		
		contentpanel.add(alignpanel);
		RootPanel.get("main").add(contentpanel);
		
	//	profilDatenauslesen();
		
		editBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
			//	aktualisiereProfil();
				
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
		
	}
	
	/*public void profilDatenauslesen(){
	//	notenVerwaltung.getStudentByID(student.getId(), new AsyncCallback<Student>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Es trat ein Fehler beim Auslesen auf.");
			}
			
			@Override
			public void onSuccess(Student result) {
				vorname.setText(result.getFirstName());
				nachname.setText(result.getLastName());			
				matrikelnummer.setText(String.valueOf(result.getId()));
				studiengang.setText(result.getStudies());
				kuerzel.setText(result.getKuerzel());
				email.setText(result.getEmail());
			}
		});
		
		
	}*/
	
	public void aktualisiereProfil() {
		Student s = new Student();
		s.setId(student.getId());
		s.setFirstName(vorname.getText());
		s.setLastName(nachname.getText());
		s.setEmail(email.getText());
		s.setKuerzel(kuerzel.getText());
		s.setStudies(studiengang.getText());
		
//		notenVerwaltung.save(s, new AsyncCallback<Void>());
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

	@Override
	protected String getHeadlineText() {
		return "Hallo " + student.getFirstName() + ", hier kannst du dein Profil bearbeiten:";
	}
	
}
