package notenberechnung.server.db;

import java.sql.*;
import java.util.Vector;

import notenberechnung.shared.bo.Modul;
import notenberechnung.shared.bo.Student;

public class ModulMapper {

	public static ModulMapper modulMapper = null;
	
	protected ModulMapper() {
	}
	
	public static ModulMapper modulMapper(){
		if(modulMapper == null){
			modulMapper = new ModulMapper();
		}
		
		return modulMapper;
	}
	
	public Modul findByKey(int id){
		Connection con = DBConnection.connection();
		
		try{
			// Wird über Import von java.sql.* reingeholt, also schon fertig
			Statement smt = con.createStatement();
			ResultSet rs = smt.executeQuery("SELECT EDV_Nummer, ECTS, Titel_des_Moduls, Modulverantwortlicher, Zeitpunkt_Leistungserbringung, Beschreibung "
					+ "FROM modul "+ "WHERE EDV_Nummer =" + id + " ORDER BY Titel_des_Moduls");
			
			/*stmt.executeUpdate("INSERT INTO profil (ProfilID, LastName, DriversLicense, Email, FirstName) " + "VALUES ("
					+ p.getID() + ",'" 
					+ p.getLastName() + "', " 
					+ p.getDriversLicence() + ", '" 
					+ p.getEmail() + "', '"
					+ p.getFirstName() + "')");*/
			
			if(rs.next()) {
				Modul m = new Modul();
				m.setId(rs.getInt("EDV_Nummer"));
				m.setECTS(rs.getInt("ECTS"));
				m.setModulTitel(rs.getString("Titel_des_Moduls"));
				m.setVerantwortlicher(rs.getString("Modulverantwortlicher"));
				m.setZeitpunkt(rs.getString("Zeitpunkt_Leistungserbringung"));
				m.setBeschreibung(rs.getString("Beschreibung"));
				
				return m;
			}
						
			}
			catch (SQLException e){
				e.printStackTrace();
		}
		
		return null;
	}
	
	public Vector<Modul> findAll(){
		Connection con = DBConnection.connection();
		Vector<Modul> result = new Vector <Modul>();
		
		try {
			Statement smt = con.createStatement();
			
			ResultSet rs = smt.executeQuery("SELECT EDV_Nummer, ECTS, Titel_des_Moduls, Modulverantwortlicher, Zeitpunkt_Leistungserbringung, Beschreibung"
					+ "FROM modul" + "ORDER BY Titel_des_Moduls");
			
			while (rs.next()) {
				Modul m = new Modul();
				m.setId(rs.getInt("EDV_Nummer"));
				m.setECTS(rs.getInt("ECTS"));
				m.setModulTitel(rs.getString("Titel_des_Moduls"));
				m.setVerantwortlicher(rs.getString("Modulverantwortlicher"));
				m.setZeitpunkt(rs.getString("Zeitpunkt_Leistungserbringung"));
				m.setBeschreibung(rs.getString("Beschreibung"));
				
				result.addElement(m);
			}					
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public Vector<Modul> findByTitel(String title) {
		Connection con = DBConnection.connection();
		Vector<Modul> result = new Vector<Modul>();
		
		try{
			Statement smt = con.createStatement();
			ResultSet rs = smt.executeQuery("SELECT EDV_Nummer, ECTS, Titel_des_Moduls, Modulverantwortlicher, Zeitpunkt_Leistungserbringung, Beschreibung"
					+ "FROM modul " + "WHERE Titel_des_Moduls = " + title + "ORDER BY EDV_Nummer");		
		
		while (rs.next()){
			Modul m = new Modul();
			m.setId(rs.getInt("EDV_Nummer"));
			m.setECTS(rs.getInt("ECTS"));
			m.setModulTitel(rs.getString("Titel_des_Moduls"));
			m.setVerantwortlicher(rs.getString("Modulverantwortlicher"));
			m.setZeitpunkt(rs.getString("Zeitpunkt_Leistungserbringung"));
			m.setBeschreibung(rs.getString("Beschreibung"));
			
			result.addElement(m);
		}
		}
		catch (SQLException e){
			e.printStackTrace();
		}
				
		return result;
	}
	
	public Vector<Modul> findByECTS(int ects){
		Connection con = DBConnection.connection();
		Vector<Modul> result = new Vector<Modul>();
		
		try{
			Statement smt = con.createStatement();
			ResultSet rs = smt.executeQuery(("SELECT EDV_Nummer, ECTS, Titel_des_Moduls, Modulverantwortlicher, Zeitpunkt_Leistungserbringung, Beschreibung"
					+ "FROM modul " + "WHERE ECTS = " + ects + "ORDER BY EDV_Nummer"));
			
			while (rs.next()) {
				Modul m = new Modul();
				m.setId(rs.getInt("EDV_Nummer"));
				m.setECTS(rs.getInt("ECTS"));
				m.setModulTitel(rs.getString("Titel_des_Moduls"));
				m.setVerantwortlicher(rs.getString("Modulverantwortlicher"));
				m.setZeitpunkt(rs.getString("Zeitpunkt_Leistungserbringung"));
				m.setBeschreibung(rs.getString("Beschreibung"));
				
				result.addElement(m);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public Vector<Modul> findByZeitpunkt(String zeitpunkt) {
		Connection con = DBConnection.connection();
		Vector<Modul> result = new Vector<Modul>();
		
		try{
			Statement smt = con.createStatement();
			ResultSet rs = smt.executeQuery(("SELECT EDV_Nummer, ECTS, Titel_des_Moduls, Modulverantwortlicher, Zeitpunkt_Leistungserbringung, Beschreibung"
					+ "FROM modul " + "WHERE Zeitpunkt_Leistungserbringung = " + zeitpunkt + "ORDER BY EDV_Nummer"));
			
			while (rs.next()) {
				Modul m = new Modul();
				m.setId(rs.getInt("EDV_Nummer"));
				m.setECTS(rs.getInt("ECTS"));
				m.setModulTitel(rs.getString("Titel_des_Moduls"));
				m.setVerantwortlicher(rs.getString("Modulverantwortlicher"));
				m.setZeitpunkt(rs.getString("Zeitpunkt_Leistungserbringung"));
				m.setBeschreibung(rs.getString("Beschreibung"));
				
				result.addElement(m);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public Modul insert(Modul m) {
		Connection con = DBConnection.connection();
		
		try{
			Statement smt = con.createStatement();
			ResultSet rs = smt.executeQuery("SELECT MAX(EDV_Nummer) AS maxid FROM modul");
			if(rs.next()){
				m.setId(rs.getInt("maxid") +1);
				smt = con.createStatement();
				smt.executeUpdate("INSERT INTO modul (EDV_Nummer, ECTS, Titel_des_Moduls, Modulverantwortlicher, Zeitpunkt_Leistungserbringung, Beschreibung)"
						+ " VALUES (" 
						+ m.getId() + "," 
						+ m.getECTS() + ",'" 
						+ m.getModulTitel() + "','" 
						+ m.getVerantwortlicher() + "','" 
						+ m.getZeitpunkt() + "','" 
						+ m.getBeschreibung() + "')");
			
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return m; 
	}
	
	public Modul update(Modul m) {
		Connection con = DBConnection.connection();
		
		try {
			Statement smt = con.createStatement();
			
			smt.executeUpdate("UPDATE modul SET " + "ECTS= " + m.getECTS() + ", " + "Titel_des_Moduls = '" + m.getModulTitel() + "', "  
			+ "Modulverantwortlicher = '" + m.getVerantwortlicher() + "', " + "Zeitpunkt_Leistungserbringung = '" + m.getZeitpunkt() + "', " + "Beschreibung = '" 
			+ m.getBeschreibung() + "' "+ "WHERE EDV_Nummer = " + m.getId());
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return m;
	}
	
	public void delete (Modul m) {
		Connection con = DBConnection.connection();
		
		try {
			Statement smt = con.createStatement();
			smt.executeUpdate("DELETE FROM modul " + "WHERE EDV_Nummer = " + m.getId());
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
