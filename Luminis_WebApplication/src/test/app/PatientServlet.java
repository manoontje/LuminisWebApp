package test.app;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public class PatientServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String requestUrl = request.getRequestURI();
		String patientNr = requestUrl.substring(requestUrl.indexOf("/patients/") + 10, requestUrl.length());
		
		Patient patient = DataStorage.getInstance().getPatient(patientNr);
		Visit visit = DataStorage.getInstance().getVisit(patientNr);
		
		if(patient != null && visit != null){
			System.out.println(visit.getVisitInfo());
			String json = "{\n";
			json += "\"name\": " + JSONObject.quote(patient.getName()) + ",\n";
			json += "\"patientNr\": " + JSONObject.quote(patient.getNumber()) + ",\n";
			json += "\"address\": " + JSONObject.quote(patient.getAddress()) + ",\n";
			json += "\"visitHospital\": " + JSONObject.quote(visit.getHospital()) + ",\n";
			json += "\"visitDate\": " + JSONObject.quote(visit.getDate()) + "\n";
			json += "}";
			response.getOutputStream().println(json);
		}
		else if(patient != null && visit == null){
			String json = "{\n";
			json += "\"name\": " + JSONObject.quote(patient.getName()) + ",\n";
			json += "\"patientNr\": " + JSONObject.quote(patient.getNumber()) + ",\n";
			json += "\"address\": " + JSONObject.quote(patient.getAddress()) + ",\n";
			json += "\"visitHospital\": " + "" + ",\n";
			json += "\"visitDate\": " + "" + "\n";
			json += "}";
			response.getOutputStream().println(json);
		}
		else{
			response.getOutputStream().println("Error, patient not found.");
		}
	}
	
	

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String name = request.getParameter("name");
		String patientNr = request.getParameter("patientNr");
		String address = request.getParameter("address");
		String visitHospital = request.getParameter("visitHospital");
		String visitDate = request.getParameter("visitDate");
//		Visit visits = new Visit(request.getParameter("patientNr"),request.getParameter("visitsHospital"),request.getParameter("visitsDate"));
		
		System.out.println(name + patientNr +address);
		DataStorage.getInstance().putPatient(new Patient(name, patientNr, address));
		DataStorage.getInstance().putVisit(new Visit(patientNr, visitHospital, visitDate));
	}
}