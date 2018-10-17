/*
 * File: ListCubes.java
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
 * Lists all cubes in an application.
 * 
 * @author Srin Ranga - https://www.linkedin.com/in/sriniranga 
 */
public class ListCubes {
	
	public static void main(String[] args) {
		try {
			String appName = "Sample";  // TODO: Change this to your application name
			System.out.printf("Listing cubes in application %s...", appName);
			
			// Perform REST request to get cubes
			Client client = ClientBuilder.newClient(new ClientConfig());
			client.register(HttpAuthenticationFeature.basic(LoginDetails.getUserName(), LoginDetails.getPassword()));
			WebTarget target = client.target(UriBuilder.fromUri(LoginDetails.getEssbaseRestURI()).build());
			Response response = target.path("applications").path(appName).path("databases").request(MediaType.APPLICATION_JSON).get(Response.class);
			
			// If Success, print cube names, Else, print error code
			if (response.getStatus() == 200) { 
				DocumentContext docCxt = JsonPath.parse(response.readEntity(String.class));
				JSONArray items = docCxt.read("$.items[*].name");
				System.out.println(items.toString());
			} else {
				System.err.println("HTTP Status code: " + response.getStatus());
			}
		} catch (Exception x) {
			System.err.println("Error: " + x.getMessage());
		}
	}
}
    
   