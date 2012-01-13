package edu.team3329;

import java.util.Hashtable;
import com.sun.squawk.io.BufferedReader;

public class Configuration{
	BufferedReader in;
	HashTable sections;
	ConfigSection currentSection;

	public Configuration(){
		in = new BufferedReader(new FileReader("config.ini"));
		sections = new HashTable();
		currentSection = new ConfigSection();
		sections.add("default", currentSection);
	}
	
	public Configuration(String filename){
		in = new BufferedReader(new FileReader(filename));
		sections = new HashTable();
		currentSection = new ConfigSection();
		sections.add("default", currentSection);		
	}

	public String getString(String key){
		return getString("default", key);
	}


	public String getString(String section, String key){
		return (String) sections.get(section).getObject(key);
	}

	public double getDouble(String section, String key){
		return (double) sections.get(section).getObject(key);
	}

	public ConfigSection getSection(){
		return sections.get("default");		
	}

	public ConfigSection getSection(String name){
		return sections.get(name);
	}

	public void addSection(String name){
		ConfigSection sect = new ConfigSection();
		sections.add(name, sect);
	}

	private parse(){
		while((thisline== br.readLine())!=null){
			
		}
	}
}

private class ConfigSection {
	private Hashtable data;
	
	public ConfigSection(){
		this.data = new Hashtable();
	}

	public Object getObject(String key){
		return data.get(key);
	}

	public void parseLine(String str){
		String[] tokens = str.split("=")
		String key = tokens[0];
		String value = tokens[1];
		data.put(key, value);
	}
}
