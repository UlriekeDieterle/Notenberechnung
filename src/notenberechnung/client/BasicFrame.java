package notenberechnung.client;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

import notenberechnung.client.gui.Startseite;
import notenberechnung.shared.NotenberechnungAdministrationAsync;
import notenberechnung.shared.bo.*;

public abstract class BasicFrame extends VerticalPanel {

	NotenberechnungAdministrationAsync notenVerwaltung = ClientsideSettings.getNotenberechnungVerwaltung();
	Student student = ClientsideSettings.getCurrentUser();
	
	@Override
	public void onLoad() {
		super.onLoad();
		this.add(this.createHeadline(this.getHeadlineText()));
		this.run();
	}
	
	protected HTML createHeadline(String text) {
		HTML content = new HTML(text);
		content.setStylePrimaryName("notenberechnung-headline");
		return content;
	}
	
	protected void append (String text) {
		HTML content = new HTML (text);
		content.setStylePrimaryName("notenberechnung-simpletext");
		this.add(content);
	}
	
	protected abstract String getHeadlineText();
	
	protected abstract void run();

	public String getSubHeadlineText() {
		// TODO Auto-generated method stub
		return null;
	}

	/*public void load() {
		
		MenuBar menu = new MenuBar();
		menu.setAutoOpen(true);
		menu.setWidth("430px");
		menu.setAnimationEnabled(true);
		
		MenuBar studentMenu = new MenuBar (true);
		studentMenu.setAnimationEnabled(true);
		MenuBar modulMenu = new MenuBar(true);
		modulMenu.setAnimationEnabled(true);
		MenuBar durchschnittMenu = new MenuBar(true);
		durchschnittMenu.setAnimationEnabled(true);
		MenuBar logoutMenu = new MenuBar(true);
		logoutMenu.setAnimationEnabled(true);
		
		menu.addItem(new MenuItem("Mein Profil", studentMenu));
		menu.addSeparator();
		menu.addItem(new MenuItem("Module verwalten", modulMenu));
		menu.addSeparator();
		menu.addItem(new MenuItem("Durchschnitt errechnen", durchschnittMenu));
		menu.addSeparator();
		menu.addItem(new MenuItem("Logout", new Command(){
			public void execute(){
				loadLogout(student);
			}
		}));
		
		studentMenu.addSeparator();
		studentMenu.addItem("Dein Profil", new Command() {
			public void execute() {
				RootPanel.get("Profil").clear();
				Startseite startseite = new Startseite();
				startseite.ladeStartseite();
			}
		});
		
		studentMenu.addItem("Profil bearbeiten", new Command() {
			public void execute() {
				RootPanel.get("Profil").clear();
				ShowStudent profilBearbeiten = new ShowStudent();
				RootPanel.get("Profil").add(profilBearbeiten);
			}
		});
		
		studentMenu.addItem("Module verwalten", new Command() {
			public void execute() {
				RootPanel.get("Profil").clear();
				ModulAnzeigen moduleanzeigen = new ModulAnzeigen();
				RootPanel.get("Modul").add(moduleanzeigen);
			}
		});
		
		studentMenu.addItem("Durchschnitt berechnen", new Command() {
			public void execute() {
				RootPanel.get("Profil").clear();
				ModulbelegungUndBerechnung durchschnitt = new ModulbelegungUndBerechnung();
				RootPanel.get("Durchschnitt").add(durchschnitt);
			}
		});
		
		studentMenu.addItem("Profil löschen", new Command() {
			public void execute() {
				notenVerwaltung.deleteStudent(student.getId(), new AsyncCallback<Void>() {
					public void onFailure(Throwable caught) {
						Window.alert("Das Profil konnte nicht gelöscht werden");
					}
					
					public void onSuccess(Void result){
						Window.alert("Das Profil wurde erfolgreich gelöscht!");
						loadLogout(student);
					}
				});
			}
		});
		
		//studentMenu.addSeparator();
		
		RootPanel.get("navigator").clear();
		RootPanel.get("navigator").add(menu);
		
	}

	protected void loadLogout(Student student2) {
		final String logoutURL = student.getLogoutUrl();
		Window.Location.assign(logoutURL);		
	}*/
	
	
}
