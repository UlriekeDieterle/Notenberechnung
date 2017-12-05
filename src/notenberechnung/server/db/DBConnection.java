package notenberechnung.server.db;

import java.sql.DriverManager;
import java.sql.*;

import com.google.appengine.api.utils.SystemProperty;
import com.google.cloud.sql.jdbc.Connection;

public class DBConnection {

	private static Connection con = null;
	private static String localURL = "http://localhost/phpmyadmin/db_export.php?db=notenberechnung";	
	
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
	                
	                con = (Connection) DriverManager.getConnection(url);
	            } catch (Exception e) {
	                con = null;
	                e.printStackTrace();
	            }
	        }

	        return con;
	}
	
	
	
	
}
