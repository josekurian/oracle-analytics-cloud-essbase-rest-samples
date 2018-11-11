/*
 * File: LoginDetails.java
 */
package oac.essbase.rest.samples;

/*
 * Used by all other classes to get OAC Essbase login details.
 * 
 * @author Srini Ranga - https://www.linkedin.com/in/sriniranga
 */
public class LoginDetails {
	// 
	// TODO: Initialize the following 3 variables to match your setup.
	//
	private static String hostUrl = "your_host_url"; // e.g http://1.160.10.240
	private static String userName = "your_user_name";
	private static String password = "your_password";
	
	public static String getHost() throws Exception {
		if (hostUrl.equalsIgnoreCase("your_host_url"))
			throw new Exception("Initialize the value of hostUrl in LoginDetails.java");
		return hostUrl;
	}
	
	public static String getUserName() throws Exception {
		if (userName.equalsIgnoreCase("your_user_name"))
			throw new Exception("Initialize the value of userName in LoginDetails.java");
		return userName;
	}
	
	public static String getPassword() throws Exception {
		if (password.equalsIgnoreCase("your_password"))
			throw new Exception("Initialize the value of password in LoginDetails.java");
		return password;
	}
	
	public static String getEssbaseRestURI() throws Exception {
		return getHost() + "/essbase/rest/v1";
	}
}
    
