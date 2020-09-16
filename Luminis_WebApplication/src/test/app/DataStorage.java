package test.app;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataStorage {
		private Map<String, Patient> patientMap = new HashMap<>();
		private Map<String, Visit> visitMap = new HashMap<>();
		
		//Singleton: should not be instantiated directly!
		private static DataStorage instance = new DataStorage();
		public static DataStorage getInstance(){
			return instance;
		}
		
		private DataStorage(){
			//dummy data
			patientMap.put("1", new Patient("Manon", "1", "Vlietstraat 28"));
			visitMap.put("1", new Visit("1", "Scheperziekenhuis", "27-10-1996"));
		}

		public Patient getPatient(String patientNr) {
			return patientMap.get(patientNr);
		}
		
		public Visit getVisit(String patientNr) {
			if (visitMap.get(patientNr) != null) {
				return visitMap.get(patientNr);
			}
			else {
				return new Visit(patientNr, "", "");
			}
		}

		public void putPatient(Patient patient) {
			patientMap.put(patient.getNumber(), patient);
		}
		
		public void putVisit(Visit visit) {
			visitMap.put(visit.getNumber(), visit);
		}
	}
