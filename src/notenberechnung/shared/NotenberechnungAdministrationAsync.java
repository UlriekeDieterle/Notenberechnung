package notenberechnung.shared;

import java.util.Date;
import java.util.Vector;
import com.google.gwt.user.client.rpc.AsyncCallback;

import notenberechnung.shared.bo.*;

public interface NotenberechnungAdministrationAsync {

	void getCurrentUser();

	//void getAlleProfileAttribute(int id, AsyncCallback<Vector<Student>> callback);

	void getStudentById(int studentID, AsyncCallback<Student> callback);

	void deleteStudent(int id, AsyncCallback<Void> callback);

	void bearbeiteProfil(int id, String vorname, String nachname, Date geburtsdatum, String studiengang, String kuerzel,
			String email);

	void findModulById(int id,AsyncCallback<Vector<Modul>> callback);

	void deleteModulbelegung(int studentId, int modulId, AsyncCallback<Void> callback);

	void login(String requestUri, AsyncCallback<Student> async);

	void getProfilFormular(AsyncCallback<Vector<Student>> callback);

	void createProfile(int matrikelnummer, String firstName, String lastName, String email, String kuerzel,
			String studiengang, AsyncCallback<Student> callback);
	
	
	
	
	

}
