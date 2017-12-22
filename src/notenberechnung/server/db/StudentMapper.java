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
	    	
	    	ResultSet rs = smt
	    			.executeQuery("SELECT Matrikelnummer, Vorname, Nachname, Geburtsdatum, Geschlecht, HdM_Kuerzel, Studiengang" + " FROM student "
              + "WHERE Matrikelnummer =" + id + " ORDER BY Nachname");
	    	
	    	// Methoden set... werden in BO Klasse Student auch gebraucht; sind dort Konstruktoren?
	    	if (rs.next()) {
	    		Student s = new Student();
	    		s.setId(rs.getInt("Matrikelnummer"));
	            s.setFirstName(rs.getString("Vorname"));
	            s.setLastName(rs.getString("Nachname"));
	            s.setGender(rs.getBoolean("Geschlecht"));
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
			
			ResultSet rs = smt.executeQuery("SELECT Matrikelnummer, Vorname, Nachname, Geburtsdatum, Geschlecht, HdM_Kuerzel, Studiengang " 
			+ "FROM student " + "ORDER BY Matrikelnummer");
			
			while (rs.next()) {
				Student s = new Student();
				s.setId(rs.getInt("Matrikelnummer"));
				s.setFirstName(rs.getString("Vorname"));
				s.setLastName(rs.getString("Nachname"));
				s.setBirthday(rs.getInt("Geburtsdatum"));
				s.setGender(rs.getBoolean("Geschlecht"));
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
			
			ResultSet rs = smt.executeQuery("SELECT Matrikelnummer, Vorname, Nachname, Geburtsdatum, Geschlecht, HdM_Kuerzel, Studiengang"
					+ "FROM student WHERE Nachname LIKE " + name + " ORDERBY Nachname");
			
			while (rs.next()){
				Student s = new Student();
				s.setId(rs.getInt("Matrikelnummer"));
				s.setFirstName(rs.getString("Vorname"));
				s.setLastName(rs.getString("Nachname"));
				s.setBirthday(rs.getInt("Geburtsdatum"));
				s.setGender(rs.getBoolean("Geschlecht"));
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
	
	public Vector<Student> findByGeschlecht (Boolean geschlecht) {
		Connection con = DBConnection.connection();
		Vector<Student> result = new Vector<Student>();
		
		try {
			Statement smt = con.createStatement();
			
			ResultSet rs = smt.executeQuery("SELECT Matrikelnummer, Vorname, Nachname, Geburtsdatum, Geschlecht, HdM_Kuerzel, Studiengang"
					+ "FROM student WHERE Geschlecht LIKE " + geschlecht + " ORDERBY Nachname");
			
			while (rs.next()){
				Student s = new Student();
				s.setId(rs.getInt("Matrikelnummer"));
				s.setFirstName(rs.getString("Vorname"));
				s.setLastName(rs.getString("Nachname"));
				s.setBirthday(rs.getInt("Geburtsdatum"));
				s.setGender(rs.getBoolean("Geschlecht"));
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
	
	public Vector<Student> findByStudiengang (String studiengang) {
		Connection con = DBConnection.connection();
		Vector<Student> result = new Vector<Student>();
		
		try {
			Statement smt = con.createStatement();
			
			ResultSet rs = smt.executeQuery("SELECT Matrikelnummer, Vorname, Nachname, Geburtsdatum, Geschlecht, HdM_Kuerzel, Studiengang"
					+ "FROM student WHERE Studiengang LIKE " + studiengang + " ORDERBY Nachname");
			
			while (rs.next()){
				Student s = new Student();
				s.setId(rs.getInt("Matrikelnummer"));
				s.setFirstName(rs.getString("Vorname"));
				s.setLastName(rs.getString("Nachname"));
				s.setBirthday(rs.getInt("Geburtsdatum"));
				s.setGender(rs.getBoolean("Geschlecht"));
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
			
			ResultSet rs = smt.executeQuery("SELECT MAX(Matrikelnummer) AS maxid FROM student");
			if(rs.next()){
				s.setId(rs.getInt("maxid") +1);
				smt = con.createStatement();
				smt.executeUpdate("INSERT INTO student (Matrikelnummer, Vorname, Nachname, Geburtsdatum, Geschlecht, HdM_Kuerzel, Studiengang)"
						+ "VALUES (" + s.getId() + ",'" + s.getFirstName() + "','" + s.getLastName() + "','" + s.getBirthday() + "','" + s.getGender() + "','" + s.getKuerzel() + "','" + s.getStudies() + "");
			}
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
		smt.executeQuery("UPDATE student " + "SET Vorname=\"" + s.getFirstName() + "\", " + "Nachname=\"" + s.getLastName() + "\","  + "SET Geburtsdatum=\"" + s.getBirthday() + "\"," + "SET Geschlecht=\"" + s.getGender() + "\"," + "SET HdM_Kuerzel=\"" + s.getKuerzel() + "\"," + "SET Studiengang=\"" + s.getStudies() + "\" "+ "WHERE Matrikelnummer= " + s.getId());
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
			smt.executeQuery("DELETE FROM student WHERE Matrikelnummer=" + s.getId());
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
