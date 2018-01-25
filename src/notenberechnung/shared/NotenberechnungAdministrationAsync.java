package notenberechnung.shared;

import java.util.Vector;
import com.google.gwt.user.client.rpc.AsyncCallback;

import notenberechnung.shared.bo.*;

public interface NotenberechnungAdministrationAsync {

	void getCurrentUser();

	void getAlleProfileAttribute(int id, AsyncCallback<Vector<Student>> callback);

	void getStudentById(int studentID, AsyncCallback<Student> callback);

	void deleteStudent(int id, AsyncCallback<Void> callback);
	
	
	

}
