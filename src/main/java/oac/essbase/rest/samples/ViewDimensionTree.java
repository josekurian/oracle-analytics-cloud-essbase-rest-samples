/*
 * File: ViewDimensionTree.java 
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

import com.jayway.jsonpath.JsonPath;

import net.minidev.json.JSONArray;

/*
 * View a dimension tree.
 * 
 * @author Srin Ranga - https://www.linkedin.com/in/sriniranga
 */
public class ViewDimensionTree {
	// TODO: Change following variables to your application, cube and dimension names
	private static String appName = "Sample", cubeName = "Basic", dimensionName = "Year"; 
	
	public static void main(String[] args) {
		try {
			Client client = ClientBuilder.newClient(new ClientConfig());
			client.register(HttpAuthenticationFeature.basic(LoginDetails.getUserName(), LoginDetails.getPassword()));
			WebTarget target = client.target(UriBuilder.fromUri(LoginDetails.getEssbaseRestURI()).build());
			
			viewDimensionTree(target);
		} catch (Throwable x) {
			System.err.println("Error: " + x.getMessage());
		}
	}
	
	public static void viewDimensionTree(WebTarget target) throws Exception {
		try {
			System.out.printf("\nView dimension tree for %s/%s/%s...\n\n", appName, cubeName, dimensionName);
			
			System.out.println(dimensionName);
			printDescendants(target, dimensionName, "\t");
		} catch (Exception x) {
			System.err.println("Error: " + x.getMessage());
		}
    }
    
    public static void printDescendants(WebTarget target, String parentMemberName, String indent) throws Exception {
    		Response response = target.path("outline").path(appName).path(cubeName).queryParam("parent", parentMemberName).
				request(MediaType.APPLICATION_JSON).get(Response.class);
		
		if (response.getStatus() != 200) 
			throw new Exception("HTTP Status code: " + response.getStatus());
		
		JSONArray items= JsonPath.parse(response.readEntity(String.class)).read("$.items[*].name");
		for (int i = 0; i < items.size(); i++) {
			String memberName = (String)items.get(i);
			System.out.println(indent + memberName);
			printDescendants(target, memberName, indent+"\t");
		}
    }
}
