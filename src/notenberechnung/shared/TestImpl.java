package notenberechnung.shared;

import notenberechnung.server.NotenberechnungAdministrationImpl;
import notenberechnung.shared.bo.Student;

public class TestImpl {

	public static void main(String[] args) {

		NotenberechnungAdministrationImpl neu = new NotenberechnungAdministrationImpl();
	Student s = new Student();
		s.setId(29489);
		neu.durchschnittBerechnen(s);
	}

}
