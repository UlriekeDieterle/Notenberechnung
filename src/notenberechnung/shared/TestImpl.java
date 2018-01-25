package notenberechnung.shared;

import notenberechnung.server.NotenberechnungAdministrationImpl;
import notenberechnung.shared.bo.Modul;
import notenberechnung.shared.bo.Student;

public class TestImpl {

	public static void main(String[] args) {

		NotenberechnungAdministrationImpl neu = new NotenberechnungAdministrationImpl();
	//Student s = new Student();
	Student s = new Student();
		s.setId(29487);
		//m.setId(12345);
		neu.init();
		//System.out.println(neu.durchschnittBerechnen(s));
		//System.out.println(neu.erreichteECTSausgeben(s));
		//System.out.println(neu.fehlendeECTSberechnen(s));
		
		System.out.println(neu.durchschnittBerechnen(s));
		System.out.println(neu.erreichteECTSausgeben(s));
		System.out.println(neu.fehlendeECTSberechnen(s));
		//neu.delete(s);
	}

}
