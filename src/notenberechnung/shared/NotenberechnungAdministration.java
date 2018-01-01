package notenberechnung.shared;

import java.util.Vector;

import notenberechnung.shared.bo.Modul;
import notenberechnung.shared.bo.Modulbelegung;
import notenberechnung.shared.bo.Student;

public interface NotenberechnungAdministration {

	Student login(String requestUri) throws IllegalArgumentException;

	Student createStudent(int id, String vorname, String nachname, String email, int gebdatum, String kuerzel,
			String studiengang) throws IllegalArgumentException;

	void delete(Student student) throws IllegalArgumentException;

	void save(Student student) throws IllegalArgumentException;

	Vector<Student> getAllProfiles() throws IllegalArgumentException;

	Student getStudentByID(int id) throws IllegalArgumentException;

	Student getStudentByEmail(String email) throws IllegalArgumentException;

	Modul createModul(int id, int ects, String titel, String verantwortl, String zeitpunkt, String beschreibung)
			throws IllegalArgumentException;

	void delete(Modul modul) throws IllegalArgumentException;

	void save(Modul modul) throws IllegalArgumentException;

	Vector<Modul> getAllModule() throws IllegalArgumentException;

	Modulbelegung createBelegung(int belegung, double note, int matnrfk, int edvnrfk) throws IllegalArgumentException;

	Modul getModulByID(int id) throws IllegalArgumentException;

	void delete(Modulbelegung belegung) throws IllegalArgumentException;

	Vector<Student> getStudentByLastname(String lastname) throws IllegalArgumentException;

	Vector<Modul> getModulByECTS(int ects) throws IllegalArgumentException;

	Vector<Modul> getModulByZeitpunkt(String zeitp) throws IllegalArgumentException;

	Vector<Modulbelegung> findAll() throws IllegalArgumentException;

	Vector<Modulbelegung> getBelegungById(int belegnr) throws IllegalArgumentException;

	Double durchschnittBerechnen(Student s) throws IllegalArgumentException;

	Vector<Modulbelegung> getBelegungByModul(Modul m) throws IllegalArgumentException;

	Modul getModulByBelegung(Modulbelegung mb) throws IllegalArgumentException;

	Vector<Modulbelegung> getBelegungByStudent(Student s) throws IllegalArgumentException;

	
	

}
