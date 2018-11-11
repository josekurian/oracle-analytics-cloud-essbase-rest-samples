/*
 * File: DataQuery.java
 */
package oac.essbase.rest.samples;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

/*
 * Data Query.
 * 
 * @author Srini Ranga - https://www.linkedin.com/in/sriniranga
 */
public class DataQuery {
	// TODO: Change appName and cubeName to your application and cube name
	private static String appName = "Sample", cubeName = "Basic"; 
	
	public static void main(String[] args) {
		try {
			Client client = ClientBuilder.newClient(new ClientConfig());
			client.register(HttpAuthenticationFeature.basic(LoginDetails.getUserName(), LoginDetails.getPassword()));
			WebTarget target = client.target(UriBuilder.fromUri(LoginDetails.getEssbaseRestURI()).build());
			
			dataQuery(target);
		} catch (Throwable x) {
			System.err.println("Error: " + x.getMessage());
		}
	}
	
	public static void dataQuery(WebTarget target) throws Exception {
		try {
			System.out.println("\nPerforming default retrieve...");
			
			// Perform REST request to perform default retrieve
			Response response = target.path("applications").path(appName).path("databases").path(cubeName).path("grid").
					request(MediaType.APPLICATION_JSON).get(Response.class);
			
			// If Success, print grid of values, Else, print error code
			if (response.getStatus() == 200) { 
				DocumentContext docCxt = JsonPath.parse(response.readEntity(String.class));
				int numCols = docCxt.read("$.slice.columns");
				int numRows = docCxt.read("$.slice.rows");
				JSONArray cellValues = docCxt.read("$.slice.data.ranges[*].values[*]");
				
				for (int rowId = 0, valuesIndex = 0; rowId < numRows; rowId++) {
					for (int colId = 0; colId < numCols; colId++) {
						String cellValue = (String)cellValues.get(valuesIndex++);
						if (cellValue.equals(""))
							System.out.printf("\t");
						else
							System.out.printf(cellValue + "\t");
					}					
					System.out.println();				
				}
			} else {
				System.err.println("HTTP Status code: " + response.getStatus());				
			}
		} catch (Exception x) {
			throw new Exception("Error: " + x.getMessage());
		}
	}
}
    
   