package notenberechnung.server.db;
import java.sql.*;
import java.util.Vector;
import notenberechnung.shared.bo.Modulbelegung;
// muss importiert werden, damit Typ Student aus Klasse BO bekannt ist, kann sonst nicht verwendet werden
import notenberechnung.shared.bo.Student;

public class StudentMapper {

	public static StudentMapper studentMapper = null;
	
	protected StudentMapper(){
	}
	
	public static StudentMapper studentMapper(){
		
		if(studentMapper == null){
			studentMapper = new StudentMapper();
		}
		
		return studentMapper;
	}
	
	
	public Student findByKey(int id){
	    Connection con = DBConnection.connection();
	    try {
	    	Statement smt = con.createStatement();
	    	
	    	ResultSet rs = smt.executeQuery("SELECT Matrikelnummer, Vorname, Nachname, EMail, Geburtsdatum, HdM_Kuerzel, Studiengang" + " FROM student "
	    			+ "WHERE Matrikelnummer =" + id + " ORDER BY Nachname");
	    	
	    	// Methoden set... werden in BO Klasse Student auch gebraucht; sind dort Konstruktoren?
	    	if (rs.next()) {
	    		Student s = new Student();
	    		s.setId(rs.getInt("Matrikelnummer"));
	            s.setFirstName(rs.getString("Vorname"));
	            s.setLastName(rs.getString("Nachname"));
	            s.setEmail(rs.getString("EMail"));
	            s.setBirthday(rs.getInt("Geburtsdatum"));
	            s.setKuerzel(rs.getString("HdM_Kuerzel"));
	            s.setStudies(rs.getString("Studiengang"));
	            
	            return s;	            
	    	}
	    	
	    }

	    catch (SQLException e) {
	        e.printStackTrace();
	        return null;
		
	}
	    return null;
	    
	}
	
	public Vector <Student> findAll(){
		Connection con = DBConnection.connection();
		Vector<Student> result = new Vector<Student>();
		
		try {
			Statement smt = con.createStatement();
			
			ResultSet rs = smt.executeQuery("SELECT Matrikelnummer, Vorname, Nachname, EMail, Geburtsdatum, HdM_Kuerzel, Studiengang " 
			+ " FROM student " + "ORDER BY Matrikelnummer");
			
			while (rs.next()) {
				Student s = new Student();
				s.setId(rs.getInt("Matrikelnummer"));
				s.setFirstName(rs.getString("Vorname"));
				s.setLastName(rs.getString("Nachname"));
				s.setEmail(rs.getString("EMail"));
				s.setBirthday(rs.getInt("Geburtsdatum"));
				s.setKuerzel(rs.getString("HdM_Kuerzel"));
				s.setStudies(rs.getString("Studiengang"));
				
				result.addElement(s);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public Vector<Student> findByNachname(String name) {
		Connection con = DBConnection.connection();
		Vector<Student> result = new Vector<Student>();
		
		try {
			Statement smt = con.createStatement();
			
			ResultSet rs = smt.executeQuery("SELECT Matrikelnummer, Vorname, Nachname, EMail, Geburtsdatum, HdM_Kuerzel, Studiengang"
					+ " FROM student" + " WHERE Nachname LIKE '" + name + "' ORDER BY Nachname");
						
			while (rs.next()){
				Student s = new Student();
				s.setId(rs.getInt("Matrikelnummer"));
				s.setFirstName(rs.getString("Vorname"));
				s.setLastName(rs.getString("Nachname"));
				s.setEmail(rs.getString("EMail"));
				s.setBirthday(rs.getInt("Geburtsdatum"));
				s.setKuerzel(rs.getString("HdM_Kuerzel"));
				s.setStudies(rs.getString("Studiengang"));
				
				result.addElement(s);
				
			}
		}
		
		catch (SQLException e) {
				e.printStackTrace();
		}
		
		return result;
	}
	
	public Student findByEmail (String email) {
		Connection con = DBConnection.connection();
				
		try {
			Statement smt = con.createStatement();
			
			ResultSet rs = smt.executeQuery("SELECT Matrikelnummer, Vorname, Nachname, EMail, Geburtsdatum, HdM_Kuerzel, Studiengang"
					+ " FROM student" + " WHERE EMail LIKE '" + email + "'");
			
			while (rs.next()){
				Student s = new Student();
				s.setId(rs.getInt("Matrikelnummer"));
				s.setFirstName(rs.getString("Vorname"));
				s.setLastName(rs.getString("Nachname"));
				s.setEmail(rs.getString ("EMail"));
				s.setBirthday(rs.getInt("Geburtsdatum"));
				s.setKuerzel(rs.getString("HdM_Kuerzel"));
				s.setStudies(rs.getString("Studiengang"));
				
				return s;
				
				//return result;
			}
		}
		
		catch (SQLException e) {
				e.printStackTrace();
		}
		
		return null;
	}
	
	public Vector<Student> findByStudiengang (String studiengang) {
		Connection con = DBConnection.connection();
		Vector<Student> result = new Vector<Student>();
		
		try {
			Statement smt = con.createStatement();
			
			ResultSet rs = smt.executeQuery("SELECT Matrikelnummer, Vorname, Nachname, EMail, Geburtsdatum, HdM_Kuerzel, Studiengang"
					+ " FROM student" + " WHERE Studiengang LIKE '" + studiengang + "' ORDER BY Nachname");
			
			while (rs.next()){
				Student s = new Student();
				s.setId(rs.getInt("Matrikelnummer"));
				s.setFirstName(rs.getString("Vorname"));
				s.setLastName(rs.getString("Nachname"));
				s.setEmail(rs.getString("EMail"));
				s.setBirthday(rs.getInt("Geburtsdatum"));
				s.setKuerzel(rs.getString("HdM_Kuerzel"));
				s.setStudies(rs.getString("Studiengang"));
				
				result.addElement(s);
				
			}
		}
		
		catch (SQLException e) {
				e.printStackTrace();
		}
		
		return result;
	}
	
	public Student insert (Student s){
		Connection con = DBConnection.connection();
		
		try{
			Statement smt = con.createStatement();
								
				// nur Strings mit '' einfügen, Rest ohne!
				//System.out.println(test);
				smt.executeUpdate("INSERT INTO student (Matrikelnummer, Vorname, Nachname, EMail, Geburtsdatum, HdM_Kuerzel, Studiengang) "
						+ "VALUES (" 
						+ s.getId() + ",'" 
						+ s.getFirstName() + "', '" 
						+ s.getLastName() + "', '" 
						+ s.getEmail() + "', "
						+ s.getBirthday() + ", '" 
						+ s.getKuerzel() + "', '" 
						+ s.getStudies() + "')");
				
				notenberechnung.client.ClientsideSettings.getLogger().info("Profil " + s.getLastName() + "  in DB geschrieben");
		}
		catch (SQLException e) {
		      e.printStackTrace();
	}
		
		return s;
	}
	
	public Student update (Student s){
		Connection con = DBConnection.connection();
		
		try {
		Statement smt = con.createStatement();
		smt.executeUpdate("UPDATE student SET " + "Vorname= '" + s.getFirstName() + "', " + "Nachname= '" 
				+ s.getLastName() + "', " + "EMail= '" + s.getEmail() + "', " + "Geburtsdatum= " 
				+ s.getBirthday() + ", " + "HdM_Kuerzel= '" + s.getKuerzel() + "', " + "Studiengang= '" 
				+ s.getStudies() + "' " + " WHERE Matrikelnummer = " + s.getId());
		
	}
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return s;
	}
	
	public void delete (Student s){
		Connection con = DBConnection.connection();
		
		try {
			Statement smt = con.createStatement();
			smt.executeUpdate("DELETE FROM student " + "WHERE Matrikelnummer = " + s.getId());
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// hier wird Modulbelegung von Student gemacht
	public Vector<Modulbelegung> getModulbelegungOfStudent (Student s) {
		return ModulBelegungsMapper.modulbelegungsMapper().findByStudent(s);
	}
			
}
