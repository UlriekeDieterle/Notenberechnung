package notenberechnung.server.db;

import notenberechnung.server.NotenberechnungAdministrationImpl;
import notenberechnung.shared.bo.Modul;
import notenberechnung.shared.bo.Modulbelegung;
import notenberechnung.shared.bo.Student;

public class Test {
	public static void main(String[] args) {
		//StudentMapper sm = new StudentMapper();
		//Student s = sm.findByKey(29489);
		//System.out.println(sm.findByKey(29489));
		
		
		//Student s = new Student();
		//s.setId(12345);
		/*sm.delete(s);
		s.setId(29491);
		s.setFirstName("Vanessa");
		s.setLastName("Hammel");
		s.setEmail("abc@abc.com");
		s.setBirthday(5508);
		s.setKuerzel("gh589");
		s.setStudies("OMM");
		sm.update(s);*/
		
		//System.out.println(sm.getModulbelegungOfStudent(s));
		
		
		//System.out.println(sm.findAll());
		//System.out.println(sm.findByNachname("Dieterle"));
		//System.out.println(sm.findByStudiengang("Wirtschaftsinformatik und digitale Medien"));

		//System.out.println(s);
		
		
		
		/*ModulMapper mm = new ModulMapper();
		//System.out.println(mm.findByKey(12345));
		Modul m = new Modul();
		m.setId(1503);
		m.setECTS(2);
		m.setModulTitel("UG Geschäftsideen");
		m.setVerantwortlicher("...");
		m.setZeitpunkt("H");
		m.setBeschreibung("");
		System.out.println(mm.insert(m));
		//System.out.println(mm.findModulbelegungByModul(m));*/
		
		/*ModulBelegungsMapper mbm = new ModulBelegungsMapper();
		//System.out.println(bm.findByKey(1));
		Modulbelegung mb = new Modulbelegung();
		mb.setBelegungsnr(2);
		mb.setNote(1.0);
		mb.setMatrikelnummerFK(29489);
		mb.setEDVNr(7045);
		System.out.println(mbm.insert(mb));
		//System.out.println(mbm.getModulbelegungStudent(mb));
		//System.out.println(mbm.getModulbelegungModul(mb));*/
		
		/*ModulMapper mbm = new ModulMapper();
		Modulbelegung mb = new Modulbelegung();
		mb.setBelegungsnr(3);
		mb.setEDVNr(5001);
		System.out.println(mbm.findModul(mb));*/
		
	}
}
