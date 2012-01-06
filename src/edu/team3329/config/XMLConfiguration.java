package team3329.config;


import com.sun.squawk.io.BufferedReader;

class XMLConfiguration{
	BufferedReader in;	

	public XMLConfiguration(){
		in = new BufferedReader(new FileReader("config.xml"));
	}

	public String getString(String key){
			
	}
}
