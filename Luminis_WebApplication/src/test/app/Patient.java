package test.app;

import java.util.List;

public class Patient {
	private String name;
	private String patientNr;
	private String address;
	
	public Patient(String name, String patientNr, String address) {
		this.name = name;
		this.patientNr = patientNr;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public String getNumber() {
		return patientNr;
	}

	public String getAddress() {
		return address;
	}
	
	
	
	
	
}