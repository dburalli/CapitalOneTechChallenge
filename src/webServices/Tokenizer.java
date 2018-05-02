package webServices;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Tokenizer {
	
	public static Map<String, String> fileTokenizer() {
		//If it doesn't read this file, or if there is an error in this file, the process displays an error
		//but if its going to be in prod, should it exit and notify devs?
		//should it try again, or search a tree for the file?
		//Would set the filename location in the config
		
		
		//Create map to hold all of the values read in from txt file (API Key, parameters for API get)
		Map<String, String> tokens = new HashMap<String, String>();
		
		// The name of the file to open.
	    String fileName = "src/environmentVariables.txt";
	
	    String line = null;
	
	    try {
	        // FileReader reads text files in the default encoding.
	        FileReader fileReader = 
	            new FileReader(fileName);
	
	        // Always wrap FileReader in BufferedReader.
	        BufferedReader bufferedReader = 
	            new BufferedReader(fileReader);
	
	        while((line = bufferedReader.readLine()) != null) {
	            tokens.put(line.split(" ")[0], line.split(" ")[1]);
	        }   

	        // Always close files.
	        bufferedReader.close();         
	    }
	    catch(FileNotFoundException ex) {
	        System.out.println(
	            "Unable to open file '" + 
	            fileName + "'");                
	    }
	    catch(IOException ex) {
	        System.out.println(
	            "Error reading file '" 
	            + fileName + "'");                  
	    }
	    
	    return tokens;
	}
}
