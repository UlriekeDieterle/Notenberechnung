package notenberechnung.server.db;

import java.sql.*;

import com.google.appengine.api.utils.SystemProperty;

public class DBConnection {

	private static java.sql.Connection con = null;
	private static String localURL = "jdbc:mysql://127.0.0.1:3306/Notenberechnung?user=root&password=";	
	
	public static Connection connection(){
		  if (con == null) {
	            String url = null;
	            try {
	                if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
	                    
	                	Class.forName("com.mysql.jdbc.GoogleDriver");
	                    //url = googleUrl;
	                } else {
	                    // Local MySQL instance to use during development.
	                    Class.forName("com.mysql.jdbc.Driver");
	                    url = localURL;
	                }
	                
	                con = DriverManager.getConnection(url);
	            } catch (Exception e) {
	                con = null;
	                e.printStackTrace();
	            }
	        }

	        return con;
	}
	
	
	
	
}
