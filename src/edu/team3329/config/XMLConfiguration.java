package edu.team3329;


import com.sun.squawk.io.BufferedReader;

class XMLConfiguration{
	BufferedReader in;	

	public XMLConfiguration(){
		in = new BufferedReader(new FileReader("config.xml"));
	}

	public String getString(String key){
			
	}
}
