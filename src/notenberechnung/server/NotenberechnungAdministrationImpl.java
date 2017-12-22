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
import java.util.concurrent.ThreadLocalRandom;

import notenberechnung.server.db.ModulBelegungsMapper;
import notenberechnung.server.db.ModulMapper;
import notenberechnung.server.db.StudentMapper;
import notenberechnung.shared.NotenberechnungAdministration;
import notenberechnung.shared.bo.Student;

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
	
		
	
		
	}
	

