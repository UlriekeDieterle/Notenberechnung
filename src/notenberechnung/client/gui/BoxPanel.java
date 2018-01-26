package notenberechnung.client.gui;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;

import notenberechnung.shared.bo.Student;



public class BoxPanel extends FlowPanel{

	Student student = null;
	
	
	 ProfilAttribute profilAttributTextBox = new ProfilAttribute(null);
	 public BoxPanel (String text) {
		 this.add(new HTML(text));
	 }
	
	 
	 
}
