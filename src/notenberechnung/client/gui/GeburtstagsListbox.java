package notenberechnung.client.gui;

import java.util.Date;

import de.superteam2000.gwt.client.gui.DateTimeFormat;
import notenberechnung.client.gui.BoxPanel;
public class GeburtstagsListbox extends BoxPanel{

	 EigenschaftListBox gebDatumTagListBox = new EigenschaftListBox();
	  EigenschaftListBox gebDatumMonatListBox = new EigenschaftListBox();
	  EigenschaftListBox gebDatumJahrListBox = new EigenschaftListBox();

	  EigenschaftListBox alterListBox = new EigenschaftListBox();
	
	  
	  public GeburtstagsListbox(String name) {
		    this.add(new Label(name));
		  }
	  
	  public GeburtstagsListbox() {}
	  
	  public void createAlterListbox() {
		    for (int i = 18; i < 100; i++) {
		      profilAttributListBox.addItem(String.valueOf(i));
		    }
		    profilAttributListBox.setStyleName("pure-input-1-4");
		    profilAttributListBox.setName("Alter");
		    this.add(profilAttributListBox);
		    this.setStyleName("pure-control-group");
		  }
	
	public void createGebtagListbox(String name) {
		
	this.add(new Label(name));
    for (int i = 1; i <= 31; i++) {
      gebDatumTagListBox.addItem(String.valueOf(i));
    }

    for (int i = 1; i <= 12; i++) {
      gebDatumMonatListBox.addItem(String.valueOf(i));
    }

    for (int i = 1900; i <= 2000; i++) {
      gebDatumJahrListBox.addItem(String.valueOf(i));
    }
    gebDatumTagListBox.setEnabled(false);
    gebDatumMonatListBox.setEnabled(false);
    gebDatumJahrListBox.setEnabled(false);

    gebDatumTagListBox.setName("GeburtstagTag");
    gebDatumMonatListBox.setName("GeburtstagMonat");
    gebDatumJahrListBox.setName("GeburtstagJahr");

    this.add(gebDatumTagListBox);
    this.add(gebDatumMonatListBox);
    this.add(gebDatumJahrListBox);

    this.setStyleName("pure-control-group");
		
	}
	
	 public void setGebtag(int i) {
		    String dateString = DateTimeFormat.getFormat("yyyy-MM-dd").format(i);
		    String[] gebDaten = dateString.split("-");
		    gebDatumTagListBox.setItemSelected(Integer.valueOf(gebDaten[2]) - 1, true);
		    gebDatumMonatListBox.setItemSelected(Integer.valueOf(gebDaten[1]) - 1, true);
		    gebDatumJahrListBox.setItemSelected(Integer.valueOf(gebDaten[0]) - 1900, true);
		  }
	 
	 @Override
	  public void setAlter(int alter) {
	    if (alter == 0) {
	      profilAttributListBox.setSelectedItemByIndex(0);
	    } else {
	      profilAttributListBox.setItemSelected(alter - 17, true);
	    }
	  }
	 
	 @Override
	  public void setEnable(boolean isEnabled) {
	    profilAttributListBox.setEnabled(isEnabled);
	    gebDatumTagListBox.setEnabled(isEnabled);
	    gebDatumMonatListBox.setEnabled(isEnabled);
	    gebDatumJahrListBox.setEnabled(isEnabled);
	  }

	


	

}
