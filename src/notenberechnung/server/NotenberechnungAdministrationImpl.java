package notenberechnung.server;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.util.logging.Logger.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

import notenberechnung.server.db.ModulBelegungsMapper;
import notenberechnung.server.db.ModulMapper;
import notenberechnung.server.db.StudentMapper;
import notenberechnung.shared.NotenberechnungAdministration;
import notenberechnung.shared.bo.Modul;
import notenberechnung.shared.bo.Student;
import notenberechnung.shared.bo.Modulbelegung;
import notenberechnung.client.ClientsideSettings;

public class NotenberechnungAdministrationImpl extends RemoteServiceServlet implements NotenberechnungAdministration {

	//hier drin ist alle Applikationslogik!
	
	private static final long serialVersionUID = 1L;
	private StudentMapper studentMapper = null;
	private ModulMapper modulMapper = null;
	private ModulBelegungsMapper modulbelegungsMapper = null;
	
	//Konstruktor
	public NotenberechnungAdministrationImpl() throws IllegalArgumentException {}
	
	@Override
	public void init() throws IllegalArgumentException {
		
		//Mapper matchen
		studentMapper = StudentMapper.studentMapper();
		modulMapper = ModulMapper.modulMapper();
		modulbelegungsMapper = ModulBelegungsMapper.modulbelegungsMapper();
		
	}
	
	@Override
	public Student login (String requestUri) {
		UserService userService = UserServiceFactory.getUserService();
	    User user = userService.getCurrentUser();

	    Student student = new Student();
	    
	    if (user != null) {
	    	Student bestehStudent = studentMapper.findByEmail(user.getEmail());
	    	
	    	if (bestehStudent != null) {
	    		notenberechnung.client.ClientsideSettings.getLogger().severe("Userobjekt E-Mail = " + user.getEmail()
	            + "  Bestehender User: E-Mail  =" + bestehStudent.getEmail());
	        bestehStudent.setLoggedIn(true);
	        bestehStudent.setLogoutUrl(userService.createLogoutURL(requestUri));

	        return bestehStudent;
	    	}
	    	
	    	 student.setLoggedIn(true);
	         student.setLogoutUrl(userService.createLogoutURL(requestUri));
	         student.setEmail(user.getEmail());

	     } else {
	         student.setLoggedIn(false);
	         student.setLoginUrl(userService.createLoginURL(requestUri));
	         student.setLogoutUrl(userService.createLogoutURL(requestUri));
	       }

	       return student;
	   }
	
		@Override
		public Student createStudent (int id, String vorname, String nachname, String email, int gebdatum, 
				String kuerzel, String studiengang) throws IllegalArgumentException {
			
			Student s = new Student();
			s.setId(id);
			s.setFirstName(vorname);
			s.setLastName(nachname);
			s.setEmail(email);
			s.setBirthday(gebdatum);
			s.setKuerzel(kuerzel);
			s.setStudies(studiengang);
			
			ClientsideSettings.setCurrentUser(s);
		    //ClientsideSettings.getLogger().info("user " + s.getLastName() + " erstellt");
			
			return studentMapper.insert(s);
		}
		
		@Override
		public void delete (Student student) throws IllegalArgumentException {
			modulbelegungsMapper.deleteOfModulbelegung(student);
			studentMapper.delete(student);
		}
		
		@Override
		public void save (Student student) throws IllegalArgumentException {
			studentMapper.update(student);
		}
		
		@Override
		public Vector<Student> getAllProfiles() throws IllegalArgumentException {
			return studentMapper.findAll();
		}
		
		@Override
		public Student getStudentByID(int id) throws IllegalArgumentException {
			return studentMapper.findByKey(id);
		}
		
		@Override
		public Student getStudentByEmail(String email) throws IllegalArgumentException {
			return studentMapper.findByEmail(email);
		}
		
		
		@Override
		public Modul createModul (int id, int ects, String titel, String verantwortl, String zeitpunkt, String beschreibung) throws IllegalArgumentException {
			Modul m = new Modul();
			m.setId(id);
			m.setECTS(ects);
			m.setModulTitel(titel);
			m.setVerantwortlicher(verantwortl);
			m.setZeitpunkt(zeitpunkt);
			m.setBeschreibung(beschreibung);
			
			return modulMapper.insert(m);
		}
		
		@Override
		public void delete (Modul modul) throws IllegalArgumentException {
			modulMapper.delete(modul);
		}
		
		@Override
		public void save (Modul modul) throws IllegalArgumentException {
			modulMapper.update(modul);
		}
		
		@Override
		public Vector<Modul> getAllModule() throws IllegalArgumentException {
			return modulMapper.findAll();
		}
		
		@Override
		public Modul getModulByID(int id) throws IllegalArgumentException {
			return modulMapper.findByKey(id);
		}
		
		/*@Override
		public Modul getModulBy () throws IllegalArgumentException {
			return ;
		}*/
		
		
		@Override
		public Modulbelegung createBelegung(int belegung, double note, int matnrfk, int edvnrfk) throws IllegalArgumentException {
			Modulbelegung mb = new Modulbelegung();
			mb.setBelegungsnr(belegung);
			mb.setNote(note);
			mb.setMatrikelnummerFK(matnrfk);
			mb.setEDVNr(edvnrfk);
			
			return modulbelegungsMapper.insert(mb);
		}
		
		@Override
		public void delete (Modulbelegung belegung) throws IllegalArgumentException {
			modulbelegungsMapper.delete(belegung);
			
		}
		
		
		
		
		
	
		
	}
	

