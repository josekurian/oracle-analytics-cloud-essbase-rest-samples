/*
 * File: RunSamples.java
 */
package oac.essbase.rest.samples;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

/*
 * Runs Samples.
 * 
 * @author Srin Ranga - https://www.linkedin.com/in/sriniranga
 */
public class RunSamples {
	
	public static void main(String[] args) {
		try {
			Client client = ClientBuilder.newClient(new ClientConfig());
			client.register(HttpAuthenticationFeature.basic(LoginDetails.getUserName(), LoginDetails.getPassword()));
			WebTarget target = client.target(UriBuilder.fromUri(LoginDetails.getEssbaseRestURI()).build());
			
			ListApplications.listApplications(target);
			ListCubes.listCubes(target);
			ListDimensions.listDimensions(target);
			ViewDimensionTree.viewDimensionTree(target);
		} catch (Throwable x) {
			System.err.println("Error: " + x.getMessage());
		}
	}
}
    
   