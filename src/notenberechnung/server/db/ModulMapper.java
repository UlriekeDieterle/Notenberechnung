package notenberechnung.server.db;

import java.sql.*;
import java.util.Vector;

import notenberechnung.shared.bo.Modul;
import notenberechnung.shared.bo.Modulbelegung;


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
			smt.executeUpdate("INSERT INTO modul (EDV_Nummer, ECTS, Titel_des_Moduls, Modulverantwortlicher, Zeitpunkt_Leistungserbringung, Beschreibung)"
						+ " VALUES (" 
						+ m.getId() + "," 
						+ m.getECTS() + ",'" 
						+ m.getModulTitel() + "','" 
						+ m.getVerantwortlicher() + "','" 
						+ m.getZeitpunkt() + "','" 
						+ m.getBeschreibung() + "')");
			
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
	
	public Vector<Modulbelegung> findModulbelegungByModul (Modul m) {
		return ModulBelegungsMapper.modulbelegungsMapper().findByModul(m);
	}

	public Modul findModul(Modulbelegung b) {

		return findByModul(b.getBelegungsnr());
	}
	
	private Modul findByModul(int id) {
		Connection con = DBConnection.connection();
		
		try {
			Statement smt = con.createStatement();
			
			ResultSet rs = smt.executeQuery("SELECT EDVNummerFK FROM modulbelegung WHERE Belegungsnummer = " +id);
			int edvnr = 0;
			
			if (rs.next()) {
				edvnr = rs.getInt("EDVNummerFK");
				smt = con.createStatement();
			ResultSet sr = smt.executeQuery("SELECT EDV_Nummer, ECTS, Titel_des_Moduls, Modulverantwortlicher, Zeitpunkt_Leistungserbringung, Beschreibung"
						+ " FROM modul " + "WHERE EDV_Nummer = " + edvnr);
				
				if(sr.next()) {
					Modul m = new Modul();
					m.setId(sr.getInt("EDV_Nummer"));
					m.setECTS(sr.getInt("ECTS"));
					m.setModulTitel(sr.getString("Titel_des_Moduls"));
					m.setVerantwortlicher(sr.getString("Modulverantwortlicher"));
					m.setZeitpunkt(sr.getString("Zeitpunkt_Leistungserbringung"));
					m.setBeschreibung(sr.getString("Beschreibung"));
					
					return m;
				}
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
