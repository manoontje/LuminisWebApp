package test.app;


	import java.io.IOException;
	import java.io.OutputStreamWriter;
	import java.net.HttpURLConnection;
	import java.net.URL;
	import java.net.URLEncoder;
	import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

	public class RestAPIClient {

		public static void main(String[] args) throws IOException, JSONException{
			
			Scanner scanner = new Scanner(System.in);
			
			System.out.println("Welcome to the Patient Info Command Line Editor.");
			System.out.println("Do you want to get or set a patient's info?");
			System.out.println("(Type 'get' or 'set' now.)");
			String getOrSet = scanner.nextLine();
			if("get".equalsIgnoreCase(getOrSet)){
				System.out.println("Whose info do you want to get?");
				System.out.println("(Enter the patient's number.)");
				String nr = scanner.nextLine();
				
				String jsonString = getPersonData(nr);
				JSONObject jsonObject = new JSONObject(jsonString);

				String name = jsonObject.getString("name");
				String patientNr = jsonObject.getString("patientNr");
				System.out.println("Patient with nr. " + nr + " is " + name + ".");
				
				String address = jsonObject.getString("address");
				System.out.println("The patient lives at " + address + ".");
			}
			else if("set".equalsIgnoreCase(getOrSet)){
				System.out.println("Whose info do you want to set?");
				System.out.println("(Type a person's name now.)");
				String name = scanner.nextLine();
				
				System.out.println("What is the patient number of " + name + "?");
				System.out.println("(Type a number now)");
				String patientNr = scanner.nextLine();
				
				System.out.println("Please enter the address of" + name + ".");
				String address = scanner.nextLine();
				
				setPersonData(name, patientNr, address);
			}
			
			scanner.close();
			
			System.out.println("Thanks for using this service.");
			
		}
		
		public static String getPersonData(String patientNr) throws IOException{

			HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:8080/Luminis_WebApplication/patients/" + patientNr).openConnection();
			
			connection.setRequestMethod("GET");

			int responseCode = connection.getResponseCode();
			if(responseCode == 200){
				String response = "";
				Scanner scanner = new Scanner(connection.getInputStream());
				while(scanner.hasNextLine()){
					response += scanner.nextLine();
					response += "\n";
				}
				scanner.close();

				return response;
			}
			
			// an error happened
			return null;
		}

		public static void setPersonData(String name, String patientNr, String address) throws IOException{
			HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:8080/Luminis_WebApplication/patients/" + patientNr).openConnection();

			connection.setRequestMethod("POST");
			
			String postData = "name=" + URLEncoder.encode(name, "UTF-8");
			postData += "&patientNr=" + URLEncoder.encode(patientNr, "UTF-8");
			postData += "&address=" + URLEncoder.encode(address,"UTF-8");
			
			connection.setDoOutput(true);
		    OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
		    wr.write(postData);
		    System.out.println(postData);
		    wr.flush();
			
			int responseCode = connection.getResponseCode();
			if(responseCode == 200){
				System.out.println("POST was successful.");
			}
			else if(responseCode == 401){
				System.out.println("Wrong password.");
			}
		}
	}