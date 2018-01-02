package notenberechnung.server.db;

import java.sql.*;
import java.util.Vector;

import notenberechnung.shared.bo.Modul;
import notenberechnung.shared.bo.Modulbelegung;
import notenberechnung.shared.bo.Student;

public class ModulBelegungsMapper {

	private static ModulBelegungsMapper modulbelegungsMapper = null;
	
	// Konstruktor für Mapper
	protected ModulBelegungsMapper() {
		
	}

	public static ModulBelegungsMapper modulbelegungsMapper() {
		if(modulbelegungsMapper == null) {
			modulbelegungsMapper = new ModulBelegungsMapper();
		}
		return modulbelegungsMapper;
	}
	
	public Modulbelegung findByKey(int id) {
		Connection con = DBConnection.connection();
		
		try {
			Statement smt = con.createStatement();
			ResultSet rs = smt.executeQuery("SELECT Belegungsnummer, Note, MatrikelnummerFK, EDVNummerFK" + " FROM modulbelegung "
					+ "WHERE Belegungsnummer =" + id + " ORDER BY Belegungsnummer");
			
			if(rs.next()) {
				Modulbelegung b = new Modulbelegung();
				b.setBelegungsnr(rs.getInt("Belegungsnummer"));
				b.setNote(rs.getDouble("Note"));
				b.setMatrikelnummerFK(rs.getInt("MatrikelnummerFK"));
				b.setEDVNr(rs.getInt("EDVNummerFK"));
				
				return b;				
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Vector<Modulbelegung> findAll() {
		Connection con = DBConnection.connection();
		Vector<Modulbelegung> result = new Vector<Modulbelegung>();
		
		try{
		Statement smt = con.createStatement();
		
		ResultSet rs = smt.executeQuery("SELECT Belegungsnummer, Note, MatrikelnummerFK, EDVNummerFK FROM modulbelegung ORDER BY Belegungsnummer");
		
		while (rs.next()) {
			Modulbelegung b = new Modulbelegung();
			b.setBelegungsnr(rs.getInt("Belegungsnummer"));
			b.setNote(rs.getDouble("Note"));
			b.setMatrikelnummerFK(rs.getInt("MatrikelnummerFK"));
			b.setEDVNr(rs.getInt("EDVNummerFK"));
			
			result.addElement(b);
			
		}
	}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public Vector<Modulbelegung> findByBelegung(int belegungsnr) {
		Connection con = DBConnection.connection();
		Vector<Modulbelegung> result = new Vector<Modulbelegung>();
		
		try {
			Statement smt = con.createStatement();
			ResultSet rs = smt.executeQuery("SELECT Belegungsnummer, Note, MatrikelnummerFK, EDVNummerFK FROM modulbelegung"
					+ "WHERE Belegungsnummer = " + belegungsnr + "ORDER BY Belegungsnummer");
			
			while (rs.next()) {
				Modulbelegung b = new Modulbelegung();
				b.setBelegungsnr(rs.getInt("Belegungsnummer"));
				b.setNote(rs.getDouble("Note"));
				b.setMatrikelnummerFK(rs.getInt("MatrikelnummerFK"));
				b.setEDVNr(rs.getInt("EDVNummerFK"));
				
				result.addElement(b);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public Modulbelegung insert (Modulbelegung b) {
		Connection con = DBConnection.connection();
		
		try {
			Statement smt = con.createStatement();
			
			ResultSet rs = smt.executeQuery("SELECT MAX(Belegungsnummer) AS maxid FROM modulbelegung");
			if (rs.next()) {
				b.setBelegungsnr(rs.getInt("maxid") + 1);
				
				smt = con.createStatement();
				smt.executeUpdate("INSERT INTO modulbelegung (Belegungsnummer, Note, MatrikelnummerFK, EDVNummerFK)"
						+ " VALUES (" 
						+ b.getBelegungsnr()
						+ ", "
						+ b.getNote()
						+ ", "
						+ b.getMatrikelnrFK()
						+ ", "
						+ b.getEDVNrFK()
						+ ")");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			}
		
		return b;
	}
	
	public Modulbelegung update (Modulbelegung b) {
		Connection con = DBConnection.connection();
		
		try {
			Statement smt = con.createStatement();
			smt.executeUpdate("UPDATE modulbelegung SET " + "Belegungsnummer = " + b.getBelegungsnr() + ", Note = " + b.getNote()
					+ ", MatrikelnummerFK = " + b.getMatrikelnrFK() + ", EDVNummerFK = " + b.getEDVNrFK()
					+ " WHERE Belegungsnummer = " + b.getBelegungsnr());
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return b;
	}
	
	public void delete (Modulbelegung b) {
		Connection con = DBConnection.connection();
		
		try {
			Statement smt = con.createStatement();
			smt.executeUpdate("DELETE FROM modulbelegung" + " WHERE Belegungsnummer = " + b.getBelegungsnr());
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteModulbelegungOfStudent (Modulbelegung b) {
		Connection con = DBConnection.connection();
		
		try {
			Statement smt = con.createStatement();
			smt.executeUpdate("DELETE FROM modulbelegung" + " WHERE MatrikelnummerFK = " + b.getMatrikelnrFK());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteModulbelegungOfModul (Modulbelegung b) {
		Connection con = DBConnection.connection();
		try {
			Statement smt = con.createStatement();
			smt.executeUpdate("DELETE FROM modulbelegung" + " WHERE EDVNummerFK = " + b.getEDVNrFK());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Student getModulbelegungStudent (Modulbelegung b) {
		return StudentMapper.studentMapper().findByKey(b.getMatrikelnrFK());
	}
	
	public Modul getModulbelegungModul (Modulbelegung b) {
		return ModulMapper.modulMapper().findByKey(b.getEDVNrFK());
	}

	public Vector<Modulbelegung> findByStudent(Student s) {
		return findByStudent(s.getId());
	}

	private Vector<Modulbelegung> findByStudent(int id) {
		Connection con = DBConnection.connection();
		Vector<Modulbelegung> result = new Vector<Modulbelegung>();
		
		try {
			Statement smt = con.createStatement();
			ResultSet rs = smt.executeQuery("SELECT Belegungsnummer, Note, MatrikelnummerFK, EDVNummerFK FROM modulbelegung"
					+ " WHERE MatrikelnummerFK = " + id);
						
			while (rs.next()) {
				Modulbelegung mb = new Modulbelegung();
				mb.setBelegungsnr(rs.getInt("Belegungsnummer"));
				mb.setNote(rs.getDouble("Note"));
				mb.setMatrikelnummerFK(rs.getInt("MatrikelnummerFK"));
				mb.setEDVNr(rs.getInt("EDVNummerFK"));
				
				result.addElement(mb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return result;
	}

	public Vector<Modulbelegung> findByModul(Modul m) {

		return findByModul(m.getId());
	}

	private Vector<Modulbelegung> findByModul(int id) {
		Connection con = DBConnection.connection();
		Vector<Modulbelegung> result = new Vector<Modulbelegung>();
		
		try {
			Statement smt = con.createStatement();
			ResultSet rs = smt.executeQuery("SELECT Belegungsnummer, Note, MatrikelnummerFK, EDVNummerFK FROM modulbelegung"
					+ " WHERE EDVNummerFK = " + id);
			
			while (rs.next()) {
				Modulbelegung mb = new Modulbelegung();
				mb.setBelegungsnr(rs.getInt("Belegungsnummer"));
				mb.setNote(rs.getDouble("Note"));
				mb.setMatrikelnummerFK(rs.getInt("MatrikelnummerFK"));
				mb.setEDVNr(rs.getInt("EDVNummerFK"));
				
				result.addElement(mb);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public Modul findModulByBelegung (Modulbelegung b) {
		return ModulMapper.modulMapper().findModul(b);
	}
}
