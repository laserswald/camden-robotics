package edu.team3329;


import com.sun.squawk.io.BufferedReader;

class Configuration{
	BufferedReader in;	

	public Configuration(){
		in = new BufferedReader(new FileReader("config.ini"));
	}

	public String getString(String key){
			
	}
}
