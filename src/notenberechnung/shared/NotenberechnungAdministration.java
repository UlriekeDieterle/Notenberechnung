package notenberechnung.shared;

import notenberechnung.shared.bo.Student;

public interface NotenberechnungAdministration {

	Student login(String requestUri) throws IllegalArgumentException;
	

}
