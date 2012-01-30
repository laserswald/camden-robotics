package edu.team3329.config;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

class ConfigurationTest{

	private Configuration tester;
	
	public ConfigurationTest(){
		tester = new Configuration("testConfig.ini");
	}

	@Test
	public void testGetString(){
		String str = tester.getString("testItem");
		assertEquals("Result", "testValue", str);
	}
	
}	
