package notenberechnung.shared;

import notenberechnung.server.NotenberechnungAdministrationImpl;
import notenberechnung.shared.bo.Student;

public class TestImpl {

	public static void main(String[] args) {

		NotenberechnungAdministrationImpl neu = new NotenberechnungAdministrationImpl();
	Student s = new Student();
		s.setId(29489);
		neu.init();
		System.out.println(neu.durchschnittBerechnen(s));
		System.out.println(neu.erreichteECTSausgeben(s));
		System.out.println("Ihnen fehlen noch mindestens " + neu.fehlendeECTSberechnen(s) + " ECTS um den Abschluss zu bestehen.");
	}

}
