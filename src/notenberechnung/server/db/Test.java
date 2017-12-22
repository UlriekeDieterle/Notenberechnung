package notenberechnung.server.db;

import notenberechnung.shared.bo.Modul;
import notenberechnung.shared.bo.Modulbelegung;
import notenberechnung.shared.bo.Student;

public class Test {
	public static void main(String[] args) {
		StudentMapper sm = new StudentMapper();
		//Student s = sm.findByKey(29489);
		//System.out.println(sm.findByKey(29489));
		
		
		/*Student s = new Student();
		s.setId(22558);
		s.setFirstName("Simon");
		s.setLastName("Hasel");
		s.setEmail("testtest");
		s.setBirthday(5508);
		s.setKuerzel("gh589");
		s.setStudies("OMM");
		sm.insert(s);*/
		
		//System.out.println(sm.findAll());
		//System.out.println(sm.findAll());
		//System.out.println(sm.findByNachname("Dieterle"));
		//System.out.println(sm.findByStudiengang("Wirtschaftsinformatik und digitale Medien"));

		//System.out.println(s);
		
		/*ModulMapper mm = new ModulMapper();
		//System.out.println(mm.findByKey(12345));
		Modul m = new Modul();
		m.setId(2);
		m.setECTS(5);
		m.setModulTitel("Blabla");
		m.setVerantwortlicher("Meth");
		m.setZeitpunkt("Haupt");
		m.setBeschreibung("blubb");
		mm.insert(m);*/
		
		ModulBelegungsMapper mbm = new ModulBelegungsMapper();
		//System.out.println(bm.findByKey(1));
		Modulbelegung mb = new Modulbelegung();
		mb.setBelegungsnr(2);
		mb.setNote(2);
		mb.setMatrikelnummerFK(12345);
		mb.setEDVNr(12346);
		mbm.insert(mb);
	}
}
