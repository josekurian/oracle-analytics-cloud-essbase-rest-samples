# oracle-analytics-cloud-essbase-rest-samples

A) Following are the steps to try out the samples in command line:

- Download and Unzip the samples to a directory
- Using command line utility, cd to that directory oracle-analytics-cloud-essbase-rest-samples-master
- If you haven't installed gradle already, run the following command: 
		./gradlew 
- Edit the file LoginDetails.java with your login details such as host, user name and password
- To build the samples, run the following command: 
		./gradlew build
- To run the samples, run the following command:
		./gradlew run
		
B) If you are using an IDE, such as Eclipse:

- Install a gradle plugin for the IDE
- Download and Unzip the samples to a directory
- Import the samples from that directory as a gradle project 

C) Following are the samples currently available:

- RunSamples.java - Invokes all the other samples 
- LoginDetails.java - Make sure to edit this file to match your login details
- ListApplications.java - List all applications.
- ListCubes.java - List all cubes in an application. Edit this file to match your application name.
- ListDimensions.java - List all dimensions in a cube. Edit this file to match your application, cube names.
- ViewDimensionTree.java - View the members of a dimension in a hierarchical structure. Edit this file to match your application, cube and dimension names.
