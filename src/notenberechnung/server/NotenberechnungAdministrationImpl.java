package notenberechnung.server;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.util.Vector;
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
	
	// Login-Versuch
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
	
	//Student
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
						
			Vector<Modulbelegung> belegungen = getBelegungByStudent(student);
			
			for (int i = 0; i < belegungen.size(); i++) {
				modulbelegungsMapper.deleteModulbelegungOfStudent(belegungen.elementAt(i));
				//System.out.println("Modulbelegung " + belegungen.elementAt(i) + " erfolgreich gelöscht.");
			}
			
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
		public Vector<Student> getStudentByLastname(String lastname) throws IllegalArgumentException {
			return studentMapper.findByNachname(lastname);
		}
		
		
		
		//Modul
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
			
			Vector<Modulbelegung> belegungen = getBelegungByModul(modul);
					
			for (int i = 0; i < belegungen.size(); i++) {
				modulbelegungsMapper.deleteModulbelegungOfModul(belegungen.elementAt(i));
				//System.out.println("Modulbelegung " + belegungen.elementAt(i) + " wurde erfolgreich gelöscht.");
			}
						
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
		
		@Override
		public Vector<Modul> getModulByECTS(int ects) throws IllegalArgumentException {
			return modulMapper.findByECTS(ects);
		}
		
		@Override
		public Vector<Modul> getModulByZeitpunkt(String zeitp) throws IllegalArgumentException {
			return modulMapper.findByZeitpunkt(zeitp);
		}
		
		/*@Override
		public Modul getModulBy () throws IllegalArgumentException {
			return ;
		}*/
		
		//Modulbelegung
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
		
		@Override
		public Vector<Modulbelegung> findAll() throws IllegalArgumentException {
			return modulbelegungsMapper.findAll();
		}
		
		@Override
		public Vector<Modulbelegung> getBelegungById(int belegnr) throws IllegalArgumentException {
			return modulbelegungsMapper.findByBelegung(belegnr);
		}
		
		@Override
		public Vector<Modulbelegung> getBelegungByStudent(Student s) throws IllegalArgumentException 
		{
			return studentMapper.getModulbelegungOfStudent(s);
		}
		
		@Override
		public Vector<Modulbelegung> getBelegungByModul (Modul m) throws IllegalArgumentException {
			return modulbelegungsMapper.findByModul(m);
		}
		
		@Override
		public Modul getModulByBelegung (Modulbelegung mb) throws IllegalArgumentException {
			return modulMapper.findModul(mb);
		}
		
		@Override
		public Double durchschnittBerechnen (Student s) throws IllegalArgumentException {
			
			Double durchschnitt = 0.0;
			Vector<Modulbelegung> belegungen = getBelegungByStudent(s);
			durchschnitt = berechneDurschnittModule(belegungen);
								
			return durchschnitt;
		}

		private Double berechneDurschnittModule(Vector<Modulbelegung> mb) {
				Modul m = new Modul();
				double gesamterDurchschnitt = 0.0;
				double durchschnittGrund = 0.0;
				double durchschnittHaupt = 0.0;
				double durchschnittBA = 0.0;
				int gesamteECTSGrund = 0;
				int gesamteECTSHaupt = 0;
				int gesamteECTSBA = 18;
				
			for (int i = 0; i < mb.size(); i++) {
				m = modulbelegungsMapper.findModulByBelegung(mb.elementAt(i));
				//System.out.println(m);
				
				int ects = m.getECTS();
				Modulbelegung b = mb.elementAt(i);
				double note = b.getNote();
				String zeitpunkt = m.getZeitpunkt();
				
				switch (zeitpunkt) {
				case "G": 
					if(note != 0.0) {
						durchschnittGrund += note*ects;
						gesamteECTSGrund += ects;
						} else {
							System.out.println(m);
						}
					break;
					
				case "H":
					if(note != 0.0) {
						durchschnittHaupt += note*ects;
						gesamteECTSHaupt += ects;
						} else {
							System.out.println(m);
						}
					break;
					
				case "BA":
					if(note != 0.0) {
						durchschnittBA += note*ects;
						gesamteECTSBA += ects;
						} else {
							System.out.println(m);
						}
					break;
					
				}
				
			}
			
			gesamterDurchschnitt = Math.round(1000.0 * (((durchschnittGrund/gesamteECTSGrund)*0.15)+((durchschnittHaupt/gesamteECTSHaupt)*0.7)
					+((durchschnittBA/gesamteECTSBA)*0.15))) / 1000.0;			
			
			return gesamterDurchschnitt;
		}
		
		@Override
		public int erreichteECTSausgeben(Student s) throws IllegalArgumentException {
			
			int gesamteECTS = 0;
			Vector<Modulbelegung> belegungen = getBelegungByStudent(s);
			gesamteECTS = berechneErreichteECTS(belegungen);
			
			return gesamteECTS;
		}

		private int berechneErreichteECTS(Vector<Modulbelegung> mb) {
			int gesamteECTS = 0;
			Modul m = new Modul();
			
			for (int i = 0; i < mb.size(); i++) {
				m = modulbelegungsMapper.findModulByBelegung(mb.elementAt(i));
				gesamteECTS += m.getECTS();
			}
						
			return gesamteECTS;
		}
	
		@Override
		public String fehlendeECTSberechnen (Student s) throws IllegalArgumentException {
			String status = "";
			int mindECTSAbschluss = 210;
			int erreichteECTS = erreichteECTSausgeben(s);
			int fehlendeECTS = mindECTSAbschluss - erreichteECTS;
			
			if (erreichteECTS < 210) {
				status = ("Sie haben bereits " + erreichteECTS + " ECTS erreicht. Ihnen fehlen noch " + fehlendeECTS + " ECTS für den Abschluss.");
			} else if (erreichteECTS >= 210 || erreichteECTS <= 220) {
				status = ("Sie haben bereits " + erreichteECTS + " ECTS erreicht. Gut gemacht!");
			} else if (erreichteECTS > 220) {
				status = ("Sie haben mehr als 220 ECTS erreicht, es besteht Handlungsbedarf!");
			} else {
				status = ("Fehler in der Berechnung der ECTS; konnte keinen Status zu Ihren ECTS bestimmen.");
			}
			
			return status;
		}
		
		
	}
	

