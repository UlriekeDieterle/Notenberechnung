package notenberechnung.server.db;

import java.sql.*;
import java.util.Vector;

import notenberechnung.shared.bo.Modulbelegung;

public class ModulBelegungsMapper {

	private static ModulBelegungsMapper modulbelegungsMapper = null;
	
	protected ModulBelegungsMapper() {
		
	}

	public static ModulBelegungsMapper modulbelegungsMapper() {
		if(modulbelegungsMapper == null) {
			ModulBelegungsMapper modulbelegungsMapper = new ModulBelegungsMapper();
		}
		return modulbelegungsMapper;
	}
	
	public Modulbelegung findByKey(int id) {
		Connection con = DBConnection.connection();
		
		try {
			Statement smt = con.createStatement();
			ResultSet rs = smt.executeQuery("SELECT Belegungsnummer, Note, MatrikelnummerFK, EDVNummerFK FROM modulbelegung"
					+ "WHERE Belegungsnummer =" + id + "ORDER BY Belegungsnummer");
			
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
		
		ResultSet rs = smt.executeQuery("SELECT Belegungsnummer, Note, MatrikelnummerFK, EDVNummerFK FROM modulbelegung");
	}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
