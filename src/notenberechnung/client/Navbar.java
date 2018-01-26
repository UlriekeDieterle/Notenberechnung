package notenberechnung.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

import notenberechnung.client.gui.*;
import notenberechnung.shared.NotenberechnungAdministrationAsync;
import notenberechnung.shared.bo.*;

public class Navbar extends VerticalPanel{

	NotenberechnungAdministrationAsync notenVerwaltung = ClientsideSettings.getNotenberechnungVerwaltung();
	Student student = ClientsideSettings.getCurrentUser();
	
	@Override
	public void onLoad() {
		RootPanel.get("menu").getElement().getStyle().setBackgroundColor("#b75d6b");
		
		if ((student != null) && student.isLoggedIn()) {
			FlowPanel menu = new FlowPanel();
		      FlowPanel pureMenu = new FlowPanel();
		      UnorderedListWidget menuList = new UnorderedListWidget();

		      // Home "Button"
		      Anchor home = new Anchor("Notenberechnung", GWT.getHostPageBaseURL() + "Notenberechnung.html");

		      // Menustiles
		      menuList.setStyleName("pure-menu-list");
		      home.setStyleName("pure-menu-heading");
		      pureMenu.setStyleName("pure-menu");

		      // "Button" Definition wie Logout, Profil, Merkliste, ...
		      Anchor logoutAnchor = new Anchor("Logout");
		      Anchor profilAnchor = new Anchor("Profil");
		      Anchor modulAnzeigenAnchor = new Anchor("Modul anzeigen");
		      Anchor durchschnittBerechnenAnchor = new Anchor("Durchschnitt berechnen");
		     // Anchor reportAnchor = new Anchor("Report");
		      
		      profilAnchor.setStyleName("pure-menu-link");
		      menuList.add(new ListItemWidget(profilAnchor));
		      
		      modulAnzeigenAnchor.setStyleName("pure-menu-link");
		      menuList.add(new ListItemWidget(modulAnzeigenAnchor));
		      
		      durchschnittBerechnenAnchor.setStyleName("pure-menu-link");
		      menuList.add(new ListItemWidget(durchschnittBerechnenAnchor));
		      
		      logoutAnchor.setStyleName("pure-menu-link");
		      menuList.add(new ListItemWidget(logoutAnchor));
		      
		      pureMenu.add(home);
		      pureMenu.add(menuList);
		      menu.add(pureMenu);

		      RootPanel.get("menu").add(menu);
		      
		      logoutAnchor.addClickHandler(new LogoutClickHandler());
		      profilAnchor.addClickHandler(new ProfilClickHandler());
		      modulAnzeigenAnchor.addClickHandler(new ModulAnzeigenClickHandler());
		      durchschnittBerechnenAnchor.addClickHandler(new DurchschnittBerechnenClickHandler());
		}
	}
	
	private class LogoutClickHandler implements ClickHandler{
		
		@Override
		public void onClick(ClickEvent event) {
			 Window.open(student.getLogoutUrl(), "_self", "");			
		}
	}
	
	private class ProfilClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			ShowStudent sp = new ShowStudent();
		      RootPanel.get("main").clear();
		      RootPanel.get("main").add(sp);
		}
	}
	
	private class ModulAnzeigenClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			ModulAnzeigen ma = new ModulAnzeigen();
			RootPanel.get("main").clear();
			RootPanel.get("main").add(ma);
		}
	}
	
	private class DurchschnittBerechnenClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			ModulbelegungUndBerechnung mub = new ModulbelegungUndBerechnung();
			RootPanel.get("main").clear();
			RootPanel.get("main").add(mub);
		}
	}
}
