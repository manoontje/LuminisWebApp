package test.app;

import java.util.Date;

public class Visit {
	private String patientNr;
	private String hospital;
	private String date;
	
	public Visit(String patientNr, String hospital, String date) {
		this.patientNr = patientNr;
		this.hospital = hospital;
		this.date = date;
	}
	
	public String getNumber() {
		return patientNr;
	}
	
	public String getVisitInfo() {
		return "Patient nr. "+ patientNr+ " was in " + hospital + " on " + date + ".";
	}
	
	public String getHospital() {
		System.out.println(hospital);
		return hospital;
	}
	
	public String getDate() {
		return date;
	}

}
