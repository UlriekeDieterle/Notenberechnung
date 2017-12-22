package notenberechnung.server.db;

import notenberechnung.shared.bo.Student;

public class Test {
	public static void main(String[] args) {
		StudentMapper sm = new StudentMapper();
		Student s = sm.findByKey(29489);
		//System.out.println(sm.findByKey(29489));

		System.out.println(s);
	}
}
