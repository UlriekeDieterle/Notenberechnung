package notenberechnung.shared;

import java.util.Vector;
import com.google.gwt.user.client.rpc.AsyncCallback;

import notenberechnung.shared.bo.*;

public interface NotenberechnungAdministrationAsync {

	void init(AsyncCallback<Void> callback);

	//void getAlleProfileAttribute(int id, AsyncCallback<Vector<Student>> callback);

	void delete(Student student, AsyncCallback<Void> callback);

	void login(String requestUri, AsyncCallback<Student> async);

	//void getProfilFormular(AsyncCallback<Vector<Student>> callback);

	void createStudent(int matrikelnummer, String firstName, String lastName, String email, String kuerzel,
			String studiengang, AsyncCallback<Student> callback);
	
	void save(Student student, AsyncCallback<Void> callback);
	
	void getAllProfiles(AsyncCallback<Vector<Student>> callback);
	
	void getStudentByID(int id, AsyncCallback<Student> callback);
	
	void getStudentByEmail(String email, AsyncCallback<Student> callback);
	
	void createModul(int id, int ects, String titel, String verantwortl, String zeitpunkt, String beschreibung, AsyncCallback<Modul> callback);
	
	void delete(Modul modul, AsyncCallback<Void> callback);
	
	void save(Modul modul, AsyncCallback<Void> callback);
	
	void getAllModule(AsyncCallback<Vector<Modul>> callback);
	
	void createBelegung(int belegung, double note, int matnrfk, int edvnrfk, AsyncCallback<Modulbelegung> callback);

	void getModulByID(int id, AsyncCallback<Modul> callback);
	
	void delete(Modulbelegung belegung, AsyncCallback<Void> callback);
	
	void getStudentByLastname(String lastname, AsyncCallback<Vector<Student>> callback);
	
	void getModulByECTS(int ects, AsyncCallback<Vector<Modul>> callback);
	
	void getModulByZeitpunkt(String zeitp, AsyncCallback<Vector<Modul>> callback);
	
	void findAll(AsyncCallback<Vector<Modulbelegung>> callback);
	
	void getBelegungById(int belegnr, AsyncCallback<Vector<Modulbelegung>> callback);
	
	void durchschnittBerechnen(Student s, AsyncCallback<Double> callback);
	
	void getBelegungByModul(Modul m, AsyncCallback<Vector<Modulbelegung>> callback);
	
	void getModulByBelegung(Modulbelegung mb, AsyncCallback<Modul> callback);
	
	void getBelegungByStudent(Student s, AsyncCallback<Vector<Modulbelegung>> callback);
	
	void erreichteECTSausgeben(Student s, AsyncCallback<Integer> callback);
	
	void fehlendeECTSberechnen(Student s, AsyncCallback<String> callback);
	
	
	
	

}
