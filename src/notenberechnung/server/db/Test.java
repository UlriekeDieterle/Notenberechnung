package notenberechnung.server.db;

import notenberechnung.shared.bo.Modul;
import notenberechnung.shared.bo.Modulbelegung;
import notenberechnung.shared.bo.Student;

public class Test {
	public static void main(String[] args) {
		//StudentMapper sm = new StudentMapper();
		//Student s = sm.findByKey(29489);
		//System.out.println(sm.findByKey(29489));
		
		
		/*Student s = new Student();
		s.setId(12345);
		sm.delete(s);
		s.setId(29491);
		s.setFirstName("Vanessa");
		s.setLastName("Hammel");
		s.setEmail("abc@abc.com");
		s.setBirthday(5508);
		s.setKuerzel("gh589");
		s.setStudies("OMM");
		sm.update(s);*/
		
		
		//System.out.println(sm.findAll());
		//System.out.println(sm.findByNachname("Dieterle"));
		//System.out.println(sm.findByStudiengang("Wirtschaftsinformatik und digitale Medien"));

		//System.out.println(s);
		
		
		
		ModulMapper mm = new ModulMapper();
		//System.out.println(mm.findByKey(12345));
		Modul m = new Modul();
		m.setId(12346);
		m.setECTS(10);
		m.setModulTitel("Big Data");
		m.setVerantwortlicher("Hendrik Meth");
		m.setZeitpunkt("Haupt");
		m.setBeschreibung("Big Data");
		mm.update(m);
		
		/*ModulBelegungsMapper mbm = new ModulBelegungsMapper();
		//System.out.println(bm.findByKey(1));
		Modulbelegung mb = new Modulbelegung();
		mb.setBelegungsnr(3);
		//mb.setNote(1.5);
		//mb.setMatrikelnummerFK(12345);
		//mb.setEDVNr(12345);
		mbm.delete(mb);*/
		
		
	}
}
